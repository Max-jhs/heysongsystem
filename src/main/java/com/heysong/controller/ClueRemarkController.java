package com.heysong.controller;

import com.github.pagehelper.PageInfo;
import com.heysong.domain.TClueRemark;
import com.heysong.model.result.R;
import com.heysong.service.TClueRemarkService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 8912
 * @Date: 2025/4/7 13:42
 * @Version: v1.0.0
 * @Description:
 **/
@RestController
public class ClueRemarkController {
    @Resource
    private TClueRemarkService tClueRemarkService;

    @GetMapping("/api/clue/remark")
    public R loadClueRemarkByPage(@RequestParam(value = "currentPage") Integer currentPage ,@RequestParam(value = "clueId") Integer clueId) {

        PageInfo<TClueRemark> tClueRemarkPageInfo = tClueRemarkService.loadClueRemarkByPage(currentPage,clueId);
        return tClueRemarkPageInfo != null ? R.OK(tClueRemarkPageInfo) : R.FAIL();
    }

    @PostMapping("/api/clue/remark/add/{clueId}")
    public R addRemark(@RequestBody TClueRemark tClueRemark, @PathVariable(value = "clueId") Integer clueId, @RequestHeader(value = "Authorization") String token) {
        tClueRemark.setClueId(clueId);
        return tClueRemarkService.add(tClueRemark, token) == 1 ? R.OK() : R.FAIL();
    }
}