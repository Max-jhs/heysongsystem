package com.heysong.controller;

import com.github.pagehelper.PageInfo;
import com.heysong.domain.TActivityRemark;
import com.heysong.model.query.ActivityRemarkQuery;
import com.heysong.model.result.R;
import com.heysong.service.ActivityRemarkService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 8912
 * @Date: 2025/3/31 22:21
 * @Version: v1.0.0
 * @Description:
 **/
@RestController
public class ActivityRemarkController {
    @Resource
    private ActivityRemarkService activityRemarkService;
    @PostMapping("/api/activity/remark/add")
    public R addActivityRemark(@RequestBody ActivityRemarkQuery activityRemarkQuery, @RequestHeader(value = "Authorization") String token) {
        activityRemarkQuery.setJwt(token);
        Integer count = activityRemarkService.addNoteContentForActivityByActivityId(activityRemarkQuery);
        return count == 1 ? R.OK():R.FAIL();
    }


    @DeleteMapping("/api/activity/remark/del/{id}")
    public R delActivityRemark(@PathVariable(value = "id") Integer id) {
        Integer count = activityRemarkService.delNoteContentForActivityByRemarkId(id);
        return count == 1 ? R.OK():R.FAIL();
    }

    // 分页查询某个id的活动的备注详情
    @GetMapping("/api/activity/remarks")
    public R loadRemarksForActivityByActivityId(@RequestParam(value = "currentPage") Integer pageNum, @RequestParam(value = "activityId") Integer activityId) {
        PageInfo<TActivityRemark> pageInfo = activityRemarkService.selectActivityRemarkByPage(pageNum, activityId);
        return R.OK(pageInfo);
    }

    // 修改某个备注
    @PutMapping("/api/activity/remark/edit")
    public R updateRemarksNoteContent(@RequestBody ActivityRemarkQuery activityRemarkQuery, @RequestHeader(value = "Authorization") String token) {
        activityRemarkQuery.setJwt(token);
        Integer count = activityRemarkService.updateRemarksNoteContent(activityRemarkQuery);
        return count == 1 ? R.OK():R.FAIL();
    }

}