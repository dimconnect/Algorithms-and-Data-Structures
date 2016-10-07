package computing;

public class MaxSubarray {
	
	private int[] array;

	public MaxSubarray(int[] array){
		
		this.array = array;
	}
	
	private int[] findMaxCrossingSubarray(int beginning, int end){
				
		int sum = 0,
			mid = (int)(beginning+end)/2;
			int[] crossSubarray = new int[3];		
		
		for(int i = mid; i >= beginning; --i){	//left part of the subarray
			sum+=array[i];
			if(i == mid){
				crossSubarray[2] = sum;
				crossSubarray[0] = i;
			}else if(sum > crossSubarray[2]){
				crossSubarray[2] = sum;
				crossSubarray[0] = i;	
			}
		}
		
		sum = crossSubarray[2];
		for(int i = mid+1; i <= end; i++){	//right part of the subarray
			sum+=array[i];
			if(i == mid+1){
				crossSubarray[2] = sum;
				crossSubarray[1] = i;
			}else if(sum > crossSubarray[2]){
				crossSubarray[2] = sum;
				crossSubarray[1] = i;
			}	
		}
		/* **************************************************
		 * crossSubarray contains the indexes of the first 
		 * and last element and max sum of the cross subarray 
		 * between left part and right part 
		 ****************************************************/
		return crossSubarray;
	}

	/* ************************************************
	 * findMaxSubarray returns indexes of the first and 
	 * the last element and max sum of maxsubarray
	 **************************************************/
	
	private int[] findMaxSubarray(int beginning, int end){
		
		/* ***************************************************************
		 *  the value of the method argument begin 
		 * is the index of the first element in the subarray,
		 * it determine offset in the array and
		 * the value of the method argument end 
		 * is the index of the last element in the subarray,
		 * both determine the range in which findMaxSubarray will proceed
		 *****************************************************************/		
		
		int[] maxSubarray = new int[3];
		int mid = (int)(beginning+end)/2;
		
		if(beginning == end){
			maxSubarray = new int[]{beginning, beginning, array[beginning]};
			return maxSubarray;
		}else{
			
			int[] leftSubarray = findMaxSubarray(beginning ,mid);
			int[] rightSubarray = findMaxSubarray(mid+1, end);
			int[] crossSubarray = findMaxCrossingSubarray(beginning, end);
			
			if(leftSubarray[2] >= rightSubarray[2] && leftSubarray[2] >= crossSubarray[2]){				
				return maxSubarray = leftSubarray;				
			}else if(rightSubarray[2] >= leftSubarray[2] && rightSubarray[2] >= crossSubarray[2]){				
				return maxSubarray = rightSubarray;
			}else{				
				return maxSubarray = crossSubarray;
			}		
		}
	}
	
	public String[] maxSubarray(int beginning, int end){
		
		int[] array = findMaxSubarray(beginning, end);
		
		String[] maxSubarray = new String[]{
				"Start at index: "+array[0]+" - value: "+this.array[array[0]], 
				"End at index: "+array[1]+" - value: "+this.array[array[1]], 
				"Max sum is: "+array[2]
		};	
		
		return maxSubarray;
	}
}
