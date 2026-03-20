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
import com.example.entity.Zhidaojiaoshi;
import com.example.exception.CustomException;
import com.example.service.ZhidaojiaoshiService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.ZhidaojiaoshiVo;
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
@RequestMapping(value = "/zhidaojiaoshi")
public class ZhidaojiaoshiController {

    @Resource
    private ZhidaojiaoshiService zhidaojiaoshiService;

    @PostMapping
    public Result<Zhidaojiaoshi> add(@RequestBody ZhidaojiaoshiVo zhidaojiaoshi) {
		//yoxuxiaxnci
        zhidaojiaoshiService.add(zhidaojiaoshi);
        return Result.success(zhidaojiaoshi);
    }
	
	
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("gonghao","工号");        row.put("xingming","姓名");        row.put("xingbie","性别");        row.put("nianling","年龄");        row.put("shoujihaoma","手机号码");        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = zhidaojiaoshiService.daochuexcel();
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
    public Result<Zhidaojiaoshi> deleteList(@RequestBody ZhidaojiaoshiVo zhidaojiaoshi) {
        zhidaojiaoshiService.deleteList(zhidaojiaoshi.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        zhidaojiaoshiService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody ZhidaojiaoshiVo zhidaojiaoshi) {
        zhidaojiaoshiService.update(zhidaojiaoshi);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Zhidaojiaoshi> detail(@PathVariable Integer id) {
        Zhidaojiaoshi zhidaojiaoshi = zhidaojiaoshiService.findById(id);
        return Result.success(zhidaojiaoshi);
    }

    @GetMapping
    public Result<List<Zhidaojiaoshi>> all() {
        return Result.success(zhidaojiaoshiService.list());
    }

    @PostMapping("/page")
    public Result<ZhidaojiaoshiVo> page(@RequestBody ZhidaojiaoshiVo zhidaojiaoshiVo) {
        return Result.success(zhidaojiaoshiService.findPage(zhidaojiaoshiVo));
    }
	
	@PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<Zhidaojiaoshi> zhidaojiaoshis = reader.readAll(Zhidaojiaoshi.class);
		for (Zhidaojiaoshi zhidaojiaoshi : zhidaojiaoshis) { zhidaojiaoshi.setMima("dc5c7986daef50c1e02ab09b442ee34f");}
        zhidaojiaoshiService.saveBatch(zhidaojiaoshis);
        return Result.success();
    }
	
	    @PostMapping("/login")
    public Result login(@RequestBody Zhidaojiaoshi zhidaojiaoshi, HttpServletRequest request) {
        if (StrUtil.isBlank(zhidaojiaoshi.getGonghao()) || StrUtil.isBlank(zhidaojiaoshi.getMima())) {
            throw new CustomException(ResultCode.PARAM_LOST_ERROR);
        }
        Zhidaojiaoshi login = zhidaojiaoshiService.login(zhidaojiaoshi);
//        if(!login.getStatus()){
//            return Result.error("1001","状态限制，无法登录系统");
//        }
        if(login != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("user", login);
            Map<String, Object> map = MapWrapperUtils.builder(MapWrapperUtils.KEY_USER_ID,zhidaojiaoshi.getId());
            String token = JwtUtil.creatToken(map);
            hashMap.put("token", token);
            return Result.success(hashMap);
        }else {
            return Result.error();
        }
    }
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Zhidaojiaoshi info, HttpServletRequest request) {
        Zhidaojiaoshi zhidaojiaoshi = zhidaojiaoshiService.findById(info.getId());
        String oldPassword = SecureUtil.md5(info.getMima());
        if (!oldPassword.equals(zhidaojiaoshi.getMima())) {
            return Result.error(ResultCode.PARAM_PASSWORD_ERROR.code, ResultCode.PARAM_PASSWORD_ERROR.msg);
        }
        info.setMima(SecureUtil.md5(info.getNewPassword()));
        Zhidaojiaoshi zhidaojiaoshi1 = new Zhidaojiaoshi();
        BeanUtils.copyProperties(info, zhidaojiaoshi1);
        zhidaojiaoshiService.update(zhidaojiaoshi1);
        return Result.success();
    }
	@PostMapping("/register")
    public Result<Zhidaojiaoshi> register(@RequestBody ZhidaojiaoshiVo zhidaojiaoshi) {
        zhidaojiaoshiService.add(zhidaojiaoshi);
        return Result.success(zhidaojiaoshi);
    }
}
