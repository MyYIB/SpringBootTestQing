package com.example.springboottestqing.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboottestqing.entity.Student;
import com.example.springboottestqing.entity.User;
import com.example.springboottestqing.mapper.StudentMapper;
import com.example.springboottestqing.mapper.UserMapper;
import com.example.springboottestqing.service.Impl.StudentServiceImpl;
import com.example.springboottestqing.service.StudentService;
import com.example.springboottestqing.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    StudentService studentService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @GetMapping("/select")
    public List<Student> test() {
        return studentService.list();
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
                                          @RequestParam String studentName
    ) {
        Page<Student> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Student> query=new QueryWrapper<>();
        query.like("student_name",studentName);
        IPage<Student> studentIPage = studentService.page(page,query);
        List<Student> records = studentIPage.getRecords();
        Map<String, Object> res = new HashMap<>();
        res.put("data", records);
        res.put("total", studentIPage.getTotal());
        return res;
    }

}
