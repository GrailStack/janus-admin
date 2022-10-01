package org.xujin.janus.domain.user.service;

import org.xujin.janus.domain.user.entity.UserE;

import java.util.List;
import java.util.Map;

/**
 * @author halo codegen
 * for demo
 **/
public interface UserService {


    public void saveOrUpdateUser(UserE userE, String role);


    public void saveUser(UserE userE);

    /**
     * @param userNames
     * @return userName to name map
     */
    Map<String, String> getNames(List<String> userNames);

    boolean isAdmin(String userName);

}
