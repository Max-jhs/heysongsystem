package com.heysong.config.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.heysong.HeysongApplication;
import com.heysong.domain.TProduct;
import com.heysong.domain.TUser;
import org.apache.catalina.User;

import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/4/6 1:07
 * @Version: v1.0.0
 * @Description:
 **/
public class UserConverter implements Converter<Integer> {
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

        List<TUser> productMapping = HeysongApplication.UserMapping;
        for (TUser tUser : productMapping) {
            if (cellData.getStringValue().equals(tUser.getName())) {
                return tUser.getId();
            }
        }
        //表格随便填名称的话 匹配不到 返会null  插入数据时没传值  数据库中可以插入null 表示摸棱两可 因为有外键约束  这里如果瞎填我规定就是管理员的id了
        return 1;
    }

    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        List<TUser> userMapping = HeysongApplication.UserMapping;
        for (TUser tUser : userMapping) {
            if (value.equals(tUser.getId())) {
                return new WriteCellData<>(tUser.getName());
            }
        }
        return new WriteCellData<>("无此用户");
    }
}