package pl.michalak.adam.scrapping;

enum CzytamPLQueries {
	URL("czytam.pl/kod,25642,outlet-ksiazki.html"), TABLE("ul.small-block-grid-1.medium-block-grid-2.large-block-grid-3.xlarge-block-grid-4 li"),
	TITLEROW(".product .col-small .col-small-info .product-info .product-title a"), AUTHORROW(".product .col-small .col-small-info .product-info "), 
	PRICEROW(".product .col-small .col-small-info .product-price"), PROMODETAILSROW(".product .col-small .col-small-info .product-price .strike");

	private String query;

	CzytamPLQueries(String query) {

		this.query = query;
	}

	String getQuery(){
		return this.query;
	}
}
