package com.heysong.manager;

import com.heysong.model.constant.Constants;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 8912
 * @Date: 2025/3/30 23:45
 * @Version: v1.0.0
 * @Description:
 **/
@Component
public class RedisManager {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public <T> T getValue(String key) {
        return (T) redisTemplate.opsForList().range(key, 0, -1);
    }
    public <T> void setValue(String key,T... value) {
        // [OwnerPO(id=1, name=管理员), OwnerPO(id=2, name=于嫣),
        redisTemplate.opsForList().leftPushAll(key, value);
        // [[{id=1, name=管理员}, {id=2, name=于嫣},
        // Java中查到的类型  和 存入 redis中的类型 又出入  redis中取出来 也有出去  总之要看类型长啥样
        redisTemplate.expire(key, 3 * 60, TimeUnit.SECONDS);
    }
}