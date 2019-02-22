package pl.michalak.adam.scrapping;

import pl.michalak.adam.anticorruptionlayer.ScrapperAPI;

class ScrapperFactory {

    static PageScrapper getPageScrapper(String url, ScrapperAPI scrapper) {
        if(url.contains("taniaksiazka.pl"))
            return new TaniaKsiazkaScrapper(scrapper);
        else if(url.contains("czytam.pl"))
        	return new CzytamPLScrapper(scrapper);
        throw new UnsupportedBookStoreException(url + "not supported!");
    }
}

