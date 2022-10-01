package org.xujin.janus.domain.user.service;


import org.xujin.janus.domain.user.entity.RoleE;

import java.util.List;

public interface RoleService {

    public void saveOrUpdateRole(RoleE roleE, List<Long> pIdList);

    public void deleteRole(Long id);

}
