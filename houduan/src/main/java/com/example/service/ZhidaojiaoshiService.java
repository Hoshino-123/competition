package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Zhidaojiaoshi;
import com.example.vo.ZhidaojiaoshiVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ZhidaojiaoshiService extends IService<Zhidaojiaoshi> {
    Zhidaojiaoshi add(Zhidaojiaoshi zhidaojiaoshi);
    void delete(Long id);
    void update(Zhidaojiaoshi zhidaojiaoshi);
    Zhidaojiaoshi findById(Integer id);
    ZhidaojiaoshiVo findPage(ZhidaojiaoshiVo zhidaojiaoshiVo);
	Zhidaojiaoshi login(Zhidaojiaoshi zhidaojiaoshi);
    void deleteList(List<Zhidaojiaoshi> list);
	
	
	List<Map<String, Object>> daochuexcel();
	//youxxianxci
}
