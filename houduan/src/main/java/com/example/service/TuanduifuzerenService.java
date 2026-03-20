package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Tuanduifuzeren;
import com.example.vo.TuanduifuzerenVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface TuanduifuzerenService extends IService<Tuanduifuzeren> {
    Tuanduifuzeren add(Tuanduifuzeren tuanduifuzeren);
    void delete(Long id);
    void update(Tuanduifuzeren tuanduifuzeren);
    Tuanduifuzeren findById(Integer id);
    TuanduifuzerenVo findPage(TuanduifuzerenVo tuanduifuzerenVo);
	Tuanduifuzeren login(Tuanduifuzeren tuanduifuzeren);
    void deleteList(List<Tuanduifuzeren> list);
	
	
	List<Map<String, Object>> daochuexcel();
	//youxxianxci
}
