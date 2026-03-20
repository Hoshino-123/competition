package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Shenqingtuandui;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShenqingtuanduiDao extends BaseMapper<Shenqingtuandui> {

@Select("select * from shenqingtuandui")
List<Map<String, Object>> daochuexcel();

@Select("select count(id) from shenqingtuandui where 1=1   and xuehao = #{xuehao}")
    Integer hsggetxianci(String xuehao);

//tonxgji1

}
