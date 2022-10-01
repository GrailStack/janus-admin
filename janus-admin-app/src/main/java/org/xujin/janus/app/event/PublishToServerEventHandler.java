package org.xujin.janus.app.event;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.xujin.halo.annotation.event.EventHandler;
import org.xujin.halo.context.Context;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.event.EventHandlerI;
import org.xujin.halo.extension.ExtensionExecutor;
import org.xujin.janus.app.command.co.ChangeCollectionCO;
import org.xujin.janus.app.command.co.ChangeInfoCO;
import org.xujin.janus.app.command.co.ChangeItemCO;
import org.xujin.janus.app.extension.extpt.BuildAPIExtPt;
import org.xujin.janus.app.server.client.ServerRequests;
import org.xujin.janus.client.cmo.OperationEnum;
import org.xujin.janus.client.cmo.RouteChangeCmd;
import org.xujin.janus.client.cmo.RouteChangeDTO;
import org.xujin.janus.client.cmo.RouteChangeTypeEnum;
import org.xujin.janus.client.co.api.ApiCO;
import org.xujin.janus.domain.api.constant.OutServiceTypeEnum;
import org.xujin.janus.domain.api.valueobject.ApiDraftVO;
import org.xujin.janus.domain.api.valueobject.OutServiceVO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiReleaseDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.OutServiceReleaseDO;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/15 16:14
 **/
@EventHandler
@Slf4j
public class PublishToServerEventHandler implements EventHandlerI<ResultData, PublishToServerEvent> {


    @Autowired
    private ExtensionExecutor extensionExecutor;


    @Override
    public ResultData execute(PublishToServerEvent publishToServerEvent) {
        List<String> ipList= publishToServerEvent.getIpList();
        if(null==ipList||ipList.size()==0){
            return null;
        }
        try {
            String changes=publishToServerEvent.getChanges();
            ChangeCollectionCO co = JSON.parseObject(changes,ChangeCollectionCO.class);

            //集群code
            String clusterCode=co.getClusterCode();
            List<ChangeInfoCO> changeInfoCOS=co.getChangeInfoList();
            List<RouteChangeDTO> routeChangeDTOS=new ArrayList<>();
            RouteChangeCmd routeChangeCmd = new RouteChangeCmd();
            for(ChangeInfoCO changeInfoCO:changeInfoCOS){

               String sourceCode= changeInfoCO.getSourceCode();
               if(sourceCode.equals("API")){
                   List<ChangeItemCO> changeItemCOS=changeInfoCO.getChangeItems();
                   buildChangeItem(routeChangeDTOS, changeItemCOS);
               }
            }

            routeChangeCmd.setRouteChangeDTOS(routeChangeDTOS);
            routeChangeCmd.setRequestID(String.valueOf(publishToServerEvent.getPublishId()));
            for(String ip:ipList){
                try {
                    ServerRequests.sendRouteChanged(ip, clusterCode, "2.0", routeChangeCmd.getRequestID(),routeChangeCmd);
                } catch (Exception e) {
                    log.error("publish api config is fail,Ip:{},ClusterCode:{},e:{}",ip,clusterCode,e.getMessage());
                }
            }
        } catch (Exception e) {
             log.error("publish fail,publishId is:{},ApplyId:{}",publishToServerEvent.getPublishId(),publishToServerEvent.getApplyId());
        }
        return ResultData.success(null);
    }

    private void buildChangeItem(List<RouteChangeDTO> routeChangeDTOS, List<ChangeItemCO> changeItemCOS) {
        for(ChangeItemCO changeItemCO:changeItemCOS){

            ApiDraftVO apiDraftVO= JSON.parseObject(String.valueOf(changeItemCO.getNewData()),ApiDraftVO.class);

            List<OutServiceVO> osList=apiDraftVO.getOutServiceConfig().getOutServices();
            OutServiceVO os=osList.get(0);

            Context context = new Context();
            if(OutServiceTypeEnum.DISCOVERY.equals(os.getType())){
                context.setBizCode("janus.api.rest.discover");
            }
            if(OutServiceTypeEnum.STRAIGHT_FORWARD.equals(os.getType())){
                context.setBizCode("janus.api.rest.direct");
            }
            ApiCO apiCO=buildApiCO(apiDraftVO, os, context);
            RouteChangeDTO routeChangeDTO=new RouteChangeDTO();
            if(OperationEnum.DELETE.name().equals(changeItemCO.getChangeType())){
                routeChangeDTO.setOperation(OperationEnum.DELETE);
            }
            if(OperationEnum.ADD.name().equals(changeItemCO.getChangeType())){
                routeChangeDTO.setOperation(OperationEnum.ADD);
            }
            if(OperationEnum.UPDATE.name().equals(changeItemCO.getChangeType())){
                routeChangeDTO.setOperation(OperationEnum.UPDATE);
            }
            routeChangeDTO.setDataJson(JSON.toJSONString(apiCO));
            routeChangeDTO.setRouteChangeType(RouteChangeTypeEnum.BASE_CONFIG);
            routeChangeDTOS.add(routeChangeDTO);
        }
    }

    private ApiCO buildApiCO(ApiDraftVO apiDraftVO, OutServiceVO os, Context context) {

        //构建API In
        ApiReleaseDO apiIn=new ApiReleaseDO();
        apiIn.setId(apiDraftVO.getApiId());
        apiIn.setClusterCode(apiDraftVO.getClusterCode());
        apiIn.setPath(apiDraftVO.getPath());
        apiIn.setProtocol(apiDraftVO.getProtocol());

        //构建API Out
        OutServiceReleaseDO apiOut=new OutServiceReleaseDO();
        apiOut.setType(os.getType());
        apiOut.setUri(os.getUri());
        apiOut.setPath(os.getPath());

        //根据扩展点构建APICO
        ApiCO apiCO= extensionExecutor.exeReturnValue(BuildAPIExtPt.class, context, extension -> extension.buildApi(apiIn,apiOut));
        return apiCO;
    }

}
