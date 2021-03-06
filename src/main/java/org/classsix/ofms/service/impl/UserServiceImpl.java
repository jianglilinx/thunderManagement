package org.classsix.ofms.service.impl;

import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.Role;
import org.classsix.ofms.domin.User;
import org.classsix.ofms.repository.UserRepository;
import org.classsix.ofms.service.UserService;
import org.classsix.ofms.status.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiang on 2017/5/3.
 * 面向运气，面向心情，面向Bug。
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;


    @Override
    public User confirmLogin(String userName, String password) throws Exception{
        User user = userRepository.findByUserNameAndPassword(userName,password);
        return user;
    }

    @Override
    public UserStatus addUser(User user){
        UserStatus status = UserStatus.ERROR;
        try {
            userRepository.save(user);
            status = UserStatus.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }


    @Override
    public ResponseMessage updateUser(Integer id,String password){
        UserStatus status = UserStatus.ERROR;
        try {
            userRepository.update(id,password);
            status = UserStatus.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseMessage(status);
    }


//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = userRepository.findByUserName(s).get(0);
//        if (user == null) {
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//        return user;
//    }
}
