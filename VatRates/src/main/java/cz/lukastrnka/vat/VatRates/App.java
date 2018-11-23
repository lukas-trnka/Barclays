package cz.lukastrnka.vat.VatRates;

import java.io.IOException;
import java.net.URL;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;


import cz.lukastrnka.vat.VatRates.data.Country;
import cz.lukastrnka.vat.VatRates.data.JsonVatData;
import cz.lukastrnka.vat.VatRates.data.Period;
import cz.lukastrnka.vat.VatRates.data.comparator.CountryComparatorByStdVAT;
import cz.lukastrnka.vat.VatRates.data.comparator.CountryComparatorByName;
import cz.lukastrnka.vat.VatRates.data.comparator.PeriodsComparatorByDateDesc;
import cz.lukastrnka.vat.VatRates.controller.VatController;
import cz.lukastrnka.vat.VatRates.controller.exceptions.UnknownPeriodException;
import cz.lukastrnka.vat.VatRates.controller.exceptions.UnknownSortingException;

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
	public static void main(String[] args) throws IOException, UnknownPeriodException, UnknownSortingException {

		VatController vCtrl = new VatController();
		JsonVatData jvData = vCtrl.loadData(new URL("http://jsonvat.com/"));

		
		// ----- test periods on France
		int i = 0;
		System.out.println("-------------");
		System.out.println("-20  France-");
		Country france = jvData.getRates().get(20);
		System.out.println(" France:" + france.getName());
		List<Period> pp = france.getPeriods();
		// Collections.sort(pp, new PeriodsCompareByDateDesc());

		Iterator<Period> cItr1 = pp.iterator();

		while (cItr1.hasNext()) {
			i++;
			Period a = cItr1.next();
			System.out.println(i + "\tEffective_from: " + a.getEffective_from());
		}
		System.out.println("---sorted----");
		Collections.sort(france.getPeriods(), new PeriodsComparatorByDateDesc());
		Iterator<Period> cItr2 = pp.iterator();
		i = 0;
		while (cItr2.hasNext()) {
			i++;
			Period a = cItr2.next();
			System.out.println(i + "\tEffective_from: " + a.getEffective_from());
		}

		System.out.println("-------------");
		
		// ----- sort all periods
		List <Country> allCountries = jvData.getRates();
		jvData.setRates(vCtrl.sortPeriods(allCountries));
		
		
		// ----- low 3
		System.out.println("-low 3-");
		Iterator<Country> cItr3 = vCtrl.getLowestStdVAT(jvData, 3).iterator();
		i = 0;
		while (cItr3.hasNext()) {
			i++;
			Country a = cItr3.next();
			System.out.println(
					i + "\tVAT: " + a.getPeriods().get(0).getRates().getStandard() + "\tcountry: " + a.getName());
		}
//		System.out.println("-low 3 curr, alpha-");
//		Iterator<Country> cItr31 = vCtrl.getLowestStdVAT(jvData, 3).iterator();
//		i = 0;
//		while (cItr31.hasNext()) {
//			i++;
//			Country a = cItr31.next();
//			System.out.println(
//					i + "\tVAT: " + a.getPeriods().get(0).getRates().getStandard() + "\tcountry: " + a.getName());
//		}		
		
		
		JsonVatData jvData5 = vCtrl.loadData(new URL("http://jsonvat.com/"));
		// ----- high 3
		System.out.println("-------------");
		System.out.println("-high 3-");
		Iterator<Country> cItr5 = vCtrl.getHighestStdVAT(jvData5, 30).iterator();
		i = 0;
		while (cItr5.hasNext()) {
			i++;
			Country a = cItr5.next();
			System.out.println(
					i + "\tVAT: " + a.getPeriods().get(0).getRates().getStandard() + "\tcountry: " + a.getName());
		}

		JsonVatData jvData51 = vCtrl.loadData(new URL("http://jsonvat.com/"));
		System.out.println("-high 3 curr, alpha-");
		Iterator<Country> cItr51 = vCtrl.getHighestStdVAT(jvData51, 3, "current", "AZ").iterator();
		i = 0;
		while (cItr51.hasNext()) {
			i++;
			Country a = cItr51.next();
			System.out.println(
					i + "\tVAT: " + a.getPeriods().get(0).getRates().getStandard() + "\tcountry: " + a.getName());
		}

		
		
		// ----- json output
//		List<Country> cc = vCtrl.getLowestStdVAT(jvData, 3);
//		String json3c = vCtrl.getAsJson(cc);
//		System.out.println("low\n" + json3c);
//		cc = vCtrl.getHighestStdVAT(jvData, 3);
//		json3c = vCtrl.getAsJson(cc);
//		System.out.println("high\n" + json3c);
//
//		List<Country> allC = jvData.getRates();
//		String jsonAllC = vCtrl.getAsJson(allC);
//
//		System.out.println("all\n" + jsonAllC);

// ----------------		

	}

}
