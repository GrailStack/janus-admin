package org.xujin.janus.app.server.processer;

import com.google.common.base.Preconditions;
import org.xujin.janus.damon.exchange.JanusCmdMsg;
import org.xujin.janus.damon.processer.ICmdMsgProcessor;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: gan
 * @date: 2020/6/12
 */
public abstract class AbstractProcessor implements ICmdMsgProcessor {
    private static Logger logger = LoggerFactory.getLogger(AbstractProcessor.class);

    @Override
    public JanusCmdMsg execute(JanusCmdMsg msg) {
        try {
            Preconditions.checkNotNull(msg, "janusMsg cannot be null");
            Preconditions.checkNotNull(msg.getCluster(), "janusMsg cluster cannot be null");
            JanusCmdMsg resultMsg = doExecute(msg);
            if (msg.isNeedCallBack()) {
                //暂时没有Admin异步回调Server的需求；暂不实现；
                //实现是需要注意Server异步请求要带上host和port
            }
            return resultMsg;
        } catch (Exception ex) {
            logger.error("processor doExecute throw exception", ex);
            if (msg.isNeedCallBack()) {
                //暂时没有Admin异步回调Server的需求；暂不实现；
                //实现是需要注意Server异步请求要带上host和port
            }
            return errorResponse(ex.getMessage(), msg);
        }
    }

    /**
     * 具体的实现方法
     *
     * @param msg
     * @return
     */
    public abstract JanusCmdMsg doExecute(JanusCmdMsg msg);

    public JanusCmdMsg errorResponse(String errorMessage, JanusCmdMsg msg) {
        return JanusCmdMsg.builder()
                .code(HttpResponseStatus.INTERNAL_SERVER_ERROR.code())
                .message(errorMessage)
                .cluster(msg.getCluster())
                .version(msg.getVersion())
                .buildFailResponse();
    }

    public JanusCmdMsg successResponse(JanusCmdMsg msg, Object payLoad) {
        JanusCmdMsg.Builder builder = JanusCmdMsg.builder()
                .method(msg.getMethod())
                .cluster(msg.getCluster())
                .version(msg.getVersion());
        if (payLoad != null) {
            builder.payload(payLoad);
        }
        return builder.buildSuccessResponse();
    }
}
