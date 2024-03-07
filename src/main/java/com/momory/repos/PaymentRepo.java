package com.momory.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momory.entitys.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long>{

}