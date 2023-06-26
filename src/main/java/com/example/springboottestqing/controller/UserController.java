package com.example.springboottestqing.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboottestqing.common.R;
import com.example.springboottestqing.entity.User;
import com.example.springboottestqing.mapper.UserMapper;
import com.example.springboottestqing.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.oas.annotations.EnableOpenApi;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin
//@EnableOpenApi
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/select")
    public List<User> test() {
        return userService.list();
    }

    /***
     * 分页查询,输入页数与每页的个数
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Map<String, Object> selectPage(@RequestParam int pageNum,
                                                                    @RequestParam int pageSize,
                                                                    @RequestParam String account
                                                                    ) {
        Page<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> query=new QueryWrapper<>();
        query.like("account",account);
        IPage<User> UserIPage = userService.page(page,query);
        List<User> records = UserIPage.getRecords();
        Map<String, Object> res = new HashMap<>();
        res.put("data", records);
        res.put("total", UserIPage.getTotal());
        return res;
    }

    @PostMapping("/addNewUser")
    public R<Object> addNewUser(@RequestBody User user){
        if (userService.saveOrUpdate(user)){
            return R.success("新增成功");
        }else {
            return R.error("新增失败");
        }
    }

    @GetMapping("/delete")
    public R<Object> deleteOne(@RequestParam int id){
        userService.removeById(id);
        return R.success("删除成功");
    }

    @PostMapping("/delBatch")
    public R<Object> delBatch(@RequestBody List<Integer> ids){
        userService.removeByIds(ids);
        return R.success("批量删除成功");
    }


    /**
     * 导出接口
     */
    @GetMapping("/export")
    public R<Object> export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<User> list = userService.list();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("id", "id");
        writer.addHeaderAlias("username", "用户名");
        writer.addHeaderAlias("password", "密码");
        writer.addHeaderAlias("nickname", "昵称");
        writer.addHeaderAlias("email", "邮箱");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("CreateTime", "创建时间");
        writer.addHeaderAlias("AvatarUrl", "头像");
        writer.addHeaderAlias("role", "角色");
        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

        return R.success("成功");
    }


//
}

