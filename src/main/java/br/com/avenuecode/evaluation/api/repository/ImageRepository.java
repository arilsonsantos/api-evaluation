package br.com.avenuecode.evaluation.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.avenuecode.evaluation.api.model.Image;
import br.com.avenuecode.evaluation.api.model.Product;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

	@Query(value="select img from Image img where img.id = :id")
	Image getOne(@Param("id") Long imageId);
	
	@Query(value = "select img from Image img where img.product.id = :productId")
	List<Image> findByProductId(@Param("productId") Long productId);
	
	@Modifying
	@Query(value = "INSERT INTO image (type, product_id) VALUES (:type,:product_id)", nativeQuery = true)
	@Transactional
	void insert(@Param("type") String type, @Param("product_id") Long product_id);

	@Modifying
	@Transactional
	void delete(Long id);
	
	@Modifying
	@Transactional
	@Query(value="delete from Image img where img.product = :product")
	void delete(@Param("product") Product product);
}
