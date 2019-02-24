package pl.michalak.adam.anticorruptionlayer;

import org.jsoup.nodes.Element;

public class Row {
	Element jsoupElement;

	public Row(Element jsoupElement){
		this.jsoupElement = jsoupElement;
	}

	public Element convertToElement(){
		return this.jsoupElement;
	}
}
