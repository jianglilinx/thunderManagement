package org.classsix.ofms.service;

import org.classsix.ofms.common.ResponseMessage;
import org.classsix.ofms.domin.User;
import org.classsix.ofms.status.UserStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by jiang on 2017/5/3.
 * 面向运气，面向心情，面向Bug。
 */
public interface UserService {
    User confirmLogin(String userName, String password) throws Exception;
    UserStatus addUser(User user);
    ResponseMessage updateUser(Integer id, String password);
}
