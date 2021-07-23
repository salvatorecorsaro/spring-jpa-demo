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
    void saveANewFruit() {
        var fruitCountBeforeSave = fruitRepository.count();
        var apple = new Fruit("Apple", Color.RED);
        fruitRepository.save(apple);
        var fruitCountAfterSave = fruitRepository.count();
        assertEquals(1, fruitCountAfterSave - fruitCountBeforeSave);
    }

    @Test
    void countMethod_right() {
        var fruitCount = fruitRepository.count();
        assertEquals(4, fruitCount);
    }

    @Test
    void updateFruit() {
        var fruitToUpdate = fruitRepository.findByName("Banana").get();
        fruitToUpdate.setName("WaterMelon");
        fruitRepository.save(fruitToUpdate);
        assertTrue(fruitRepository.findByName("WaterMelon").isPresent());
    }

    @Test
    void deleteFruitById() {
        fruitRepository.save(new Fruit(null , "Papaya", Color.ORANGE, ""));
        var fruitCountBeforeSave = fruitRepository.count();
        var fruitToDelete = fruitRepository.findByName("Papaya").get();
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

    @Test
    void findByNameStartingWith_positive() {
        var durStartingFruits = fruitRepository.findByNameStartingWithIgnoreCase("dur");
        assertEquals(1, durStartingFruits.size());
    }

    @Test
    void findAllNoteNull_findOne () {
        var nullNote = fruitRepository.findAllNoteNull();
        assertEquals("Kiwi", nullNote.get(0).getName());
    }

    @Test
    void findAllWithNoteOrderByNameAsc_testOrdered() {
        var orderedCompleted = fruitRepository.findAllWithNoteOrderByNameAsc();
        int lastElementIndex = orderedCompleted.size() - 1;
        assertEquals("Strawberry", orderedCompleted.get(lastElementIndex).getName());
    }

    @Test
    void findByNameUsingQuery_positive() {
        var banana = fruitRepository.findByNameUsingQuery("Banana");
        assertTrue(banana.isPresent());
    }

    @Test
    void findByNameUsingNativeQuery_isPresent() {
        var kiwi = fruitRepository.findByNameUsingNativeQuery("Kiwi");
        assertTrue(kiwi.isPresent());
    }

    @Test
    void testFindByColorUsingNativeQuery_rightSize() {
        var red = fruitRepository.findByColorUsingNativeQuery(Color.RED.toString());
        assertEquals(1, red.size());
    }

    @Test
    void findAllNames_listOfNames() {
        var onlyNames = fruitRepository.findAllNames();
        assertTrue(!onlyNames.isEmpty());
    }

    @Test
    void dropFruit() {
        fruitRepository.dropFruit();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}