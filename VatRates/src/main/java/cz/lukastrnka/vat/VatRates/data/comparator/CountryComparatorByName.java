package cz.lukastrnka.vat.VatRates.data.comparator;

import java.util.Comparator;

import cz.lukastrnka.vat.VatRates.data.Country;

public class CountryComparatorByName implements Comparator<Country> {
	public int compare(Country o1, Country o2) {

		return o1.getName().compareTo(o2.getName());
	}
}
