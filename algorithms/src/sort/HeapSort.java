package sort;

public class HeapSort{
	
private int[] heap;
	
	private void maxHeaplify(int index, int offset){
		
		/* offset is determine the number of the elements
		 * in the array (heap), which are going to be sorted,
		 * starting from the first element - (offset-1) 
		 */
		
		if(offset > 1){			
			int left = 2*index + 1;
			int  right = left + 1;
			int n = (int)(offset/2 - 1);
			int largest;			
			
			if(heap[left] > heap[index]){ 
				largest = left;
			}else{
				largest = index;
			}
			
			if(right < offset && heap[right] > heap[largest]){
				largest = right;
			}
			
			if(largest != index){
				int temp = heap[index];
				heap[index] = heap[largest];
				heap[largest] = temp;
				
				if(largest <= n){					
					maxHeaplify(largest, offset);
				}
			}	
		}
	}

	private void buildMaxHeap(){		
		int n = (int)(heap.length/2 - 1);		
		
		for(int i = n; i >=0; --i ){
			maxHeaplify(i, heap.length);
		}		
	}

	public int[] heapSort(int[] unsortedHeap){		
		heap = unsortedHeap;
		
		buildMaxHeap();	
		
		for(int i = heap.length - 1; i >= 0; --i){
			int temp = heap[i];
			heap[i] = heap[0];
			heap[0] = temp;
			
			maxHeaplify(0, i);
		}
		return heap;
	}
}

