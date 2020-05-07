package com.example.graduation.sys.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.graduation.sys.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author: huiyuan
 * @date: 2020/5/7 11:39
 * @desc: 这是描述
 **/
@Data
@ExcelTarget("项目")
public class ProjectDTO {
    /**
     * 项目id
     */
    @TableId(value = "project_id", type = IdType.AUTO)
    @Excel(name = "项目ID", orderNum = "1", width = 25)
    private Integer projectId;

    /**
     * 项目名
     */
    @Excel(name = "项目名称", width = 25)
    private String projectName;

    /**
     * 项目所有人id
     */
    @ExcelEntity(id = "user_id")
    private String projectOwner;

    /**
     * 项目描述
     */
    @Excel(name = "项目描述", width = 25)
    private String projectDesc;

    @ExcelCollection(name = "项目成员", orderNum = "4")
    private List<User> projectMembers;
}
