package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Daxuesheng;
import com.example.vo.DaxueshengVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface DaxueshengService extends IService<Daxuesheng> {
    Daxuesheng add(Daxuesheng daxuesheng);
    void delete(Long id);
    void update(Daxuesheng daxuesheng);
    Daxuesheng findById(Integer id);
    DaxueshengVo findPage(DaxueshengVo daxueshengVo);
	Daxuesheng login(Daxuesheng daxuesheng);
    void deleteList(List<Daxuesheng> list);


    void findyonghuyonghuming(Daxuesheng daxuesheng);

    List<Map<String, Object>> daochuexcel();
	//youxxianxci
}
