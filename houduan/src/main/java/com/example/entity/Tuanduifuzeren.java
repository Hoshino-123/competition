package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("tuanduifuzeren")
@Data
public class Tuanduifuzeren {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	
	@TableField(value = "tuanduibianhao")	private String tuanduibianhao;	@TableField(value = "mima")	private String mima;	@TableField(value = "tuanduimingcheng")	private String tuanduimingcheng;	@TableField(value = "tuanduileixing")	private String tuanduileixing;	@TableField(value = "tuanduidizhi")	private String tuanduidizhi;	@TableField(value = "lianxidianhua")	private String lianxidianhua;	@TableField(value = "xuexiaoxinxi")	private String xuexiaoxinxi;	@TableField(value = "tuanduirenshu")	private String tuanduirenshu;	@TableField(value = "duiwei")	private String duiwei;	@TableField(exist = false)	private String newPassword;	
    
	
	
    @TableField(value = "addtime")
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getTuanduibianhao() {
        return tuanduibianhao;
    }
    public void setTuanduibianhao(String tuanduibianhao) {
		this.tuanduibianhao = tuanduibianhao;
    }	public String getMima() {
        return mima;
    }
    public void setMima(String mima) {
		this.mima = mima;
    }	public String getTuanduimingcheng() {
        return tuanduimingcheng;
    }
    public void setTuanduimingcheng(String tuanduimingcheng) {
		this.tuanduimingcheng = tuanduimingcheng;
    }	public String getTuanduileixing() {
        return tuanduileixing;
    }
    public void setTuanduileixing(String tuanduileixing) {
		this.tuanduileixing = tuanduileixing;
    }	public String getTuanduidizhi() {
        return tuanduidizhi;
    }
    public void setTuanduidizhi(String tuanduidizhi) {
		this.tuanduidizhi = tuanduidizhi;
    }	public String getLianxidianhua() {
        return lianxidianhua;
    }
    public void setLianxidianhua(String lianxidianhua) {
		this.lianxidianhua = lianxidianhua;
    }	public String getXuexiaoxinxi() {
        return xuexiaoxinxi;
    }
    public void setXuexiaoxinxi(String xuexiaoxinxi) {
		this.xuexiaoxinxi = xuexiaoxinxi;
    }	public String getTuanduirenshu() {
        return tuanduirenshu;
    }
    public void setTuanduirenshu(String tuanduirenshu) {
		this.tuanduirenshu = tuanduirenshu;
    }	public String getDuiwei() {
        return duiwei;
    }
    public void setDuiwei(String duiwei) {
		this.duiwei = duiwei;
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