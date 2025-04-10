package com.heysong.config.task;

import com.heysong.HeysongApplication;
import com.heysong.domain.TActivity;
import com.heysong.domain.TDicType;
import com.heysong.domain.TDicValue;
import com.heysong.domain.TProduct;
import com.heysong.domain.TUser;
import com.heysong.mapper.TActivityMapper;
import com.heysong.mapper.TDicValueMapper;
import com.heysong.mapper.TProductMapper;
import com.heysong.mapper.TUserMapper;
import com.heysong.service.TDicTypeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 8912
 * @Date: 2025/4/5 18:03
 * @Version: v1.0.0
 * @Description:
 **/
@EnableScheduling
@Component
@Slf4j
public class InitPropertyMapping {
    @Resource
    private TDicTypeService tDicTypeService;
    @Resource
    private TProductMapper tProductMapper;
    @Resource
    private TUserMapper tUserMapper;
    @Resource
    private TActivityMapper tActivityMapper;
    @Scheduled(fixedDelayString = "${load.property.cron}",initialDelay = 1000,timeUnit = TimeUnit.MILLISECONDS)
    public void task() {

        List<TDicType> tDicTypeList = tDicTypeService.selectDicType();
        List<TProduct> tProducts = tProductMapper.selectAllProduct();
        List<TUser> tUsers = tUserMapper.selectUsers();
        List<TActivity> tActivities = tActivityMapper.selectAllAct();

        HeysongApplication.PropertyMapping.clear();
        HeysongApplication.ProductMapping.clear();
        HeysongApplication.ActivityMapping.clear();
        HeysongApplication.UserMapping.clear();

        for (TDicType tDicType : tDicTypeList) {
            HeysongApplication.PropertyMapping.put(tDicType.getTypeCode(), tDicType.getDicValueList());
        }
        HeysongApplication.UserMapping.addAll(tUsers);
        HeysongApplication.ProductMapping.addAll(tProducts);
        HeysongApplication.ActivityMapping.addAll(tActivities);
    }
}