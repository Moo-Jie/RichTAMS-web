package com.rich.service.impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rich.annotation.RecordLog;
import com.rich.mapper_DAO.EmpMapper;
import com.rich.pojo.Emp;
import com.rich.pojo.PageBean;
import com.rich.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @RecordLog
    @Override
    public Emp selectById(Integer id) {
        return empMapper.selectById(id);
    }

    @RecordLog
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
    }

    //这个不能加@RecordLog
    @Override
    public Emp login(Emp emp) {
        return empMapper.selectByuserNameAndPassword(emp);
    }

    @RecordLog
    @Override
    public void insert(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insertById(emp);
    }

    @RecordLog
    @Override
    public void delete(List<Integer> ids) {
        empMapper.deleteById(ids);
    }

    @RecordLog
    @Override
    public PageBean selectPage(Integer page, Integer pageSize,
                               String name, Short gender, LocalDate begin, LocalDate end) {//利用PageHelper实现分页
        //静态函数统一设置设置分页参数
        PageHelper.startPage(page,pageSize);
        //把全部数据查询出来并导入到Page容器
        Page<Emp> p=(Page<Emp>)empMapper.list(name,gender,begin,end);
        //根据设定好的分页参数查询数据
        return new PageBean(p.getTotal(),p.getResult());
    }

    @RecordLog
    @Override
    public void deleteByDeptId(Integer id) {
        empMapper.deleteByDeptId(id);
    }
}
