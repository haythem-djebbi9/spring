package com.example.produit;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.produit.entities.Produit;
import com.example.produit.repos.ProduitRepository;
import com.example.produit.service.ProduitService;

@SpringBootTest
class ProduitsApplicationTests {
    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ProduitService produitService;

    @Test
    void testCreateProduit() {
        Produit prod = new Produit("PC Dell", 2200.500, new Date());
        produitRepository.save(prod);
    }

    @Test
    void testFindProduit() {
        Produit p = produitRepository.findById(1L).get();
        System.out.println(p);
    }

    @Test
    void testUpdateProduit() {
        Produit p = produitRepository.findById(1L).get();
        p.setPrixProduit(1000.0);
        produitRepository.save(p);
        System.out.println(p);
    }

    @Test
    void testDeleteProduit() {
        produitRepository.deleteById(1L);
    }

    @Test
    void testListerTousProduits() {
        List<Produit> prods = produitRepository.findAll();
        for (Produit p : prods) {
            System.out.println(p);
        }
    }

    @Test
    public void testFindByNomProduitContains() {
        Page<Produit> prods = produitService.getAllProduitsParPage(0, 2);
        System.out.println(prods.getSize());
        System.out.println(prods.getTotalElements());
        System.out.println(prods.getTotalPages());
        prods.getContent().forEach(p -> {
            System.out.println(p.toString());
        });
        /*ou bien
        for (Produit p : prods) {
            System.out.println(p);
        } */
    }
}
