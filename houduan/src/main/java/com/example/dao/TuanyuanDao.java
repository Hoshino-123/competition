package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Tuanyuan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TuanyuanDao extends BaseMapper<Tuanyuan> {

@Select("select * from tuanyuan")
List<Map<String, Object>> daochuexcel();

//youxxianxci

//tonxgji1

}
