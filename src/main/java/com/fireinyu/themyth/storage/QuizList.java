package com.fireinyu.themyth.storage;

import com.fireinyu.themyth.Quiz;
import com.fireinyu.themyth.exceptions.CorruptedTaskFileException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

/**
 * List of {@link Quiz} questions that can be loaded from and synced with a CSV file on disk or in resources.
 * @see Quiz
 * @see CsvBackedList
 */
public class QuizList extends CsvBackedList<Quiz> {

    private final Random random = new Random(LocalDateTime.now().getNano());

    /**
     * Initialises an empty {@code QuizList}.
     */
    public QuizList() {
        super();
    }

    @Override
    protected Quiz parse(String... item) throws CorruptedTaskFileException {
        return new Quiz(item[0], Arrays.asList(item).subList(2, item.length), Integer.parseInt(item[1]));
    }

    /**
     * Selects and returns a random quiz question from the list.
     *
     * @return a randomly chosen {@link Quiz} question
     */
    public Quiz getRandom() {
        return this.get(this.random.nextInt(0, this.size()));
    }

}
