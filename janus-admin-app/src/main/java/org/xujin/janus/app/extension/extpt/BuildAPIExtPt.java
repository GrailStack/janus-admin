package org.xujin.janus.app.extension.extpt;

import org.xujin.halo.annotation.extension.ExtensionPoint;
import org.xujin.halo.extension.ExtensionPointI;
import org.xujin.janus.client.co.api.ApiCO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiReleaseDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.OutServiceReleaseDO;

@ExtensionPoint(name="BuildAPIExtPt",desc = "组装给网关Server API的扩展点")
public interface BuildAPIExtPt extends ExtensionPointI {

    ApiCO buildApi(ApiReleaseDO apiIn,OutServiceReleaseDO apiOut);
}
