package pl.michalak.adam.scrapping;

public class ScrapperQuery {

	private String queryContent;

	ScrapperQuery(String queryContent){
		this.queryContent = queryContent;
	}

	@Override
	public String toString(){
		return this.queryContent;
	}
}
