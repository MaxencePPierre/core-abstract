package com.example.jpa;

import com.example.demo.dto.in.ShoeFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ShoeRepository extends JpaRepository<ShoeFilter, Long> {
    ShoeFilter       findById(long id);
    List<ShoeFilter> findAll();
    ShoeFilter       findByColorAndSize(ShoeFilter.Color color, BigInteger size);
}