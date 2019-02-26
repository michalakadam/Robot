package pl.michalak.adam.scrapping;

import java.math.BigDecimal;
import java.math.RoundingMode;

class DataFormattingHelper {

    static String removeSpecificWordFromSentence(String sentence){
    	for(UnwantedWords wordToBeRemoved : UnwantedWords.values())
        	sentence = sentence.replace(wordToBeRemoved.getUnwantedWords(), "");
    	return sentence;
    }

    static double parseStringToDouble(String data){
    	return Double.parseDouble(prepareStringForParsing(data));

	}

	private static String prepareStringForParsing(String data){
    	return data.replace("zł", "").replace("PLN", "").replace(",", ".");
	}

	static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();
		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	static String getPromoInPercents(double price, double previousPrice){
		int promoInPercents = (int) (((previousPrice-price)/previousPrice)*100);
		if(promoInPercents < 10)
			promoInPercents = 10; //solves problem with sorting strings in database. Fix: pass integer to db and then fomat it on front-end
		return new StringBuilder().append("-").append(promoInPercents).append("%").toString();
	}
}
