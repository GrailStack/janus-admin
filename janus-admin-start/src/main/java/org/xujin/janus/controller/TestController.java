package org.xujin.janus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xujin.halo.dto.ResultData;
import org.xujin.halo.event.EventBus;
import org.xujin.janus.app.event.PublishToServerEvent;
import org.xujin.janus.domain.user.entity.ApplyE;
import org.xujin.janus.domain.user.factory.ApplyFactory;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin/test/ss")
@RestController
public class TestController {

    @Autowired
    private ApplyFactory  applyFactory;

    @Autowired
    private EventBus eventBus;


    @GetMapping("/")
    public ResultData test(){
        ApplyE applyE = applyFactory.createApplyE(1L);
        PublishToServerEvent event = new PublishToServerEvent();
        event.setApplyId(applyE.getId());
        event.setChanges(applyE.getChanges());
        event.setPublishId(1L);
        List<String> ipList=new ArrayList<>();
        ipList.add("127.0.0.1:8082");
        event.setIpList(ipList);
        eventBus.asyncPublishEvent(event);
        return ResultData.success(null);
    }
}
