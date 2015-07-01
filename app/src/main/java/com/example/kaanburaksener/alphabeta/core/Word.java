package com.example.kaanburaksener.alphabeta.core;

/**
 * Created by KAAN BURAK SENER on 27.06.2015.
 */
public class Word {
    private String inRussian;
    private String inTurkish;
    private String pronunciation;
    private int firstLetterId;

    public Word(String inRussian, String inTurkish, String pronunciation, int firstLetterId) {
        super();
        this.inRussian = inRussian;
        this.inTurkish = inTurkish;
        this.pronunciation = pronunciation;
        this.firstLetterId = firstLetterId;
    }

    public String getInRussian() {
        return inRussian;
    }

    public void setInRussian(String inRussian) {
        this.inRussian = inRussian;
    }

    public String getInTurkish() {
        return inTurkish;
    }

    public void setInTurkish(String inTurkish) {
        this.inTurkish = inTurkish;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public int getFirstLetterID() {
        return firstLetterId;
    }

    public void setFirstLetterId(int firstLetterId) {
        this.firstLetterId = firstLetterId;
    }

    @Override
    public String toString() {
        return "Rusça: " + this.inRussian + ", Türkçe: " + this.inTurkish + ", Okunuşu: " + this.pronunciation + ", Harf ID: " + this.firstLetterId;
    }
}
