package com.example.springboottestqing.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboottestqing.entity.Clazz;
import com.example.springboottestqing.mapper.ClazzMapper;
import com.example.springboottestqing.service.ClazzService;
import org.springframework.stereotype.Service;

@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper,Clazz> implements ClazzService {
}
