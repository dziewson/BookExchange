package com.p.lodz.pl.bookexchange.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p.lodz.pl.bookexchange.entities.Intrests;
import com.p.lodz.pl.bookexchange.repositories.IntrestsRepository;
@Service
public class IntrestsServiceImpl implements IntrestsService {
	@Autowired
	IntrestsRepository intrestRepo;
	@Override
	public List<Intrests> findAllByUserId(long userId) {
		return intrestRepo.findAllByUserId(userId);
	}
	@Override
	public void save(Intrests intrest) {
		if(intrestRepo.findByCategoryIdAndUserId(intrest.getCategoryId(), intrest.getUserId()) == null)
		intrestRepo.save(intrest);
		
	}

}
