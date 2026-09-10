package com.fireinyu.themyth.chatmodes;

import com.fireinyu.themyth.Defaults;
import com.fireinyu.themyth.Quiz;
import com.fireinyu.themyth.requests.AnswerRequest;
import com.fireinyu.themyth.requests.QuizRequest;
import com.fireinyu.themyth.requests.MarkRequest;
import com.fireinyu.themyth.requests.events.CloseRequest;
import com.fireinyu.themyth.requests.events.InitRequest;
import com.fireinyu.themyth.responses.Response;
import com.fireinyu.themyth.storage.QuizList;
import com.fireinyu.themyth.tasks.QuizTask;
import com.fireinyu.themyth.tasks.Task;

import java.util.Random;

/**
 * Request-Response model that provides interactive trivia quiz capabilities in addition to task management.
 * Loads a collection of quiz questions, maintains an active quiz question, and allows the user to answer questions.
 * @see TaskMode
 * @see Quiz
 * @see QuizTask
 */
public class QuizMode extends TaskMode {
    private final QuizTask quizTask = new QuizTask();
    private final QuizList quizzes = new QuizList();
    private Quiz activeQuiz;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Response respondToInit(InitRequest request) {
        Response response = super.respondToInit(request);
        this.quizzes.open(Defaults.QUIZFILE);
        this.activeQuiz = this.quizzes.get(new Random().nextInt(0,this.quizzes.size()));
        this.getTaskList().add(this.quizTask);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Response respondToMark(MarkRequest request) {
        int itemIndex = request.getArg(1, Integer.class)-1;
        Task task = super.getTaskList().get(itemIndex);
        if (task instanceof QuizTask) {
            return this.takeQuiz();
        }
        return super.mark(itemIndex);
    }

    @Override
    protected Response respondToClose(CloseRequest request) {
        this.getTaskList().remove(this.quizTask);
        return super.respondToClose(request);
    }

    @Override
    protected Response respondToQuiz(QuizRequest request) {
        return this.takeQuiz();
    }

    @Override
    protected Response respondToAnswer(AnswerRequest request) {
        if (request.getArg(1, Integer.class) == this.activeQuiz.getAnswerIndex()) {
            this.quizTask.mark();
            this.activeQuiz = this.quizzes.getRandom();
            return new Response("You are absolutely right!");
        } else {
            this.activeQuiz = this.quizzes.getRandom();
            return new Response("Oops! The correct answer is " + this.activeQuiz.getAnswerIndex());
        }
    }

    private Response takeQuiz() {
        return new Response(activeQuiz.toString());
    }

}
