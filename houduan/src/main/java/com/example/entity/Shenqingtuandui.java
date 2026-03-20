package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("shenqingtuandui")
@Data
public class Shenqingtuandui {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	
	@TableField(value = "tuanduibianhao")	private String tuanduibianhao;	@TableField(value = "tuanduimingcheng")	private String tuanduimingcheng;	@TableField(value = "tuanduileixing")	private String tuanduileixing;	@TableField(value = "tuanduijieshao")	private String tuanduijieshao;	@TableField(value = "tuanduidizhi")	private String tuanduidizhi;	@TableField(value = "xuehao")	private String xuehao;	@TableField(value = "xuexiaoxinxi")	private String xuexiaoxinxi;	@TableField(value = "lianxidianhua")	private String lianxidianhua;	@TableField(value = "issh")	private String issh;	@TableField(value = "shhf")	private String shhf;	
    
	
	
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
    }	public String getTuanduijieshao() {
        return tuanduijieshao;
    }
    public void setTuanduijieshao(String tuanduijieshao) {
		this.tuanduijieshao = tuanduijieshao;
    }	public String getTuanduidizhi() {
        return tuanduidizhi;
    }
    public void setTuanduidizhi(String tuanduidizhi) {
		this.tuanduidizhi = tuanduidizhi;
    }	public String getXuehao() {
        return xuehao;
    }
    public void setXuehao(String xuehao) {
		this.xuehao = xuehao;
    }	public String getXuexiaoxinxi() {
        return xuexiaoxinxi;
    }
    public void setXuexiaoxinxi(String xuexiaoxinxi) {
		this.xuexiaoxinxi = xuexiaoxinxi;
    }	public String getLianxidianhua() {
        return lianxidianhua;
    }
    public void setLianxidianhua(String lianxidianhua) {
		this.lianxidianhua = lianxidianhua;
    }	
	

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtme(Date addtime) {
        this.addtime = addtime;
    }
}