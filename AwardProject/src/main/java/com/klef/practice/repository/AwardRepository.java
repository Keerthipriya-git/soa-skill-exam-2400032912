package com.klef.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.practice.entity.Award;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long>
{
    List<Award> findByName(String name);

    List<Award> findByType(String type);
}