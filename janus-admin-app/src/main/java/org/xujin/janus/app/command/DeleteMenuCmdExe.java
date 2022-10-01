package org.xujin.janus.app.command;


import com.google.common.base.Preconditions;

import org.xujin.halo.annotation.command.CmdHandler;
import org.xujin.halo.command.CommandExecutorI;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.exception.BusinessException;
import org.xujin.janus.app.command.cmo.DeleteMenuCmd;
import org.xujin.janus.domain.user.entity.MenuE;
import org.xujin.janus.domain.user.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2019/7/17 16:50
 **/
@Slf4j
@CmdHandler
public class DeleteMenuCmdExe implements CommandExecutorI<ResultData, DeleteMenuCmd> {

    @Autowired
    private MenuService menuService;

    @Override
    public ResultData execute(DeleteMenuCmd cmd) {
        check(cmd);

        MenuE menuE = menuService.findMenuById(cmd.getId());
        if (menuE == null) {
            throw new BusinessException("400", "该Menu不存在");
        }

        if (menuE.getParentId() != null) {
            List<MenuE> menuEList = menuService.findMenusByParentId(menuE.getId());
            if (CollectionUtils.isNotEmpty(menuEList)) {
                throw new BusinessException("400", "该菜单包含子菜单，不能删除！");
            }
        }
        menuService.deleteMenu(cmd.getId());
        return ResultData.success(true);
    }

    protected void check(DeleteMenuCmd cmd) {
        Preconditions.checkArgument(cmd != null, "cmd不能为null");
        Preconditions.checkArgument(cmd.getId() != null, "menu id不能为null");
    }

}
