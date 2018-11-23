package cz.lukastrnka.vat.VatRates.data;

import java.util.Comparator;

public class CountryCompareByName implements Comparator<Country> {
	public int compare(Country o1, Country o2) {

		return o1.getName().compareTo(o2.getName());
	}
}
