package com.muttsapp.mapper;

import com.muttsapp.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface UserMapper {

    String SELECT_ALL_USERS = "select * from `muttsappdb`.user";

    String SELECT_USERID_BY_EMAIL = "select user_id from `muttsappdb`.user where email = #{email}";

    @Select(SELECT_ALL_USERS)
    public ArrayList<User> getAllUsers();

    @Select(SELECT_USERID_BY_EMAIL)
    int findUserIDByEmail(String email);
}
