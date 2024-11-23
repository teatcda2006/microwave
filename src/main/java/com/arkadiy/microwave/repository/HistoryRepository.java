package com.arkadiy.microwave.repository;

import com.arkadiy.microwave.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query("SELECT h.name, COUNT(h) as c FROM History h GROUP BY h.name ORDER BY c DESC LIMIT 3")
    List<Object[]> getTopModes();
}
