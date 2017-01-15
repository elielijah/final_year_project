package com.example.elijah.memorymadness;


public class Question {

    private int _id;
    private int _image;
    private String _scenario;
    private String _question;
    private String _hint_basic;
    private String _hint_advance;
    private String _answer;
    private String _wrong_answer1;
    private String _wrong_answer2;
    private String _wrong_answer3;

    private String _part_two_question;
    private String _part_two_answer;

    private String _part_two_hint_basic;
    private String _part_two_hint_advanced;


    public Question(){
    }

    public Question(int image, String question, String Bhint, String Ahint, String answer){
        this._image = image;
        this._question = question;
        this._hint_basic = Bhint;
        this._hint_advance = Ahint;
        this._answer = answer;
    }

    public Question(String scenario, String Bhint, String Ahint, String answer){
        this._scenario = scenario;
        this._hint_basic = Bhint;
        this._hint_advance = Ahint;
        this._answer = answer;
    }

    public Question(String scenario, String question, String Bhint, String Ahint, String answer){
        this._scenario = scenario;
        this._question = question;
        this._hint_basic = Bhint;
        this._hint_advance = Ahint;
        this._answer = answer;
    }

    public Question(int image, String question, String Bhint, String Ahint, String answer, String wrong_answer1, String wrong_answer2, String wrong_answer3){
        this._image = image;
        this._question = question;
        this._hint_basic = Bhint;
        this._hint_advance = Ahint;
        this._answer = answer;
        this._wrong_answer1 = wrong_answer1;
        this._wrong_answer2 = wrong_answer2;
        this._wrong_answer3 = wrong_answer3;
    }

    public Question(String scenario, String question, String Bhint, String Ahint, String answer, String second_question, String second_answer, String wrong_answer1, String wrong_answer2, String wrong_answer3, String part_two_B_hint, String part_two_A_hint){
        this._scenario = scenario;
        this._question = question;
        this._hint_basic = Bhint;
        this._hint_advance = Ahint;
        this._answer = answer;
        this._part_two_question = second_question;
        this._part_two_answer = second_answer;
        this._wrong_answer1 = wrong_answer1;
        this._wrong_answer2 = wrong_answer2;
        this._wrong_answer3 = wrong_answer3;
        this._part_two_hint_basic = part_two_B_hint;
        this._part_two_hint_advanced = part_two_A_hint;
    }

    public int get_image(){
        return this._image;
    }

    public String get_scenario(){
        return this._scenario;
    }

    public String get_question(){
        return this._question;
    }

    public String get_Bhint(){
        return this._hint_basic;
    }

    public String get_Ahint(){
        return this._hint_advance;
    }

    public String get_answer(){
        return this._answer;
    }

    public String get_two_question(){
        return this._part_two_question;
    }

    public String get_part_two_answer(){
        return this._part_two_answer;
    }

    public String get_part_two_hint_basic(){
        return this._part_two_hint_basic;
    }

    public String get_part_two_hint_advanced(){
        return this._part_two_hint_advanced;
    }

    public String get_wrong_answer1(){
        return this._wrong_answer1;
    }

    public String get_wrong_answer2(){
        return this._wrong_answer2;
    }

    public String get_wrong_answer3(){
        return this._wrong_answer3;
    }
}
