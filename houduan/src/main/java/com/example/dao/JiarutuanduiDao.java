package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Jiarutuandui;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface JiarutuanduiDao extends BaseMapper<Jiarutuandui> {

@Select("select * from jiarutuandui")
List<Map<String, Object>> daochuexcel();

@Select("select count(id) from jiarutuandui where 1=1   and tuanduibianhao = #{tuanduibianhao} and xuesheng = #{xuesheng}")
    Integer hsggetxianci(String tuanduibianhao,String xuesheng);

//tonxgji1

}
