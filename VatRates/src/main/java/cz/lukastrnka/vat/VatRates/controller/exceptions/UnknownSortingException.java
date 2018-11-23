package cz.lukastrnka.vat.VatRates.controller.exceptions;

public class UnknownSortingException extends Exception {
	String sorting;

	public UnknownSortingException(String period) {
		super();
		this.sorting = period;
	}

	public String getPeriod() {
		return sorting;
	}

	public void setPeriod(String period) {
		this.sorting = period;
	}
}
