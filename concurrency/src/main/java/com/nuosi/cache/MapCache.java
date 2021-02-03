package com.nuosi.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @name MapCache
 * @version 0.1.0
 * @desc 使用DCL设计，防止多线程问题导致new多个服务实例
 * @author nuosi fsofs@163.com
 * @date 2021/1/18 21:27
 */
public class MapCache {
    private static final Map<String, Object> objectMap = new ConcurrentHashMap<>();

    /**
     * @name getObject
     * @version 0.1.0
     * @desc 可参考design-patterns>SingletonDCL
     * @param ObjectName
     * @return java.lang.Object
     * @throws
     * @author nuosi fsofs@163.com
     * @date 2021/2/3 11:16
     */
    public static Object getObject(String ObjectName) {
        Object Object = objectMap.get(ObjectName);
        if (Object == null) {
            synchronized (Object.class) {
                Object = objectMap.get(ObjectName);
                if (Object == null) {
                    Object = new Object();
                    objectMap.put(ObjectName, Object);
                }
            }
        }
        return Object;
    }


}
