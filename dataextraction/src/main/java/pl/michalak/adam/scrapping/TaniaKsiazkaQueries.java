package pl.michalak.adam.scrapping;

enum TaniaKsiazkaQueries {

    URL("https://www.taniaksiazka.pl/outlet"), TABLE("ul.toggle-view.list li"),
    TITLEROW(".product-container .product-main .product-main-top .product-main-top-info .product-main-hidden a.ecommerce-datalayer.product-title"),
    AUTHORROW(".product-container .product-main .product-main-top .product-main-top-info .product-main-hidden .product-authors a"),
    PRICEROW(".product-main .product-main-bottom .updateable .product-price"), PROMODETAILSROW(".product-container .product-image .product-discount");

    private final String query;
    TaniaKsiazkaQueries(String query) {
        this.query = query;
    }

    String getQuery(){
        return this.query;
    }
}
