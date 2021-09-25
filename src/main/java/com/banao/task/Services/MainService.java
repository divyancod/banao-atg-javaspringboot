package com.banao.task.Services;

import com.banao.task.Exceptions.UserNotFound;
import com.banao.task.Exceptions.VerificationFailed;
import com.banao.task.Model.MyUser;

import java.util.List;

public interface MainService {
    void signupUser(MyUser myUser);

    void verifyUser(String email, String token) throws VerificationFailed, UserNotFound;

    List<MyUser> fetchAllUserDetails();

    boolean checkUserExists(String email);

    MyUser getSingleUser(String email);
}
