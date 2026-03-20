package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Shenqingtuandui;
import com.example.vo.ShenqingtuanduiVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ShenqingtuanduiService extends IService<Shenqingtuandui> {
    Shenqingtuandui add(Shenqingtuandui shenqingtuandui);
    void delete(Long id);
    void update(Shenqingtuandui shenqingtuandui);
    Shenqingtuandui findById(Integer id);
    ShenqingtuanduiVo findPage(ShenqingtuanduiVo shenqingtuanduiVo);
	
    void deleteList(List<Shenqingtuandui> list);
	
	
	List<Map<String, Object>> daochuexcel();
	public Integer hsggetxianci(String xuehao);
}
