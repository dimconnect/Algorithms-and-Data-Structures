package binarysearchtree;

public class QuickSort {
	
	public Double[] sortedArray;
	
	public QuickSort(Double[] array){
		
		sortedArray = array;
	}
	
	private int partition(int p, int r){
		
		double pivot = sortedArray[r];
		int i = p - 1;
		double temp;
		
		for(int j = p; j <  r; j++){
			if(sortedArray[j] <= pivot){
				i+=1;
				temp = sortedArray[i];				
				sortedArray[i] = sortedArray[j];
				sortedArray[j] = temp;
			}
		}
		
		temp = sortedArray[r];			
		sortedArray[r] = sortedArray[i+1];
		sortedArray[i+1] = temp;  
		
		return i+1;
	}

	public void quickSort(int p, int r){
			
		if(r > p){
			int q = partition(p, r);				
			quickSort(p, q-1);
			quickSort(q+1, r);
		}
	}
}
