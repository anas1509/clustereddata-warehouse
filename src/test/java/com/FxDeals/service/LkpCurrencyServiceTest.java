package com.FxDeals.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.FxDeals.repo.LkpCurrencyRepo;

public class LkpCurrencyServiceTest {

	@InjectMocks
	private LkpCurrencyService lkpCurrencyService;

	@Mock
	private LkpCurrencyRepo lkpCurrencyRepo;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllCurrenciesIsoCodes() {
		List<String> expectedCurrencies = Arrays.asList("USD", "EUR", "GBP");
		when(lkpCurrencyRepo.getAllCurrenciesIsoCodes()).thenReturn(expectedCurrencies);

		List<String> actualCurrencies = lkpCurrencyService.getAllCurrenciesIsoCodes();

		assertEquals(expectedCurrencies, actualCurrencies);
	}

	@Test
	public void testGetAllCurrenciesIsoCodes_Empty() {
		List<String> expectedCurrencies = Arrays.asList();
		when(lkpCurrencyRepo.getAllCurrenciesIsoCodes()).thenReturn(expectedCurrencies);

		List<String> actualCurrencies = lkpCurrencyService.getAllCurrenciesIsoCodes();

		assertEquals(expectedCurrencies, actualCurrencies);
	}

	@Test
    public void testGetAllCurrenciesIsoCodes_Null() {
        when(lkpCurrencyRepo.getAllCurrenciesIsoCodes()).thenReturn(null);

        List<String> actualCurrencies = lkpCurrencyService.getAllCurrenciesIsoCodes();

        assertEquals(null, actualCurrencies);
    }
}
