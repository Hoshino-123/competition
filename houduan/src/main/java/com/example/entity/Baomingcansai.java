package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("baomingcansai")
@Data
public class Baomingcansai {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	
	@TableField(value = "baomingbianhao")	private String baomingbianhao;	@TableField(value = "jingsaibianhao")	private String jingsaibianhao;	@TableField(value = "jingsaimingcheng")	private String jingsaimingcheng;	@TableField(value = "jingsaileixing")	private String jingsaileixing;	@TableField(value = "jingsaididian")	private String jingsaididian;	@TableField(value = "zhidaolaoshi")	private String zhidaolaoshi;	@TableField(value = "tuanduibianhao")	private String tuanduibianhao;	@TableField(value = "tuanduimingcheng")	private String tuanduimingcheng;	@TableField(value = "tuanduirenshu")	private String tuanduirenshu;	@TableField(value = "xuexiaoxinxi")	private String xuexiaoxinxi;	@TableField(value = "zuopinmingcheng")	private String zuopinmingcheng;	@TableField(value = "cansaizuopin")	private String cansaizuopin;	@TableField(value = "issh")	private String issh;	@TableField(value = "shhf")	private String shhf;	
    
	
	
    @TableField(value = "addtime")
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getBaomingbianhao() {
        return baomingbianhao;
    }
    public void setBaomingbianhao(String baomingbianhao) {
		this.baomingbianhao = baomingbianhao;
    }	public String getJingsaibianhao() {
        return jingsaibianhao;
    }
    public void setJingsaibianhao(String jingsaibianhao) {
		this.jingsaibianhao = jingsaibianhao;
    }	public String getJingsaimingcheng() {
        return jingsaimingcheng;
    }
    public void setJingsaimingcheng(String jingsaimingcheng) {
		this.jingsaimingcheng = jingsaimingcheng;
    }	public String getJingsaileixing() {
        return jingsaileixing;
    }
    public void setJingsaileixing(String jingsaileixing) {
		this.jingsaileixing = jingsaileixing;
    }	public String getJingsaididian() {
        return jingsaididian;
    }
    public void setJingsaididian(String jingsaididian) {
		this.jingsaididian = jingsaididian;
    }	public String getZhidaolaoshi() {
        return zhidaolaoshi;
    }
    public void setZhidaolaoshi(String zhidaolaoshi) {
		this.zhidaolaoshi = zhidaolaoshi;
    }	public String getTuanduibianhao() {
        return tuanduibianhao;
    }
    public void setTuanduibianhao(String tuanduibianhao) {
		this.tuanduibianhao = tuanduibianhao;
    }	public String getTuanduimingcheng() {
        return tuanduimingcheng;
    }
    public void setTuanduimingcheng(String tuanduimingcheng) {
		this.tuanduimingcheng = tuanduimingcheng;
    }	public String getTuanduirenshu() {
        return tuanduirenshu;
    }
    public void setTuanduirenshu(String tuanduirenshu) {
		this.tuanduirenshu = tuanduirenshu;
    }	public String getXuexiaoxinxi() {
        return xuexiaoxinxi;
    }
    public void setXuexiaoxinxi(String xuexiaoxinxi) {
		this.xuexiaoxinxi = xuexiaoxinxi;
    }	public String getZuopinmingcheng() {
        return zuopinmingcheng;
    }
    public void setZuopinmingcheng(String zuopinmingcheng) {
		this.zuopinmingcheng = zuopinmingcheng;
    }	public String getCansaizuopin() {
        return cansaizuopin;
    }
    public void setCansaizuopin(String cansaizuopin) {
		this.cansaizuopin = cansaizuopin;
    }	
	

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtme(Date addtime) {
        this.addtime = addtime;
    }
}