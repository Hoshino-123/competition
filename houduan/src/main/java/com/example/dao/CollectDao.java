package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Collect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CollectDao extends BaseMapper<Collect> {

    Collect findByUser(Collect collectInfo);

    List<Collect> findByEndUserId(Integer userId);

    @Delete("delete from collect where shangpinxinxiID = #{id} and user_id = #{userId}")
    void deleteByUserId(@Param("id") Integer id,@Param("userId") Integer userId);

    @Select("SELECT user_id, GROUP_CONCAT(shangpinxinxiID) AS items " +
            "FROM collect " +
            "GROUP BY user_id")
    List<Map<String, String>> getCollected();
}
