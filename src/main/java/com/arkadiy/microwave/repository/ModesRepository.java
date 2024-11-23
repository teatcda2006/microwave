package com.arkadiy.microwave.repository;

import com.arkadiy.microwave.entity.Mode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModesRepository extends JpaRepository<Mode, Long> {
}
