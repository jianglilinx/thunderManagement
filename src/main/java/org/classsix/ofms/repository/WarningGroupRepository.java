package org.classsix.ofms.repository;

import org.classsix.ofms.domin.WarningGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jiang on 2018/5/28.
 * Coding
 */
@Transactional
public interface WarningGroupRepository extends JpaRepository<WarningGroup, Integer> {
    WarningGroup findByGroupName(String groupName);
}
