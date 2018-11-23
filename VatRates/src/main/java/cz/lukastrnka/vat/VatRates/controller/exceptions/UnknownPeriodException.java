package cz.lukastrnka.vat.VatRates.controller.exceptions;

public class UnknownPeriodException extends Exception {
	String period;

	public UnknownPeriodException(String period) {
		super();
		this.period = period;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	
}
