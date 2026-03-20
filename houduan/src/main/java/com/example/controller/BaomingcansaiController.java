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
import com.example.entity.Baomingcansai;
import com.example.exception.CustomException;
import com.example.service.BaomingcansaiService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.BaomingcansaiVo;
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
@RequestMapping(value = "/baomingcansai")
public class BaomingcansaiController {

    @Resource
    private BaomingcansaiService baomingcansaiService;

    @PostMapping
    public Result<Baomingcansai> add(@RequestBody BaomingcansaiVo baomingcansai) {
		Integer xiancishu = baomingcansaiService.hsggetxianci(baomingcansai.getJingsaibianhao(),baomingcansai.getTuanduibianhao());
        if(xiancishu>=1)
        {
            return Result.error("500","已满，请勿重复添加，或换其他条件重试！！！");
        }
        baomingcansaiService.add(baomingcansai);
        return Result.success(baomingcansai);
    }
	
	
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("baomingbianhao","报名编号");        row.put("jingsaibianhao","竞赛编号");        row.put("jingsaimingcheng","竞赛名称");        row.put("jingsaileixing","竞赛类型");        row.put("jingsaididian","竞赛地点");        row.put("zhidaolaoshi","指导老师");        row.put("tuanduibianhao","团队编号");        row.put("tuanduimingcheng","团队名称");        row.put("tuanduirenshu","团队人数");        row.put("xuexiaoxinxi","学校信息");        row.put("zuopinmingcheng","作品名称");        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = baomingcansaiService.daochuexcel();
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
    public Result<Baomingcansai> deleteList(@RequestBody BaomingcansaiVo baomingcansai) {
        baomingcansaiService.deleteList(baomingcansai.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        baomingcansaiService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody BaomingcansaiVo baomingcansai) {
        baomingcansaiService.update(baomingcansai);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Baomingcansai> detail(@PathVariable Integer id) {
        Baomingcansai baomingcansai = baomingcansaiService.findById(id);
        return Result.success(baomingcansai);
    }

    @GetMapping
    public Result<List<Baomingcansai>> all() {
        return Result.success(baomingcansaiService.list());
    }

    @PostMapping("/page")
    public Result<BaomingcansaiVo> page(@RequestBody BaomingcansaiVo baomingcansaiVo) {
        return Result.success(baomingcansaiService.findPage(baomingcansaiVo));
    }
	
	@PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<Baomingcansai> baomingcansais = reader.readAll(Baomingcansai.class);
		
        baomingcansaiService.saveBatch(baomingcansais);
        return Result.success();
    }
	
	//youupdt2login
}
