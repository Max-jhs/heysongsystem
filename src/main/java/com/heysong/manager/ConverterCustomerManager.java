package com.heysong.manager;

import com.heysong.domain.TClue;
import com.heysong.domain.TCustomer;
import com.heysong.mapper.TClueMapper;
import com.heysong.mapper.TCustomerMapper;
import com.heysong.util.JWTUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: 8912
 * @Date: 2025/4/7 23:35
 * @Version: v1.0.0
 * @Description:
 **/
@Component
public class ConverterCustomerManager {
    @Resource
    private TCustomerMapper tCustomerMapper;
    @Resource
    private TClueMapper tClueMapper;
    public int converterToCustomer(TCustomer tCustomer, String token) {
        //如果线索的状态是否为-1  == 该线索已转客户   ==-1 就不能再转了
        TClue tClue = tClueMapper.selectByPrimaryKey(tCustomer.getId());
        if (tClue != null && tClue.getState() == -1) {
            throw new RuntimeException("该线索已经被使用，转成用户了！");
        }
        // 正常使用
        Integer id = JWTUtils.parseTokenUserId(token);
        tCustomer.setCreateBy(id);
        tCustomer.setEditBy(id);
        tCustomer.setCreateTime(new Date());
        tCustomer.setEditTime(new Date());
        int inserted = tCustomerMapper.insert(tCustomer);
        //修改线索状态值
        tClue.setState(-1);
        tClueMapper.updateByPrimaryKeySelective(tClue);
        return inserted;
    }
}