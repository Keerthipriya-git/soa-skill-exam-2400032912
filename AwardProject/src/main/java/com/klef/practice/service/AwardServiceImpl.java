package com.klef.practice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.practice.entity.Award;
import com.klef.practice.repository.AwardRepository;

@Service
public class AwardServiceImpl implements AwardService
{
    @Autowired
    private AwardRepository repo;

    @Override
    public Award addAward(Award award)
    {
        return repo.save(award);
    }

   
    @Override
    public Award updateAward(Award award)
    {
        Optional<Award> optional = repo.findById(award.getAwardId());

        if(optional.isPresent())
        {
            Award a = optional.get();

            a.setName(award.getName());
            a.setType(award.getType());
            a.setStatus(award.isStatus());
            a.setLocation(award.getLocation());

            return repo.save(a);
        }
        else
        {
            return null;
        }
    }

   
    @Override
    public String deleteAwardById(Long awardId)
    {
        boolean status = repo.existsById(awardId);

        if(status)
        {
            repo.deleteById(awardId);

            return "Award Deleted Successfully";
        }
        else
        {
            return "Award ID Not Found";
        }
    }

    @Override
    public List<Award> displayAllAwards()
    {
        return repo.findAll();
    }

    @Override
    public Award displayAwardById(Long awardId)
    {
        return repo.findById(awardId).orElse(null);
    }

    @Override
    public List<Award> findByName(String name)
    {
        return repo.findByName(name);
    }

    
    @Override
    public List<Award> findByType(String type)
    {
        return repo.findByType(type);
    }
}