package com.blocks.old.web.controller.demo.domain;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.blocks.common.data.easyexcel.annotation.DictStrFormat;
import com.blocks.common.data.easyexcel.conver.DictStrFormatConverter;
import com.blocks.old.common.core.domain.BaseEntity;
import com.blocks.old.common.utils.DateUtils;

import java.util.Date;
@ExcelIgnoreUnannotated
public class UserOperateModel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private int userId;

    @ExcelProperty(value = "用户编号")
    private String userCode;

    @ExcelProperty(value = "用户姓名")
    private String userName;

    @DictStrFormat(value = "0=男,1=女,2=未知" )
    @ExcelProperty(value = "用户性别",converter = DictStrFormatConverter.class)
    private String userSex;

    @ExcelProperty(value = "用户手机")
    private String userPhone;

    @ExcelProperty(value = "用户邮箱")
    private String userEmail;

    @ExcelProperty(value = "用户余额")
    private double userBalance;

    @DictStrFormat(value = "0=正常,1=停用")
    @ExcelProperty(value = "用户状态",converter = DictStrFormatConverter.class)
    private String status;

    @DateTimeFormat(value = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    public UserOperateModel()
    {

    }

    public UserOperateModel(int userId, String userCode, String userName, String userSex, String userPhone,
            String userEmail, double userBalance, String status)
    {
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.userSex = userSex;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userBalance = userBalance;
        this.status = status;
        this.createTime = DateUtils.getNowDate();
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUserCode()
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserSex()
    {
        return userSex;
    }

    public void setUserSex(String userSex)
    {
        this.userSex = userSex;
    }

    public String getUserPhone()
    {
        return userPhone;
    }

    public void setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public double getUserBalance()
    {
        return userBalance;
    }

    public void setUserBalance(double userBalance)
    {
        this.userBalance = userBalance;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public Date getCreateTime()
    {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
}