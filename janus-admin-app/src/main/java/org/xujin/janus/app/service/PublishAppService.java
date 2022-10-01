package org.xujin.janus.app.service;

import org.xujin.halo.annotation.app.AppService;
import org.xujin.halo.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/18 9:45
 **/
@Slf4j
@AppService
public class PublishAppService {

    public void changeServerOnlineStatus(String clusterCode, String serverAddress, boolean online, String requestID) {
        try {
        } catch (Exception e) {
            throw new BusinessException("400", "改变server状态失败:{}", e);
        }
    }


}
