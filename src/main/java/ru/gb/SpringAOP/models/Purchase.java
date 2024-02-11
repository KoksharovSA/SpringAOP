package ru.gb.SpringAOP.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "purchase")
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    private String userName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
