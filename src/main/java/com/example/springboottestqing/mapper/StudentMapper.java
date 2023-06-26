package com.example.springboottestqing.mapper;

import com.example.springboottestqing.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 97337
* @description 针对表【student】的数据库操作Mapper
* @createDate 2023-06-25 21:30:07
* @Entity generator.domain.Student
*/
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}




