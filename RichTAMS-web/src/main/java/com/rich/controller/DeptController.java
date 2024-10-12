package com.rich.controller;
import com.rich.pojo.Dept;
import com.rich.pojo.Result;
import com.rich.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")//遵循restful风格
public class DeptController {
    @Autowired
    private DeptService deptService;

   @GetMapping
    public Result getDepts() {
        log.info("进行了查询部门表操作");
        List<Dept> deptList=deptService.list();
        return Result.success(deptList);
    }

    @DeleteMapping("/{id}")
    public Result deleteDept(@PathVariable Integer id) {
        log.info("进行了删除操作，id为{}",id);
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping
    //RequestBody将json数据封装到Dept对象中
    //ResponseBody将返回的Dept对象封装成json数据
    public Result addDept(@RequestBody Dept dept) {
        log.info("进行了添加操作，添加的部门信息为{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getDept(@PathVariable Integer id) {
       log.info("进行了查询操作，id为{}",id);
       Dept dept=deptService.getDeptById(id);
       return Result.success(dept);
    }

    @PutMapping
    public Result updateDept(@RequestBody Dept dept) {
        log.info("进行了修改操作，修改的部门信息为{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
