package org.classsix.ofms.repository;

import org.classsix.ofms.domin.Admin;
import org.classsix.ofms.domin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jiang on 2018/5/28.
 * Coding
 */
@Transactional
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUserNameAndPassword(String userName,String password);
}
