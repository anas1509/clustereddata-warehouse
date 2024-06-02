package com.FxDeals.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FxDeals.model.Deal;
import com.FxDeals.service.DealService;

@RestController
@RequestMapping(value = "/service")
public class DealController {

	/**
	 * As requested, the Deal has no roll back policy, whenever a deal is saved there is no API to update or delete from the DB.
	 * So the only operation that are supported are save and get.
	 */
	@Autowired
	private DealService dealService;

	@RequestMapping(value = "/getDealByUuid", method = RequestMethod.GET)
	public ResponseEntity<Deal> getDealByUuid(@RequestParam String uuid) {
		return new ResponseEntity<Deal>(dealService.getDealByUuid(uuid), HttpStatus.OK);
	}

	@RequestMapping(value = "/saveDeal", method = RequestMethod.POST)
	public ResponseEntity<Void> saveDeal(@RequestBody Deal deal) {
		dealService.saveDeal(deal);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllDeals", method = RequestMethod.GET)
	public ResponseEntity<List<Deal>> getAllDeals() {
		return new ResponseEntity<List<Deal>>(dealService.getAllDeals(), HttpStatus.OK);
	}

}
