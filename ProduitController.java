package com.example.produit.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.produit.entities.Produit;
import com.example.produit.service.ProduitService;

@Controller
public class ProduitController {
@Autowired
ProduitService produitService;
@GetMapping("/showCreate")
public String showCreate()
{
return "createProduit";
}
@GetMapping("/saveProduit")
public String saveProduit(@ModelAttribute Produit produit,
 @RequestParam String date,
 ModelMap modelMap) throws
ParseException
{
//conversion de la date
 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
 Date dateCreation = dateformat.parse(String.valueOf(date));
 produit.setDateCreation(dateCreation);

Produit saveProduit = produitService.saveProduit(produit);
String msg ="produit enregistré avec Id "+saveProduit.getIdProduit();
modelMap.addAttribute("msg", msg);
return "createProduit";
}
@GetMapping("/ListeProduits")
public String listeProduits(ModelMap modelMap,
		@RequestParam (name="page",defaultValue = "0") int page,
		@RequestParam (name="size", defaultValue = "2") int size		)
{
	Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
	modelMap.addAttribute("produits", prods);
	 modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	return "listeProduits";
}
@RequestMapping("/supprimerProduit")
public String supprimerProduit(@RequestParam("id") Long id,
 ModelMap modelMap,
 @RequestParam (name="page",defaultValue = "0") int page,
 @RequestParam (name="size", defaultValue = "2") int size)

 
{ 
produitService.deleteProduitById(id);
Page<Produit> prods = produitService.getAllProduitsParPage(page,
size);
modelMap.addAttribute("produits", prods);
modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
modelMap.addAttribute("currentPage", page);
modelMap.addAttribute("size", size);
return "listeProduits";

}
@RequestMapping("/modifierProduit")
public String editerProduit(@RequestParam("id") Long id,ModelMap modelMap)
{
Produit p= produitService.getProduit(id);
modelMap.addAttribute("produit", p);
return "editerProduit";
}
@RequestMapping("/updateProduit")
public String updateProduit(@ModelAttribute("produit") Produit produit,
@RequestParam("date") String date,

 ModelMap modelMap) throws ParseException 
{
//conversion de la date 
 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
 Date dateCreation = dateformat.parse(String.valueOf(date));
 produit.setDateCreation(dateCreation);
 
 produitService.updateProduit(produit);
 List<Produit> prods = produitService.getAllProduits();
 modelMap.addAttribute("produits", prods);
return "listeProduits";
}


}