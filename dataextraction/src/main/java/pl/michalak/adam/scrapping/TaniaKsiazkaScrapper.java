package pl.michalak.adam.scrapping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class TaniaKsiazkaScrapper implements PageScrapper {

    String title;
    String author;
    String price;
    String promoDetails;
    Set<Book> books;

    public Set<Book> scrapData() {
        books = new HashSet<>();
        Document document = null;
        try {
            document = Jsoup.connect(TaniaKsiazkaQueries.URL.getQuery()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Element row : document.select(TaniaKsiazkaQueries.TABLE.getQuery())) {
            title = row.select(TaniaKsiazkaQueries.TITLEROW.getQuery()).text();
            author = row.select(TaniaKsiazkaQueries.AUTHORROW.getQuery()).text();
            price = row.select(TaniaKsiazkaQueries.PRICEROW.getQuery()).text();
            promoDetails = row.select(TaniaKsiazkaQueries.PROMODETAILSROW.getQuery()).text();
            books.add(new Book.BookBuilder(title).setAuthor(author).setPrice(price).setPromoDetails(promoDetails).build());
        }
        return books;
    }
}
