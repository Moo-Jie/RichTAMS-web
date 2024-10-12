package com.rich.service.impl;
import com.rich.annotation.RecordLog;
import com.rich.annotation.RunTimeLog;
import com.rich.mapper_DAO.DeptLogMapper;
import com.rich.mapper_DAO.DeptMapper;
import com.rich.mapper_DAO.EmpMapper;
import com.rich.pojo.Dept;
import com.rich.pojo.log.DeptLog;
import com.rich.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@RunTimeLog
public class DeptServiceImlp implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogMapper deptLogMapper;


    @RecordLog
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @RecordLog
    @Override
    public void update(Dept newDept) {
        //获取到要被更新的部门信息
        Integer id = newDept.getId();
        Dept olddept=deptMapper.getDeptById(id);
        //获取其创建时间
        newDept.setCreateTime(olddept.getCreateTime());
        //添加更新时间
        newDept.setUpdateTime(LocalDateTime.now());
        //提交更新
        deptMapper.update(newDept);
    }

    @RecordLog
    @Override
    public Dept getDeptById(Integer id) {
        return deptMapper.getDeptById(id);
    }

    @RecordLog
    @Transactional(rollbackFor = Exception.class,propagation = REQUIRES_NEW)//不希望影响deptLogMapper.insert(deptLog)的提交
    @Override
    public void delete(Integer id) {
        try {
            deptMapper.delete(id);
            empMapper.deleteByDeptId(id);
        }
        finally {
            DeptLog deptLog=new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了部门号为："+id+" 的部门的删除操作");
            deptLogMapper.insert(deptLog);
        }
    }
    @RecordLog
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }


}
