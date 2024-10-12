package com.rich.mapper_DAO;
import com.rich.pojo.Dept;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//配置文件实现该接口内声明的方法，因此直接写成接口
@Mapper
public interface DeptMapper {
    //查询全部部门数据
    @Select("select * from dept")//自动实现该方法
    List<Dept> list();

    @Select("delete from dept where id=#{id}")
    void delete(Integer id);

    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select * from dept where id=#{id}")
    Dept getDeptById(Integer id);

    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept newDept);
}
