package cz.lukastrnka.vat.VatRates.data;

import java.util.Date;

public class Period {
	private Date effective_from;
	private Rates rates;

	public Period(Date effective_from, Rates rates) {
		super();
		this.effective_from = effective_from;
		this.rates = rates;
	}

	public Period() {
		super();
	}

	public Date getEffective_from() {
		return effective_from;
	}

	public void setEffective_from(Date effective_from) {
		this.effective_from = effective_from;
	}

	public Rates getRates() {
		return rates;
	}

	public void setRates(Rates rates) {
		this.rates = rates;
	}

}
