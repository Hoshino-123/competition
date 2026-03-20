package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Baomingcansai;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaomingcansaiDao extends BaseMapper<Baomingcansai> {

@Select("select * from baomingcansai")
List<Map<String, Object>> daochuexcel();

@Select("select count(id) from baomingcansai where 1=1   and jingsaibianhao = #{jingsaibianhao} and tuanduibianhao = #{tuanduibianhao}")
    Integer hsggetxianci(String jingsaibianhao,String tuanduibianhao);

//tonxgji1

}
