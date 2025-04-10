package com.heysong.manager;

import com.heysong.mapper.TActivityMapper;
import com.heysong.mapper.TClueMapper;
import com.heysong.mapper.TCustomerMapper;
import com.heysong.mapper.TTranMapper;
import com.heysong.model.result.NameValue;
import com.heysong.model.result.OverviewData;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/4/10 14:26
 * @Version: v1.0.0
 * @Description:
 **/
@Component
public class StaticManager {
    @Resource
    private TActivityMapper tActivityMapper;
    @Resource
    private TClueMapper tClueMapper;
    @Resource
    private TCustomerMapper tCustomerMapper;
    @Resource
    private TTranMapper tTranMapper;
    public OverviewData getOverviewData() {

        return OverviewData.builder().effectiveActivityCount(tActivityMapper.selectOnGoingActivity().size())
                .totalActivityCount(tActivityMapper.selectAllAct().size())
                .totalClueCount(tClueMapper.selectAllTclue())
                .totalCustomerCount(tCustomerMapper.selectAllTcustomer())
                .successTranAmount(tTranMapper.selectSuccessTran())
                .totalTranAmount(tTranMapper.selectTotalTran())
                .build();
    }

    public List<NameValue> loadSaleFunnel() {
        ArrayList<NameValue> nameValues = new ArrayList<>();
        NameValue xiaosuo = NameValue.builder().name("线索").value(tClueMapper.selectAllTclue()).build();
        NameValue kehu = NameValue.builder().name("客户").value(tCustomerMapper.selectAllTcustomer()).build();
        NameValue jiaoyi = NameValue.builder().name("交易").value(tTranMapper.selectTranCountTotal()).build();
        NameValue chengjiao = NameValue.builder().name("成交").value(tTranMapper.selectTranCountSeccessTotal()).build();

        nameValues.add(xiaosuo);
        nameValues.add(kehu);
        nameValues.add(jiaoyi);
        nameValues.add(chengjiao);
        return nameValues;
    }

    public List<NameValue> loadClueSourcePic() {
        ArrayList<NameValue> nameValues = new ArrayList<>();
        return tCustomerMapper.selectSourceGroup();
    }
}