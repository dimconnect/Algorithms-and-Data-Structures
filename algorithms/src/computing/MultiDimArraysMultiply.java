package computing;

import java.util.ArrayList;
import java.util.List;

public class MultiDimArraysMultiply {
	
	private static List<ArrayList<Integer>> resultMatrixList; 
	private static int[][] resultMatrixArray;
	
 	public static List<ArrayList<Integer>> multiDimArrayMultiplyInList(int[][] array1, int[][] array2){				
		
 		resultMatrixList = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list;
		int mat2columns = largestRow(array2);				
		
		for(int i = 0; i < array1.length; i++){			
			list = new ArrayList<Integer>();			
			for(int j = 0; j < mat2columns; j++){
				list.add(0);					
				for(int k = 0; k < array1[i].length; k++){
					
					/* *****************************************************************
					 * check if the number of the array1's columns 
					 * is larger than number of the array2's rows, 
					 * wont get ArrayIndexOutOfBoundException for array2's row's index
					 * *****************************************************************
					 * also check if the number of the elements 
					 * in the largest row of array2(j) is larger than 
					 * the number of the elements in the current row					 
					 *******************************************************************/
					
					if(k < array2.length && j < array2[k].length){						
						list.set(j, list.get(j) + array1[i][k] * array2[k][j]);						
					}
				}
			}
			resultMatrixList.add(list);
		}
		return resultMatrixList; 		
	} 

	public static int[][] multiDimArrayMultiplyInMatrix(int[][] array1, int[][] array2){				
		
		int[] array;
		resultMatrixArray = new int[array1.length][];
		int mat2columns = largestRow(array2);				
		
		for(int i = 0; i < array1.length; i++){	
			array = new int[mat2columns];
			for(int j = 0; j < mat2columns; j++){									
				for(int k = 0; k < array1[i].length; k++){
					
					/* *****************************************************************
					 * check if the number of the array1's columns 
					 * is larger than number of the array2's rows, 
					 * wont get ArrayIndexOutOfBoundException for array2's row's index
					 * *****************************************************************
					 * also check if the number of the elements 
					 * in the largest row of array2(j) is larger than 
					 * the number of the elements in the current row					 
					 *******************************************************************/
					
					if(k < array2.length && j < array2[k].length){						
						array[j] += array1[i][k] * array2[k][j];						
					}
				}
			}
			resultMatrixArray[i] = array;
		}
		return resultMatrixArray; 		
	}
	
	private static int largestRow(int[][] matrix){
		
		/* ****************************************************
		 * search for the largest row in multidimensional array
		 ******************************************************/	
		
		int matColumns = 0;
		
		for(int i = 0; i < matrix.length; i++){			
			if(i == 0) 
				matColumns = matrix[i].length;
			else if(matColumns < matrix[i].length) 
				matColumns = matrix[i].length;			
		}
		
		return matColumns;
	}
}
