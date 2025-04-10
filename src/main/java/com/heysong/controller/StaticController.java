package com.heysong.controller;

import com.heysong.manager.StaticManager;
import com.heysong.model.result.NameValue;
import com.heysong.model.result.OverviewData;
import com.heysong.model.result.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/4/10 14:21
 * @Version: v1.0.0
 * @Description:
 **/
@RestController
public class StaticController {
    @Resource
    private StaticManager staticManager;

    @GetMapping("/api/statistic")
    public R getOverviewData() {
        OverviewData overviewData = staticManager.getOverviewData();
        return overviewData != null ? R.OK(overviewData) : R.FAIL();
    }

    @GetMapping("/api/saleFunnel")
    public R loadSaleFunnel() {
        List<NameValue> nameValueList = staticManager.loadSaleFunnel();
        return nameValueList != null ? R.OK(nameValueList) : R.FAIL();
    }

    @GetMapping("/api/clueSourcePie")
    public R loadClueSourcePic() {
        List<NameValue> nameValueList = staticManager.loadClueSourcePic();
        return nameValueList != null ? R.OK(nameValueList) : R.FAIL();
    }
}