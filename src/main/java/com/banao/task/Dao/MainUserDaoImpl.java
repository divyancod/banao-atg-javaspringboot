package com.banao.task.Dao;

import com.banao.task.Configuration.MyCustomToken;
import com.banao.task.Configuration.MyUserRowMapper;
import com.banao.task.Model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MainUserDaoImpl implements MainUserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MyCustomToken myCustomToken;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void saveUserToDB(MyUser myUser) {
        String userSql = "insert into users(email,password,enabled,firstname) values (?,?,'0',?)";
        jdbcTemplate.update(userSql, myUser.getEmail(), passwordEncoder.encode(myUser.getPassword()), myUser.getFirstName());

        userSql = "select id from users where email = ?";
        Integer userid = jdbcTemplate.queryForObject(userSql, Integer.class, myUser.getEmail());

        String rolesSql = "insert into roles values (?,?)";
        jdbcTemplate.update(rolesSql, userid, "user");
        sendEmail(myUser.getEmail());
    }

    @Override
    public void sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("task@banao.com");
        message.setTo(email);
        message.setSubject("Verification Mail");
        StringBuffer str = new StringBuffer();
        str.append("Your email verification link is \n");
        str.append(myCustomToken.getVerificationLink(email));
        message.setText(str.toString());
        System.out.println(str);
        javaMailSender.send(message);
    }

    @Override
    public MyUser getUser(String email) {
        String usersql = "select u.*,r.role from users u,roles r where u.id=r.id and email=?";
        List<MyUser> myUser = jdbcTemplate.query(usersql, new MyUserRowMapper(), email);
        if (myUser.size() == 0)
            return null;
        else
            return myUser.get(0);
    }

    @Override
    public void toggleUserLogin(int id) {
        String sql = "update users set enabled = NOT enabled where id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<MyUser> fetchAllUsers() {
        String usersql = "select u.*,r.role from users u,roles r where u.id=r.id";
        List<MyUser> myUser = jdbcTemplate.query(usersql, new MyUserRowMapper());
        return myUser;
    }

}
