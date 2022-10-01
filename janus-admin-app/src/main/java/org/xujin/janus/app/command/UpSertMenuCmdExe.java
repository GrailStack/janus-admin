package org.xujin.janus.app.command;

import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.janus.app.converter.MenuClientConverter;
import org.xujin.janus.domain.user.entity.MenuE;
import org.xujin.janus.domain.user.ports.MenuPort;
import org.xujin.janus.domain.user.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2019/7/16 18:49
 **/
@CmdHandler
public class UpSertMenuCmdExe implements CommandExecutorI<ResultData<Long>, UpSertMenuCmd> {

    @Autowired
    private MenuClientConverter menuClientConverter;

    @Autowired
    private MenuService menuService;

    @Resource
    private MenuPort menuPort;

    @Override
    public ResultData<Long> execute(UpSertMenuCmd cmd) {
        check(cmd);
        MenuE menuE = menuClientConverter.cmdToEntity(cmd);
        menuPort.save(menuE);
        return ResultData.success(menuE.getId());
    }

    protected void check(UpSertMenuCmd cmd) {
        Preconditions.checkArgument(cmd != null, "cmd不能为null");
        Preconditions.checkArgument(StringUtils.isNotBlank(cmd.getName()), "name不能为empty");
        Preconditions.checkArgument(StringUtils.isNotBlank(cmd.getUrl()), "url不能为empty");
        Preconditions.checkArgument(StringUtils.isNotBlank(cmd.getMenuKey()), "menuKey不能为empty");
        if (cmd.getId() != null) {
            Preconditions.checkArgument(menuService.findMenuById(cmd.getId()) != null, "menu id 不存在");
        }
    }

}
