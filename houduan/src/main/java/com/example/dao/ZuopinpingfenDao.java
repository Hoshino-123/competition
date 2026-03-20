package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Zuopinpingfen;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ZuopinpingfenDao extends BaseMapper<Zuopinpingfen> {

@Select("select * from zuopinpingfen")
List<Map<String, Object>> daochuexcel();

//youxxianxci

@Select("SELECT distinct(jingsaileixing) as aa,count(id) as bb FROM zuopinpingfen group by jingsaileixing order by id")
List<Map<String, Object>> zuopinpingfen_tj_jingsaileixing();

@Select("SELECT distinct(xuexiaoxinxi) as aa,count(id) as bb FROM zuopinpingfen group by xuexiaoxinxi order by id")
List<Map<String, Object>> zuopinpingfen_tj_xuexiaoxinxi();

@Select("SELECT distinct(zuopinmingcheng) as aa,sum(zuopinfenshu) as bb FROM zuopinpingfen group by zuopinmingcheng order by id")
List<Map<String, Object>> zuopinpingfen_tj_zuopinmingcheng();

//tonxgji1

}
