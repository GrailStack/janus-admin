package org.xujin.janus.app.event;

import org.xujin.halo.annotation.event.EventHandler;
import org.xujin.halo.dto.Response;
import org.xujin.halo.event.EventHandlerI;

/**
 * 创建或者更新Instance事件处理器
 * @author xujin
 */
@EventHandler
public class CreateInstanceEventHandler implements EventHandlerI<Response, CreateInstanceEvent> {

    @Override
    public Response execute(CreateInstanceEvent createInstanceEvent) {

        return null;
    }
}
