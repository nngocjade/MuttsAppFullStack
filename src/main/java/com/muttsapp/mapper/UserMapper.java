package com.muttsapp.mapper;

import com.muttsapp.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;

@Mapper
public interface UserMapper {

    String FIND_ALL_USERS = "select * from `muttsappdb`.user";

    String SELECT_USER_ID_BY_EMAIL = "select user_id from `muttsappdb`.user where user_name = #{user_name}";

    String SELECT_USER_BY_ID = "select * from `muttsappdb`.user where user_id = #{user_id}";

    @Select(FIND_ALL_USERS)
    public ArrayList<User> findAllUsers();

    @Select(SELECT_USER_ID_BY_EMAIL)
    User findUserByUserName(String user_name);

    @Select(SELECT_USER_BY_ID)
    ArrayList<User> findUserById(int id);


}
