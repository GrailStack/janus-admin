package org.xujin.janus.app.event;

import org.xujin.halo.event.Event;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/15 16:06
 **/
@Data
@Accessors(chain = true)
public class PublishToServerEvent extends Event {

    private Long applyId;

    private String changes;

    private Long publishId;

    private List<String> ipList;

}
