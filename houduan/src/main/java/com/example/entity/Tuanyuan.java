package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@TableName("tuanyuan")
@Data
public class Tuanyuan {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	
	@TableField(value = "shenqingbianhao")	private String shenqingbianhao;	@TableField(value = "tuanduibianhao")	private String tuanduibianhao;	@TableField(value = "tuanduimingcheng")	private String tuanduimingcheng;	@TableField(value = "tuanduileixing")	private String tuanduileixing;	@TableField(value = "xuesheng")	private String xuesheng;	
    
	
	
    @TableField(value = "addtime")
    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getShenqingbianhao() {
        return shenqingbianhao;
    }
    public void setShenqingbianhao(String shenqingbianhao) {
		this.shenqingbianhao = shenqingbianhao;
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
    }	public String getTuanduileixing() {
        return tuanduileixing;
    }
    public void setTuanduileixing(String tuanduileixing) {
		this.tuanduileixing = tuanduileixing;
    }	public String getXuesheng() {
        return xuesheng;
    }
    public void setXuesheng(String xuesheng) {
		this.xuesheng = xuesheng;
    }	
	

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtme(Date addtime) {
        this.addtime = addtime;
    }
}