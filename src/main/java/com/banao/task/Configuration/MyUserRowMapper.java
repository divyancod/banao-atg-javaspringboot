package com.banao.task.Configuration;

import com.banao.task.Model.MyUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyUserRowMapper implements RowMapper<MyUser> {

    @Override
    public MyUser mapRow(ResultSet resultSet, int i) throws SQLException {
        MyUser myUser = new MyUser();
        myUser.setEmail(resultSet.getString("email"));
        myUser.setFirstName(resultSet.getString("firstname"));
        myUser.setId(resultSet.getInt("id"));
        myUser.setUserType(resultSet.getString("role"));
        myUser.setEnabled(resultSet.getBoolean("enabled"));
        return myUser;
    }
}
