
package com.ashokit.service;

import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ashokit.bindings.Quote;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DashboardServiceeImpl implements DashboardService {

	private String quoteUrl = "https://type.fit/api/quotes";
	Quote[] quotes = null;

	@Override
	public String getQuote() {
		if (quotes == null) {
			RestTemplate rt = new RestTemplate();
			ResponseEntity<String> forentity = rt.getForEntity(quoteUrl, String.class);
			String body = forentity.getBody();
			ObjectMapper mapper = new ObjectMapper();
			try{
				quotes =       mapper.readValue(body, Quote[].class);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
           Random r = new Random();
           int nextint = r.nextInt(quotes.length-1);
	       return quotes[nextint].getText();
	}

}