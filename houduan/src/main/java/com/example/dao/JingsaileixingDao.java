package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Jingsaileixing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface JingsaileixingDao extends BaseMapper<Jingsaileixing> {

@Select("select * from jingsaileixing")
List<Map<String, Object>> daochuexcel();

//youxxianxci

//tonxgji1

}
