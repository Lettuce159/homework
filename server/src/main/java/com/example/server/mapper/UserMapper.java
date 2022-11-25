package com.example.server.mapper;
import com.example.server.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
@Repository
public interface  UserMapper {

    @Insert("insert into user(username,password) values(#{username},#{password})")
    int saveUser(@Param("username") String username,@Param("password") String password);

    @Select("select id,username,password from user where username=#{username}")
    User selectUsername(@Param("username") String username);

    @Select("select id,username,password from user where username=#{username} and password=#{password}")
    User selectUser(@Param("username") String username,@Param("password") String password);

    @Update("update user set password = #{newPassword} where username= #{username} ")
    int updatePassword(@Param("username") String username,@Param("newPassword") String newPassword);

}
