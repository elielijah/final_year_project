package com.example.elijah.memorymadness;

public class Users {

      private int _id;
      private String _username;
      private String _userage;
      private String _usergender;
      private String _userscore;
      private String _difficulty;

      public Users(String name, String age, String gender){
            this._username = name;
            this._userage = age;
            this._usergender = gender;
          }

      public Users(Integer id, String name, String age, String gender){
        this._id = id;
        this._username = name;
        this._userage = age;
        this._usergender = gender;
    }

      public void set_id(int _id) {
            this._id = _id;
          }

      public void set_username(String username) {
            this._username = username;
          }

      public void set_userage(String age){
          this._userage = age;
      }

      public void set_userscore(String score){
          this._userscore = score;
      }

      public void set_usergender(String gender){
          this._usergender = gender;
      }

      public void set_difficulty(String difficulty) { this._difficulty = difficulty; }

      public int get_id() {
            return _id;
          }

      public String get_username(){
            return _username;
          }

      public String get_userage(){
          return _userage;
      }

      public String get_usergender() {
          return _usergender;
      }

      public String get_userscore(){
        return _userscore;
    }





}