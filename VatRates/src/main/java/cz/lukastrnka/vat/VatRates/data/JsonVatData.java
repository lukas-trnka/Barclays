package cz.lukastrnka.vat.VatRates.data;

import java.util.List;

public class JsonVatData {
	private String details;
	private String version;
	private List<Country> rates;

	public JsonVatData(String details, String version, List<Country> rates) {
		super();
		this.details = details;
		this.version = version;
		this.rates = rates;
	}

	public JsonVatData() {
		super();
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<Country> getRates() {
		return rates;
	}

	public void setRates(List<Country> rates) {
		this.rates = rates;
	}

}
