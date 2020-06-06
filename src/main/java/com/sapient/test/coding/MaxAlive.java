package com.sapient.test.coding;

import java.util.Arrays;

/**
 * 
 * @author shivam.rohilla
 *
 */
public class MaxAlive {

	public static void main(String[] args) {
		int[] birth = { 1915, 1900, 1945, 1985, 1910 };
		int[] death = { 1958, 1970, 2000, 2030, 2005 };

		MaxAlive maxAlive = new MaxAlive();
		System.out.println(maxAlive.findYear(birth, death));
	}

	private int findYear(int[] birth, int[] death) {
		if (birth == null || death == null || birth.length == 0 || death.length == 0 || birth.length != death.length)
			return 0;

		sortArrays(birth, death);
		return findMaxAliveYear(birth, death);
	}

	private int findMaxAliveYear(int[] birth, int[] death) {
		int length = birth.length;
		int startBirth = 0, startDeath = 0, count = 0;
		int maxAlive = Integer.MIN_VALUE;
		int maxAliveYear = -1;

		while (startBirth < length || startDeath < length) {
			if (startBirth < length && birth[startBirth] < death[startDeath]) {
				if (count > maxAlive) {
					maxAlive = count;
					maxAliveYear = birth[startBirth];
				}
				count++;
				startBirth++;
			} else {
				startDeath++;
				count--;
			}
		}
		return maxAliveYear;
	}

	private void sortArrays(int[] birth, int[] death) {
		Arrays.sort(birth);
		Arrays.sort(death);
	}

}
