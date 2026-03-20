package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Jingsaileixing;
import com.example.vo.JingsaileixingVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface JingsaileixingService extends IService<Jingsaileixing> {
    Jingsaileixing add(Jingsaileixing jingsaileixing);
    void delete(Long id);
    void update(Jingsaileixing jingsaileixing);
    Jingsaileixing findById(Integer id);
    JingsaileixingVo findPage(JingsaileixingVo jingsaileixingVo);
	
    void deleteList(List<Jingsaileixing> list);
	
	
	List<Map<String, Object>> daochuexcel();
	//youxxianxci
}
