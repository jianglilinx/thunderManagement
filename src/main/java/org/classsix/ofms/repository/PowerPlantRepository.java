package org.classsix.ofms.repository;

import org.classsix.ofms.domin.PowerPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jiang on 2018/6/8.
 * Coding
 */
@Transactional
public interface PowerPlantRepository extends JpaRepository<PowerPlant, Integer> {
    PowerPlant findByPlantName(String plantName);
}
