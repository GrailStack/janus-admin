package org.xujin.janus.app.server.callback;

import org.xujin.halo.annotation.app.AppService;
import org.xujin.halo.command.BaseAppService;

@AppService
public class RouteChangeCallBack implements BaseAppService<String,String> {

    @Override
    public String execute(String input) {
        return null;
    }
}
