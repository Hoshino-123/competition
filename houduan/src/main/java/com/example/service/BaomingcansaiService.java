package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Baomingcansai;
import com.example.vo.BaomingcansaiVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BaomingcansaiService extends IService<Baomingcansai> {
    Baomingcansai add(Baomingcansai baomingcansai);
    void delete(Long id);
    void update(Baomingcansai baomingcansai);
    Baomingcansai findById(Integer id);
    BaomingcansaiVo findPage(BaomingcansaiVo baomingcansaiVo);
	
    void deleteList(List<Baomingcansai> list);
	
	
	List<Map<String, Object>> daochuexcel();
	public Integer hsggetxianci(String jingsaibianhao,String tuanduibianhao);
}
