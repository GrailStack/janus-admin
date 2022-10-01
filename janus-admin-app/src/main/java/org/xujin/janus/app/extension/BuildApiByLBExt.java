package org.xujin.janus.app.extension;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.xujin.halo.annotation.extension.Extension;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.co.HostVO;
import org.xujin.janus.app.extension.extpt.BuildAPIExtPt;
import org.xujin.janus.client.co.api.ApiCO;
import org.xujin.janus.client.co.api.FilterCO;
import org.xujin.janus.client.co.api.PredicatesCO;
import org.xujin.janus.domain.api.constant.OutServiceProtocolEnum;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiReleaseDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.OutServiceReleaseDO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通过多个Hosts进行LB转发
 * @author xujin
 */
@Extension(name="BuildApiByLBExt",bizCode="janus.api.rest.lb",desc = "Rest直接LB")
public class BuildApiByLBExt implements BuildAPIExtPt {

    /**
     * - id: 7 #根据配置的多个Hosts不通过注册中心进行LB  #http://localhost:8081/admin7/menu/manage/allMenu2
     *     filters:
     *       - name: PathMapping
     *         args:
     *           /admin7/menu/manage/allMenu2: "/admin/menu/manage/allMenu"
     *     protocol: lb://hosts
     *     hosts:
     *       - 127.0.0.1:8084
     *       - admin.qingcloud.net:80
     *     predicates: # and ; if need or add a new route
     *       - name: PathPrecise
     *         args:
     *           paths: /admin7/menu/manage/allMenu2
     * @param apiIn
     * @param apiOut
     * @return
     */
    @Override
    public ApiCO buildApi(ApiReleaseDO apiIn, OutServiceReleaseDO apiOut) {
        ApiCO apiCO=new ApiCO();
        apiCO.setId(String.valueOf(apiIn.getId()));
        String outUri=apiOut.getUri();
        if(StringUtils.isEmpty(outUri)){
            throw new BusinessException("406","构建API出现异常");
        }
        apiCO.setProtocol(apiOut.getProtocol().getCode());
        List<HostVO> serviceAddressList= JSON.parseArray(outUri,HostVO.class);
        List<String> hosts=new ArrayList<>();
        for(HostVO hostVO:serviceAddressList){
            hosts.add(hostVO.getHost()+":"+hostVO.getPort());
        }
        apiCO.setHosts(hosts);
        apiCO.setProtocol(OutServiceProtocolEnum.REST_LB_HOSTS.getCode());
        List<FilterCO> filters=new ArrayList<>();
        setPathMapping(apiIn, apiOut, filters);
        List<PredicatesCO> predicates= new ArrayList<>();
        setPredicate(apiIn, predicates);
        apiCO.setPredicates(predicates);
        apiCO.setFilters(filters);
        return apiCO;
    }


    /**
     * 设置精准匹配的断言
     * @param apiIn
     * @param predicates
     */
    private void setPredicate(ApiReleaseDO apiIn, List<PredicatesCO> predicates) {
        PredicatesCO predicatesCO=new PredicatesCO();
        predicatesCO.setName("PathPrecise");
        Map preArgs=new HashMap();
        preArgs.put("paths",apiIn.getPath());
        predicatesCO.setArgs(preArgs);
        predicates.add(predicatesCO);
    }

    /**
     * 通过Filter的方式设置精准匹配的Path
     * @param apiIn
     * @param apiOut
     * @param filters
     */
    private void setPathMapping(ApiReleaseDO apiIn, OutServiceReleaseDO apiOut, List<FilterCO> filters) {
        FilterCO filterCO=new FilterCO();
        filterCO.setName("PathMapping");
        Map<String, String> filterArgs=new HashMap<>();
        filterArgs.put(apiIn.getPath(),apiOut.getPath());
        filterCO.setArgs(filterArgs);
        filters.add(filterCO);
    }
}
