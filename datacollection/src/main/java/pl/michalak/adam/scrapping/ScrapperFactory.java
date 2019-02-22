package pl.michalak.adam.scrapping;

class ScrapperFactory {

    static PageScrapper getPageScrapper(String url) {
        if(url.contains("taniaksiazka.pl"))
            return new TaniaKsiazkaScrapper();
        else if(url.contains("czytam.pl"))
        	return new CzytamPLScrapper();
        throw new UnsupportedBookStoreException(url + "not supported!");
    }
}

