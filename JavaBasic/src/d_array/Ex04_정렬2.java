package d_array;

import java.util.Scanner;

public class Ex04_정렬2 {
	static void bubbleSort(int array[]) {
		for(int i=0; i<array.length-1; i++)
		{
			for(int j=0; j<array.length-1-i; j++)
			{
				if(array[j]>array[j+1])
				{
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
	}
	
	static void selectionSort(int array[]){
		for(int i=0; i<array.length-1; i++)
		{
			int min_idx=i;
			for(int j=i+1; j<array.length; j++)
			{
				if(array[j]<array[min_idx])
					min_idx=j;
			}
			int temp = array[min_idx];
			array[min_idx]= array[i];
			array[i] = temp;
		}
	}
	
	static void insertionSort(int array[]){
		for(int i=1; i<array.length; i++)
		{
			int temp = array[i];
			int idx = i-1;
			while(idx>=0 && temp<array[idx])
			{
				array[idx+1]=array[idx];
				idx--;
			}
			array[idx+1]= temp;
		}
	}
	
	static void printArray(int array[]){
		for(int i=0; i<array.length; i++)
			System.out.print(array[i] + " ");
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int score [] = {90, 44, 55, 22, 20, 14, 80, 40};
		bubbleSort(score);
		System.out.println("bubble sort--------------------");
		printArray(score);
		int score2 [] = {90, 44, 55, 22, 20, 14, 80, 40};
		selectionSort(score2);
		System.out.println();
		System.out.println("selection sort------------------");
		printArray(score2);
		int score3 [] = {90, 44, 55, 22, 20, 14, 80, 40};
		insertionSort(score3);
		System.out.println();
		System.out.println("selection sort------------------");
		printArray(score3);
		
	}
}
