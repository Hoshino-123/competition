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
import com.example.entity.Daxuesheng;
import com.example.exception.CustomException;
import com.example.service.DaxueshengService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.DaxueshengVo;
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
@RequestMapping(value = "/daxuesheng")
public class DaxueshengController {

    @Resource
    private DaxueshengService daxueshengService;

    @PostMapping
    public Result<Daxuesheng> add(@RequestBody DaxueshengVo daxuesheng) {
		//yoxuxiaxnci
        daxueshengService.add(daxuesheng);
        return Result.success(daxuesheng);
    }
	
	
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("xuehao","学号");        row.put("xingming","姓名");        row.put("xingbie","性别");        row.put("xuexiaoxinxi","学校信息");        row.put("zhuanye","专业");        row.put("lianxidianhua","联系电话");        row.put("tuanduirenshu","团队人数");        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = daxueshengService.daochuexcel();
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
    public Result<Daxuesheng> deleteList(@RequestBody DaxueshengVo daxuesheng) {
        daxueshengService.deleteList(daxuesheng.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        daxueshengService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody DaxueshengVo daxuesheng) {
        daxueshengService.update(daxuesheng);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Daxuesheng> detail(@PathVariable Integer id) {
        Daxuesheng daxuesheng = daxueshengService.findById(id);
        return Result.success(daxuesheng);
    }

    @GetMapping
    public Result<List<Daxuesheng>> all() {
        return Result.success(daxueshengService.list());
    }

    @PostMapping("/page")
    public Result<DaxueshengVo> page(@RequestBody DaxueshengVo daxueshengVo) {
        return Result.success(daxueshengService.findPage(daxueshengVo));
    }
	
	@PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<Daxuesheng> daxueshengs = reader.readAll(Daxuesheng.class);
		for (Daxuesheng daxuesheng : daxueshengs) { daxuesheng.setMima("dc5c7986daef50c1e02ab09b442ee34f");}
        daxueshengService.saveBatch(daxueshengs);
        return Result.success();
    }
	
	    @PostMapping("/login")
    public Result login(@RequestBody Daxuesheng daxuesheng, HttpServletRequest request) {
        if (StrUtil.isBlank(daxuesheng.getXuehao()) || StrUtil.isBlank(daxuesheng.getMima())) {
            throw new CustomException(ResultCode.PARAM_LOST_ERROR);
        }
        Daxuesheng login = daxueshengService.login(daxuesheng);
//        if(!login.getStatus()){
//            return Result.error("1001","状态限制，无法登录系统");
//        }
        if(login != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("user", login);
            Map<String, Object> map = MapWrapperUtils.builder(MapWrapperUtils.KEY_USER_ID,daxuesheng.getId());
            String token = JwtUtil.creatToken(map);
            hashMap.put("token", token);
            return Result.success(hashMap);
        }else {
            return Result.error();
        }
    }
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Daxuesheng info, HttpServletRequest request) {
        Daxuesheng daxuesheng = daxueshengService.findById(info.getId());
        String oldPassword = SecureUtil.md5(info.getMima());
        if (!oldPassword.equals(daxuesheng.getMima())) {
            return Result.error(ResultCode.PARAM_PASSWORD_ERROR.code, ResultCode.PARAM_PASSWORD_ERROR.msg);
        }
        info.setMima(SecureUtil.md5(info.getNewPassword()));
        Daxuesheng daxuesheng1 = new Daxuesheng();
        BeanUtils.copyProperties(info, daxuesheng1);
        daxueshengService.update(daxuesheng1);
        return Result.success();
    }
	@PostMapping("/register")
    public Result<Daxuesheng> register(@RequestBody DaxueshengVo daxuesheng) {
        daxueshengService.add(daxuesheng);
        return Result.success(daxuesheng);
    }
}
