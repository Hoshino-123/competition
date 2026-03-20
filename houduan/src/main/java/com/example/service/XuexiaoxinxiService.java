package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Xuexiaoxinxi;
import com.example.vo.XuexiaoxinxiVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface XuexiaoxinxiService extends IService<Xuexiaoxinxi> {
    Xuexiaoxinxi add(Xuexiaoxinxi xuexiaoxinxi);
    void delete(Long id);
    void update(Xuexiaoxinxi xuexiaoxinxi);
    Xuexiaoxinxi findById(Integer id);
    XuexiaoxinxiVo findPage(XuexiaoxinxiVo xuexiaoxinxiVo);
	
    void deleteList(List<Xuexiaoxinxi> list);
	
	
	List<Map<String, Object>> daochuexcel();
	//youxxianxci
}
