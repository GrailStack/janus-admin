package org.xujin.janus.domain.user.entity;

import org.xujin.halo.annotation.domain.DomainAbility;
import org.xujin.halo.annotation.domain.Entity;
import org.xujin.janus.domain.user.repository.RoleRepository;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
public class RoleE {

    private RoleRepository roleRepository;

    private Long id;

    /**
     *
     */
    private String key;


    /**
     *  角色名称
     */
    private String name;


    /**
     * 描述
     */
    private String desc;

    /**
     * 创建时间
     */
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    private Timestamp gmtModified;

    /**
     * 是否删除 0 否 1 已经删除
     */
    private Byte isDeleted;

}
