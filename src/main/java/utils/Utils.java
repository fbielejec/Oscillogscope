package utils;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author fbielejec
 */
public class Utils {

	public static <E> void headArray(E[] array, int nrow) {

		 Arrays.stream(array).limit(nrow).forEach(System.out::println);
		
//		int i = 0;
//		for (E element : array) {
//			System.out.printf("%s ", element);
//			i++;
//			if (i > nrow) {
//				break;
//			}
//		}
//
//		System.out.println();
	}// END: headArray

	public static <E> void printArray(E[] array) {
//		for (E element : array) {
//			System.out.printf("%s ", element);
//		}
//		System.out.println();
		
		
		 Arrays.stream(array).forEach(System.out::println);
		
	}// END: printArray

	
	public static <E> void headCollection(Collection<E> coll, int nrow) {
		coll.stream().limit(nrow).forEach(System.out::println);
	}// END: headArray
	
	public static <E> void printCollection(Collection<E> coll ) {
		headCollection(coll, coll.size());
	}// END: headArray
	
}
