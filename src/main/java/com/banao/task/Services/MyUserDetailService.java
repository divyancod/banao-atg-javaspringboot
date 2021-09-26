package com.banao.task.Services;

import com.banao.task.Model.CustomUserDetails;
import com.banao.task.Dao.MainUserDao;
import com.banao.task.Model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    MainUserDao mainUserDao;

    //    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        MyUser myUser = mainUserDao.fetchUserModelWithPassword(s);
//        System.out.println(myUser);
//        if(myUser==null)
//            throw new UsernameNotFoundException("User Not Found");
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(myUser.getUserType()));
//        return new User(myUser.getEmail(),myUser.getPassword(),authorities);
//    }
    @Override
    public CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MyUser myUser = mainUserDao.getUser(s);
        if (myUser == null)
            throw new UsernameNotFoundException("User Not Found");
        return new CustomUserDetails(myUser);
    }
}
