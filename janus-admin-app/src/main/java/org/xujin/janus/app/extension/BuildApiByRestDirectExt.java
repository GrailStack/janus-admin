package org.xujin.janus.app.extension;

import com.alibaba.fastjson.JSON;
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
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 直接将请求转发到另外一个Host
 * @author xujin
 */
@Extension(name="buildApiByRestDirectExt",bizCode="janus.api.rest.direct",desc = "Rest直接转发")
public class BuildApiByRestDirectExt implements BuildAPIExtPt {


    /**
     * - id: 6 #用于演示精准匹配,不走LB,直接转发到指定的IP和端口   http://localhost:8081/admin2/menu/manage/allMenu2
     *     filters:
     *       - name: PathMapping
     *         args:
     *           /admin2/menu/manage/allMenu2: "/admin/menu/manage/allMenu"
     *     protocol: http
     *     hosts:
     *       - 127.0.0.1:8084
     *     predicates: # and ; if need or add a new route
     *       - name: PathPrecise
     *         args:
     *           paths: /admin2/menu/manage/allMenu2
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
        List<HostVO> serviceAddressList= JSON.parseArray(outUri,HostVO.class);
        apiCO.setProtocol(OutServiceProtocolEnum.HTTP.getCode());
        List<String> hosts=new ArrayList<>();
        for(HostVO hostVO:serviceAddressList){
            hosts.add(hostVO.getHost()+":"+hostVO.getPort());
        }
        apiCO.setHosts(hosts);
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
