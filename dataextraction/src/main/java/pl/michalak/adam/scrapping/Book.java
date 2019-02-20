package pl.michalak.adam.scrapping;

class Book {
    String title;
    String author;
    double price;
    String promoDetails;

    Book(BookBuilder bookBuilder){
        this.title = bookBuilder.title;
        this.author = bookBuilder.author;
        this.price = bookBuilder.price;
        this.promoDetails = bookBuilder.promoDetails;
    }

    static class BookBuilder{
        String title;
        String author;
        double price;
        String promoDetails;

        BookBuilder(String title){
            this.title = title;
        }

        BookBuilder setAuthor(String author){
            this.author = author;
            return this;
        }

        BookBuilder setPrice(String price){
            this.price = Double.parseDouble(price);
            return this;
        }

        BookBuilder setPromoDetails(String promoDetails){
            this.promoDetails = promoDetails;
            return this;
        }

        Book build(){
            return new Book(this);
        }
    }

}
