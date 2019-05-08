package main;

import bayes.Bayes;

public class Main {

	public static void main(String[] args) {
		Bayes bayes = new Bayes("train.txt", "test.txt");
		bayes.classify();

	}

}
