package com.heysong.service;

import com.github.pagehelper.PageInfo;
import com.heysong.domain.TClueRemark;
import com.heysong.domain.TCustomer;
import com.heysong.model.vo.CustomerExport;
import com.heysong.model.vo.TCustomerVo;

import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/4/7 15:14
 * @Version: v1.0.0
 * @Description:
 **/
public interface TCustomerService {

    int addCustomer(TCustomer tCustomer, String token);

    PageInfo<TCustomerVo> loadClueRemarkByPage(Integer currentPage);
    List<CustomerExport> loadCustomerInfos();

    List<CustomerExport> loadSelectedCustomerInfos(String ids);
}