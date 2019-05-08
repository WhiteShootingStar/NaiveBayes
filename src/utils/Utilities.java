package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utilities {

	public static String[][] makeSet(String filepath) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(filepath));
		int setHeigth = lines.size();
		int setWidth = lines.get(0).split(",").length;
		String[][] set = new String[setHeigth][setWidth];
		for (int i = 0; i < setHeigth; i++) {
			set[i] = lines.get(i).split(",");
		}

		return set;
	}

	public static List<String> makeStartingList(String[][] set) {
		int index = set[0].length - 1;
		return Arrays.stream(set).map(e -> e[index]).sequential().distinct().collect(Collectors.toList());

	}
	
//	static double calculateProbability(String[][] set, String condition) {
//		return (double) Arrays.stream(set).filter(e -> e[e.length - 1].equals(condition)).count() / set.length;
//	}

	static boolean contains(String[] array, String line, int index) {
		return array[index].equals(line);
	}

	static int numberOfPossibleValues(String[][] arr, int index) {

		return (int) Arrays.stream(arr).map(e -> e[index]).distinct().count();
	}

	static int howMuchContains(String[][] arr, String line, int index) {

		return (int) Arrays.stream(arr).filter(e -> contains(e, line, index)).count();
	}

	public static double[] calculateProbabilityFromTest(String[][] set, String[] testing, String keyname) {
		double[] table = new double[testing.length];
		String[][] tempArray = Arrays.stream(set).filter(e -> e[e.length - 1].equals(keyname)).toArray(String[][]::new);

		table[0] = (double) tempArray.length / set.length;

		for (int i = 1; i < testing.length; i++) {

			table[i] = (howMuchContains(tempArray, testing[i - 1], i - 1) + 1)
					/ (double) (tempArray.length + numberOfPossibleValues(set, i - 1));

		}

		return table;
	}

}
