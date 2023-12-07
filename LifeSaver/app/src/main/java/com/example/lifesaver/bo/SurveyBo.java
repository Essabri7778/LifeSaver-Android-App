package com.example.lifesaver.bo;

import java.util.ArrayList;
import java.util.List;

public class SurveyBo {

    private List<String> questions;

    private int current = 0;

    private int score = 0;

    public SurveyBo(){
        questions = new ArrayList<>();
        questions.add("Have you been feeling overwhelmed or hopeless lately?");
        questions.add("Do you find it difficult to enjoy activities that you used to find pleasurable?");
        questions.add("Have you had thoughts of hurting yourself?");
        questions.add("Do you feel like a burden to others?");
        questions.add("Have you experienced significant changes in your sleep patterns?");
        questions.add("Are you frequently isolating yourself from friends or family?");
        questions.add("Have you noticed changes in your appetite or weight?");
        questions.add("Do you feel like you're trapped in a cycle of negative thoughts that you can't escape?");
        questions.add("Have you recently experienced a major loss or life event that has affected your mood?");
        questions.add("Do you have a plan for self-harm?");
    }

    public String getCurrentQuestion(){
        return questions.get(current);
    }

    public int getScore(){
        return score;
    }

    public int getCurrent(){
        return current;
    }

    public List<String> getQuestions(){
        return questions;
    }

    public void incrementScore(){
        score++;
    }

    public void incrementCurrent(){
        current++;
    }

    public void decrementScore(){
        score--;
    }

}
