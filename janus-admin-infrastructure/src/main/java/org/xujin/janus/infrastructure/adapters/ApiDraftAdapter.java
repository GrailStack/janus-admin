package org.xujin.janus.infrastructure.adapters;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.xujin.halo.annotation.infrastructure.Adapter;
import org.xujin.janus.domain.api.constant.ApiDraftStatusEnum;
import org.xujin.janus.domain.api.constant.ApiOperateTypeEnum;
import org.xujin.janus.domain.api.entity.ApiDraftE;
import org.xujin.janus.domain.api.port.ApiDraftPort;
import org.xujin.janus.infrastructure.converter.ApiDraftConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.ApiDraftMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.ApiDraftDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/2 17:19
 **/
@Adapter
public class ApiDraftAdapter implements ApiDraftPort {

    @Autowired
    private ApiDraftMapper mapper;

    @Autowired
    private ApiDraftConverter converter;

    @Override
    public long add(ApiDraftE draftE) {
        ApiDraftDO draftDO = converter.entityToData(draftE);
        if (mapper.insert(draftDO) < 1) {
            return -1L;
        }
        return draftDO.getId();
    }

    @Override
    public boolean updateApiIdToId(Long id) {
        return mapper.updateApiIdToId(id) > 0;
    }

    @Override
    public ApiDraftE findUnDeletedById(Long id) {
        return converter.dataToEntity(mapper.findUnDeletedById(id));
    }

    @Override
    public boolean updateStatusById(Long id, ApiDraftStatusEnum status) {
        return mapper.updateStatusById(id, status.getCode()) > 0;
    }

    @Override
    public boolean logicalDeleteWithCheck(Long id, List<ApiDraftStatusEnum> deletableAuditStatus) {
        QueryWrapper<ApiDraftDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.in("status", deletableAuditStatus);
        return mapper.delete(queryWrapper) > 0;
    }

    @Override
    public boolean containsNameInCluster(String clusterCode, String name) {
        QueryWrapper<ApiDraftDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cluster_code", clusterCode);
        queryWrapper.eq("name", name);
        queryWrapper.eq("is_deleted", "0");
        queryWrapper.and(
                qw -> qw.ne("operate_type", ApiOperateTypeEnum.DELETE.getCode())
                        .or()
                        .ne("status", ApiDraftStatusEnum.RELEASED.getCode())
        );
        queryWrapper.last("limit 1");
        return mapper.selectOne(queryWrapper) != null;
    }

    @Override
    public boolean containsApiInCluster(String clusterCode, String path, String method) {
        QueryWrapper<ApiDraftDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cluster_code", clusterCode);
        queryWrapper.eq("path", path);
        queryWrapper.eq("method", method);
        queryWrapper.eq("is_deleted", "0");
        queryWrapper.and(
                qw -> qw.ne("operate_type", ApiOperateTypeEnum.DELETE.getCode())
                        .or()
                        .ne("status", ApiDraftStatusEnum.RELEASED.getCode())
        );
        queryWrapper.last("limit 1");
        return mapper.selectOne(queryWrapper) != null;
    }

    @Override
    public int update(ApiDraftE draftE) {
        ApiDraftDO draftDO = converter.entityToData(draftE);
        return mapper.updateById(draftDO);
    }

    @Override
    public List<ApiDraftE> findInEditing(String clusterCode) {
        QueryWrapper<ApiDraftDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cluster_code", clusterCode);
        queryWrapper.eq("status", ApiDraftStatusEnum.EDITING.getCode());
        queryWrapper.eq("is_deleted", "0");
        queryWrapper.orderByDesc("id");
        return converter.dataToEntity(mapper.selectList(queryWrapper));
    }

    @Override
    public int batchSubmitToAudit(List<Long> apiDraftIdList, Long applyId) {
        UpdateWrapper<ApiDraftDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", ApiDraftStatusEnum.WAIT_AUDIT.getCode());
        updateWrapper.set("apply_id", applyId);
        updateWrapper.in("id", apiDraftIdList);
        updateWrapper.eq("status", ApiDraftStatusEnum.EDITING.getCode());
        updateWrapper.eq("is_deleted", "0");
        return mapper.update(null, updateWrapper);
    }

    @Override
    public int updateStatusByApplyId(Long applyId, ApiDraftStatusEnum status) {
        UpdateWrapper<ApiDraftDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status.getCode());
        updateWrapper.eq("apply_id", applyId);
        updateWrapper.eq("is_deleted", "0");
        return mapper.update(null, updateWrapper);
    }

    @Override
    public List<ApiDraftE> findByIdList(List<Long> idList) {
        QueryWrapper<ApiDraftDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", idList);
        queryWrapper.orderByDesc("id");
        return converter.dataToEntity(mapper.selectList(queryWrapper));
    }

    @Override
    public List<ApiDraftE> findByApplyId(Long applyId) {
        QueryWrapper<ApiDraftDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("apply_id", applyId);
        queryWrapper.eq("is_deleted", "0");
        return converter.dataToEntity(mapper.selectList(queryWrapper));
    }

}
