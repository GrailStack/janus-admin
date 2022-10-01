package org.xujin.janus.cluster.test.controller;

import org.xujin.janus.app.server.processer.step.QueryStartUpStep;
import org.xujin.janus.cluster.test.CommonTest;
import org.junit.Test;

import javax.annotation.Resource;

public class QueryStartUpController extends CommonTest {

    @Resource
    private QueryStartUpStep queryStartUpStep;

    @Test
    public void testAdd() {
        System.out.println(queryStartUpStep.execute("Janus_Test"));
    }

}
