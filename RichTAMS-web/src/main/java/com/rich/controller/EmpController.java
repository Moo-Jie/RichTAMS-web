package com.rich.controller;

import com.rich.pojo.Emp;
import com.rich.pojo.PageBean;
import com.rich.pojo.Result;
import com.rich.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")//rest风格的控制器
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result selectPage(@RequestParam(defaultValue = "1")Integer page,
                             @RequestParam(defaultValue = "10")Integer pageSize,
                             String name, Short gender,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                             @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        log.info("进行了分页查询操作，查询的参数为{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageBean pg= empService.selectPage(page,pageSize,name,gender,begin,end);
        return Result.success(pg);
    }
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("进行了查询操作，查询的id为{}",id);
        Emp emp= empService.selectById(id);
        return Result.success(emp);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("进行了批量删除操作，删除的id为{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody Emp emp){
        log.info("进行了新增操作，新增的员工信息为{}",emp);
        empService.insert(emp);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("进行了修改操作，修改的员工信息为{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
