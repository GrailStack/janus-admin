package org.xujin.janus.domain.user.entity;

import org.xujin.halo.annotation.domain.DomainAbility;
import org.xujin.halo.annotation.domain.Entity;
import org.xujin.janus.domain.user.repository.MenuRepository;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2019/7/17 11:52
 **/
@Entity
@Data
public class MenuE {

    private MenuRepository menuRepository;

    private Long id;

    private Long parentId;

    private String name;

    private String url;

    private List<String> roleList;

    private Integer sort;

    private String icon;

    private String menuKey;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    private Byte isDeleted;



}
