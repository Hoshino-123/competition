package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Jingsaixinxi;
import com.example.vo.JingsaixinxiVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface JingsaixinxiService extends IService<Jingsaixinxi> {
    Jingsaixinxi add(Jingsaixinxi jingsaixinxi);
    void delete(Long id);
    void update(Jingsaixinxi jingsaixinxi);
    Jingsaixinxi findById(Integer id);
    JingsaixinxiVo findPage(JingsaixinxiVo jingsaixinxiVo);
	
//    void deleteList(List<Jingsaixinxi> list);


    void deleteList(List<Jingsaixinxi> list);
    List<Map<String,Object>> jingsaixinxi_tj_cansairenshu();


    Map<String, Double> recommend(Integer userId, Integer num);
    List<Jingsaixinxi> findByIds(List<Integer> ids);


    List<Map<String, Object>> daochuexcel();
	//youxxianxci
}
