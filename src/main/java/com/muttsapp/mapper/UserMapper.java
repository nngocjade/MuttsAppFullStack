package com.muttsapp.mapper;

import com.muttsapp.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface UserMapper {

    String SELECT_ALL_USERS = "select * from `testdb`.users";

    @Select(SELECT_ALL_USERS)
    public ArrayList<User> getAllUsers();

}
