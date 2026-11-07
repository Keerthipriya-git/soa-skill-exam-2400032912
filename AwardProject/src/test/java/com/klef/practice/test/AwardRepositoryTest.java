
package com.klef.practice.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.klef.practice.entity.Award;
import com.klef.practice.repository.AwardRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AwardRepositoryTest {

    @Autowired
    private AwardRepository repository;

    @Test
    void testFindAllAwards() {

        List<Award> awards = repository.findAll();

        assertNotNull(awards);
        assertFalse(awards.isEmpty());

        System.out.println("Total Awards: " + awards.size());
    }

    
    @Test
    void testFindAwardById() {

        List<Award> awards = repository.findAll();
        assertFalse(awards.isEmpty());

        Long id = awards.get(0).getAwardId();

        Optional<Award> award = repository.findById(id);

        assertTrue(award.isPresent());
        assertEquals(id, award.get().getAwardId());

        System.out.println("Award ID: " + award.get().getAwardId());
        System.out.println("Award Name: " + award.get().getName());
    }

   
    @Test
    void testFindAwardByName() {

        List<Award> awards = repository.findAll();
        assertFalse(awards.isEmpty());

        String name = awards.get(0).getName();

        List<Award> result = repository.findByName(name);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(name, result.get(0).getName());

        System.out.println("Award Name: " + name);
    }

    
    @Test
    void testFindAwardByInvalidName() {

        List<Award> result =
                repository.findByName("InvalidAwardName123");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

  
    @Test
    void testDatabaseConnection() {

        long count = repository.count();

        assertTrue(count >= 0);

        System.out.println("Total Awards in Database: " + count);
    }

    @Test
    void testAwardDetails() {

        List<Award> awards = repository.findAll();

        assertFalse(awards.isEmpty());

        Award foundAward = awards.get(0);

        assertNotNull(foundAward.getAwardId());
        assertNotNull(foundAward.getName());
        assertNotNull(foundAward.getType());
        assertNotNull(foundAward.getLocation());

        System.out.println("Award ID: " + foundAward.getAwardId());
        System.out.println("Name: " + foundAward.getName());
        System.out.println("Type: " + foundAward.getType());
        System.out.println("Status: " + foundAward.isStatus());
        System.out.println("Location: " + foundAward.getLocation());
        System.out.println("Created At: " + foundAward.getCreatedAt());
        System.out.println("Updated At: " + foundAward.getUpdatedAt());
    }

   
    @Test
    void testFindAwardByType() {

        List<Award> awards = repository.findAll();
        assertFalse(awards.isEmpty());

        String type = awards.get(0).getType();

        List<Award> result = repository.findByType(type);

        assertNotNull(result);
        assertFalse(result.isEmpty());

        for (Award award : result) {
            assertEquals(type, award.getType());
        }

        System.out.println("Award Type: " + type);
        System.out.println("Awards with this type: " + result.size());
    }
    @Test
    void testTotalAwardCount() {

        long count = repository.count();

        List<Award> awards = repository.findAll();

        assertEquals(count, (long) awards.size());

        System.out.println("Total Award Count: " + count);
    }

   
    @Test
    void testFindAwardByInvalidType() {

        List<Award> result =
                repository.findByType("InvalidType123");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
