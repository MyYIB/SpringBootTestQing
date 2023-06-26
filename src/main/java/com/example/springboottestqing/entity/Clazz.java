package com.example.springboottestqing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "clazz")
public class Clazz {
    @TableId(type = IdType.AUTO)
    private Integer clazzId;

    private String clazzNo;

    private String institution;

}
