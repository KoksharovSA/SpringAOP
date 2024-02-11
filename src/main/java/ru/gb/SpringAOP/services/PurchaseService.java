package ru.gb.SpringAOP.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.gb.SpringAOP.aspects.TrackUserAction;
import ru.gb.SpringAOP.models.Purchase;
import ru.gb.SpringAOP.repositories.PurchaseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    @TrackUserAction
    public List<Purchase> getAllPurchase() {
        return purchaseRepository.findAll();
    }

    public List<Purchase> getAllPurchasesUser() {
        return getAllPurchase().stream().filter(x->x.getUserName().equals(SecurityContextHolder.getContext().getAuthentication().getName())).toList();
    }

    public Purchase getPurchaseById(Long id) {
        return purchaseRepository.findById(id).orElseThrow(null);
    }
    @TrackUserAction
    public void addPurchase(Purchase purchase){
        purchaseRepository.save(purchase);
    }
    @TrackUserAction
    public void deletePurchaseById(Long id){
        purchaseRepository.deleteById(id);
    }
}
