package org.xujin.janus.app.command.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.command.cmo.query.QueryPublishDetailCmd;
import org.xujin.janus.app.command.co.PublishDetailCO;
import org.xujin.janus.app.command.co.PublishIpDetailCO;
import org.xujin.janus.app.converter.PublishClientConverter;
import org.xujin.janus.app.converter.PublishIpClientConverter;
import org.xujin.janus.infrastructure.tunnel.db.dao.PublishIpMapper;
import org.xujin.janus.infrastructure.tunnel.db.dao.PublishMapper;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PublishDO;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.PublishIpDO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * @author chenglong.ren
 * @date 2020/5/28 11:06
 * @desc
 */
@Slf4j
@CmdHandler
public class QueryPublishDetailCmdExe implements CommandExecutorI<ResultData<PublishDetailCO>, QueryPublishDetailCmd> {

    @Resource
    private PublishClientConverter publishClientConverter;

    @Resource
    private PublishMapper publishMapper;

    @Resource
    private PublishIpMapper publishIpMapper;

    @Resource
    private PublishIpClientConverter publishIpClientConverter;

    @Override
    public ResultData<PublishDetailCO> execute(QueryPublishDetailCmd cmd) {
        ResultData<PublishDetailCO> resultData = ResultData.success(null);
        check(cmd);
        PublishDetailCO detail = detail(cmd.getId());
        if (null != detail && detail.getId() != null) {
            List<PublishIpDetailCO> list = list(cmd.getId());
            detail.setPublishIpDetailCOS(list);
        }
        resultData.setData(detail);
        return resultData;
    }

    protected boolean check(QueryPublishDetailCmd cmd) {
        Preconditions.checkNotNull(cmd, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId() != null, "id不能为空");
        return true;
    }

    public PublishDetailCO detail(BigInteger id) {
        PublishDO publishDO = publishMapper.selectById(id);
        return publishClientConverter.dataToClient(publishDO);
    }

    public List<PublishIpDetailCO> list(BigInteger id) {
        QueryWrapper<PublishIpDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.eq("publish_id", id);
        List<PublishIpDO> list = publishIpMapper.selectList(queryWrapper);
        return publishIpClientConverter.dataToClients(list);
    }

}
