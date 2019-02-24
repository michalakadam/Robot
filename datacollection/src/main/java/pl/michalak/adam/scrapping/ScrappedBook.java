package pl.michalak.adam.scrapping;

class ScrappedBook implements Book {
	private String title;
	private String author;
	private double price;
	private String promoDetails;

    private ScrappedBook(BookBuilder bookBuilder){
        this.title = bookBuilder.title;
        this.author = bookBuilder.author;
        this.price = bookBuilder.price;
        this.promoDetails = bookBuilder.promoDetails;
    }

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getAuthor() {
		return author;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public String getPromoDetails() {
		return promoDetails;
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

        BookBuilder setPrice(double price){
            this.price = DataFormattingHelper.round(price, 2);
            return this;
        }

        BookBuilder setPromoDetails(String promoDetails){
            this.promoDetails = promoDetails;
            return this;
        }

        ScrappedBook build(){
            return new ScrappedBook(this);
        }

        static BookBuilder create(String title){
            return new BookBuilder(DataFormattingHelper.removeSpecificWordFromSentence(title));
        }
    }

}
