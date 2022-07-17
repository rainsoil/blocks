package com.blocks.common.data.easyexcel.conver;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.blocks.common.data.dict.exception.DictException;
import com.blocks.common.data.easyexcel.DictMessageCode;
import com.blocks.common.data.easyexcel.annotation.DictStrFormat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 字典值转换器
 *
 * @author luyanan
 * @since 2022/7/14
 **/
public class DictStrFormatConverter implements Converter<String> {


    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String cellDataStr = cellData.getStringValue();
        DictStrFormat dictStrFormat = contentProperty.getField().getAnnotation(DictStrFormat.class);
        if (null != dictStrFormat) {
            return dictStrFormatConvertToJavaData(cellDataStr, dictStrFormat);
        }
        return cellDataStr;
    }


    @Override
    public WriteCellData<?> convertToExcelData(String value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {

        DictStrFormat dictStrFormat = contentProperty.getField().getAnnotation(DictStrFormat.class);
        if (null != dictStrFormat) {
            return dictStrFormatConvertToExcelData(value, dictStrFormat);
        }
        return new WriteCellData<>(value);
    }


    /**
     * 字典项之间的分隔符
     *
     * @since 2022/7/17
     */

    private static final String DICT_ITEM_SEPARATOR = ",";

    /**
     * 字典value与label的分隔符
     *
     * @since 2022/7/17
     */

    private static final String KEY_VALUE_SEPARATOR = "=";

    /**
     * excel的值转换为java类
     *
     * @param cellDataStr   excel的值
     * @param dictStrFormat 注解
     * @return java.lang.String
     * @since 2022/7/16
     */
    private String dictStrFormatConvertToJavaData(String cellDataStr, DictStrFormat dictStrFormat) {
        String value = dictStrFormat.value();
        Map<String, String> map = new HashMap<>();
        Arrays.stream(value.split(DICT_ITEM_SEPARATOR)).forEach(a -> {

            String[] split = a.split(KEY_VALUE_SEPARATOR);
            if (split.length != 2) {
                throw new DictException(DictMessageCode.DICTSTRFORMAT_VALUE_INCORRECT_FORMAT, a);
            }
            map.put(split[1], split[0]);
        });
        return map.getOrDefault(cellDataStr, cellDataStr);
    }


    /**
     * java的值转换为excel
     *
     * @param javaValue     javal的值
     * @param dictStrFormat 注解
     * @return java.lang.String
     * @since 2022/7/17
     */
    private WriteCellData dictStrFormatConvertToExcelData(String javaValue, DictStrFormat dictStrFormat) {
        String value = dictStrFormat.value();
        Map<String, String> map = new HashMap<>();
        Arrays.stream(value.split(DICT_ITEM_SEPARATOR)).forEach(a -> {

            String[] split = a.split(KEY_VALUE_SEPARATOR);
            if (split.length != 2) {
                throw new DictException(DictMessageCode.DICTSTRFORMAT_VALUE_INCORRECT_FORMAT, a);
            }
            map.put(split[0], split[1]);
        });
        return new WriteCellData(map.getOrDefault(javaValue, javaValue));
    }


}
