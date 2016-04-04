package com.festivities_register.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festivities_register.domain.Festivity;

public interface FestivityRepository extends JpaRepository<Festivity, Long>{

}
