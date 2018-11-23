package cz.lukastrnka.vat.VatRates.data;

import java.util.Comparator;

public class PeriodsCompareByDateDesc implements Comparator<Period> {

	public int compare(Period o1, Period o2) {
		if (o1.getEffective_from().before(o2.getEffective_from()))
			return 1;
		if (o1.getEffective_from().after(o2.getEffective_from()))
			return -1;
		return 0;
	}

}
