package com.muttsapp.mapper;

import com.muttsapp.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface UserMapper {

    String SELECT_ALL_USERS = "select * from `muttsappdb`.user";

    String SELECT_USER_ID_BY_EMAIL = "select user_id from `muttsappdb`.user where email = #{email}";

    String SELECT_USER_BY_ID = "select * from `muttsappdb`.user where user_id = #{user_id}";

    @Select(SELECT_ALL_USERS)
    public ArrayList<User> getAllUsers();

    @Select(SELECT_USER_ID_BY_EMAIL)
    User findUserIDByEmail(String email);

    @Select(SELECT_USER_BY_ID)
    ArrayList<User> findUserById(int id);
}
