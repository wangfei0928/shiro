package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select name from role where id in (select rid from role_user where uid = (select id from user where name = #{principal}))")
    List<String> getUserRoleInfoMapper(@Param("principal") String principal);


    @Select({
            "<script>",
            "select info FROM permissions WHERE id IN ",
            "(SELECT pid FROM role_ps WHERE rid IN (",
            "SELECT id FROM role WHERE NAME IN ",
            "<foreach collection='roles' item='name' open='(' separator=',' close=')'>",
            "#{name}",
            "</foreach>",
            "))",
            "</script>"
    })
    List<String> getUserPermissionInfoMapper(@Param("roles") List<String> roles);
}
