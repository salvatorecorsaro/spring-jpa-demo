package com.example.springjpademo.repository;

import com.example.springjpademo.dao.Color;
import com.example.springjpademo.dao.Fruit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@Sql(scripts={"classpath:data.sql"})
@SpringBootTest
class FruitRepositoryTest {

    @Autowired
    FruitRepository fruitRepository;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void countMethod_right() {
        var fruitCount = fruitRepository.count();
        assertEquals(4, fruitCount);
    }

    @Test
    void saveANewFruit() {
        var fruitCountBeforeSave = fruitRepository.count();
        var apple = new Fruit("Apple", Color.RED);
        fruitRepository.save(apple);
        var fruitCountAfterSave = fruitRepository.count();
        assertEquals(1, fruitCountAfterSave - fruitCountBeforeSave);
    }

    @Test
    void updateFruit() {
        var fruitToUpdate = fruitRepository.getFruitsByName("Banana").get();
        fruitToUpdate.setName("WaterMelon");
        fruitRepository.save(fruitToUpdate);
        assertTrue(fruitRepository.getFruitsByName("WaterMelon").isPresent());
    }

    @Test
    void deleteFruitById() {
        fruitRepository.save(new Fruit(null , "Papaya", Color.ORANGE, ""));
        var fruitCountBeforeSave = fruitRepository.count();
        var fruitToDelete = fruitRepository.getFruitsByName("Papaya").get();
        fruitRepository.deleteById(fruitToDelete.getId());
        var fruitCountAfterSave = fruitRepository.count();
        assertEquals(-1, fruitCountAfterSave - fruitCountBeforeSave);
    }

    @Test
    void getFruitByColor() {
        var greenFruits = fruitRepository.getFruitsByColor(Color.GREEN);
        assertEquals(1, greenFruits.size());
    }


    @Test
    void getFruitByNameAndColor() {
        var yellowBanana = fruitRepository.findFirstByColorAndName(Color.YELLOW, "Banana");
        assertTrue(yellowBanana.isPresent());
    }
}