package com.kaanburaksener.alphabeta.core;

/**
 * Created by KAAN BURAK SENER on 27.06.2015.
 */
public class Letter {
    private int id;
    private String uppercaseLetter;
    private String lowercaseLetter;
    private String pronunciation;

    public Letter(int id, String uppercaseLetter, String lowercaseLetter, String pronunciation) {
        super();
        this.id = id;
        this.uppercaseLetter = uppercaseLetter;
        this.lowercaseLetter = lowercaseLetter;
        this.pronunciation = pronunciation;
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

    @Override
    public String toString() {
        return ", Harf ID: " + this.id + "Büyük Harf: " + this.uppercaseLetter + ", Küçük Harf: " + this.lowercaseLetter + ", Okunuşu: " + this.pronunciation;
    }
}
