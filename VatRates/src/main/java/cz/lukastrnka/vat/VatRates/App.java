package cz.lukastrnka.vat.VatRates;

import java.io.IOException;
import java.net.URL;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;


import cz.lukastrnka.vat.VatRates.data.Country;
import cz.lukastrnka.vat.VatRates.data.CountryComparatorByCurrentStdVAT;
import cz.lukastrnka.vat.VatRates.data.CountryCompareByName;
import cz.lukastrnka.vat.VatRates.data.JsonVatData;
import cz.lukastrnka.vat.VatRates.data.Period;
import cz.lukastrnka.vat.VatRates.data.PeriodsCompareByDateDesc;
import cz.lukastrnka.vat.VatRates.controller.VatController;

/**
 * Task: Implement an application in Java capable of printing out three EU
 * countries with the lowest and three EU countries with the highest standard
 * VAT rate as of today within the EU.
 * 
 * Input: http://jsonvat.com/
 * 
 * Suggestions: Keep it clean and simple (yet with a reasonable design allowing
 * future extendibility), use any library of your choice, verify the program
 * correctness, implement as a Maven or Gradle project, submit preferably as a
 * GitHub repo link.
 * 
 * @author Lukas Trnka
 *
 */

public class App {
	public static void main(String[] args) throws IOException {

		VatController vc = new VatController();
		JsonVatData jvd = vc.loadData(new URL("http://jsonvat.com/"));

		int i = 0;
		System.out.println("-------------");
		System.out.println("-20  France-");
		Country france = jvd.getRates().get(20);
		System.out.println(" France:" + france.getName());
		List<Period> pp = france.getPeriods();
		// Collections.sort(pp, new PeriodsCompareByDateDesc());

		Iterator<Period> cItr3 = pp.iterator();

		while (cItr3.hasNext()) {
			i++;
			Period a = cItr3.next();
			System.out.println(i + "\tEffective_from: " + a.getEffective_from());
		}
		System.out.println("---sorted----");
		Collections.sort(france.getPeriods(), new PeriodsCompareByDateDesc());
		Iterator<Period> cItr4 = pp.iterator();
		i = 0;
		while (cItr4.hasNext()) {
			i++;
			Period a = cItr4.next();
			System.out.println(i + "\tEffective_from: " + a.getEffective_from());
		}

		System.out.println("-------------");
		System.out.println("-low 3-");
		Iterator<Country> cItr = vc.getLowestStdVAT(jvd, 3).iterator();
		i = 0;
		while (cItr.hasNext()) {
			i++;
			Country a = cItr.next();
			System.out.println(
					i + "\tVAT: " + a.getPeriods().get(0).getRates().getStandard() + "\tcountry: " + a.getName());
		}

		System.out.println("-------------");
		System.out.println("-high 3-");
		Iterator<Country> cItr2 = vc.getHighestStdVAT(jvd, 3).iterator();
		i = 0;
		while (cItr2.hasNext()) {
			i++;
			Country a = cItr2.next();
			System.out.println(
					i + "\tVAT: " + a.getPeriods().get(0).getRates().getStandard() + "\tcountry: " + a.getName());
		}

		List<Country> cc = vc.getLowestStdVAT(jvd, 3);
		String json3c = vc.getAsJson(cc);
		System.out.println("low\n" + json3c);
		cc = vc.getHighestStdVAT(jvd, 3);
		json3c = vc.getAsJson(cc);
		System.out.println("high\n" + json3c);

		List<Country> allC = jvd.getRates();
		String jsonAllC = vc.getAsJson(allC);

		System.out.println("all\n" + jsonAllC);

// ----------------		

	}

}
