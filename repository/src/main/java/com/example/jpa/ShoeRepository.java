package com.example.jpa;

import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.dto.out.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface ShoeRepository extends JpaRepository<ShoeFilter, Long> {
    String UPDATE_SHOE = "UPDATE shoe s SET s.quantity = :quantity WHERE s.ID = :id";

    ShoeFilter       findById(long id);
    List<ShoeFilter> findAll();
    ShoeFilter       findByColorAndSize(ShoeFilter.Color color, BigInteger size);
    ShoeFilter       save(ShoeFilter shoeFilter);

    @Modifying
    @Transactional
    @Query(value = UPDATE_SHOE, nativeQuery = true)
    int updateShoeQuantity(@Param("id")Long id, @Param("quantity")BigInteger quantity);
}