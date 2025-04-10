package com.heysong.controller;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.heysong.domain.TClueRemark;
import com.heysong.domain.TCustomer;
import com.heysong.model.query.ActivityQuery;
import com.heysong.model.result.R;
import com.heysong.model.vo.CustomerExport;
import com.heysong.model.vo.TCustomerVo;
import com.heysong.service.TCustomerService;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/4/7 15:10
 * @Version: v1.0.0
 * @Description:
 **/
@RestController
public class CustomerController {
    @Resource
    private TCustomerService tCustomerService;
    @ApiOperation("新增用户")
    @PostMapping("api/customer/add/{clueId}")
    public R addActivity(@RequestBody TCustomer tCustomer, @RequestHeader(value = "Authorization") String token, @PathVariable(value = "clueId") Integer clueId) {
            tCustomer.setClueId(clueId);
        return tCustomerService.addCustomer(tCustomer,token) == 1 ? R.OK() : R.FAIL();
    }

    @GetMapping("api/customers")
    public R loadClueRemarkByPage(@RequestParam(value = "currentPage") Integer currentPage) {

        PageInfo<TCustomerVo> tClueRemarkPageInfo = tCustomerService.loadClueRemarkByPage(currentPage);
        return tClueRemarkPageInfo != null ? R.OK(tClueRemarkPageInfo) : R.FAIL();
    }
    @ApiOperation("用户excel全导出")
    @GetMapping("/api/customer/exportExcel")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        List<CustomerExport> tCustomerVos = tCustomerService.loadCustomerInfos();
        EasyExcel.write(response.getOutputStream(), CustomerExport.class).sheet("模板").doWrite(tCustomerVos);
    }

    @ApiOperation("选中导出")
    @GetMapping("/api/customer/exportSelectedExcel")
    public void downloadSelectedExcel(HttpServletResponse response, @RequestParam(value = "ids") String ids) throws IOException {

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        List<CustomerExport> tCustomerVos = tCustomerService.loadSelectedCustomerInfos(ids);
        EasyExcel.write(response.getOutputStream(), CustomerExport.class).sheet("模板").doWrite(tCustomerVos);
    }

}