package org.xujin.janus.domain.user.service.impl;

import org.xujin.halo.annotation.domain.DomainService;
import org.xujin.janus.domain.user.entity.UserE;
import org.xujin.janus.domain.user.ports.UserPort;
import org.xujin.janus.domain.user.ports.UserRolePort;
import org.xujin.janus.domain.user.repository.UserRepository;
import org.xujin.janus.domain.user.service.UserService;
import org.xujin.janus.domain.user.valueobject.UserRolesVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@DomainService
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolePort userRolePort;

    @Autowired
    private UserPort userPort;

    /**
     * 保存User和UserRole
     *
     * @param userE
     * @param role
     */
    @Override
    public void saveOrUpdateUser(UserE userE, String role) {
        userE.setUserRepository(userRepository);
        UserRolesVO userRolesVO = new UserRolesVO();
        userRolesVO.setRole(role);
        userRolesVO.setUserRolePort(userRolePort);
        userRolesVO.setUsername(userE.getUserName());
        if (null == userE.getId()) {
            userPort.save(userE);
            userRolesVO.save();
        } else {
            userPort.update(userE);
            userRolesVO.update();
        }

    }

    @Override
    public void saveUser(UserE userE) {
        userE.setUserRepository(userRepository);
        userPort.save(userE);
    }

    @Override
    public Map<String, String> getNames(List<String> userNames) {
        return userPort.getNames(userNames.stream().filter(Objects::nonNull).collect(Collectors.toList()));
    }

    @Override
    public boolean isAdmin(String userName) {
        return userRolePort.isAdmin(userName);
    }

}
