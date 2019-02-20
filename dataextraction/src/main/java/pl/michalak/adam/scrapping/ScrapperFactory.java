package pl.michalak.adam.scrapping;

class ScrapperFactory {

    static PageScrapper getPageScrapper(String url) {
        if(url.contains("taniaksiazka.pl/outlet"))
            return new TaniaKsiazkaScrapper();
        throw new UnsupportedBookStoreException(url + "not supported!");
    }
}

