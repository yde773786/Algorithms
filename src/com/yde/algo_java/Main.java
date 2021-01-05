package com.yde.algo_java;

public class Main {

    public static void main(String[] args) {
	    for(String s: args){
            switch(s){
                case "--peak-finder": PeakFinder.main(args);
                                break;
                case "--sort": Sort.main(args);
                                break;
                default:
                    System.out.println("No such flag");
            }
        }
    }
}