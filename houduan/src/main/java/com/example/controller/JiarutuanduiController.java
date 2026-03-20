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
import com.example.entity.Jiarutuandui;
import com.example.exception.CustomException;
import com.example.service.JiarutuanduiService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.JiarutuanduiVo;
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
@RequestMapping(value = "/jiarutuandui")
public class JiarutuanduiController {

    @Resource
    private JiarutuanduiService jiarutuanduiService;

    @PostMapping
    public Result<Jiarutuandui> add(@RequestBody JiarutuanduiVo jiarutuandui) {
		Integer xiancishu = jiarutuanduiService.hsggetxianci(jiarutuandui.getTuanduibianhao(),jiarutuandui.getXuesheng());
        if(xiancishu>=1)
        {
            return Result.error("500","已满，请勿重复添加，或换其他条件重试！！！");
        }
        jiarutuanduiService.add(jiarutuandui);
        return Result.success(jiarutuandui);
    }
	
	
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("shenqingbianhao","申请编号");        row.put("tuanduibianhao","团队编号");        row.put("tuanduimingcheng","团队名称");        row.put("tuanduileixing","团队类型");        row.put("xuexiaoxinxi","学校信息");        row.put("xuesheng","学生");        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = jiarutuanduiService.daochuexcel();
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
    public Result<Jiarutuandui> deleteList(@RequestBody JiarutuanduiVo jiarutuandui) {
        jiarutuanduiService.deleteList(jiarutuandui.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        jiarutuanduiService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody JiarutuanduiVo jiarutuandui) {
        jiarutuanduiService.update(jiarutuandui);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Jiarutuandui> detail(@PathVariable Integer id) {
        Jiarutuandui jiarutuandui = jiarutuanduiService.findById(id);
        return Result.success(jiarutuandui);
    }

    @GetMapping
    public Result<List<Jiarutuandui>> all() {
        return Result.success(jiarutuanduiService.list());
    }

    @PostMapping("/page")
    public Result<JiarutuanduiVo> page(@RequestBody JiarutuanduiVo jiarutuanduiVo) {
        return Result.success(jiarutuanduiService.findPage(jiarutuanduiVo));
    }
	
	@PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<Jiarutuandui> jiarutuanduis = reader.readAll(Jiarutuandui.class);
		
        jiarutuanduiService.saveBatch(jiarutuanduis);
        return Result.success();
    }
	
	//youupdt2login
}
