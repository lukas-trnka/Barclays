package cz.lukastrnka.vat.VatRates.data;

import java.util.Collections;
import java.util.Comparator;

public class CountryComparatorByCurrentStdVAT implements Comparator<Country> {

	/***
	 * 
	 */
	public int compare(Country o1, Country o2) {
		
		Collections.sort(o1.getPeriods(), new PeriodsCompareByDateDesc());	// Makes sure, that the most current period is on index 0
		Collections.sort(o2.getPeriods(), new PeriodsCompareByDateDesc());	// Makes sure, that the most current period is on index 0
		
		if (o1.getPeriods().get(0).getRates().getStandard() < o2.getPeriods().get(0).getRates().getStandard())
			return -1;
		if (o1.getPeriods().get(0).getRates().getStandard() > o2.getPeriods().get(0).getRates().getStandard())
			return 1;
		return 0;
	}

}
