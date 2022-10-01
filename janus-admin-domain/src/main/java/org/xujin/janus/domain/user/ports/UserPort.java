package org.xujin.janus.domain.user.ports;

import org.xujin.halo.annotation.domain.Port;
import org.xujin.janus.domain.user.entity.UserE;

import java.util.List;
import java.util.Map;


/**
 * @author halo codegen
 * for demo
 **/
@Port
public interface UserPort {

    void save(UserE useE);

    void update(UserE useE);

    Map<String, String> getNames(List<String> userNames);

}