package cz.lukastrnka.vat.VatRates.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cz.lukastrnka.vat.VatRates.controller.exceptions.UnknownPeriodException;
import cz.lukastrnka.vat.VatRates.controller.exceptions.UnknownSortingException;
import cz.lukastrnka.vat.VatRates.data.Country;
import cz.lukastrnka.vat.VatRates.data.JsonVatData;
import cz.lukastrnka.vat.VatRates.data.comparator.CountryComparatorByName;
import cz.lukastrnka.vat.VatRates.data.comparator.CountryComparatorByStdVAT;
import cz.lukastrnka.vat.VatRates.data.comparator.PeriodsComparatorByDateDesc;

public class VatController {

	/**
	 * Method for downloading source data in Json-format form an URL and parsing
	 * them into POJO.
	 * 
	 * @param source URL source of input data in Json format
	 * @return JsonVatData object corresponding to Json input structure
	 */
	public JsonVatData loadData(URL source) {

		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		mapper.setDateFormat(df);
		mapper.setSerializationInclusion(Include.NON_NULL);

		JsonVatData jvd;
		try {
			jvd = mapper.readValue(source, JsonVatData.class);
			return jvd;
		} catch (JsonParseException e) {
			System.out.println("JsonParseException: " + e.getMessage());
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			System.out.println("JsonMappingException: " + e.getMessage());
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Method for configuring how the list of countries should be sorted and
	 * prepared for comparing of countries.
	 * 
	 * @param listOfCountries List of countries
	 * @param periods         What period should be prepared for comparing the
	 *                        countries
	 * @param sortCountries   In which order should the list be sorted
	 * @return Sorted list of countries
	 * @throws UnknownPeriodException
	 * @throws UnknownSortingException
	 */
	public List<Country> sortingCriteria(List<Country> listOfCountries, String periods, String sortCountries)
			throws UnknownPeriodException, UnknownSortingException {
		if (periods.equals("current")) {
			listOfCountries = sortPeriods(listOfCountries);
		} // else if(periods.equals("upTo2011")) sort differently ...
		else {
			throw new UnknownPeriodException(periods);
		}

		if (sortCountries.equals("default")) {
			// no sorting
		} else if (sortCountries.equals("AZ")) {
			listOfCountries = sortCountriesAZ(listOfCountries);

		} else {// else if(periods.equals ("ZA")) sort differently ...
			throw new UnknownSortingException(sortCountries);
		}

		return listOfCountries;
	}

	/**
	 * Method returns list of required number of countries with the lowest standard
	 * VAT rate.
	 * 
	 * @param jvd POJO representation of Json-source data
	 * @param num Number of required countries
	 * @return List of countries with the lowest standard VAT.
	 */
	public List<Country> getLowestStdVAT(JsonVatData jvd, int num) {

		List<Country> listOfCountries = jvd.getRates();
		Collections.sort(listOfCountries, new CountryComparatorByStdVAT());

		List<Country> lowestStdVAT = listOfCountries.subList(0, Math.min(num, listOfCountries.size()));

		return lowestStdVAT;
	}

	/**
	 * Method returns list of required number of countries with the lowest standard
	 * VAT rate acording the sorting criteria.
	 * 
	 * @param jvd           POJO representation of Json-source data
	 * @param num           Number of required countries
	 * @param periods       What period should be prepared for comparing the
	 *                      countries
	 * @param sortCountries In which order should the list be sorted
	 * @return List of countries with the lowest standard VAT in specified order.
	 */
	public List<Country> getLowestStdVAT(JsonVatData jvd, int num, String periods, String sortCountries) {
		List<Country> listOfCountries = jvd.getRates();

		try {
			listOfCountries = sortingCriteria(listOfCountries, periods, sortCountries);
		} catch (UnknownPeriodException e) {
			e.printStackTrace();
		} catch (UnknownSortingException e) {
			e.printStackTrace();
		}
		jvd.setRates(listOfCountries);

		List<Country> lowestStdVAT = getLowestStdVAT(jvd, num);

		return lowestStdVAT;
	}

	/**
	 * Method returns list of required number of countries with the highest standard
	 * VAT rate.
	 * 
	 * @param jvd POJO representation of Json-source data
	 * @param num Number of required countries
	 * @return List of countries with the highest standard VAT.
	 */
	public List<Country> getHighestStdVAT(JsonVatData jvd, int num) {
		List<Country> listOfCountries = jvd.getRates();
		Collections.sort(listOfCountries, new CountryComparatorByStdVAT());

		List<Country> highestStdVAT = listOfCountries.subList(Math.max(listOfCountries.size() - num, 0),
				listOfCountries.size());
		Collections.reverse(highestStdVAT);
		return highestStdVAT;
	}

	/**
	 * Method returns list of required number of countries with the highest standard
	 * VAT rate acording the sorting criteria.
	 * 
	 * @param jvd           POJO representation of Json-source data
	 * @param num           Number of required countries
	 * @param periods       What period should be prepared for comparing the
	 *                      countries
	 * @param sortCountries In which order should the list be sorted
	 * @return List of countries with the highest standard VAT in specified order.
	 */
	public List<Country> getHighestStdVAT(JsonVatData jvd, int num, String periods, String sortCountries) {

		List<Country> listOfCountries = jvd.getRates();

		try {
			listOfCountries = sortingCriteria(listOfCountries, periods, sortCountries);
		} catch (UnknownPeriodException e) {
			e.printStackTrace();
		} catch (UnknownSortingException e) {
			e.printStackTrace();
		}

		jvd.setRates(listOfCountries);
		List<Country> highestStdVAT = getHighestStdVAT(jvd, num);

		return highestStdVAT;
	}

	/**
	 * Method creates a Json array of given countries.
	 * 
	 * @param c List of countries
	 * @return Json-format string of countries
	 */
	public String getAsJson(List<Country> c) {
		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		mapper.setDateFormat(df);
		mapper.setSerializationInclusion(Include.NON_NULL);

		String json;
		try {
			json = mapper.writeValueAsString(c);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Method prints list of countries into a console.
	 * 
	 * @param c List of countries
	 */
	public void printAsText(List<Country> c) {
		Iterator<Country> cItr = c.iterator();
		int i = 0;
		while (cItr.hasNext()) {
			i++;
			Country a = cItr.next();
			System.out.print(i + "\tStandard VAT: " + a.getPeriods().get(0).getRates().getStandard() + "\tcountry: "
					+ a.getName() + "\n");
		}
	}

	/**
	 * Method for sorting of list of periods in each country. The most current
	 * period will be first in the list.
	 * 
	 * @param countries List of countries
	 * @return List of countries
	 */
	public List<Country> sortPeriods(List<Country> countries) {
		countries.forEach(country -> {
			Collections.sort(country.getPeriods(), new PeriodsComparatorByDateDesc());
		});

		return countries;
	}

	/**
	 * Sorting countries in alphabetical order from A to Z.
	 * 
	 * @param countries
	 * @return Alphabetically sorted list of countries.
	 */
	public List<Country> sortCountriesAZ(List<Country> countries) {
		Collections.sort(countries, new CountryComparatorByName());

		return countries;
	}

}
