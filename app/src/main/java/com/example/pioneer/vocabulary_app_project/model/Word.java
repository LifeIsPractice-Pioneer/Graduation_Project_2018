package com.example.pioneer.vocabulary_app_project.model;

/**
 * Created by Pioneer on 2018/3/3.
 */

public class Word {
    private String ID;
    private String meanning;
    private String spelling;
    private String phonetic_alphabet;
    private String list;

    public Word() {

    }

    public Word(String ID, String meanning, String spelling, String phonetic_alphabet, String list) {
        this.ID = ID;
        this.meanning = meanning;
        this.spelling = spelling;
        this.phonetic_alphabet = phonetic_alphabet;
        this.list = list;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setMeanning(String meanning) {
        this.meanning = meanning;
    }

    public String getMeanning() {
        return meanning;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setPhonetic_alphabet(String phonetic_alphabet) {
        this.phonetic_alphabet = phonetic_alphabet;
    }

    public String getPhonetic_alphabet() {
        return phonetic_alphabet;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getList() {
        return list;
    }

}
