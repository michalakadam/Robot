package pl.michalak.adam.anticorruptionlayer;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class ScrapperAPITest {


	@Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "malformedURLs")
	public void shouldThrowExceptionWhenSuppliedWithMalformedURL(String url) throws IOException {
	    //given
	    String whyItFailed = "Exception was not thrown!";

	    ScrapperAPI scrapperAPI = new ScrapperAPI(new JSoupScrapper());
	    //when
	    scrapperAPI.connect(url);
	    //then
	    fail(whyItFailed);
	}

	@DataProvider
	public Object[] malformedURLs(){
		return new Object[]{"czytam.pl", "www.czytam.pl", "htt://czytam.pl",
		"http//czytam.pl", "http:/czytam.pl"};
	}
}