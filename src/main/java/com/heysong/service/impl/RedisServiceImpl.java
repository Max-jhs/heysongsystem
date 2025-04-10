package com.heysong.service.impl;

import com.heysong.model.vo.OwnerPO;
import com.heysong.service.RedisService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 8912
 * @Date: 2025/3/7 20:12
 * @Version: v1.0.0
 * @Description:
 **/
@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    public <T> void leftPushAll(String key, T... values) {
        redisTemplate.opsForList().leftPushAll(key, values);
    }

    @Override
    public Boolean removeData(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public <T> List<T> getListDataByKey(String ownerIdKey) {
        return (List<T>) redisTemplate.opsForList().range(ownerIdKey,0,-1);
    }

    @Override
    public void setExpired(String key) {
        redisTemplate.expire(key, 5 * 60, TimeUnit.SECONDS);
    }

}