package com.heysong.config.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.heysong.HeysongApplication;
import com.heysong.domain.TActivity;
import com.heysong.domain.TProduct;

import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/4/6 1:53
 * @Version: v1.0.0
 * @Description:
 **/
public class ActivityConverter implements Converter<Integer> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return Converter.super.supportJavaTypeKey();
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return Converter.super.supportExcelTypeKey();
    }

    // excel 中的 字符串  转成   Java中的 integer
    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {


        List<TActivity> tActivityList = HeysongApplication.ActivityMapping;
        for (TActivity tActivity : tActivityList) {
            if (cellData.getStringValue().equals(tActivity.getName())) {
                return tActivity.getId();
            }
        }
        //表格随便填名称的话 匹配不到 返会null  插入数据时没传值  数据库中可以插入null 表示摸棱两可 因为有外键约束  这里如果瞎填我规定就是百度推广的id了
        // 当然excel中也可以给定可选项  不是瞎值  也不太现实人很随意 也不好是固定值
        return 1;
    }

    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        List<TActivity> tActivityList = HeysongApplication.ActivityMapping;
        for (TActivity tActivity : tActivityList) {
            if (value.equals(tActivity.getId())) {
                return new WriteCellData<>(tActivity.getName());
            }
        }
        return new WriteCellData<>("无此活动");
    }
}