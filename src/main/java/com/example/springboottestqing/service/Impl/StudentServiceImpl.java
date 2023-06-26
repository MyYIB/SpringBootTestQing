package com.example.springboottestqing.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboottestqing.entity.Student;
import com.example.springboottestqing.mapper.StudentMapper;
import com.example.springboottestqing.service.StudentService;
import org.springframework.stereotype.Service;

/**
* @author 97337
* @description 针对表【student】的数据库操作Service实现
* @createDate 2023-06-25 21:30:07
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}




