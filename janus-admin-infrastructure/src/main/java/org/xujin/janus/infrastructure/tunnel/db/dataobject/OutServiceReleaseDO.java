package org.xujin.janus.infrastructure.tunnel.db.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.xujin.janus.domain.api.constant.HttpMethodEnum;
import org.xujin.janus.domain.api.constant.OutServiceProtocolEnum;
import org.xujin.janus.domain.api.constant.OutServiceTypeEnum;
import org.xujin.janus.domain.api.valueobject.OutServiceParamTransConfig;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/5 14:32
 **/
@TableName("t_out_service_release")
@Data
public class OutServiceReleaseDO implements Serializable {

    @TableId(value = "id", type = AUTO)
    private Long id;

    private Long apiReleaseId;
    private String clusterCode;
    private String name;
    private OutServiceTypeEnum type;
    private OutServiceProtocolEnum protocol;
    private String uri;
    private String path;
    private HttpMethodEnum method;
    private String paramTransType;
    private OutServiceParamTransConfig paramTransConfig;
    private String responseTransType;
    private String responseTransConfig;
    private String createUser;
    private String updateUser;
    private Date createTime;
    private Date updateTime;

}
