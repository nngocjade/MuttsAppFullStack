package com.muttsapp.mapper;


import com.muttsapp.model.Message;
import com.muttsapp.model.UserChat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ChatMapper {

    String FIND_USER_CHATS_BY_ID = "select distinct(c.chat_title) as chat_name, c.id as chat_id, m.date_sent, " +
            "m.user_id as sender_id " +
            "from chats c " +
            "join messages m " +
            "on m.chat_id = c.id " +
            "join user_chats uc " +
            "on uc.chat_id = c.id " +
            "where uc.user_id = #{id} " +
            "and m.user_id != #{id} " +
            "group by chat_id, sender_id, m.date_sent " +
            "order by m.date_sent asc";

    String FIND_PHOTO_URL = "Select photo_url from user " +
            "where user_id = #{user_id} ";

    String GET_LAST_MESSAGE = "Select message, date_sent from messages " +
            "where chat_id = #{user_id} " +
            "order by id desc limit 1";

    String FIND_CHAT_ID_FOR_USERS_ID = "select uc.chat_id " +
            "from user_chats uc " +
            "where uc.user_id = #{param1} " +
            "or uc.user_id = #{param2} " +
            "group by uc.chat_id " +
            "order by count(uc.chat_id) desc " +
            "limit 1";

    String FIND_MESSAGE_BY_CHAT_ID = "select m.id, m.message, m.date_sent, m.chat_id, m.user_id as sender_id, c.chat_title " +
            "from messages m " +
            "join chats c " +
            "on m.chat_id = c.id " +
            "where m.chat_id = #{chat_id} " +
            "order by m.date_sent asc;";

    String INSERT_MESSAGE = "INSERT INTO `muttsappdb`.`messages` (`message`, `chat_id`, `user_id`) VALUES (#{message}, " +
            "#{chat_id}, #{user_id})";

    @Select(FIND_USER_CHATS_BY_ID)
    public ArrayList<UserChat> findAllUserChatsById(int id);

    @Select(FIND_PHOTO_URL)
    String findPhotoURL(int sender_id);

    @Select(GET_LAST_MESSAGE)
    String getLastMessage(int chat_id);

    @Select(FIND_CHAT_ID_FOR_USERS_ID)
    int findChatIdForUserIds(int id, int chat_id);

    @Select(FIND_MESSAGE_BY_CHAT_ID)
    ArrayList<Message> findMessagesByChatId(int chat_id);

    @Insert(INSERT_MESSAGE)
    void insertMessage(Message message);
}
