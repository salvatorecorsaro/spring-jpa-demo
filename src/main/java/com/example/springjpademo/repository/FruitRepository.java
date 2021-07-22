package com.example.springjpademo.repository;

import com.example.springjpademo.dao.Color;
import com.example.springjpademo.dao.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface FruitRepository extends JpaRepository<Fruit, Long> {
    Optional<Fruit> findByName(String name);
    List<Fruit> getFruitsByColor(Color color);
    Optional<Fruit> findFirstByColorAndName(Color color, String name);
    List<Fruit> findByNameStartingWithIgnoreCase(String name);
    List<Fruit> findByNameContainingIgnoreCase(String name);
    List<Fruit> findByNameEndingWithIgnoreCase(String name);

//    JPQL Queries

    @Query("SELECT f FROM Fruit f WHERE f.note IS NULL ")
    List<Fruit> findAllNoteNull();

    @Query("SELECT f FROM Fruit f WHERE f.note IS NOT NULL ORDER BY f.name ASC ")
    List<Fruit> findAllWithNoteOrderByNameAsc();

    @Query("SELECT f FROM Fruit f WHERE f.name = ?1 ") // ?1 means it will use the first parameter we pass to the method "String name"
    Optional<Fruit> findByNameUsingQuery(String name);

//    Native query

    @Query(value = "SELECT * FROM fruits f WHERE name = ?1", nativeQuery = true) // notice we are using directly the table name and column name
    Optional<Fruit> findByNameUsingNativeQuery(String name);

    @Query(value = "SELECT * FROM fruits f WHERE color = :color", nativeQuery = true) // using a different way to pass the parameter
    List<Fruit> findByColorUsingNativeQuery(@Param("color") String color);

    @Query(value="SELECT name from fruits", nativeQuery = true)
    List<String> findAllNames();

}
