package utils;

public class Utils {

	public static <E> void headArray(E[] array, int nrow) {

		int i = 0;
		for (E element : array) {
			System.out.printf("%s ", element);
			i++;
			if (i > nrow) {
				break;
			}
		}

		System.out.println();
	}// END: headArray

	public static <E> void printArray(E[] array) {
		for (E element : array) {
			System.out.printf("%s ", element);
		}
		System.out.println();
	}// END: printArray

}
