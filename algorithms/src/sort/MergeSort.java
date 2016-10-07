package sort;

public class MergeSort{
	
	public int[] sortedArray;

	public MergeSort(int[] array){
		
		sortedArray = array;
	}
	private void divideAndSort(int p, int r){
				
		int[] array = new int[r-p+1];
		int mid = (int)(p + r)/2;
		int left = p, right = mid+1;
	
		for(int i = p; i <= r; i++){
		
			if(left <= mid && (right > r || sortedArray[left] <= sortedArray[right])){
				array[i -p] = sortedArray[left];
				++left;
			}else{
				array[i - p] = sortedArray[right];
				++right;
			}
		} 


		for(int i = 0; i < array.length; i++){
			sortedArray[p+i] = array[i];	
		}
	}

	public void mergeSort(int p, int r){

		if(r  > p){
			
			int mid = (int)(p + r)/2;
						
			mergeSort(p, mid);
			mergeSort(mid+1, r);						
			divideAndSort(p, r);
		}
	}
}

