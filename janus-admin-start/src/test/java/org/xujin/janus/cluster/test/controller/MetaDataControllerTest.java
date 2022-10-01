package org.xujin.janus.cluster.test.controller;

import org.xujin.janus.app.command.cmo.AddDictDataCmd;
import org.xujin.janus.app.command.cmo.UpdateDictDataCmd;
import org.xujin.janus.cluster.test.CommonTest;
import org.xujin.janus.controller.MetaDataController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenglong.ren
 * @date 2020/4/30 10:33
 * @desc
 */
@Slf4j
public class MetaDataControllerTest extends CommonTest {

    @Autowired
    private MetaDataController metaDataController;

    @Test
    public void testAdd() {
        AddDictDataCmd cmd = new AddDictDataCmd();
        cmd.setDictCode("dictCode");
        cmd.setItemCode("itemCode");
        cmd.setItemDesc("iterDesc");
        cmd.setItemName("itemName");
        cmd.setItemSort(1);
        cmd.setItemValue("itemValue");
        cmd.setStatus(new Byte("1"));
        cmd.setCurrentUserId("7");
        metaDataController.addDictData(cmd);
    }

    @Test
    public void testUpdate() {
        UpdateDictDataCmd cmd = new UpdateDictDataCmd();
        cmd.setId(new Long(1));
        cmd.setItemCode("testCode");
        metaDataController.updateDictData(cmd);
    }


}
