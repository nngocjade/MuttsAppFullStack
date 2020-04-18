package com.muttsapp.mapper;

import com.muttsapp.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface UserMapper {

    String SELECT_ALL_USERS = "select * from `muttsappdb`.user";

    String SELECT_USER_BY_ID = "select * from `muttsappdb`.user where user_id = #{user_id}";

    String SELECT_USER_ID_BY_EMAIL = "select user_id from `muttsappdb`.user where email = #{email}";

    String UPDATE_USER = "UPDATE `muttsappdb`.`user` SET `user_name` = '#{user_name}', `email` = '#{email}', " +
            "`first_name` = '#{first_name}', `last_name` = '#{last_name}' WHERE user_id = #{user_id}";

    @Select(SELECT_ALL_USERS)
    public ArrayList<User> getAllUsers();

    @Select (SELECT_USER_BY_ID)
    User findUserById(int id);

    @Select(SELECT_USER_ID_BY_EMAIL)
    int findUserIDByEmail(String email);

}
