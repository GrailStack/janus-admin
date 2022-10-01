package org.xujin.janus.app.interceptor;

import org.xujin.halo.annotation.command.PreInterceptor;
import org.xujin.halo.command.CommandInterceptorI;
import org.xujin.halo.dto.Command;

/**
 * @author halo codegen
 * for demo
 */
@PreInterceptor
public class HaloContextPreInterceptor implements CommandInterceptorI {

    @Override
    public void preIntercept(Command command) {

    }

}
