package com.FxDeals.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FxDeals.repo.LkpCurrencyRepo;

@Service("LkpCurrencyService")
public class LkpCurrencyService {

	/**
	 * This is a helper service that is used to validate the currencies ISO codes.
	 */

	@Autowired
	private LkpCurrencyRepo repo;

	public List<String> getAllCurrenciesIsoCodes() {
		return repo.getAllCurrenciesIsoCodes();
	}

}
