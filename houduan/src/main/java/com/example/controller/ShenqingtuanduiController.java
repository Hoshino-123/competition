package com.example.controller;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.Shenqingtuandui;
import com.example.exception.CustomException;
import com.example.service.ShenqingtuanduiService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.ShenqingtuanduiVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import cn.hutool.poi.excel.ExcelReader;
import org.springframework.web.multipart.MultipartFile;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/shenqingtuandui")
public class ShenqingtuanduiController {

    @Resource
    private ShenqingtuanduiService shenqingtuanduiService;

    @PostMapping
    public Result<Shenqingtuandui> add(@RequestBody ShenqingtuanduiVo shenqingtuandui) {
		Integer xiancishu = shenqingtuanduiService.hsggetxianci(shenqingtuandui.getXuehao());
        if(xiancishu>=1)
        {
            return Result.error("500","已满，请勿重复添加，或换其他条件重试！！！");
        }
        shenqingtuanduiService.add(shenqingtuandui);
        return Result.success(shenqingtuandui);
    }
	
	
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("tuanduibianhao","团队编号");        row.put("tuanduimingcheng","团队名称");        row.put("tuanduileixing","团队类型");        row.put("tuanduijieshao","团队介绍");        row.put("tuanduidizhi","团队地址");        row.put("xuehao","学号");        row.put("xuexiaoxinxi","学校信息");        row.put("lianxidianhua","联系电话");        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = shenqingtuanduiService.daochuexcel();
        for (Map<String, Object> map : daochuexcellist) {
            list.add(map);
        }
        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=chaoba.xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }
	
	

    @PostMapping("/deleteList")
    public Result<Shenqingtuandui> deleteList(@RequestBody ShenqingtuanduiVo shenqingtuandui) {
        shenqingtuanduiService.deleteList(shenqingtuandui.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        shenqingtuanduiService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody ShenqingtuanduiVo shenqingtuandui) {
        shenqingtuanduiService.update(shenqingtuandui);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Shenqingtuandui> detail(@PathVariable Integer id) {
        Shenqingtuandui shenqingtuandui = shenqingtuanduiService.findById(id);
        return Result.success(shenqingtuandui);
    }

    @GetMapping
    public Result<List<Shenqingtuandui>> all() {
        return Result.success(shenqingtuanduiService.list());
    }

    @PostMapping("/page")
    public Result<ShenqingtuanduiVo> page(@RequestBody ShenqingtuanduiVo shenqingtuanduiVo) {
        return Result.success(shenqingtuanduiService.findPage(shenqingtuanduiVo));
    }
	
	@PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<Shenqingtuandui> shenqingtuanduis = reader.readAll(Shenqingtuandui.class);
		
        shenqingtuanduiService.saveBatch(shenqingtuanduis);
        return Result.success();
    }
	
	//youupdt2login
}
