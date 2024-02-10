package ru.gb.SpringAOP.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.SpringAOP.models.Product;
import ru.gb.SpringAOP.models.Purchase;
import ru.gb.SpringAOP.repositories.ProductRepository;
import ru.gb.SpringAOP.repositories.PurchaseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public List<Purchase> getAllPurchase() {
        return purchaseRepository.findAll();
    }

    public Purchase getPurchaseById(Long id) {
        return purchaseRepository.findById(id).orElseThrow(null);
    }

    public void addPurchase(Purchase purchase){
        purchaseRepository.save(purchase);
    }
}
