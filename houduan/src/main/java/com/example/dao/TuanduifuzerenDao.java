package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Tuanduifuzeren;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TuanduifuzerenDao extends BaseMapper<Tuanduifuzeren> {

@Select("select * from tuanduifuzeren")
List<Map<String, Object>> daochuexcel();

//youxxianxci

//tonxgji1

}
