package com.example.produit.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.produit.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

}
