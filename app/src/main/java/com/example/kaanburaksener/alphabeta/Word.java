package com.example.kaanburaksener.alphabeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KAAN BURAK SENER on 27.06.2015.
 */
public class Word {
    private String inRussian;
    private String inTurkish;
    private String pronunciation;

    public Word(String inRussian, String inTurkish, String pronunciation) {
        super();
        this.inRussian = inRussian;
        this.inTurkish = inTurkish;
        this.pronunciation = pronunciation;
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

    @Override
    public String toString() {
        return "Rusça: " + this.inRussian + ", Türkçe: " + this.inTurkish + ", Telafuz: " + this.pronunciation;
    }
}
