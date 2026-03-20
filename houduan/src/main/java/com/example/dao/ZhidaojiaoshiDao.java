package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Zhidaojiaoshi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ZhidaojiaoshiDao extends BaseMapper<Zhidaojiaoshi> {

@Select("select * from zhidaojiaoshi")
List<Map<String, Object>> daochuexcel();

//youxxianxci

//tonxgji1

}
