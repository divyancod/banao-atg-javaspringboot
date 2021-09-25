package com.banao.task.Services;

import com.banao.task.Configuration.MyCustomToken;
import com.banao.task.Dao.MainUserDao;
import com.banao.task.Exceptions.UserNotFound;
import com.banao.task.Exceptions.VerificationFailed;
import com.banao.task.Model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {
    @Autowired
    MainUserDao mainUserDao;

    @Autowired
    MyCustomToken myCustomToken;

    @Override
    public void signupUser(MyUser myUser) {
        mainUserDao.saveUserToDB(myUser);
    }

    @Override
    public void verifyUser(String email, String token) throws VerificationFailed, UserNotFound {
        if (myCustomToken.validateKey(email, token)) {
            MyUser myUser = mainUserDao.getUser(email);
            if(myUser==null)
                throw new UserNotFound("User Not Found");
            else
                mainUserDao.toggleUserLogin(myUser.getId());
        }else
        {
            throw new VerificationFailed("Verification Failed");
        }
    }

    @Override
    public List<MyUser> fetchAllUserDetails() {
        return mainUserDao.fetchAllUsers();
    }

    @Override
    public boolean checkUserExists(String email) {
        if(mainUserDao.getUser(email)==null)
            return false;
        return true;
    }

    @Override
    public MyUser getSingleUser(String email) {
        return mainUserDao.getUser(email);
    }
}
