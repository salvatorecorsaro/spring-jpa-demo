package com.example.springjpademo.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fruits")
public class Fruit {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Color color;
    private String note;


    public Fruit(String name, Color color, String note) {
        this.name = name;
        this.color = color;
        this.note = note;
    }

    public Fruit(String name, Color color) {
        this.name = name;
        this.color = color;
    }
}
