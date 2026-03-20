package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Jiarutuandui;
import com.example.vo.JiarutuanduiVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface JiarutuanduiService extends IService<Jiarutuandui> {
    Jiarutuandui add(Jiarutuandui jiarutuandui);
    void delete(Long id);
    void update(Jiarutuandui jiarutuandui);
    Jiarutuandui findById(Integer id);
    JiarutuanduiVo findPage(JiarutuanduiVo jiarutuanduiVo);
	
    void deleteList(List<Jiarutuandui> list);
	
	
	List<Map<String, Object>> daochuexcel();
	public Integer hsggetxianci(String tuanduibianhao,String xuesheng);
}
