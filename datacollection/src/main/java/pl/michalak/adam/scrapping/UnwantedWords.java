package pl.michalak.adam.scrapping;

enum UnwantedWords {
	OUTLET("Outlet"), COVERDAMAGE("(Uszkodzona okładka)"), CONTENTDAMAGE("(Uszkodzona zawartość)"),
	PAGESDAMAGE("(Uszkodzenia stron)");

	private String unwantedWords;

	UnwantedWords(String unwantedWords) {

		this.unwantedWords = unwantedWords;
	}

	String getUnwantedWords(){
		return this.unwantedWords;
	}
}
