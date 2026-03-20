package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Jingsaixinxi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface JingsaixinxiDao extends BaseMapper<Jingsaixinxi> {

@Select("select * from jingsaixinxi")
List<Map<String, Object>> daochuexcel();

//youxxianxci

//tonxgji1

    @Select("SELECT distinct(jingsaimingcheng) as aa,sum(cansairenshu) as bb FROM jingsaixinxi group by cansairenshu order by id")
    List<Map<String, Object>> jingsaixinxi_tj_cansairenshu();
}
