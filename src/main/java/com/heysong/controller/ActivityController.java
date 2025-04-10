package com.heysong.controller;

import com.github.pagehelper.PageInfo;
import com.heysong.domain.TActivity;
import com.heysong.model.query.ActivityQuery;
import com.heysong.model.result.R;
import com.heysong.model.vo.OwnerPO;
import com.heysong.service.ActivityService;
import com.heysong.service.TUserService;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/3/17 18:22
 * @Version: v1.0.0
 * @Description:
 **/
@RestController
public class ActivityController {
    @Resource
    private ActivityService activityService;
    @Autowired
    private TUserService tUserService;

    @ApiOperation("分页查询活动表")
    @GetMapping("/api/activities")
    // @RequestParam 加了就必须要有‘activityQuery’名字相同的参数 get请求{json} 多个参数不是一个参数
    public R loadActivitiesByPage(@ModelAttribute ActivityQuery activityQuery){
        PageInfo<TActivity> pageInfo = activityService.loadActivitiesByPage(activityQuery);
        R ok = R.OK(pageInfo);
        return ok;
    }


    @ApiOperation("活动负责人全查询")
    @GetMapping("/api/owners")
    // @RequestParam 加了就必须要有‘activityQuery’名字相同的参数 get请求{json} 多个参数不是一个参数
    public R loadOwnerList(){
        List<OwnerPO> ownerPOS = tUserService.selectAll();
        R ok = R.OK(ownerPOS);
        System.out.println(ownerPOS);
        // [[{id=1, name=管理员}, {id=2, name=于嫣}, 集合 集合 json  redis中存的[{"id": 1,"name": "管理员"},
        return ok;
    }



    @ApiOperation("新增活动")
    @PostMapping("/api/activity/add")
    public R addActivity(@RequestBody ActivityQuery activityQuery, @RequestHeader(value = "Authorization") String token) {
        activityQuery.setJwt(token);
        Integer mount = activityService.addActivity(activityQuery);
        return mount == 1 ? R.OK() : R.FAIL();
    }

    @ApiOperation("删除活动")
    @DeleteMapping("/api/activity/del/{ids}")
    //  del/1,2,3  del/x
    public R delActivityById(@PathVariable(value = "ids") String ids) {
        // 不管了都是批量干
        String[] splitIds = ids.split(",");
        int length = splitIds.length;
        int count = activityService.delActivityByIds(splitIds);
        return length == count ? R.OK() : R.FAIL();
    }



    @ApiOperation("查询某个活动的具体详情")
    @GetMapping("/api/activity/detail/{id}")
    public R loadActivityDetail(@PathVariable(value = "id") Integer activityId) {
        TActivity tActivity = activityService.loadActivityDetail(activityId);
        R ok = R.OK(tActivity);
        return ok;
    }


    @ApiOperation("编辑活动的具体详情")
    @PutMapping("/api/activity/edit")
    public R loadActivityDetail(@RequestBody ActivityQuery activityQuery, @RequestHeader(value = "Authorization") String token) {
        activityQuery.setJwt(token);
        Integer count = activityService.editActivityDetails(activityQuery);
        return count == 1 ? R.OK() : R.FAIL();
    }


    @ApiOperation("查询活动id和名称")
    @GetMapping("/api/activity")
    public R loadActivityIdAndName() {
        List<TActivity> activities = activityService.loadAll();
        return R.OK(activities);
    }
}