package org.classsix.ofms.repository;

import org.classsix.ofms.domin.Warning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jiang on 2018/6/9.
 * Coding
 */
@Transactional
public interface WarningRepository extends JpaRepository<Warning, Integer> {
    List<Warning> findByGroupId(Integer groupId);
}
