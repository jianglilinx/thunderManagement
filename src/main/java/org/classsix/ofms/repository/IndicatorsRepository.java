package org.classsix.ofms.repository;

import org.classsix.ofms.domin.Indicators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jiang on 2018/6/9.
 * Coding
 */
@Transactional
public interface IndicatorsRepository extends JpaRepository<Indicators, Integer> {
    List<Indicators> findByPowerPlantId(Integer powerPlantId);
}
