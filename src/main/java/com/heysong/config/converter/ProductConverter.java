package com.heysong.config.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.heysong.HeysongApplication;
import com.heysong.domain.TDicValue;
import com.heysong.domain.TProduct;

import java.util.Collection;
import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/4/6 1:07
 * @Version: v1.0.0
 * @Description:
 **/
public class ProductConverter implements Converter<Integer> {
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

        String stringValue = cellData.getStringValue();
        List<TProduct> productMapping = HeysongApplication.ProductMapping;
        for (TProduct tProduct : productMapping) {
            if (cellData.getStringValue().equals(tProduct.getName())) {
                return tProduct.getId();
            }
        }
        //表格随便填名称的话 匹配不到 返会null  插入数据时没传值  数据库中可以插入null 表示摸棱两可 因为有外键约束  这里如果瞎填我规定就是海鸥的id了
        return 1;
    }

    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        List<TProduct> productMapping = HeysongApplication.ProductMapping;
        for (TProduct tProduct : productMapping) {
            if (value.equals(tProduct.getId())) {
                return new WriteCellData<>(tProduct.getName());
            }
        }
        return new WriteCellData<>("无此产品");
    }
}