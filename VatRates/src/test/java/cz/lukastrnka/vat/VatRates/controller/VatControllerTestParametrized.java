package cz.lukastrnka.vat.VatRates.controller;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import cz.lukastrnka.vat.VatRates.controller.VatController;
import cz.lukastrnka.vat.VatRates.data.Country;
import cz.lukastrnka.vat.VatRates.data.JsonVatData;

/**
 * Tests of methods getHighestStdVAT() and getLowestStdVAT() for different
 * number of countries.
 *
 */
@RunWith(Parameterized.class)
public class VatControllerTestParametrized {

	private VatController vCtrl;
	private JsonVatData jvData;
	private int i;
	private Integer numberOfCountries;

	@Parameterized.Parameters
	public static Collection numberOfCountries() {
		return Arrays.asList(new Object[][] { { 0 }, { 1 }, { 2 }, { 3 }, { 4 }, { 5 }, { 6 }, { 28 } });
	}

	public VatControllerTestParametrized(Integer num1) {
		this.numberOfCountries = num1;
	}

	@Before
	public void setUpTestingTools() throws MalformedURLException {
		vCtrl = new VatController();
		jvData = vCtrl.loadData(new URL("http://jsonvat.com/"));
	}

	@Test
	public void testGetLowestStdVAT() throws ParseException {

		i = numberOfCountries;
		List<Country> ccLow = vCtrl.getLowestStdVAT(jvData, numberOfCountries);
		assertEquals(i, ccLow.size());
	}

	@Test
	public void testGetHighestStdVAT() throws ParseException {

		i = numberOfCountries;
		List<Country> ccLow = vCtrl.getHighestStdVAT(jvData, numberOfCountries);
		assertEquals(i, ccLow.size());
	}
}
