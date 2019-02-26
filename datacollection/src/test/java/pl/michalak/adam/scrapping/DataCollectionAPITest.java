package pl.michalak.adam.scrapping;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertTrue;

public class DataCollectionAPITest {

	@Ignore
	@Test
	public void shouldReturnNotEmptySetOfBooks(){
	    //given
	    String whyItFailed = "Set of scrapped books is empty.";

	    DataCollectionAPI dataCollectionAPI = new DataCollectionAPI();
	    //when
	    Set<Book> booksReturnedByUpdate = dataCollectionAPI.updateData();
	    int booksScrappedAmount = booksReturnedByUpdate.size();
		System.out.println(booksScrappedAmount);
	    //then
	    assertTrue(booksScrappedAmount > 0, whyItFailed);
	}
}