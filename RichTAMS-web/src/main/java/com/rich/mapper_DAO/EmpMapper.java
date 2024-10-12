package com.rich.mapper_DAO;

import com.rich.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

//配置文件实现该接口内声明的方法，因此直接写成接口
@Mapper
public interface EmpMapper {
    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    void deleteById(List<Integer> ids);

    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) values(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insertById(Emp emp);

    @Select("select * from emp where id=#{id}")
    Emp selectById(Integer id);

    @Update("update emp set username=#{username},name=#{name},gender=#{gender},image=#{image},job=#{job},entrydate=#{entrydate},dept_id=#{deptId},create_time=#{createTime},update_time=#{updateTime} where id=#{id}")
    void updateById(Emp emp);

    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp selectByuserNameAndPassword(Emp emp);

    @Delete("delete from emp where dept_id=#{id}")
    void deleteByDeptId(Integer id);
}
