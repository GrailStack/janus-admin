package org.xujin.janus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.xujin.halo.annotation.HaloApplication;
import org.xujin.halo.annotation.domain.Domain;
import org.xujin.janus.damon.JanusAdminDamonServer;

/**
 * @author halo codegen
 */

@Domain(code = "basic", parentCode = "bp", name = "基础域", desc = "基础域")
@HaloApplication(appId = "janus-admin",appName = "网关管控端")
//@EnableDiscoveryClient
public class JanusAdminApplication implements CommandLineRunner {

    @Value("${server.port}")
    private int port;

    public static void main(String[] args) {
        SpringApplication.run(JanusAdminApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        JanusAdminDamonServer.start(port+1);
    }
}
