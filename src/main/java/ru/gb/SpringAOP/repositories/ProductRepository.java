package ru.gb.SpringAOP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.SpringAOP.models.Product;

import java.util.List;

/**
 * Класс JPA репозитория для общения с базой данных
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
