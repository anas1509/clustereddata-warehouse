package com.FxDeals.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.FxDeals.exception.BusinessException;
import com.FxDeals.model.Deal;
import com.FxDeals.repo.DealRepo;

public class DealServiceTest {

	@InjectMocks
	private DealService dealService;

	@Mock
	private DealRepo dealRepo;

	@Mock
	private LkpCurrencyService lkpCurrencyService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSaveDeal_ValidDeal_Success() {
		Deal deal = new Deal();
		deal.setUuid("123e4567-e89b-12d3-a456-426614174023");
		deal.setFromCurrencyIsoCode("USD");
		deal.setToCurrencyIsoCode("EUR");
		deal.setDealTimestamp(LocalDateTime.now());
		deal.setAmount(BigDecimal.valueOf(1000));

		when(dealRepo.existitingDealsCount(deal.getUuid())).thenReturn(0L);
		when(lkpCurrencyService.getAllCurrenciesIsoCodes()).thenReturn(List.of("USD", "EUR"));

		dealService.saveDeal(deal);

		verify(dealRepo, times(1)).save(deal);
	}

	@Test
	public void testSaveDeal_DuplicateDeal_ThrowsException() {
		Deal deal = new Deal();
		deal.setUuid("123e4567-e89b-12d3-a456-426614174000");
		deal.setFromCurrencyIsoCode("USD");
		deal.setToCurrencyIsoCode("EUR");
		deal.setDealTimestamp(LocalDateTime.now());
		deal.setAmount(BigDecimal.valueOf(1000));

		when(dealRepo.existitingDealsCount(deal.getUuid())).thenReturn(1L);

		BusinessException exception = assertThrows(BusinessException.class, () ->
			{
				dealService.saveDeal(deal);
			});

		assertEquals("duplicatedDealException", exception.getCode());
	}

	@Test
	public void testSaveDeal_InvalidCurrencyFromDeal_ThrowsException() {
		Deal deal = new Deal();
		deal.setUuid("123e4567-e89b-12d3-a456-426614178888");
		deal.setFromCurrencyIsoCode("USDDD");
		deal.setToCurrencyIsoCode("EUR");
		deal.setDealTimestamp(LocalDateTime.now());
		deal.setAmount(BigDecimal.valueOf(1000));

		when(dealRepo.existitingDealsCount(deal.getUuid())).thenReturn(0L);
		when(lkpCurrencyService.getAllCurrenciesIsoCodes()).thenReturn(List.of("USD", "EUR"));

		BusinessException exception = assertThrows(BusinessException.class, () ->
			{
				dealService.saveDeal(deal);
			});

		assertEquals("invalidCurrencyFromIsoCode", exception.getCode());
	}

	@Test
	public void testSaveDeal_InvalidCurrencyToDeal_ThrowsException() {
		Deal deal = new Deal();
		deal.setUuid("123e4567-e89b-12d3-a456-426614174045");
		deal.setFromCurrencyIsoCode("USD");
		deal.setToCurrencyIsoCode("EUORO");
		deal.setDealTimestamp(LocalDateTime.now());
		deal.setAmount(BigDecimal.valueOf(1000));

		when(dealRepo.existitingDealsCount(deal.getUuid())).thenReturn(0L);
		when(lkpCurrencyService.getAllCurrenciesIsoCodes()).thenReturn(List.of("USD", "EUR"));

		BusinessException exception = assertThrows(BusinessException.class, () ->
			{
				dealService.saveDeal(deal);
			});

		assertEquals("invalidCurrencyToIsoCode", exception.getCode());
	}

	@Test
	public void testSaveDeal_InvalidAmountDeal_ThrowsException() {
		Deal deal = new Deal();
		deal.setUuid("123e4567-e89b-12d3-a456-426614174045");
		deal.setFromCurrencyIsoCode("USD");
		deal.setToCurrencyIsoCode("JOD");
		deal.setDealTimestamp(LocalDateTime.now());
		deal.setAmount(BigDecimal.valueOf(-250));

		when(dealRepo.existitingDealsCount(deal.getUuid())).thenReturn(0L);
		when(lkpCurrencyService.getAllCurrenciesIsoCodes()).thenReturn(List.of("USD", "JOD"));

		BusinessException exception = assertThrows(BusinessException.class, () ->
			{
				dealService.saveDeal(deal);
			});

		assertEquals("invalidDealAmount", exception.getCode());
	}
}
