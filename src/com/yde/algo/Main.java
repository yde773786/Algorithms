package com.yde.algo;

public class Main {

    public static void main(String[] args) {
	    for(String s: args){
            switch(s){
                case "--lec1": Lecture_1.main();
                                break;
                default:
                    System.out.println("No such flag");
            }
        }
    }
}