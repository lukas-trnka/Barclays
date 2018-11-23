package cz.lukastrnka.vat.VatRates.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
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
		} catch (JsonMappingException e) {
			System.out.println("JsonMappingException: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method returns list of required number of countries with the current lowest
	 * standard VAT rate.
	 * 
	 * @param jvd POJO representation of Json-source data
	 * @param num Number of required countries
	 * @return List of countries with the lowest standard VAT in ascending order.
	 */
	public List<Country> getLowestStdVAT(JsonVatData jvd, int num) {

		List<Country> listOfCountries = jvd.getRates();
		Collections.sort(listOfCountries, new CountryComparatorByStdVAT());

		List<Country> lowestStdVAT = listOfCountries.subList(0, Math.min(num, listOfCountries.size()));

		return lowestStdVAT;
	}

	public List<Country> sortData(List<Country> listOfCountries, String periods, String sortCountries) {
		
		
		return listOfCountries;
	}
	
	public List<Country> getLowestStdVAT(JsonVatData jvd, int num, String periods, String sortCountries)
			throws UnknownPeriodException, UnknownSortingException {
		List<Country> listOfCountries = jvd.getRates();
		if (periods == "current") {
			listOfCountries = sortPeriods(listOfCountries);
		} // else if(periods == "upTo2011") sort differently ...
		else {
			throw new UnknownPeriodException(periods);
		}

		if (sortCountries == "AZ") {
			listOfCountries = sortCountries(listOfCountries);
		} else {// else if(periods == "ZA") sort differently ...
			throw new UnknownSortingException(sortCountries);
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
	 * @return List of countries with the highest standard VAT in descending order.
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
	 * 
	 * @param jvd
	 * @param num
	 * @param currentPeriods
	 * @param alphabetical
	 * @return
	 * @throws UnknownPeriodException
	 * @throws UnknownSortingException
	 */
	public List<Country> getHighestStdVAT(JsonVatData jvd, int num, String periods, String sortCountries)
			throws UnknownPeriodException, UnknownSortingException {

		List<Country> listOfCountries = jvd.getRates();
		if (periods == "current") {
			listOfCountries = sortPeriods(listOfCountries);
		} // else if(periods == "upTo2011") sort differently ...
		else {
			throw new UnknownPeriodException(periods);
		}

		if (sortCountries == "AZ") {
			listOfCountries = sortCountries(listOfCountries);
		} else {
			throw new UnknownSortingException(sortCountries);
		}

		jvd.setRates(listOfCountries);
		List<Country> highestStdVAT = getHighestStdVAT(jvd, num);

		return highestStdVAT;
	}

	/**
	 * Method creates a Json array of given countries
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 
	 * @param countries
	 * @return
	 */
	public List<Country> sortPeriods(List<Country> countries) {
		countries.forEach(country -> {
			Collections.sort(country.getPeriods(), new PeriodsComparatorByDateDesc());
		});

		return countries;
	}

	/**
	 * 
	 * @param countries
	 * @return
	 */
	public List<Country> sortCountries(List<Country> countries) {
		Collections.sort(countries, new CountryComparatorByName());

		return countries;
	}

}
