package br.com.avenuecode.evaluation.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.avenuecode.evaluation.api.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	// **** ALL PRODUCTS ***
	// Get all products excluding relationships (child products, images)
	List<Product> findAllWithoutRelationship();

	// Get all products including specified relationships (child product)
	List<Product> findAllFetchProduct();
	
	// Get all products including specified relationships (child product)
	List<Product> findAllFetchImage();

	// Get all products including specified relationships (child product and/or images)
	@Query(nativeQuery=true)
	List<Product> findAllFetchProductAndOrImage();

	// **** SPECIFIED PRODUCT ***
	// Get a specified product excluding relationships (child products, images)
	Product geOnetWithoutRelationship(@Param("productId") Long productId);

	// Get a specified product including specified relationships (child products)
	@Query(nativeQuery=true)
	List<Product> getOneFetchProduct(@Param("productId") Long productId);
	
	@Query(nativeQuery=true)
	// Get a specified product including specified relationships (child products)
	Product getOneFetchImage(@Param("productId") Long productId);
	
	@Query(nativeQuery=true)
	List<Product> getOneContainsProductAndOrImage(@Param("productId") Long productId);
	
	@Modifying
	@Transactional
	void delete(@Param("productId") Long productId);
	
}
