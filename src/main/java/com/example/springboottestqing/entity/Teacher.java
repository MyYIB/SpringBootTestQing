package com.example.springboottestqing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName teacher
 */
@TableName(value ="teacher")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Teacher {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer teacherId;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private String teacherNo;

    /**
     * 
     */
    private String teacherName;

    /**
     * 
     */
    private String teacherSex;

    /**
     * 
     */
    private String teacherPhone;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}