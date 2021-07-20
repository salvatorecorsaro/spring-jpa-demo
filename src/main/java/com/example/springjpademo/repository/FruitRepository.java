package com.example.springjpademo.repository;

import com.example.springjpademo.dao.Color;
import com.example.springjpademo.dao.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface FruitRepository extends JpaRepository<Fruit, Long> {
    Optional<Fruit> getFruitsByName(String name);
    List<Fruit> getFruitsByColor(Color color);
    Optional<Fruit> findFirstByColorAndName(Color color, String name);

}
