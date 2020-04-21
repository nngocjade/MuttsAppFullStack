package com.muttsapp.mapper;


import com.muttsapp.model.UserChats;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface ChatMapper {

    String SELECT_USER_CHATS_BY_ID = "select distinct(c.chat_title) as chat_name, c.id as chat_id, " +
            "m.user_id as sender_id " +
            "from chats c " +
            "join messages m " +
            "on m.chat_id = c.id " +
            "join user_chats uc " +
            "on uc.chat_id = c.id " +
            "where uc.user_id = #{id} " +
            "and m.user_id != #{id} " +
            "group by chat_id, sender_id " +
            "order by m.date_sent asc";

    String SELECT_PHOTO_URL = "Select photo_url from user " +
            "where user_id = #{user_id} ";

    String SELECT_LAST_MESSAGE = "Select message, date_sent from messages " +
            "where chat_id = #{user_id} " +
            "order by id desc limit 1";

    @Select(SELECT_USER_CHATS_BY_ID)
    public ArrayList<UserChats> findAllUserChatsById(int id);

    @Select(SELECT_PHOTO_URL)
    String FindPhotoURL(int sender_id);

    @Select(SELECT_LAST_MESSAGE)
    String getLastMessage(int chat_id);

}
