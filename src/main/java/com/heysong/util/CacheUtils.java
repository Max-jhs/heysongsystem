package com.heysong.util;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Author: 8912
 * @Date: 2025/3/30 23:35
 * @Version: v1.0.0
 * @Description:
 **/
public class CacheUtils {

    public static <T> T getCacheData(Supplier<T> redisCache, Supplier<T> databaseCache, Consumer<T> cached) {
        T data = redisCache.get();
        if (ObjectUtils.isEmpty(data)) {
            data = databaseCache.get();
            if (!ObjectUtils.isEmpty(data)) {
                cached.accept(data);
            }
        }
        return data;
    }
}