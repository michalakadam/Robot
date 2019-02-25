package pl.michalak.adam.scrapping;

import pl.michalak.adam.anticorruptionlayer.ScrapperAPI;

class ScrapperFactory {

    static PageScrapper getPageScrapper(String url) {
        if(url.contains("taniaksiazka.pl"))
            return new TaniaKsiazkaScrapper();
        else if(url.contains("czytam.pl"))
        	return new CzytamPLScrapper();
        throw new UnsupportedBookStoreException(url + "not supported!");
    }
}

