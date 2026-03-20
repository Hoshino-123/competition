package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("jingsaixinxi")
@Data
public class Jingsaixinxi {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	
	@TableField(value = "jingsaibianhao")	private String jingsaibianhao;	@TableField(value = "jingsaimingcheng")	private String jingsaimingcheng;	@TableField(value = "jingsaileixing")	private String jingsaileixing;	@TableField(value = "jingsaididian")	private String jingsaididian;	@TableField(value = "jingsaishijian")	private String jingsaishijian;	@TableField(value = "jingsaineirong")	private String jingsaineirong;	@TableField(value = "jingsaifengmian")	private String jingsaifengmian;	@TableField(value = "cansairenshu")	private String cansairenshu;	@TableField(value = "zhidaolaoshi")	private String zhidaolaoshi;	
    
	
	
    @TableField(value = "addtime")
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getJingsaibianhao() {
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
    }	public String getJingsaishijian() {
        return jingsaishijian;
    }
    public void setJingsaishijian(String jingsaishijian) {
		this.jingsaishijian = jingsaishijian;
    }	public String getJingsaineirong() {
        return jingsaineirong;
    }
    public void setJingsaineirong(String jingsaineirong) {
		this.jingsaineirong = jingsaineirong;
    }	public String getJingsaifengmian() {
        return jingsaifengmian;
    }
    public void setJingsaifengmian(String jingsaifengmian) {
		this.jingsaifengmian = jingsaifengmian;
    }	public String getCansairenshu() {
        return cansairenshu;
    }
    public void setCansairenshu(String cansairenshu) {
		this.cansairenshu = cansairenshu;
    }	public String getZhidaolaoshi() {
        return zhidaolaoshi;
    }
    public void setZhidaolaoshi(String zhidaolaoshi) {
		this.zhidaolaoshi = zhidaolaoshi;
    }	
	

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtme(Date addtime) {
        this.addtime = addtime;
    }
}