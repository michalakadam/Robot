package pl.michalak.adam.scrapping;

enum SupportedBookStore {

	TANIAKSIAZKA("https://www.taniaksiazka.pl/outlet"), CZYTAMPL("https://czytam.pl/kod,25642,outlet-ksiazki.html");

	private String url;

	SupportedBookStore(String url) {

		this.url = url;
	}

	String getUrl(){
		return this.url;
	}
}
