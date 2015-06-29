package com.example.kaanburaksener.alphabeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KAAN BURAK SENER on 27.06.2015.
 */
public class Letter {
    private int id;
    private String uppercaseLetter;
    private String lowercaseLetter;
    private String pronunciation;
    private List<Word> words;

    public Letter(int id, String uppercaseLetter, String lowercaseLetter, String pronunciation) {
        super();
        this.id = id;
        this.uppercaseLetter = uppercaseLetter;
        this.lowercaseLetter = lowercaseLetter;
        this.pronunciation = pronunciation;
        words = new ArrayList<Word>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUppercaseLetter() {
        return uppercaseLetter;
    }

    public void setUppercaseLetter(String uppercaseLetter) {
        this.uppercaseLetter = uppercaseLetter;
    }

    public String getLowercaseLetter() {
        return lowercaseLetter;
    }

    public void setLowercaseLetter(String lowercaseLetter) {
        this.lowercaseLetter = lowercaseLetter;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public List<Word> getWords() {
        return words;
    }

    public void addWord(String inRussian, String inTurkish, String pronunciation) {
       Word newWord = new Word(inRussian, inTurkish, pronunciation);
       words.add(newWord);
   }

    @Override
    public String toString() {
        return "Büyük Harf: " + this.uppercaseLetter + ", Küçük Harf: " + this.lowercaseLetter + ", Telafuz: " + this.pronunciation;
    }
}
