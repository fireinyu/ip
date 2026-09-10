package com.fireinyu.themyth.storage;

import com.fireinyu.themyth.Quiz;
import com.fireinyu.themyth.exceptions.CorruptedTaskFileException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

public class QuizList extends CsvBackedList<Quiz> {

    private final Random random = new Random(LocalDateTime.now().getNano());

    public QuizList() {
        super();
    }

    @Override
    protected Quiz parse(String... item) throws CorruptedTaskFileException {
        return new Quiz(item[0], Arrays.asList(item).subList(2, item.length), Integer.parseInt(item[1]));
    }

    public Quiz getRandom() {
        return this.get(this.random.nextInt(0, this.size()));
    }

}
