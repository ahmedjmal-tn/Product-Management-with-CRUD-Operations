package com.boostmytool.beststore.controllers;

import com.boostmytool.beststore.models.ProductDto;
import com.boostmytool.beststore.services.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.boostmytool.beststore.models.Product;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository repo;
    @GetMapping({"","/"})
    public String showProducts(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products",products);
        return "products/index";
    }
    @GetMapping("/create")
    public String showCreateProductForm(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "products/addProd";
    }
    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute ProductDto productDto, BindingResult result) {
        // Validation du nom du fichier d'image
        if (productDto.getImageeFileName() == null || productDto.getImageeFileName().isEmpty()) {
            result.addError(new FieldError("productDto", "imageeFileName", "Vous devez fournir un nom de fichier"));
        }

        if (result.hasErrors()) {
            return "products/addProd"; // Retourner au formulaire avec les erreurs de validation
        }




            // Sauvegarde des informations du produit dans la base de données
            Product product = new Product();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setCategory(productDto.getCategory());
            product.setBrand(productDto.getBrand());
            product.setDescription(productDto.getDescription());
            product.setCreatedAt(new Date());
            repo.save(product);


        return "redirect:/products"; // Redirection vers la liste des produits après création réussie
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = repo.findById(Math.toIntExact(id));
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            repo.delete(product);
        }
        return "redirect:/products"; // Redirect to the product list after deletion
    }
}
