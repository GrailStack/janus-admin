package org.xujin.janus.domain.user.valueobject;

import org.xujin.halo.annotation.domain.DomainAbility;
import org.xujin.halo.annotation.domain.ValueObject;
import org.xujin.janus.domain.user.repository.RolePermissionRepository;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author halo codegen
 */
@Data
@ValueObject
public class RolePermissionVO {

    private RolePermissionRepository rolePRepository;


    private Long id;

    private String role;


    private Long permissionId;


    private Timestamp gmtCreate;


    private Timestamp gmtModified;


    private Byte isDeleted;

}