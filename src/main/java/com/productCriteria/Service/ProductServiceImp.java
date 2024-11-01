package com.productCriteria.Service;

import com.productCriteria.Entity.Product;
import com.productCriteria.Exception.ProductNotFound;
import com.productCriteria.Repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;
    private final EntityManager entityManager;

    @Override
    public Product add(Product product) {
        product.setId(UUID.randomUUID().toString());
        return productRepository.save(product);
    }

    @Override
    public List<Product> all() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product product) {
        Product productToUpdate = getById(product.getId());
        productToUpdate.setName(product.getName());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setPrice(product.getPrice());
        return productRepository.save(productToUpdate);
    }

    @Override
    public void delete(String id) {
        productRepository.delete(getById(id));
    }

    @Override
    public Product getById(String id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ProductNotFound(id)
        );
    }

    @Override
    public List<Product> findProductsByPrice(double min, double max) {
        //crée une instance de CriteriaBuilder, qui est utilisée pour construire des requêtes de critères
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //crée une instance de CriteriaQuery pour l'entité Product.
        //Cela signifie que nous allons construire une requête qui retourne des objets Product
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        //définit la racine de la requête, qui correspond à la table Product dans la base de données.
        //Cela permet d'accéder aux colonnes de l'entité Product pour les utiliser dans la requête
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        criteriaQuery.select(productRoot)
                .where(criteriaBuilder.between(productRoot.get("price"),min,max));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Product> findProductsByNameContains(String substring) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        criteriaQuery.select(productRoot)
                .where(criteriaBuilder.like(productRoot.get("name"),"%"+substring+"%"));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
