package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("daxuesheng")
@Data
public class Daxuesheng {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	
	@TableField(value = "xuehao")	private String xuehao;	@TableField(value = "mima")	private String mima;	@TableField(value = "xingming")	private String xingming;	@TableField(value = "xingbie")	private Boolean xingbie;	@TableField(value = "xuexiaoxinxi")	private String xuexiaoxinxi;	@TableField(value = "zhuanye")	private String zhuanye;	@TableField(value = "lianxidianhua")	private String lianxidianhua;	@TableField(value = "zhaopian")	private String zhaopian;	@TableField(value = "tuanduirenshu")	private String tuanduirenshu;	@TableField(exist = false)	private String newPassword;	
    
	
	
    @TableField(value = "addtime")
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getXuehao() {
        return xuehao;
    }
    public void setXuehao(String xuehao) {
		this.xuehao = xuehao;
    }	public String getMima() {
        return mima;
    }
    public void setMima(String mima) {
		this.mima = mima;
    }	public String getXingming() {
        return xingming;
    }
    public void setXingming(String xingming) {
		this.xingming = xingming;
    }	public Boolean getXingbie() {
        return xingbie;
    }
    public void setXingbie(Boolean xingbie) {
		this.xingbie = xingbie;
    }	public String getXuexiaoxinxi() {
        return xuexiaoxinxi;
    }
    public void setXuexiaoxinxi(String xuexiaoxinxi) {
		this.xuexiaoxinxi = xuexiaoxinxi;
    }	public String getZhuanye() {
        return zhuanye;
    }
    public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
    }	public String getLianxidianhua() {
        return lianxidianhua;
    }
    public void setLianxidianhua(String lianxidianhua) {
		this.lianxidianhua = lianxidianhua;
    }	public String getZhaopian() {
        return zhaopian;
    }
    public void setZhaopian(String zhaopian) {
		this.zhaopian = zhaopian;
    }	public String getTuanduirenshu() {
        return tuanduirenshu;
    }
    public void setTuanduirenshu(String tuanduirenshu) {
		this.tuanduirenshu = tuanduirenshu;
    }	public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
    }	
	

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtme(Date addtime) {
        this.addtime = addtime;
    }
}