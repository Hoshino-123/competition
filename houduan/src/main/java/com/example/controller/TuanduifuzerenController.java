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
import com.example.entity.Tuanduifuzeren;
import com.example.exception.CustomException;
import com.example.service.TuanduifuzerenService;
import com.example.utils.MapWrapperUtils;
import com.example.utils.jwt.JwtUtil;
import com.example.vo.TuanduifuzerenVo;
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
@RequestMapping(value = "/tuanduifuzeren")
public class TuanduifuzerenController {

    @Resource
    private TuanduifuzerenService tuanduifuzerenService;

    @PostMapping
    public Result<Tuanduifuzeren> add(@RequestBody TuanduifuzerenVo tuanduifuzeren) {
		//yoxuxiaxnci
        tuanduifuzerenService.add(tuanduifuzeren);
        return Result.success(tuanduifuzeren);
    }
	
	
    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("tuanduibianhao","团队编号");        row.put("tuanduimingcheng","团队名称");        row.put("tuanduileixing","团队类型");        row.put("tuanduidizhi","团队地址");        row.put("lianxidianhua","联系电话");        row.put("xuexiaoxinxi","学校信息");        row.put("tuanduirenshu","团队人数");        

        row.put("addtime","添加时间");
        List<Map<String, Object>> list = CollUtil.newArrayList(row);
        List<Map<String, Object>> daochuexcellist = tuanduifuzerenService.daochuexcel();
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
    public Result<Tuanduifuzeren> deleteList(@RequestBody TuanduifuzerenVo tuanduifuzeren) {
        tuanduifuzerenService.deleteList(tuanduifuzeren.getList());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        tuanduifuzerenService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody TuanduifuzerenVo tuanduifuzeren) {
        tuanduifuzerenService.update(tuanduifuzeren);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Tuanduifuzeren> detail(@PathVariable Integer id) {
        Tuanduifuzeren tuanduifuzeren = tuanduifuzerenService.findById(id);
        return Result.success(tuanduifuzeren);
    }

    @GetMapping
    public Result<List<Tuanduifuzeren>> all() {
        return Result.success(tuanduifuzerenService.list());
    }

    @PostMapping("/page")
    public Result<TuanduifuzerenVo> page(@RequestBody TuanduifuzerenVo tuanduifuzerenVo) {
        return Result.success(tuanduifuzerenService.findPage(tuanduifuzerenVo));
    }
	
	@PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<Tuanduifuzeren> tuanduifuzerens = reader.readAll(Tuanduifuzeren.class);
		for (Tuanduifuzeren tuanduifuzeren : tuanduifuzerens) { tuanduifuzeren.setMima("dc5c7986daef50c1e02ab09b442ee34f");}
        tuanduifuzerenService.saveBatch(tuanduifuzerens);
        return Result.success();
    }
	
	    @PostMapping("/login")
    public Result login(@RequestBody Tuanduifuzeren tuanduifuzeren, HttpServletRequest request) {
        if (StrUtil.isBlank(tuanduifuzeren.getTuanduibianhao()) || StrUtil.isBlank(tuanduifuzeren.getMima())) {
            throw new CustomException(ResultCode.PARAM_LOST_ERROR);
        }
        Tuanduifuzeren login = tuanduifuzerenService.login(tuanduifuzeren);
//        if(!login.getStatus()){
//            return Result.error("1001","状态限制，无法登录系统");
//        }
        if(login != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("user", login);
            Map<String, Object> map = MapWrapperUtils.builder(MapWrapperUtils.KEY_USER_ID,tuanduifuzeren.getId());
            String token = JwtUtil.creatToken(map);
            hashMap.put("token", token);
            return Result.success(hashMap);
        }else {
            return Result.error();
        }
    }
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Tuanduifuzeren info, HttpServletRequest request) {
        Tuanduifuzeren tuanduifuzeren = tuanduifuzerenService.findById(info.getId());
        String oldPassword = SecureUtil.md5(info.getMima());
        if (!oldPassword.equals(tuanduifuzeren.getMima())) {
            return Result.error(ResultCode.PARAM_PASSWORD_ERROR.code, ResultCode.PARAM_PASSWORD_ERROR.msg);
        }
        info.setMima(SecureUtil.md5(info.getNewPassword()));
        Tuanduifuzeren tuanduifuzeren1 = new Tuanduifuzeren();
        BeanUtils.copyProperties(info, tuanduifuzeren1);
        tuanduifuzerenService.update(tuanduifuzeren1);
        return Result.success();
    }
	@PostMapping("/register")
    public Result<Tuanduifuzeren> register(@RequestBody TuanduifuzerenVo tuanduifuzeren) {
        tuanduifuzerenService.add(tuanduifuzeren);
        return Result.success(tuanduifuzeren);
    }
}
