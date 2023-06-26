package com.example.springboottestqing.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboottestqing.entity.User;
import com.example.springboottestqing.mapper.UserMapper;
import com.example.springboottestqing.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
