package cz.lukastrnka.vat.VatRates;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
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

	public static void main(String[] args)
			throws IOException, UnknownPeriodException, UnknownSortingException, ParseException {

		VatController vCtrl = new VatController();

		//
		// lowest VAT
		// ================================================
		System.out.println("== lowest VAT - 3 countries, sorted AZ == ");
		JsonVatData jvData001 = vCtrl.loadData(new URL("http://jsonvat.com/"));

		// as text
		List<Country> cc001 = vCtrl.getLowestStdVAT(jvData001, 3, "current", "AZ");
		vCtrl.printAsText(cc001);

		// as Json
		System.out.println("\n== lowest VAT - 3 countries, sorted AZ ==  >> as Json");
		List<Country> ccLow = vCtrl.getLowestStdVAT(jvData001, 3);
		System.out.println(vCtrl.getAsJson(ccLow));

		//
		// highest VAT
		// ================================================
		System.out.println("-----------------------------------------------------\n");

		System.out.println("\n== highest VAT - 3 countries, sorted AZ == ");
		JsonVatData jvData002 = vCtrl.loadData(new URL("http://jsonvat.com/"));

		// as text
		List<Country> cc002 = vCtrl.getHighestStdVAT(jvData002, 3, "current", "AZ");
		vCtrl.printAsText(cc002);

		// as Json
		System.out.println("\n== highest VAT - 3 countries, sorted AZ ==  >> as Json-");
		List<Country> ccHigh = vCtrl.getHighestStdVAT(jvData002, 3, "current", "default");
		System.out.println(vCtrl.getAsJson(ccHigh));

		System.out.println("-----------------------------------------------------\n");
		// as Json 5 countries

		JsonVatData jvData003 = vCtrl.loadData(new URL("http://jsonvat.com/"));
		System.out.println("\n== highest VAT - 5 countries, no sort == ");
		List<Country> ccHigh5 = vCtrl.getHighestStdVAT(jvData003, 5);
		vCtrl.printAsText(ccHigh5);

		System.out.println("-----------------------------------------------------\n");

		//
		// json output all countries
		// ================================================
		System.out.println("== Json output all countries ==");
		JsonVatData jvData004 = vCtrl.loadData(new URL("http://jsonvat.com/"));

		String jsonAll = vCtrl.getAsJson(jvData004.getRates());
		System.out.println(jsonAll);

	}

}
