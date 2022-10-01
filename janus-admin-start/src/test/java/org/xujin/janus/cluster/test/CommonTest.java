package org.xujin.janus.cluster.test;

import com.alibaba.fastjson.JSON;
import org.xujin.janus.JanusAdminApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenglong.ren
 * @date 2020/4/14 19:02
 * @desc
 */
@Slf4j
@SpringBootTest(classes = JanusAdminApplication.class)
@RunWith(SpringRunner.class)
public class CommonTest {
    public void printJson(Object o){
        log.info("" + JSON.toJSONString(o));
    }
}
