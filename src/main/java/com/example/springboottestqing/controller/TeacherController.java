package com.example.springboottestqing.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboottestqing.common.R;
import com.example.springboottestqing.entity.Teacher;
import com.example.springboottestqing.entity.User;
import com.example.springboottestqing.mapper.TeacherMapper;
import com.example.springboottestqing.mapper.UserMapper;
import com.example.springboottestqing.service.TeacherService;
import com.example.springboottestqing.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    TeacherService teacherService;

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    @GetMapping("/select")
    public List<Teacher> test() {
        return teacherService.list();
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
                                          @RequestParam String teacherName
                                          ) {
        Page<Teacher> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Teacher> query=new QueryWrapper<>();
        query.like("teacher_name",teacherName);
        IPage<Teacher> TeacherIPage = teacherService.page(page,query);
        List<Teacher> records = TeacherIPage.getRecords();
        Map<String, Object> res = new HashMap<>();
        res.put("data", records);
        res.put("total", TeacherIPage.getTotal());
        return res;
    }

    @PostMapping("/addNewTeacher")
    public R<Object> addNewTeacher(@RequestBody Teacher teacher){
        User user = new User();
        user.setAccount(teacher.getTeacherNo());
        user.setPassword("123456");
        userService.saveOrUpdate(user);
        teacher.setUserId(user.getUserId());
        teacherService.saveOrUpdate(teacher);
        return R.success("新增成功");
        /*2465*/
    }
}
