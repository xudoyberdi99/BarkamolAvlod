package com.company.repository;

import com.company.entity.Achievements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementsRepository extends JpaRepository<Achievements,Long> {
}
