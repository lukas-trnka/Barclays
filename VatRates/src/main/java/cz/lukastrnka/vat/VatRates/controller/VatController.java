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

import cz.lukastrnka.vat.VatRates.data.Country;
import cz.lukastrnka.vat.VatRates.data.CountryComparatorByCurrentStdVAT;
import cz.lukastrnka.vat.VatRates.data.JsonVatData;

public class VatController {

	/***
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
			// TODO Auto-generated catch block
			System.out.println("JsonParseException: " + e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			System.out.println("JsonMappingException: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IOException: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * Method returns list of required number of countries with the current lowest
	 * standard VAT rate.
	 * 
	 * @param jvd POJO representation of Json-source data
	 * @param num Number of required countries
	 * @return List of countries with the lowest standard VAT in ascending order.
	 */
	public List<Country> getLowestStdVAT(JsonVatData jvd, int num) {

		List<Country> listOfCountries = jvd.getRates();
		Collections.sort(listOfCountries, new CountryComparatorByCurrentStdVAT());

		List<Country> lowestStdVAT = listOfCountries.subList(0, Math.min(num, listOfCountries.size()));

		return lowestStdVAT;
	}

	/***
	 * Method returns list of required number of countries with the current highest
	 * standard VAT rate.
	 * 
	 * @param jvd POJO representation of Json-source data
	 * @param num Number of required countries
	 * @return List of countries with the highest standard VAT in descending order.
	 */
	public List<Country> getHighestStdVAT(JsonVatData jvd, int num) {
		List<Country> listOfCountries = jvd.getRates();
		Collections.sort(listOfCountries, new CountryComparatorByCurrentStdVAT());

		List<Country> highestStdVAT = listOfCountries.subList(Math.max(listOfCountries.size() - num, 0),
				listOfCountries.size());
		Collections.reverse(highestStdVAT);
		return highestStdVAT;
	}

	/***
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

}
