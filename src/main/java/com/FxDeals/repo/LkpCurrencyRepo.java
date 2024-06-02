package com.FxDeals.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.FxDeals.model.LkpCurrency;

@Repository("LkpCurrencyRepo")
public interface LkpCurrencyRepo extends JpaRepository<LkpCurrency, Long> {

	// we can use a named query but writing a JPQL or native queries always gives us more control.

	@Query("SELECT l.alphabeticCode FROM LkpCurrency l")
	List<String> getAllCurrenciesIsoCodes();
}
