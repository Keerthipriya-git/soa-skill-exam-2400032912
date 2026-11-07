package com.klef.practice.service;

import java.util.List;

import com.klef.practice.entity.Award;

public interface AwardService
{
    Award addAward(Award award);

    Award updateAward(Award award);

    String deleteAwardById(Long awardId);

    List<Award> displayAllAwards();

    Award displayAwardById(Long awardId);

    List<Award> findByName(String name);

    List<Award> findByType(String type);
}