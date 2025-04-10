package com.heysong.config.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.heysong.HeysongApplication;
import com.heysong.domain.TDicValue;
import com.heysong.mapper.TClueMapper;
import com.heysong.model.vo.TClueVo;
import jakarta.annotation.Resource;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/4/5 17:32
 * @Version: v1.0.0
 * @Description:
 **/
public class FieldConverter implements Converter<Integer> {
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
        // map:   appellation : {'先生 1','女士 2'，···}
        String stringValue = cellData.getStringValue();
        Collection<List<TDicValue>> values = HeysongApplication.PropertyMapping.values();
        Integer result = null;
        for (List<TDicValue> list : values) {
            for (TDicValue dicValue : list) {
                if (stringValue.equals(dicValue.getTypeValue())) {
                    result = dicValue.getId();
                    break;
                }
            }
            if (result != null) {
                return result;
                // break;
            }
        }
        return result;
    }

    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        Collection<List<TDicValue>> values = HeysongApplication.PropertyMapping.values();
        for (List<TDicValue> tDicValues : values) {
            for (TDicValue tDicValue : tDicValues) {
                if (tDicValue.getId().equals(value)) {
                    return new WriteCellData<>(tDicValue.getTypeValue());
                }
            }
        }
        return new WriteCellData<>("无");
    }
}