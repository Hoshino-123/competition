package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Xuexiaoxinxi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface XuexiaoxinxiDao extends BaseMapper<Xuexiaoxinxi> {

@Select("select * from xuexiaoxinxi")
List<Map<String, Object>> daochuexcel();

//youxxianxci

//tonxgji1

}
