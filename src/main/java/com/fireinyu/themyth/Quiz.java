package com.fireinyu.themyth;

import com.fireinyu.themyth.storage.CsvSerializable;

import java.util.ArrayList;
import java.util.List;

public class Quiz implements CsvSerializable {
    private final String question;
    private final List<String> choices;
    private final int answerIndex;

    public Quiz(String question, List<String> choices, int answerIndex) {
        this.question = question;
        this.choices = choices;
        this.answerIndex = answerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getChoices() {
        return choices;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    @Override
    public List<String> extract() {
        List<String> extracted = new ArrayList<>();
        extracted.add(this.question);
        extracted.add(String.valueOf(this.answerIndex));
        extracted.addAll(this.choices);
        return extracted;
    }

    @Override
    public String toString() {
        StringBuilder body = new StringBuilder();
        body.append(this.question);
        for (int i = 0; i < this.choices.size(); i++) {
            body.append(
                    String.format("\n   %d: %s",i, this.choices.get(i))
            );
        }
        return body.toString();
    }
}
