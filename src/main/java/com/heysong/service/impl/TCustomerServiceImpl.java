package com.heysong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heysong.domain.TClueRemark;
import com.heysong.domain.TCustomer;
import com.heysong.manager.ConverterCustomerManager;
import com.heysong.mapper.TCustomerMapper;
import com.heysong.model.constant.Constants;
import com.heysong.model.vo.CustomerExport;
import com.heysong.model.vo.TClueVo;
import com.heysong.model.vo.TCustomerVo;
import com.heysong.service.TCustomerService;
import com.heysong.util.JWTUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/4/7 15:15
 * @Version: v1.0.0
 * @Description:
 **/
@Service
public class TCustomerServiceImpl implements TCustomerService {
    @Resource
    private TCustomerMapper tCustomerMapper;
    @Resource
    private ConverterCustomerManager customerManager;

    @Override
    public int addCustomer(TCustomer tCustomer, String token) {
        return customerManager.converterToCustomer(tCustomer, token);
    }

    @Override
    public PageInfo<TCustomerVo> loadClueRemarkByPage(Integer currentPage) {
        PageHelper.startPage(currentPage, Constants.PAGE_SIZE);
        List<TCustomerVo> customerList = tCustomerMapper.selectAll();

        return new PageInfo<>(customerList);
    }

    @Override
    public List<CustomerExport> loadCustomerInfos() {
        List<TCustomerVo> tCustomerVos = tCustomerMapper.selectAll();
        ArrayList<CustomerExport> customerExports = new ArrayList<>(tCustomerVos.size());
        for (TCustomerVo tCustomerVo : tCustomerVos) {
            CustomerExport customerExport = new CustomerExport();
            customerExport.setOwnerName(tCustomerVo.getClueVo().getOwnerPO().getName());
            customerExport.setActivityName(tCustomerVo.getClueVo().getActivityPO().getName());
            customerExport.setFullName(tCustomerVo.getClueVo().getFullName());
            customerExport.setAddress(tCustomerVo.getClueVo().getAddress());
            customerExport.setPhone(tCustomerVo.getClueVo().getPhone());
            customerExport.setWeixin(tCustomerVo.getClueVo().getWeixin());
            String qq = tCustomerVo.getClueVo().getQq();
            if (qq != null && !qq.equals("")) {
                customerExport.setQq(qq);
            }

            String email = tCustomerVo.getClueVo().getEmail();
            if (email != null && !email.equals("")) {
                customerExport.setEmail(email);
            }
            customerExport.setAge(tCustomerVo.getClueVo().getAge());
            String job = tCustomerVo.getClueVo().getJob();
            if (job != null && !job.equals("")) {
                customerExport.setJob(job);
            }
            customerExport.setYearIncome(tCustomerVo.getClueVo().getYearIncome());
            customerExport.setNeedLoadName(tCustomerVo.getClueVo().getNeedLoanPO().getTypeValue());
            customerExport.setAppellationName(tCustomerVo.getClueVo().getAppellationPO().getTypeValue());
            customerExport.setProductName(tCustomerVo.getClueVo().getIntentionProductPO().getProductName());
            customerExport.setSourceName(tCustomerVo.getClueVo().getSourcePO().getTypeValue());
            customerExport.setDescription(tCustomerVo.getClueVo().getSourcePO().getTypeValue());
            customerExport.setNextContactTime(tCustomerVo.getClueVo().getNextContactTime());
            customerExports.add(customerExport);
        }
        return customerExports;
    }

    @Override
    public List<CustomerExport> loadSelectedCustomerInfos(String ids) {
        String[] split = ids.split(",");
        List<TCustomerVo> tClueVos = tCustomerMapper.selectSelectAll(split);
        ArrayList<CustomerExport> customerExports = new ArrayList<>(tClueVos.size());
        for (TCustomerVo tCustomerVo : tClueVos) {
            CustomerExport customerExport = new CustomerExport();
            customerExport.setOwnerName(tCustomerVo.getClueVo().getOwnerPO().getName());
            customerExport.setActivityName(tCustomerVo.getClueVo().getActivityPO().getName());
            customerExport.setFullName(tCustomerVo.getClueVo().getFullName());
            customerExport.setAddress(tCustomerVo.getClueVo().getAddress());
            customerExport.setPhone(tCustomerVo.getClueVo().getPhone());
            customerExport.setWeixin(tCustomerVo.getClueVo().getWeixin());
            String qq = tCustomerVo.getClueVo().getQq();
            if (qq != null && !qq.equals("")) {
                customerExport.setQq(qq);
            }

            String email = tCustomerVo.getClueVo().getEmail();
            if (email != null && !email.equals("")) {
                customerExport.setEmail(email);
            }
            customerExport.setAge(tCustomerVo.getClueVo().getAge());
            String job = tCustomerVo.getClueVo().getJob();
            if (job != null && !job.equals("")) {
                customerExport.setJob(job);
            }
            customerExport.setYearIncome(tCustomerVo.getClueVo().getYearIncome());
            customerExport.setNeedLoadName(tCustomerVo.getClueVo().getNeedLoanPO().getTypeValue());
            customerExport.setAppellationName(tCustomerVo.getClueVo().getAppellationPO().getTypeValue());
            customerExport.setProductName(tCustomerVo.getClueVo().getIntentionProductPO().getProductName());
            customerExport.setSourceName(tCustomerVo.getClueVo().getSourcePO().getTypeValue());
            customerExport.setDescription(tCustomerVo.getClueVo().getSourcePO().getTypeValue());
            customerExport.setNextContactTime(tCustomerVo.getClueVo().getNextContactTime());
            customerExports.add(customerExport);
        }
        return customerExports;
    }
}