package com.example.springboottestqing.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboottestqing.common.R;
import com.example.springboottestqing.entity.Clazz;
import com.example.springboottestqing.entity.User;
import com.example.springboottestqing.mapper.ClazzMapper;
import com.example.springboottestqing.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/clazz")
public class ClazzController {


    @Autowired
    ClazzMapper clazzMapper;

    @Autowired
    ClazzService clazzService;

    @GetMapping("/select")
    public List<Clazz> getAll(){
        return clazzService.list();
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
                                          @RequestParam String clazzNo
    ) {
        Page<Clazz> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Clazz> query=new QueryWrapper<>();
        query.like("clazz_no",clazzNo);
        IPage<Clazz> clazzIPage = clazzService.page(page,query);
        List<Clazz> records = clazzIPage.getRecords();
        System.out.println(records.get(0).getClazzId());
        Map<String, Object> res = new HashMap<>();
        res.put("data", records);
        res.put("total", clazzIPage.getTotal());
        return res;
    }

    @PostMapping("/addNewClazz")
    public R<Object> addNewUser(@RequestBody Clazz clazz){
        if (clazzService.saveOrUpdate(clazz)){
            return R.success("新增成功");
        }else {
            return R.error("新增失败");
        }
    }

}
