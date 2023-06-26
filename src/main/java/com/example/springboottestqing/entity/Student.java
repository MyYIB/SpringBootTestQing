package com.example.springboottestqing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName student
 */
@TableName(value ="student")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer studentId;

    /**
     * 
     */
    private Integer clazzId;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private String studentNo;

    /**
     * 
     */
    private String studentName;

    /**
     * 
     */
    private String studentSex;

    /**
     * 
     */
    private String studentPhone;

}