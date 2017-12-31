package com.sandip.model;


import java.io.Serializable;

public class ModelWords  implements Serializable{
    String wordsNo,word,wordsType,wordsEngMeaning,wordsMarathiMeaning,wordUse;


    public String getWordsNo() {
        return wordsNo;
    }

    public void setWordsNo(String wordsNo) {
        this.wordsNo = wordsNo;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordsType() {
        return wordsType;
    }

    public void setWordsType(String wordsType) {
        this.wordsType = wordsType;
    }

    public String getWordsEngMeaning() {
        return wordsEngMeaning;
    }

    public void setWordsEngMeaning(String wordsEngMeaning) {
        this.wordsEngMeaning = wordsEngMeaning;
    }

    public String getWordsMarathiMeaning() {
        return wordsMarathiMeaning;
    }

    public void setWordsMarathiMeaning(String wordsMarathiMeaning) {
        this.wordsMarathiMeaning = wordsMarathiMeaning;
    }

    public String getWordUse() {
        return wordUse;
    }

    public void setWordUse(String wordUse) {
        this.wordUse = wordUse;
    }
}
