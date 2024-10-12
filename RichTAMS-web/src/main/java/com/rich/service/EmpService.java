package com.rich.service;

import com.rich.pojo.Dept;
import com.rich.pojo.Emp;
import com.rich.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageBean selectPage(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void insert(Emp emp);

    Emp selectById(Integer id);

    void update(Emp emp);


    Emp login(Emp emp);

    void deleteByDeptId(Integer id);
}
