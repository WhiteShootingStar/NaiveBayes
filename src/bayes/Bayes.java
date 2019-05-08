package bayes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import utils.Utilities;

public class Bayes {
	private String[][] trainingSet, testingSet;

	public Bayes(String filename, String testFilename) {
		try {
			trainingSet = Utilities.makeSet(filename);
			testingSet = Utilities.makeSet(testFilename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void classify() {
		List<String> list = Utilities.makeStartingList(trainingSet);
		Map<Integer, List<Tuple>> map = new HashMap<>();
		int counterName = 1;
		for (String[] strings : testingSet) {
			for (String name : list) {

				double[] arr = Utilities.calculateProbabilityFromTest(trainingSet, strings, name);
				Tuple tuple = new Tuple(name, probability(arr));
				List<Tuple> l = map.get(counterName);
				if (l == null)
					l = new ArrayList<>();
				l.add(tuple);
				map.put(counterName, l);

			}
			System.out.println(map);
			counterName++;
		}

		int TP = 0;
		int FP = 0;
		for (Integer val : map.keySet()) {
			String prognose = getPrognose(map.get(val));
			String actual = testingSet[val - 1][testingSet[0].length - 1];
			if (actual.equals(prognose)) {
				TP++;
			} else {
				FP++;
			}
			System.out.println("Actual value was " + actual + " and prognosed value is " + prognose);
		}
		System.out.println("Precision is " + (double) TP / (TP + FP));
		
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("Please enter " +(trainingSet[0][0].length()+1) + " separated with comma values and I wll try to classify them" );
			String[] in = scan.nextLine().split(",");
			for (String name : list) {

				double[] arr = Utilities.calculateProbabilityFromTest(trainingSet, in, name);
				Tuple tuple = new Tuple(name, probability(arr));
				List<Tuple> l = map.get(counterName);
				if (l == null)
					l = new ArrayList<>();
				l.add(tuple);
				map.put(counterName, l);

			}
			String prognose = getPrognose(map.get(counterName));
			System.out.println("This is your prognosed value " + prognose);
			counterName++;
		}
	}

	double probability(double[] arr) {
		return Arrays.stream(arr).reduce(1, (e1, e2) -> e1 * e2);
	}

	String getPrognose(List<Tuple> list) {
		double maxProb = list.stream().mapToDouble(e -> e.prob).max().getAsDouble();
		return list.stream().filter(e -> e.prob == maxProb).map(e -> e.name).findFirst().get();
	}
}
