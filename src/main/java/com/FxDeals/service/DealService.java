package com.FxDeals.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FxDeals.exception.BusinessException;
import com.FxDeals.model.Deal;
import com.FxDeals.repo.DealRepo;

@Service("DealService")
public class DealService {

	/**
	 * As requested, the Deal has no roll back policy, whenever a deal is saved there is no API to update or delete from the DB.
	 * So the only operation that are supported are save and get.
	 */

	@Autowired
	private DealRepo repo;

	@Autowired
	private LkpCurrencyService lkpCurrencyService;

	private static final Logger logger = Logger.getLogger(DealService.class.getName());

	public void saveDeal(Deal deal) {
		if (validateDeal(deal)) {
			logger.info("saving Deal with uuid: " + deal.getUuid());
			deal.setCreationDate(new Date());
			deal.setDealTimestamp(LocalDateTime.now());
			repo.save(deal);
		}
	}

	private Boolean validateDeal(Deal deal) {

		// a private method to validate the Deal before saving.

		// check if there is a deal with the same UUID in the DB, assuming that the UUID is the business identifier of the deal.
		if (repo.existitingDealsCount(deal.getUuid()) > 0) {
			BusinessException e = new BusinessException("duplicatedDealException", "Deal with uuid " + deal.getUuid() + " already exists.",
					400);
			logger.warning("exception in: " + DealService.class.getName() + " " + e.getCode());
			throw e;
		}

		// validate the Deal currency from & currency to, using a lookup table with values from an online data set.
		// only to validate the ISO code.

		if (!lkpCurrencyService.getAllCurrenciesIsoCodes().contains(deal.getToCurrencyIsoCode())) {
			BusinessException e = new BusinessException("invalidCurrencyToIsoCode", "Please enter a valid currency to ISO code.", 400);
			logger.warning("exception in: " + DealService.class.getName() + " " + e.getCode());
			throw e;
		}

		if (!lkpCurrencyService.getAllCurrenciesIsoCodes().contains(deal.getFromCurrencyIsoCode())) {
			BusinessException e = new BusinessException("invalidCurrencyFromIsoCode", "Please enter a valid currency from ISO code.", 400);
			logger.warning("exception in: " + DealService.class.getName() + " " + e.getCode());
			throw e;
		}

		// check the Deal amount, as it does not make sense to have a negative value in a deal.
		if (deal.getAmount().compareTo(new BigDecimal("0")) != 1) {
			BusinessException e = new BusinessException("invalidDealAmount", "Please enter a valid amount.", 400);
			logger.warning("exception in: " + DealService.class.getName() + " " + e.getCode());
			throw e;
		}

		return true;
	}

	public Deal getDealByUuid(String uuid) {
		return repo.getDealByUuid(uuid);
	}

	public List<Deal> getAllDeals() {
		return repo.getAllDeals();
	}

}
