package cz.lukastrnka.vat.VatRates.data.comparator;

import java.util.Comparator;

import cz.lukastrnka.vat.VatRates.data.Country;

public class CountryComparatorByStdVAT implements Comparator<Country> {

	public int compare(Country o1, Country o2) {
	
		if (o1.getPeriods().get(0).getRates().getStandard() < o2.getPeriods().get(0).getRates().getStandard())
			return -1;
		if (o1.getPeriods().get(0).getRates().getStandard() > o2.getPeriods().get(0).getRates().getStandard())
			return 1;
		return 0;
	}

}
