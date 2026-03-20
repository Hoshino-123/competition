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
import com.example.entity.Zuopinpingfen;
import com.example.exception.CustomException;
import com.example.service.ZuopinpingfenService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.ZuopinpingfenVo;
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
@RequestMapping(value = "/zuopinpingfen")
public class ZuopinpingfenController {

    @Resource
    private ZuopinpingfenService zuopinpingfenService;

    @PostMapping
    public Result<Zuopinpingfen> add(@RequestBody ZuopinpingfenVo zuopinpingfen) {
		//yoxuxiaxnci
        zuopinpingfenService.add(zuopinpingfen);
        return Result.success(zuopinpingfen);
    }
	
	
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("baomingbianhao","报名编号");        row.put("jingsaibianhao","竞赛编号");        row.put("jingsaimingcheng","竞赛名称");        row.put("jingsaileixing","竞赛类型");        row.put("zhidaolaoshi","指导老师");        row.put("tuanduibianhao","团队编号");        row.put("tuanduimingcheng","团队名称");        row.put("tuanduirenshu","团队人数");        row.put("xuexiaoxinxi","学校信息");        row.put("zuopinmingcheng","作品名称");        row.put("zuopinfenshu","作品分数");        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = zuopinpingfenService.daochuexcel();
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
	
	@GetMapping("/zuopinpingfen_tj_jingsaileixing")    public Result zuopinpingfen_tj_jingsaileixing() {        return Result.success(zuopinpingfenService.zuopinpingfen_tj_jingsaileixing());    }    @GetMapping("/zuopinpingfen_tj_xuexiaoxinxi")    public Result zuopinpingfen_tj_xuexiaoxinxi() {        return Result.success(zuopinpingfenService.zuopinpingfen_tj_xuexiaoxinxi());    }    @GetMapping("/zuopinpingfen_tj_zuopinmingcheng")    public Result zuopinpingfen_tj_zuopinmingcheng() {        return Result.success(zuopinpingfenService.zuopinpingfen_tj_zuopinmingcheng());    }    

    @PostMapping("/deleteList")
    public Result<Zuopinpingfen> deleteList(@RequestBody ZuopinpingfenVo zuopinpingfen) {
        zuopinpingfenService.deleteList(zuopinpingfen.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        zuopinpingfenService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody ZuopinpingfenVo zuopinpingfen) {
        zuopinpingfenService.update(zuopinpingfen);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Zuopinpingfen> detail(@PathVariable Integer id) {
        Zuopinpingfen zuopinpingfen = zuopinpingfenService.findById(id);
        return Result.success(zuopinpingfen);
    }

    @GetMapping
    public Result<List<Zuopinpingfen>> all() {
        return Result.success(zuopinpingfenService.list());
    }

    @PostMapping("/page")
    public Result<ZuopinpingfenVo> page(@RequestBody ZuopinpingfenVo zuopinpingfenVo) {
        return Result.success(zuopinpingfenService.findPage(zuopinpingfenVo));
    }
	
	@PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<Zuopinpingfen> zuopinpingfens = reader.readAll(Zuopinpingfen.class);
		
        zuopinpingfenService.saveBatch(zuopinpingfens);
        return Result.success();
    }
	
	//youupdt2login
}
