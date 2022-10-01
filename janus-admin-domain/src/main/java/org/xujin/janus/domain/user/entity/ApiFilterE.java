package org.xujin.janus.domain.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import org.xujin.halo.annotation.domain.Entity;
import org.xujin.janus.domain.user.repository.ApiFilterRepository;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author chenglong.ren
 * @date 2020/6/17 11:51
 * @desc
 */
@Entity
@Data
public class ApiFilterE {

    private ApiFilterRepository apiFilterRepository;


    /**
     * 主键
     */
    private Integer id;

    /**
     * api Id
     */
    private String apiId;

    /**
     * 该表逻辑主键
     */
    private String filterId;

    /**
     * 集群编码
     */
    private String clusterCode;

    /**
     * Filter中文名
     */
    private String filterName;

    /**
     * Filter类名
     */
    private String filterCode;

    /**
     * filter参数,json类型
     */
    private String filterParam;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改者
     */
    private String modifier;

    /**
     * 创建时间
     */
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    private Timestamp gmtModified;

    public void save() {
        apiFilterRepository.save(this);
    }

    public void update() {
        apiFilterRepository.update(this);
    }

}
