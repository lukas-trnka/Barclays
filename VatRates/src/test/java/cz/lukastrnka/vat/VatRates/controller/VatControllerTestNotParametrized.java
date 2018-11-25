package cz.lukastrnka.vat.VatRates.controller;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import cz.lukastrnka.vat.VatRates.controller.VatController;
import cz.lukastrnka.vat.VatRates.controller.exceptions.UnknownPeriodException;
import cz.lukastrnka.vat.VatRates.controller.exceptions.UnknownSortingException;
import cz.lukastrnka.vat.VatRates.data.Country;
import cz.lukastrnka.vat.VatRates.data.JsonVatData;
import cz.lukastrnka.vat.VatRates.data.Period;
import cz.lukastrnka.vat.VatRates.data.Rates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VatControllerTestNotParametrized {

	private VatController vCtrl;
	private JsonVatData jvData;
	private Map<String, Country> testMock;

	/**
	 * Prepares 7 countries with highest and lowest std VAT for testing purposes.
	 * 
	 * @return HashMap of contries
	 * @throws ParseException
	 */
	private Map<String, Country> getMock() throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

		// ---- Luxembourg
		Rates testRatesLU0 = new Rates();
		testRatesLU0.setSuper_reduced(3.0);
		testRatesLU0.setReduced1(8.0);
		testRatesLU0.setStandard(17.0);
		testRatesLU0.setParking(13.0);

		Rates testRatesLU1 = new Rates();
		testRatesLU1.setSuper_reduced(3.0);
		testRatesLU1.setReduced1(8.0);
		testRatesLU1.setReduced2(14.0);
		testRatesLU1.setStandard(17.0);
		testRatesLU1.setParking(12.0);

		Rates testRatesLU2 = new Rates();
		testRatesLU2.setSuper_reduced(3.0);
		testRatesLU2.setReduced1(6.0);
		testRatesLU2.setReduced2(12.0);
		testRatesLU2.setStandard(15.0);
		testRatesLU2.setParking(12.0);

		Date testDateLU0 = sd.parse("2016-01-01");
		Date testDateLU1 = sd.parse("2015-01-01");
		Date testDateLU2 = sd.parse("0000-01-01");

		Period testPeriodLU0 = new Period(testDateLU0, testRatesLU0);
		Period testPeriodLU1 = new Period(testDateLU1, testRatesLU1);
		Period testPeriodLU2 = new Period(testDateLU2, testRatesLU2);

		List<Period> testListOfPeriodsLU = new ArrayList<Period>();
		testListOfPeriodsLU.add(testPeriodLU0);
		testListOfPeriodsLU.add(testPeriodLU1);
		testListOfPeriodsLU.add(testPeriodLU2);

		Country testLuxembourg = new Country("Luxembourg", "LU", "LU", testListOfPeriodsLU);

		// ==== Malta ====
		Rates testRatesMT0 = new Rates();
		testRatesMT0.setReduced1(5.0);
		testRatesMT0.setReduced2(7.0);
		testRatesMT0.setStandard(18.0);

		Date testDateMT0 = sd.parse("0000-01-01");
		Period testPeriodMT0 = new Period(testDateMT0, testRatesMT0);

		List<Period> testListOfPeriodsMT = new ArrayList<Period>();
		testListOfPeriodsMT.add(testPeriodMT0);

		Country testMalta = new Country("Malta", "MT", "MT", testListOfPeriodsMT);

		// ==== Cyprus ====
		Rates testRatesCY0 = new Rates();
		testRatesCY0.setReduced1(5.0);
		testRatesCY0.setReduced2(9.0);
		testRatesCY0.setStandard(19.0);

		Date testDateCY0 = sd.parse("0000-01-01");
		Period testPeriodCY0 = new Period(testDateCY0, testRatesCY0);

		List<Period> testListOfPeriodsCY = new ArrayList<Period>();
		testListOfPeriodsCY.add(testPeriodCY0);

		Country testCyprus = new Country("Cyprus", "CY", "CY", testListOfPeriodsCY);

		// ==== Hungary ====
		Rates testRatesHU0 = new Rates();
		testRatesHU0.setReduced1(5.0);
		testRatesHU0.setReduced2(18.0);
		testRatesHU0.setStandard(27.0);

		Date testDate = sd.parse("0000-01-01");
		Period testPeriod = new Period(testDate, testRatesHU0);

		List<Period> testListOfPeriodsHU = new ArrayList<Period>();
		testListOfPeriodsHU.add(testPeriod);

		Country testHungary = new Country("Hungary", "HU", "HU", testListOfPeriodsHU);

		// ==== Sweden ====
		Rates testRatesSE0 = new Rates();
		testRatesSE0.setReduced1(6.0);
		testRatesSE0.setReduced2(12.0);
		testRatesSE0.setStandard(25.0);

		Date testDateSE0 = sd.parse("0000-01-01");
		Period testPeriodSE0 = new Period(testDateSE0, testRatesSE0);

		List<Period> testListOfPeriodsSE = new ArrayList<Period>();
		testListOfPeriodsSE.add(testPeriodSE0);

		Country testSweden = new Country("Sweden", "SE", "SE", testListOfPeriodsSE);

		// ==== Croatia ====
		Rates testRatesHR0 = new Rates();
		testRatesHR0.setReduced1(5.0);
		testRatesHR0.setReduced2(13.0);
		testRatesHR0.setStandard(25.0);

		Date testDateHR0 = sd.parse("0000-01-01");
		Period testPeriodHR0 = new Period(testDateHR0, testRatesHR0);

		List<Period> testListOfPeriodsHR = new ArrayList<Period>();
		testListOfPeriodsHR.add(testPeriodHR0);

		Country testCroatia = new Country("Croatia", "HR", "HR", testListOfPeriodsHR);

		// ==== Denmark ====
		Rates testRatesDK0 = new Rates();
		testRatesDK0.setStandard(25.0);

		Date testDateDK0 = sd.parse("0000-01-01");
		Period testPeriodDK0 = new Period(testDateDK0, testRatesDK0);

		List<Period> testListOfPeriodsDK = new ArrayList<Period>();
		testListOfPeriodsDK.add(testPeriodDK0);

		Country testDenmark = new Country("Denmark", "DK", "DK", testListOfPeriodsDK);

		// ==== Create Map of test data ====

		Map<String, Country> testMapOfCountries = new HashMap<String, Country>();
		testMapOfCountries.put("Luxembourg", testLuxembourg);
		testMapOfCountries.put("Malta", testMalta);
		testMapOfCountries.put("Cyprus", testCyprus);
		testMapOfCountries.put("Hungary", testHungary);
		testMapOfCountries.put("Sweden", testSweden);
		testMapOfCountries.put("Croatia", testCroatia);
		testMapOfCountries.put("Denmark", testDenmark);

		return testMapOfCountries;
	}

	@Before
	public void setUpTestTools() throws MalformedURLException, ParseException {
		testMock = getMock();
		vCtrl = new VatController();
		jvData = vCtrl.loadData(new URL("http://jsonvat.com/"));
	}

	@Test
	public void testLoadData() throws MalformedURLException, JSONException {
		JsonVatData jvData01 = vCtrl.loadData(new URL("http://jsonvat.com/"));

		assertNotNull(jvData01);
	}

	@Test
	public void testGetLowestStdVAT() {

		Country lux = testMock.get("Luxembourg");
		Country malta = testMock.get("Malta");
		Country cyprus = testMock.get("Cyprus");

		List<Country> testListOfCountries = new ArrayList<Country>();
		testListOfCountries.add(lux);
		testListOfCountries.add(malta);
		testListOfCountries.add(cyprus);

		List<Country> ccLow = vCtrl.getLowestStdVAT(jvData, 3);
		assertEquals(testListOfCountries, ccLow);
	}

	@Test
	public void testGetHighestStdVAT() {

		Country hungary = testMock.get("Hungary");
		Country croatia = testMock.get("Croatia");
		Country denmark = testMock.get("Denmark");

		List<Country> testListOfCountries = new ArrayList<Country>();
		testListOfCountries.add(hungary);
		testListOfCountries.add(croatia);
		testListOfCountries.add(denmark);

		List<Country> ccHigh = vCtrl.getHighestStdVAT(jvData, 3);
		assertEquals(testListOfCountries, ccHigh);
	}

	/**
	 * Method tests that only existing number of countries will be returned, even if
	 * we ask for more.
	 */
	@Test
	public void testGetMoreCountriesThenExists() {
		int requiredNumberOfCountries = jvData.getRates().size() + 1;

		List<Country> ccMore = vCtrl.getHighestStdVAT(jvData, requiredNumberOfCountries);
		assertEquals(jvData.getRates().size(), ccMore.size());
	}

	/**
	 * Test of sorting highest VAT output alphabetically. (As a secondary
	 * criterion.)
	 */
	@Test
	public void testSortingCriteria() {

		Country hungary = testMock.get("Hungary");
		Country sweden = testMock.get("Sweden");
		Country denmark = testMock.get("Denmark");

		List<Country> testListOfCountries = new ArrayList<Country>();
		testListOfCountries.add(hungary);
		testListOfCountries.add(sweden);
		testListOfCountries.add(denmark);

		List<Country> ccHigh = vCtrl.getHighestStdVAT(jvData, 3, "current", "AZ");
		assertEquals(testListOfCountries, ccHigh);
	}

	@Test(expected = UnknownPeriodException.class)
	public void testUnknownPeriodException() throws UnknownSortingException, UnknownPeriodException {

		Country hungary = testMock.get("Hungary");
		List<Country> testListOfCountries = new ArrayList<Country>();
		testListOfCountries.add(hungary);
		vCtrl.sortingCriteria(testListOfCountries, "2011", "AZ");

	}

	@Test(expected = UnknownSortingException.class)
	public void testUnknownSortingException() throws UnknownSortingException, UnknownPeriodException {

		Country hungary = testMock.get("Hungary");
		List<Country> testListOfCountries = new ArrayList<Country>();
		testListOfCountries.add(hungary);
		vCtrl.sortingCriteria(testListOfCountries, "current", "ZA");

	}

	@Test
	public void testGetAsJson() throws MalformedURLException, JSONException {

		String expectedJson = "[{\"name\":\"Hungary\",\"code\":\"HU\",\"country_code\":\"HU\",\"periods\":[{\"effective_from\":\"0001-01-01\",\"rates\":{\"reduced1\":5.0,\"reduced2\":18.0,\"standard\":27.0}}]},{\"name\":\"Denmark\",\"code\":\"DK\",\"country_code\":\"DK\",\"periods\":[{\"effective_from\":\"0001-01-01\",\"rates\":{\"standard\":25.0}}]}]";
		String result = "";
		Country hungary = testMock.get("Hungary");
		Country denmark = testMock.get("Denmark");

		List<Country> testListOfCountries = new ArrayList<Country>();
		testListOfCountries.add(hungary);
		testListOfCountries.add(denmark);

		result = vCtrl.getAsJson(testListOfCountries);
		assertEquals(expectedJson, result);

		// JSONAssert.assertEquals(expectedJson,result , JSONCompareMode.STRICT);

	}

	/**
	 * Test of console output
	 */
	@Test
	public void testPrintAsText() {

		String expectedOutput = "1	Standard VAT: 27.0	country: Hungary\n";

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));

		Country hungary = testMock.get("Hungary");

		List<Country> testListOfCountries = new ArrayList<Country>();
		testListOfCountries.add(hungary);

		vCtrl.printAsText(testListOfCountries);
		assertEquals(expectedOutput, outContent.toString());

		System.setOut(originalOut);
	}
}
