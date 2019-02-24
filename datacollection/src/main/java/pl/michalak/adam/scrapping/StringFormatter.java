package pl.michalak.adam.scrapping;

class StringFormatter {

    String removeSpecificWordFromSentence(String sentence){
    	for(UnwantedWords wordToBeRemoved : UnwantedWords.values())
        	sentence.replace(wordToBeRemoved.getUnwantedWords(), "\b");
    	return sentence;
    }
}
