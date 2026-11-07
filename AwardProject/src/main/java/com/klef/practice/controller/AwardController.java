package com.klef.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.practice.entity.Award;
import com.klef.practice.service.AwardService;

@RestController
@RequestMapping("/award")
public class AwardController
{
    @Autowired
    private AwardService service;

    // Test
    @GetMapping("/")
    public String test()
    {
        return "Award Demo Project";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addAward(@RequestBody Award award)
    {
       service.addAward(award);

        return ResponseEntity.status(HttpStatus.CREATED).body("Award Added Successfully");
    }

    @GetMapping("/displayall")
    public ResponseEntity<List<Award>> displayAllAwards()
    {
        List<Award> awards = service.displayAllAwards();

        return ResponseEntity.ok(awards);
    }

    @GetMapping("/display")
    public ResponseEntity<?> displayAwardById(@RequestParam Long id)
    {
        Award award = service.displayAwardById(id);

        if(award != null)
        {
            return ResponseEntity.ok(award);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Award ID Not Found");
      
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAward(@RequestBody Award award)
    {
        Award a = service.updateAward(award);

        if(a != null)
        {
            return ResponseEntity.ok("Award Updated Successfully");
                   
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) .body("Award ID Not Found");
                   
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAwardById(@PathVariable Long id)
    {
        String message = service.deleteAwardById(id);

        return ResponseEntity.ok(message);
    }

   
    @GetMapping("/findbyname")
    public ResponseEntity<List<Award>> findByName(@RequestParam String name)
    {
        List<Award> awards = service.findByName(name);

        return ResponseEntity.ok(awards);
    }

    
    @GetMapping("/findbytype")
    public ResponseEntity<List<Award>> findByType(@RequestParam String type)
    {
        List<Award> awards = service.findByType(type);

        return ResponseEntity.ok(awards);
    }
}