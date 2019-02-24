package pl.michalak.adam.anticorruptionlayer;

import org.jsoup.nodes.Element;

/**
 * Wrapper for jsoup Element. Created to remove dependency
 * on jsoup library of scrapping package.
 */
public class Row {
	Element jsoupElement;

	public Row(Element jsoupElement){
		this.jsoupElement = jsoupElement;
	}

	/**
	 * converts row wrapper object back to jsoup's Element.
	 * @return Element representing a row in a table
	 */
	public Element convertToElement(){
		return this.jsoupElement;
	}
}
