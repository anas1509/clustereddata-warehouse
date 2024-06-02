package com.FxDeals.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "lkp_currency")
public class LkpCurrency {

	/*
	 * There is no need to extend the base entity here, as I do not support update or add operation for this entity,
	 * the data is imported as SQL script.
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;

	@Column(name = "country")
	@NotEmpty
	private String country;

	@Column(name = "currency")
	@NotEmpty
	private String currency;

	@Column(name = "alphabetic_code")
	private String alphabeticCode;

	@Column(name = "numeric_code")
	private String numericCode;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAlphabeticCode() {
		return alphabeticCode;
	}

	public void setAlphabeticCode(String alphabeticCode) {
		this.alphabeticCode = alphabeticCode;
	}

	public String getNumericCode() {
		return numericCode;
	}

	public void setNumericCode(String numericCode) {
		this.numericCode = numericCode;
	}

}
