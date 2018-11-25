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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((effective_from == null) ? 0 : effective_from.hashCode());
		result = prime * result + ((rates == null) ? 0 : rates.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Period other = (Period) obj;
		if (effective_from == null) {
			if (other.effective_from != null)
				return false;
		} else if (!effective_from.equals(other.effective_from))
			return false;
		if (rates == null) {
			if (other.rates != null)
				return false;
		} else if (!rates.equals(other.rates))
			return false;
		return true;
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
