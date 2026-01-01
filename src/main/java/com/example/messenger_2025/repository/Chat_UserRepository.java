package com.example.messenger_2025.repository;

import com.example.messenger_2025.model.Chat;
import com.example.messenger_2025.model.Chat_User;
import com.example.messenger_2025.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Chat_UserRepository extends JpaRepository<Chat_User,Long> {

    List<Chat_User> findAllByUser(User user);

    List<Chat_User> findAllByChat(Chat chat);

}
