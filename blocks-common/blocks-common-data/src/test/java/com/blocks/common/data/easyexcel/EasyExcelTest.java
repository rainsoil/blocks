package com.blocks.common.data.easyexcel;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * easyExcel测试类
 *
 * @author luyanan
 * @since 2022/7/17
 **/
public class EasyExcelTest {


    @Test
    public void write() {
        List<SysUserVo> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SysUserVo sysUserVo = new SysUserVo();
            sysUserVo.setName("测试-" + i);
            sysUserVo.setSex(RandomUtil.randomInt(2) + "");
            data.add(sysUserVo);
        }


        String filePath = "/tmp/测试.xlsx";
        FileUtil.del(filePath);
        EasyExcel.write(filePath, SysUserVo.class)
                .sheet().doWrite(data);

        EasyExcel.read(filePath, SysUserVo.class, new ReadListener<SysUserVo>() {


            @Override
            public void invoke(SysUserVo sysUserVo, AnalysisContext analysisContext) {
                System.out.println(sysUserVo);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
        FileUtil.del(filePath);
    }
}
