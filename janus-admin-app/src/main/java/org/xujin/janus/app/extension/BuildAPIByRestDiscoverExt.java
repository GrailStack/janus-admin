package org.xujin.janus.app.extension;

import org.xujin.halo.annotation.extension.Extension;
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
 *  lb://sc 协议方式构建ApiCO
 * @author xujin
 */
@Extension(name="buildAPIByRestDiscoverExt",bizCode="janus.api.rest.discover",desc = "走服务发现处理API")
public class BuildAPIByRestDiscoverExt implements BuildAPIExtPt {

    /**
     * - id: 2 #演示 URL1->URL2 并走LB  #http://localhost:8081/api/getAllMenu
     *     protocol: lb://sc
     *     serviceName: janus-admin
     *     filters:
     *       - name: PathMapping
     *         args:
     *           /api/getAllMenu: "/admin/menu/manage/allMenu"
     *     predicates: # and ; if need or add a new route
     *       - name: PathPrecise
     *         args:
     *           pattern: /api/getAllMenu
     * @param apiIn
     * @param apiOut
     * @return
     */
    @Override
    public ApiCO buildApi(ApiReleaseDO apiIn, OutServiceReleaseDO apiOut) {
        ApiCO apiCO=new ApiCO();
        apiCO.setId(String.valueOf(apiIn.getId()));
        apiCO.setProtocol(OutServiceProtocolEnum.REST_LB_SC.getCode());
        //设置Spring Cloud的serviceId
        apiCO.setServiceName(apiOut.getUri());
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
        preArgs.put("pattern",apiIn.getPath());
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
