package com.p.lodz.pl.bookexchange.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.p.lodz.pl.bookexchange.entities.Intrests;

public interface IntrestsRepository extends JpaRepository<Intrests, Long> {
	List<Intrests> findAllByUserId(long userId);
	Intrests findByCategoryIdAndUserId(long categoryId, long userId);

}
