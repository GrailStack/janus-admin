package org.xujin.janus.infrastructure.common;

public interface HaloConstant {


    String ME = "me";
    String ALL = "all";

    /**
     * 用Halo的标识
     */
    int USE_HALO=1;

    /**
     * 不用Halo的标识
     */
    int NO_USE_HALO=0;

    /**
     * 启用
     */
    Byte ENABLE=1;

    /**
     * 禁用
     */
    Byte DISABLE=0;


    Byte  IS_DELETED_TRUE=1;

    Byte  IS_DELETED_FALSE=0;

    String API_STATUS_ONLINE = "ONLINE";

    /**
     * 用户登录面板显示
     */
    String EMAIL = "@qq.com";
    String VERSION = "1.0.0";

    String JANUS_SERVER_CONF="serverConf";

    String JANUS_CLIENT_CONF="clientConf";

    String JANUS_DYNAMIC_CLASS_FILE_CONFIG="dynamicClassFileConfig";


    String JANUS_INFLUX_CONFIG="janusInfluxConfig";





}
