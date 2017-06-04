package com.p.lodz.pl.bookexchange.services;

public interface SecurityService {

	String findLoggedInUsername();

	void autoLogin(String username, String password);

}
