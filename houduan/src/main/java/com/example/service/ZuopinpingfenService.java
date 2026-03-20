package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Zuopinpingfen;
import com.example.vo.ZuopinpingfenVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ZuopinpingfenService extends IService<Zuopinpingfen> {
    Zuopinpingfen add(Zuopinpingfen zuopinpingfen);
    void delete(Long id);
    void update(Zuopinpingfen zuopinpingfen);
    Zuopinpingfen findById(Integer id);
    ZuopinpingfenVo findPage(ZuopinpingfenVo zuopinpingfenVo);
	
    void deleteList(List<Zuopinpingfen> list);
	List<Map<String,Object>> zuopinpingfen_tj_jingsaileixing();    List<Map<String,Object>> zuopinpingfen_tj_xuexiaoxinxi();    List<Map<String,Object>> zuopinpingfen_tj_zuopinmingcheng();    
	
	List<Map<String, Object>> daochuexcel();
	//youxxianxci
}
