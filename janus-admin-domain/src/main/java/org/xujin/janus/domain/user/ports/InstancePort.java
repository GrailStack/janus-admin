package org.xujin.janus.domain.user.ports;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.user.entity.InstanceE;

/**
 * @author xujin
 */
@Port
public interface InstancePort {
    void add(InstanceE instanceE);
}
