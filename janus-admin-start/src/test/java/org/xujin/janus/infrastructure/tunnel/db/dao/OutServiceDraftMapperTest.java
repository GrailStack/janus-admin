package org.xujin.janus.infrastructure.tunnel.db.dao;

import org.xujin.janus.cluster.test.CommonTest;
import org.xujin.janus.infrastructure.tunnel.db.dataobject.OutServiceDraftDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 20:13
 **/
public class OutServiceDraftMapperTest extends CommonTest {

    @Autowired
    private OutServiceDraftMapper outServiceDraftMapper;

    @Test
    public void test_findByApiDraftId() {
        List<OutServiceDraftDO> draftList = outServiceDraftMapper.findByApiDraftId((long) 3);
        System.out.println(draftList);
    }

}
