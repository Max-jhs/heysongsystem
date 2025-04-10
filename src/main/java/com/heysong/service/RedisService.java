package com.heysong.service;

import com.heysong.model.vo.OwnerPO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 *
 */

public interface RedisService {

    <T> void leftPushAll(String key,T... values);

    Boolean removeData(String key);

    <T> List<T> getListDataByKey(String ownerIdKey);

    void setExpired(String key);
}
