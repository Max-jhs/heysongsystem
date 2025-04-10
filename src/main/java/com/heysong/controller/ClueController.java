package com.heysong.controller;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.heysong.HeysongApplication;
import com.heysong.domain.TClue;
import com.heysong.domain.TClueRemark;
import com.heysong.model.result.R;
import com.heysong.model.vo.TClueVo;
import com.heysong.service.ClueService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: 8912
 * @Date: 2025/4/1 22:36
 * @Version: v1.0.0
 * @Description: 线索控制层
 **/
@RestController
@Slf4j
public class ClueController {
    @Resource
    private ClueService clueService;
    @GetMapping("/api/clues")
    public R loadCluesByPage(@RequestParam(value = "currentPage") Integer currentPage) {
        PageInfo<TClueVo> pageInfo = clueService.loadCluesByPage(currentPage);
        return R.OK(pageInfo);
    }


    @PostMapping("/api/clue/importExcel")
    // 专门处理文件类型的参数  形式参数名要和 前端 formdata的key 相同  formData.append('file', fileData.file)
    public R importClueExcel(@RequestBody MultipartFile file, @RequestHeader(value = "Authorization") String token) throws IOException {
        Boolean flag = clueService.uploadClueExcel(file,token);
        return flag ? R.OK() : R.FAIL();
    }


    @GetMapping("/api/dicValue/{type}")
    public R loadDicValue(@PathVariable(value = "type") String type){
        if (type.equals("appellation")) {
            return R.OK(HeysongApplication.PropertyMapping.get("appellation"));
        }
        if (type.equals("needLoan")) {
            return R.OK(HeysongApplication.PropertyMapping.get("needLoan"));
        }
        if (type.equals("intentionState")) {
            return R.OK(HeysongApplication.PropertyMapping.get("intentionState"));
        }
        if (type.equals("clueState")) {
            return R.OK(HeysongApplication.PropertyMapping.get("clueState"));
        }
        if (type.equals("source")) {
            return R.OK(HeysongApplication.PropertyMapping.get("source"));
        }
        if (type.equals("noteWay")) {
            return R.OK(HeysongApplication.PropertyMapping.get("noteWay"));
        }
        if (type.equals("product")) {
            return R.OK(HeysongApplication.ProductMapping);
        }

        return R.FAIL();
    }


    @GetMapping("/api/clue/detail/{id}")
    public R loadDicValue(@PathVariable(value = "id") Integer clueId) {
        TClue tClue = clueService.loadClueDetailById(clueId);
        return tClue != null ? R.OK(tClue) : R.FAIL();
    }

    @PostMapping("/api/clue/add")
    public R addClue(@RequestBody TClue tClue, @RequestHeader(value = "Authorization") String token) {

        Integer count = clueService.addClue(tClue,token);
        return count == 1 ? R.OK(tClue) : R.FAIL();
    }

    @PutMapping("/api/clue/edit")
    public R editClue(@RequestBody TClue tClue, @RequestHeader(value = "Authorization") String token) {

        Integer count = clueService.editClue(tClue,token);
        return count == 1 ? R.OK(tClue) : R.FAIL();
    }



}