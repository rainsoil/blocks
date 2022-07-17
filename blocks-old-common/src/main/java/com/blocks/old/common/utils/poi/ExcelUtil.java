package com.blocks.old.common.utils.poi;

import com.alibaba.excel.EasyExcel;
import com.blocks.common.data.page.AjaxResult;
import com.blocks.old.common.config.RuoYiConfig;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Excel相关处理
 *
 * @author ruoyi
 */
public class ExcelUtil<T> {


    /**
     * 实体对象
     */
    public Class<T> clazz;

    public ExcelUtil(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 编码文件名
     */
    public String encodingFilename(String filename) {
        filename = UUID.randomUUID().toString() + "_" + filename + ".xlsx";
        return filename;
    }

/*************easyExcel 导入导出*****************/
    /**
     * 对excel表单默认第一个索引名转换成list（EasyExcel）
     *
     * @param is 输入流
     * @return 转换后集合
     */
    public List<T> importEasyExcel(InputStream is) throws Exception {
        return EasyExcel.read(is).head(clazz).sheet().doReadSync();
    }

    /**
     * 获取下载路径
     *
     * @param filename 文件名称
     */
    public String getAbsoluteFile(String filename) {
        String downloadPath = RuoYiConfig.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        return downloadPath;
    }

    /**
     * 对list数据源将其里面的数据导入到excel表单（EasyExcel）
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @return 结果
     */
    public AjaxResult exportEasyExcel(List<T> list, String sheetName) {
        String filename = encodingFilename(sheetName);
        EasyExcel.write(getAbsoluteFile(filename), clazz).sheet(sheetName).doWrite(list);
        return AjaxResult.success(filename);
    }

    /**
     * 导出模板
     *
     * @param sheetName 工作表的名称
     * @return com.blocks.old.common.core.domain.AjaxResult
     * @since 2022/7/17
     */
    public AjaxResult importTemplateExcel(String sheetName) {
        String filename = encodingFilename(sheetName);
        EasyExcel.write(getAbsoluteFile(filename), clazz).sheet(sheetName).doWrite(new ArrayList<>());
        return AjaxResult.success(filename);
    }
}
