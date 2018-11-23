package cz.lukastrnka.vat.VatRates;

import java.io.IOException;
import java.net.URL;

import java.util.Iterator;
import java.util.List;

import cz.lukastrnka.vat.VatRates.data.Country;
import cz.lukastrnka.vat.VatRates.data.JsonVatData;
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
		// JsonVatData jvData = vCtrl.loadData(new URL("http://jsonvat.com/"));
		int i = 0;

		// ================================================
		System.out.println("-low 3 countries, sorted AZ-");
		JsonVatData jvData001 = vCtrl.loadData(new URL("http://jsonvat.com/"));
		Iterator<Country> cItr001 = vCtrl.getLowestStdVAT(jvData001, 3, "current", "AZ").iterator();
		i = 0;
		while (cItr001.hasNext()) {
			i++;
			Country a = cItr001.next();
			System.out.println(
					i + "\tVAT: " + a.getPeriods().get(0).getRates().getStandard() + "\tcountry: " + a.getName());
		}

		// as Json
		System.out.println("-low 3 countries as Json-");
		List<Country> ccLow = vCtrl.getLowestStdVAT(jvData001, 3);
		String json3lowCountries = vCtrl.getAsJson(ccLow);
		System.out.println(json3lowCountries);

		// ================================================
		System.out.println("-------------");

		JsonVatData jvData002 = vCtrl.loadData(new URL("http://jsonvat.com/"));
		System.out.println("-high 3 countries, sorted AZ-");
		Iterator<Country> cItr002 = vCtrl.getHighestStdVAT(jvData002, 3, "current", "AZ").iterator();
		i = 0;
		while (cItr002.hasNext()) {
			i++;
			Country a = cItr002.next();
			System.out.println(
					i + "\tVAT: " + a.getPeriods().get(0).getRates().getStandard() + "\tcountry: " + a.getName());
		}
		// as Json
		System.out.println("-low 3 countries as Json-");
		List<Country> ccHigh = vCtrl.getHighestStdVAT(jvData002, 3);
		String json3highCountries = vCtrl.getAsJson(ccHigh);
		System.out.println(json3highCountries);
		
		// as Json 5 countries 
		
		System.out.println("-low 5 countries as Json-");
		List<Country> ccHigh5 = vCtrl.getHighestStdVAT(jvData002, 5);
		String json5highCountries = vCtrl.getAsJson(ccHigh5);
		System.out.println(json5highCountries);

		System.out.println("-------------\n\n\n\n");
		// ================================================

		// ----- test periods on France

//		System.out.println("-------------");
//		System.out.println("-20  France-");
//		Country france = jvData.getRates().get(20);
//		System.out.println(" France:" + france.getName());
//		List<Period> pp = france.getPeriods();
//		// Collections.sort(pp, new PeriodsCompareByDateDesc());
//		System.out.println("---default----");
//		Iterator<Period> cItr1 = pp.iterator();
//
//		while (cItr1.hasNext()) {
//			i++;
//			Period a = cItr1.next();
//			System.out.println(i + "\tEffective_from: " + a.getEffective_from());
//		}
//		System.out.println("---sorted----");
//		Collections.sort(france.getPeriods(), new PeriodsComparatorByDateDesc());
//		Iterator<Period> cItr2 = pp.iterator();
//		i = 0;
//		while (cItr2.hasNext()) {
//			i++;
//			Period a = cItr2.next();
//			System.out.println(i + "\tEffective_from: " + a.getEffective_from());
//		}
//
//		System.out.println("-------------");

		// ----- sort all periods
//		JsonVatData jvData2 = vCtrl.loadData(new URL("http://jsonvat.com/"));
//		List<Country> allCountries = jvData2.getRates();
//		jvData2.setRates(vCtrl.sortPeriods(allCountries));
//
//		// ----- low 3
//		System.out.println("-low 3-");
//		JsonVatData jvData3 = vCtrl.loadData(new URL("http://jsonvat.com/"));
//		Iterator<Country> cItr3 = vCtrl.getLowestStdVAT(jvData3, 5).iterator();
//		i = 0;
//		while (cItr3.hasNext()) {
//			i++;
//			Country a = cItr3.next();
//			System.out.println(
//					i + "\tVAT: " + a.getPeriods().get(0).getRates().getStandard() + "\tcountry: " + a.getName());
//		}
//
//		System.out.println("-low 3 curr, alpha-");
//		JsonVatData jvData31 = vCtrl.loadData(new URL("http://jsonvat.com/"));
//		Iterator<Country> cItr31 = vCtrl.getLowestStdVAT(jvData31, 5, "current", "AZ").iterator();
//		i = 0;
//		while (cItr31.hasNext()) {
//			i++;
//			Country a = cItr31.next();
//			System.out.println(
//					i + "\tVAT: " + a.getPeriods().get(0).getRates().getStandard() + "\tcountry: " + a.getName());
//		}
//
//		JsonVatData jvData5 = vCtrl.loadData(new URL("http://jsonvat.com/"));
//		// ----- high 3
//		System.out.println("-------------");
//		System.out.println("-high 3-");
//		Iterator<Country> cItr5 = vCtrl.getHighestStdVAT(jvData5, 5).iterator();
//		i = 0;
//		while (cItr5.hasNext()) {
//			i++;
//			Country a = cItr5.next();
//			System.out.println(
//					i + "\tVAT: " + a.getPeriods().get(0).getRates().getStandard() + "\tcountry: " + a.getName());
//		}
//
//		JsonVatData jvData51 = vCtrl.loadData(new URL("http://jsonvat.com/"));
//		System.out.println("-high 3 curr, alpha-");
//		Iterator<Country> cItr51 = vCtrl.getHighestStdVAT(jvData51, 5, "current", "AZ").iterator();
//		i = 0;
//		while (cItr51.hasNext()) {
//			i++;
//			Country a = cItr51.next();
//			System.out.println(
//					i + "\tVAT: " + a.getPeriods().get(0).getRates().getStandard() + "\tcountry: " + a.getName());
//		}

		// ----- json output

//		JsonVatData jvData6 = vCtrl.loadData(new URL("http://jsonvat.com/"));
//		List<Country> cc = vCtrl.getLowestStdVAT(jvData6, 3);
//		String json3c = vCtrl.getAsJson(cc);
//		System.out.println("low\n" + json3c);
//		cc = vCtrl.getHighestStdVAT(jvData6, 3);
//		json3c = vCtrl.getAsJson(cc);
//		System.out.println("high\n" + json3c);
//
//		List<Country> allC = jvData6.getRates();
//		String jsonAllC = vCtrl.getAsJson(allC);
//
//		System.out.println("all\n" + jsonAllC);

// ----------------		

	}

}
