package org.xujin.janus.app.server.processer.step;

import org.xujin.halo.annotation.app.Step;
import org.xujin.halo.command.BaseAppService;
import org.xujin.halo.context.Context;
import org.xujin.halo.extension.ExtensionExecutor;
import org.xujin.janus.app.extension.extpt.BuildAPIExtPt;
import org.xujin.janus.client.co.api.ApiCO;
import org.xujin.janus.domain.api.constant.OutServiceTypeEnum;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApiReleaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.OutServiceReleaseMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiReleaseDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.OutServiceReleaseDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据集群Code查询Api列表
 * @author xujin
 */
@Step
public class QueryApiStep  implements BaseAppService<String,List<ApiCO>> {

    @Autowired
    private ApiReleaseMapper apiReleaseMapper;

    @Autowired
    private OutServiceReleaseMapper outServiceReleaseMapper;

    @Autowired
    private ExtensionExecutor extensionExecutor;

    @Override
    public List<ApiCO> execute(String clusterCode) {
        List<ApiCO> apiCOList=new ArrayList<>();
        List<ApiReleaseDO> apiReleaseDOS= apiReleaseMapper.findByClusterCode(clusterCode);
        for (ApiReleaseDO apiIn: apiReleaseDOS){
            List<OutServiceReleaseDO>  outServiceReleaseDOS=outServiceReleaseMapper.findByApiReleaseId(apiIn.getId());
            if(null==outServiceReleaseDOS||outServiceReleaseDOS.size()==0){
                continue;
            }
            OutServiceReleaseDO apiOut=outServiceReleaseDOS.get(0);
            Context context = new Context();
            if(OutServiceTypeEnum.DISCOVERY.equals(apiOut.getType())){
                context.setBizCode("janus.api.rest.discover");
            }
            if(OutServiceTypeEnum.STRAIGHT_FORWARD.equals(apiOut.getType())){
                context.setBizCode("janus.api.rest.direct");
            }
            if(OutServiceTypeEnum.LOAD_BALANCE.equals(apiOut.getType())){
                context.setBizCode("janus.api.rest.lb");
            }
            ApiCO apiCO= extensionExecutor.exeReturnValue(BuildAPIExtPt.class, context, extension -> extension.buildApi(apiIn,apiOut));
            apiCOList.add(apiCO);
        }
        return  apiCOList;
    }


}
