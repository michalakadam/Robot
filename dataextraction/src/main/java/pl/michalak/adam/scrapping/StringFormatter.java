package pl.michalak.adam.scrapping;

class StringFormatter {

    String removeSpecificWordFromSentence(String sentence, String wordToBeRemoved){
        return sentence.replace(wordToBeRemoved, "\b");
    }
}
