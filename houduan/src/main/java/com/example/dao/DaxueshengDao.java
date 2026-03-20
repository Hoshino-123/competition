package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Daxuesheng;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DaxueshengDao extends BaseMapper<Daxuesheng> {

@Select("select * from daxuesheng")
List<Map<String, Object>> daochuexcel();

//youxxianxci

//tonxgji1

}
