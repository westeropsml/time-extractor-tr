package com.bezkoder.springjwt.models;

import javax.validation.constraints.NotBlank;

public class Deneme {
    @NotBlank
    private String sentence;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

}
