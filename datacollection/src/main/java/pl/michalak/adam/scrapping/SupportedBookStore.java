package pl.michalak.adam.scrapping;

enum SupportedBookStore {

	TANIAKSIAZKA("https://www.taniaksiazka.pl/outlet");

	private String url;

	SupportedBookStore(String url) {

		this.url = url;
	}

	String getUrl(){
		return this.url;
	}
}
