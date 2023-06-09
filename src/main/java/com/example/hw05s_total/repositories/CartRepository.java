package com.example.hw05s_total.repositories;

import com.example.hw05s_total.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Integer> {
    
    List<Cart> findByPersonId(int id);
    
//    @Query(value = "select count(product_id), product_id from product_cart where person_id = ?1 group by product_id", nativeQuery = true)
    List<Cart> findDistinctProductByPersonId(int id);
    
    @Modifying
    @Query(value = "delete from product_cart where product_cart.product_id = ?1", nativeQuery = true)
    void deleteCartByProductId(int id);
    
    
}
