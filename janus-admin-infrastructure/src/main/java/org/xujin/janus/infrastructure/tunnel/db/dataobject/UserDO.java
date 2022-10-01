package org.xujin.janus.infrastructure.tunnel.db.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.xujin.halo.shiro.model.Account;

import java.sql.Timestamp;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

@Data
@TableName("t_user")
public class UserDO implements Account {

    @TableId(type=AUTO)
    private Long id;

    @TableField("gmt_create")
    private Timestamp gmtCreate;

    @TableField("gmt_modified")
    private Timestamp gmtModified;

    @TableField("username")
    private String userName;

    @TableField("name")
    private String name;


    @TableField("email")
    private String email;

    @TableField("password")
    private String password;

    @TableField("status")
    private byte status;

    @TableField("is_deleted")
    private Byte isDeleted=0;

    @Override
    public String getAccount() {
        return userName;
    }
    @Override
    public String getPassword() {
        return password;
    }
}
