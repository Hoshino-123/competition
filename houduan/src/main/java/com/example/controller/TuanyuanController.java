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
import com.example.entity.Tuanyuan;
import com.example.exception.CustomException;
import com.example.service.TuanyuanService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.TuanyuanVo;
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
@RequestMapping(value = "/tuanyuan")
public class TuanyuanController {

    @Resource
    private TuanyuanService tuanyuanService;

    @PostMapping
    public Result<Tuanyuan> add(@RequestBody TuanyuanVo tuanyuan) {
		//yoxuxiaxnci
        tuanyuanService.add(tuanyuan);
        return Result.success(tuanyuan);
    }
	
	
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("shenqingbianhao","申请编号");        row.put("tuanduibianhao","团队编号");        row.put("tuanduimingcheng","团队名称");        row.put("tuanduileixing","团队类型");        row.put("xuesheng","学生");        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = tuanyuanService.daochuexcel();
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
    public Result<Tuanyuan> deleteList(@RequestBody TuanyuanVo tuanyuan) {
        tuanyuanService.deleteList(tuanyuan.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        tuanyuanService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody TuanyuanVo tuanyuan) {
        tuanyuanService.update(tuanyuan);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Tuanyuan> detail(@PathVariable Integer id) {
        Tuanyuan tuanyuan = tuanyuanService.findById(id);
        return Result.success(tuanyuan);
    }

    @GetMapping
    public Result<List<Tuanyuan>> all() {
        return Result.success(tuanyuanService.list());
    }

    @PostMapping("/page")
    public Result<TuanyuanVo> page(@RequestBody TuanyuanVo tuanyuanVo) {
        return Result.success(tuanyuanService.findPage(tuanyuanVo));
    }
	
	@PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<Tuanyuan> tuanyuans = reader.readAll(Tuanyuan.class);
		
        tuanyuanService.saveBatch(tuanyuans);
        return Result.success();
    }
	
	//youupdt2login
}
