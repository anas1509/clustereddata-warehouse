package com.FxDeals.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "deal")
public class Deal extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "uuid", unique = true)
	@NotEmpty
	private String uuid;

	@Column(name = "from_currency_iso_code")
	@NotEmpty
	private String fromCurrencyIsoCode;

	@Column(name = "to_currency_iso_code")
	@NotEmpty
	private String toCurrencyIsoCode;

	@Column(name = "deal_time_stamp")
	@NotNull
	private LocalDateTime dealTimestamp;

	@Column(name = "amount")
	@NotNull
	private BigDecimal amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFromCurrencyIsoCode() {
		return fromCurrencyIsoCode;
	}

	public void setFromCurrencyIsoCode(String fromCurrencyIsoCode) {
		this.fromCurrencyIsoCode = fromCurrencyIsoCode;
	}

	public String getToCurrencyIsoCode() {
		return toCurrencyIsoCode;
	}

	public void setToCurrencyIsoCode(String toCurrencyIsoCode) {
		this.toCurrencyIsoCode = toCurrencyIsoCode;
	}

	public LocalDateTime getDealTimestamp() {
		return dealTimestamp;
	}

	public void setDealTimestamp(LocalDateTime dealTimestamp) {
		this.dealTimestamp = dealTimestamp;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
