package pl.michalak.adam.scrapping;

class DataFormatter {

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
}
