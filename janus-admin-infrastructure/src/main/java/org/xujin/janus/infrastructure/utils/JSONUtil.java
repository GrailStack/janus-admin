package org.xujin.janus.infrastructure.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/10 16:48
 **/
public class JSONUtil {

    public static String toPrettyJSONStr(Object obj) {
        if (obj == null) {
            return null;
        }
        return JSON.toJSONString(obj, SerializerFeature.PrettyFormat);
    }

    public static String toJSONStr(Object obj) {
        if (obj == null) {
            return null;
        }
        return JSON.toJSONString(obj);
    }

}
