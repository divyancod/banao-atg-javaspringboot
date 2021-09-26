package com.banao.task.Dao;

import com.banao.task.Model.MyUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public interface MainUserDao {
    void saveUserToDB(MyUser myUser);

    void sendEmail(String email);

    MyUser getUser(String email);

    void toggleUserLogin(int id);

    List<MyUser> fetchAllUsers();

    List<GrantedAuthority> getAuthorities(int id);
}
