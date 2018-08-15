package org.classsix.ofms.repository;


import org.classsix.ofms.domin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jiang on 2017/5/3.
 * 面向运气，面向心情，面向Bug。
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserNameAndPassword(String userName,String password);
    User findById(int uid);
    List<User> findByRole(int role);


    @Modifying
    @Query("update User u set u.password=:password where u.id=:id")
    int update(@Param("id") Integer id,@Param("password") String password);

}
