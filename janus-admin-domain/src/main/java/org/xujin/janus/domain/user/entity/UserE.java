package org.xujin.janus.domain.user.entity;

import org.xujin.halo.annotation.domain.DomainAbility;
import org.xujin.halo.annotation.domain.Entity;
import org.xujin.janus.domain.user.repository.UserRepository;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class UserE {

    private Long id;

    private String userName;

    private String name;

    private String email;

    private String password;

    private byte status;

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    private Byte isDeleted=0;

    private UserRepository userRepository;

}
