package org.xujin.janus.domain.lock.port;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.lock.Resource;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/10 13:52
 **/
@Port
public interface ResourceLockPort {

    int insert(Resource resource);

    int delete(Resource resource);

}
