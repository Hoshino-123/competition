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
import com.example.entity.Jingsaixinxi;
import com.example.exception.CustomException;
import com.example.service.JingsaixinxiService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.JingsaixinxiVo;
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
@RequestMapping(value = "/jingsaixinxi")
public class JingsaixinxiController {

    @Resource
    private JingsaixinxiService jingsaixinxiService;

    @PostMapping
    public Result<Jingsaixinxi> add(@RequestBody JingsaixinxiVo jingsaixinxi) {
		//yoxuxiaxnci
        jingsaixinxiService.add(jingsaixinxi);
        return Result.success(jingsaixinxi);
    }

    @GetMapping("/jingsaixinxi_tj_cansairenshu")
    public Result jingsaixinxi_tj_cansairenshu() {
        return Result.success(jingsaixinxiService.jingsaixinxi_tj_cansairenshu());
    }

    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("jingsaibianhao","竞赛编号");
        row.put("jingsaimingcheng","竞赛名称");
        row.put("jingsaileixing","竞赛类型");
        row.put("jingsaididian","竞赛地点");
        row.put("jingsaishijian","竞赛时间");
        row.put("jingsaineirong","竞赛内容");
        row.put("cansairenshu","参赛人数");
        row.put("zhidaolaoshi","指导老师");
        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = jingsaixinxiService.daochuexcel();
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
    public Result<Jingsaixinxi> deleteList(@RequestBody JingsaixinxiVo jingsaixinxi) {
        jingsaixinxiService.deleteList(jingsaixinxi.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        jingsaixinxiService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody JingsaixinxiVo jingsaixinxi) {
        jingsaixinxiService.update(jingsaixinxi);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Jingsaixinxi> detail(@PathVariable Integer id) {
        Jingsaixinxi jingsaixinxi = jingsaixinxiService.findById(id);
        return Result.success(jingsaixinxi);
    }

    @GetMapping
    public Result<List<Jingsaixinxi>> all() {
        return Result.success(jingsaixinxiService.list());
    }

    @PostMapping("/page")
    public Result<JingsaixinxiVo> page(@RequestBody JingsaixinxiVo jingsaixinxiVo) {
        return Result.success(jingsaixinxiService.findPage(jingsaixinxiVo));
    }
	
	@PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<Jingsaixinxi> jingsaixinxis = reader.readAll(Jingsaixinxi.class);
		
        jingsaixinxiService.saveBatch(jingsaixinxis);
        return Result.success();
    }
	
	//youupdt2login
}
