package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Tuanyuan;
import com.example.vo.TuanyuanVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface TuanyuanService extends IService<Tuanyuan> {
    Tuanyuan add(Tuanyuan tuanyuan);
    void delete(Long id);
    void update(Tuanyuan tuanyuan);
    Tuanyuan findById(Integer id);
    TuanyuanVo findPage(TuanyuanVo tuanyuanVo);
	
    void deleteList(List<Tuanyuan> list);
	
	
	List<Map<String, Object>> daochuexcel();
	//youxxianxci
}
