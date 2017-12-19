package com.p.lodz.pl.bookexchange.services;

import java.util.List;

import com.p.lodz.pl.bookexchange.entities.Intrests;

public interface IntrestsService {
	public List<Intrests> findAllByUserId(long userId);
	public void save(Intrests intrest);

}
