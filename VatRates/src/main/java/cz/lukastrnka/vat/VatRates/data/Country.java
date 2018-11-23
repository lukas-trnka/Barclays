package cz.lukastrnka.vat.VatRates.data;

import java.util.List;

public class Country {

	private String name;
	private String code;
	private String country_code;
	private List<Period> periods;

	public Country(String name, String code, String country_code, List<Period> periods) {
		super();
		this.name = name;
		this.code = code;
		this.country_code = country_code;
		this.periods = periods;
	}

	public Country() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public List<Period> getPeriods() {
		return periods;
	}

	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}

}
