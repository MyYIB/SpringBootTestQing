package com.example.springboottestqing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottestqing.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
