package computing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestCommonSequence {
	
	public static List<Integer> sequenceOfIntegerLists(List<Integer> list1, List<Integer> list2){
		List<Integer> list = null;
		List<Integer> sequence = new ArrayList<Integer>();
		int startingPosition = 0;
		
		while((list1.size()-startingPosition) > sequence.size()){
			
			/* repeat while number the of remaining elements in list1 (list1.size()-startingPosition)
			 * is greater than length of the current longest common sequence, otherwise 
			 * even if all remaining elements match elements in list2 length of the sequence
			 * cannot be greater than length of the current longest common sequence 
			 * *********************************************************************************
			 * for every while loop iteration initial position in list1 for searching is changed
			 * and initial position in list2 for searching is 0 (starting from first element)
			 * so that for every list1's element search will start from first list2's element
			 * Example: list1-> [1, 0, 0, 1, 0, 1, 0, 1] and list2-> [0, 1, 0, 1, 1, 0, 1, 1, 0]
			 * at first iteration the initial position in list1 for start searching will be 0,
			 * so that will start from first list1's element and will produce sequence-> [1, 0, 0, 1, 0],
			 * at second iteration the initial position in list1 for start searching will be 1,
			 * so that will start from second list1's element and will produce sequence-> [0, 0, 1, 0, 1, 0]*/
			
			int continueFromPosition = 0;
			list = new ArrayList<Integer>();		
			for(int j = startingPosition; j < list1.size(); ++j){
				if(continueFromPosition == list2.size() || list2.size()-continueFromPosition+list.size() <= sequence.size()) break;
				
				/* if the number the of remaining elements in list2 plus elements in the current sequence
				 * (list2.size()-continueFromPosition+list.size())is greater than length of the current
				 * longest common sequence, otherwise even if all remaining elements match elements in list1 
				 * length of the sequence cannot be greater than length of the current 
				 * longest common sequence and previous condition statement will break the loop*/
				
				for(int k = continueFromPosition; k < list2.size(); ++k){
					if(list1.get(j).equals(list2.get(k))){
						if(list.size() == 0){	
							startingPosition = j+1;
							/* Example: list1-> [2, 3, 1, 0, 0, 1, 0, 1, 0, 1] and list2-> [0, 1, 0, 1, 1, 0, 1, 1, 0]
							 * first two elements in list1 wouldn't match any of the elements in list2 and thus
							 * for both elements first match occur for third element which will start searching
							 * from the first element of list2 and thus will produce the same sequence three times - 
							 * start searching from first element of list1 will produce sequence-> [1, 0, 0, 1, 0]
							 * start searching from second element of list1 will produce sequence-> [1, 0, 0, 1, 0]
							 * start searching from third element of list1 will produce sequence-> [1, 0, 0, 1, 0]
							 * so current condition statement check if after match is founded length of the current
							 * sequence is zero, if so next initial position in list1 for next while loop iteration will be 
							 * index of the element next to index of the current matched element and thus avoid swapping*/
						}
						
						/* if match is found add the element in current "match" sequence (list),
						 * increase value of variable continueFromPosition, so that next element of list1
						 * will start looking for match in list2 from element next to the current matched element,
						 * and finally stop searching another match for current list1's element by breaking the loop*/
						list.add(list1.get(j));
						continueFromPosition = k + 1;
						break;
					}
				}
			}		
			if(list.size() == 0){		
				/* check if after first search no match is found 
				 * which means both sequence are completely different
				 * of each other and search ends with loop break*/
				break;
			}else if(sequence.size() < list.size()){				
				sequence = list;
			}
		}
		return sequence;
	}
	
	public static int[] sequenceOfIntArrays(int[] array1, int[] array2){
		List<Integer> 
			list1 = new ArrayList<Integer>(),
			list2 = new ArrayList<Integer>(),
			list;
		for(int i : array1)	list1.add(i);
		for(int i: array2)	list2.add(i);
		list = sequenceOfIntegerLists(list1, list2);
		int[] sequence = new int[list.size()];
		for(int i = 0; i < list.size(); ++i)	sequence[i] = list.get(i);	//autounboxing from Integer to int
		return sequence;
	}
	
	public static List<Double> sequenceOfDoubleLists(List<Double> list1, List<Double> list2){
		List<Double> list = null;
		List<Double> sequence = new ArrayList<Double>();
		int startingPosition = 0;
		
		while((list1.size()-startingPosition) > sequence.size()){
			int continueFromPosition = 0;
			list = new ArrayList<Double>();
			
			for(int j = startingPosition; j < list1.size(); ++j){
				if(continueFromPosition == list2.size() || list2.size()-continueFromPosition+list.size() <= sequence.size()) break;
				
				for(int k = continueFromPosition; k < list2.size(); ++k){
					if(list1.get(j).equals(list2.get(k))){
						if(list.size() == 0)	startingPosition = j+1;
						list.add(list1.get(j));
						continueFromPosition = k + 1;
						break;
					}
				}
			}
			if(list.size() == 0){
				break;
			}else if(sequence.size() < list.size()){				
				sequence = list;
			}
		}
		return sequence;
	}
	
	public static double[] sequenceOfDoubleArrays(double[] array1, double[] array2){
		List<Double> 
			list1 = new ArrayList<Double>(),
			list2 = new ArrayList<Double>(),
			list;
		for(double i : array1)	list1.add(i);
		for(double i: array2)	list2.add(i);
		list = sequenceOfDoubleLists(list1, list2);
		double[] sequence = new double[list.size()];
		for(int i = 0; i < list.size(); ++i)	sequence[i] = list.get(i);
		return sequence;
	}
	
	public static List<Float> sequenceOfFloatLists(List<Float> list1, List<Float> list2){
		List<Float> list = null;
		List<Float> sequence = new ArrayList<Float>();
		int startingPosition = 0;
		
		while((list1.size()-startingPosition) > sequence.size()){
			int continueFromPosition = 0;
			list = new ArrayList<Float>();
			
			for(int j = startingPosition; j < list1.size(); ++j){
				if(continueFromPosition == list2.size() || list2.size()-continueFromPosition+list.size() <= sequence.size()) break;
				
				for(int k = continueFromPosition; k < list2.size(); ++k){
					if(list1.get(j).equals(list2.get(k))){
						if(list.size() == 0)	startingPosition = j+1;
						list.add(list1.get(j));
						continueFromPosition = k + 1;
						break;
					}
				}
			}
			if(list.size() == 0){
				break;
			}else if(sequence.size() < list.size()){				
				sequence = list;
			}
		}
		return sequence;
	}
	
	public static float[] sequenceOfFloatArrays(float[] array1, float[] array2){
		List<Float> 
			list1 = new ArrayList<Float>(),
			list2 = new ArrayList<Float>(),
			list;
		for(float i : array1)	list1.add(i);
		for(float i: array2)	list2.add(i);	
		list = sequenceOfFloatLists(list1, list2);
		float[] sequence = new float[list.size()];
		for(int i = 0; i < list.size(); ++i)	sequence[i] = list.get(i);	
		return sequence;
	}
	
	public static List<Short> sequenceOfShortLists(List<Short> list1, List<Short> list2){
		List<Short> list = null;
		List<Short> sequence = new ArrayList<Short>();
		int startingPosition = 0;
		
		while((list1.size()-startingPosition) > sequence.size()){
			int continueFromPosition = 0;
			list = new ArrayList<Short>();
			
			for(int j = startingPosition; j < list1.size(); ++j){
				if(continueFromPosition == list2.size() || list2.size()-continueFromPosition+list.size() <= sequence.size()) break;
				
				for(int k = continueFromPosition; k < list2.size(); ++k){
					if(list1.get(j).equals(list2.get(k))){
						if(list.size() == 0)	startingPosition = j+1;
						list.add(list1.get(j));
						continueFromPosition = k + 1;
						break;
					}
				}
			}
			if(list.size() == 0){
				break;
			}else if(sequence.size() < list.size()){				
				sequence = list;
			}
		}
		return sequence;
	}
	
	public static short[] sequenceOfShortArrays(short[] array1, short[] array2){
		List<Short> 
			list1 = new ArrayList<Short>(),
			list2 = new ArrayList<Short>(),
			list;
		for(short i : array1)	list1.add(i);
		for(short i: array2)	list2.add(i);	
		list = sequenceOfShortLists(list1, list2);
		short[] sequence = new short[list.size()];
		for(int i = 0; i < list.size(); ++i)	sequence[i] = list.get(i);
		return sequence;
	}
	
	public static List<Byte> sequenceOfByteLists(List<Byte> list1, List<Byte> list2){
		List<Byte> list = null;
		List<Byte> sequence = new ArrayList<Byte>();
		int startingPosition = 0;
		
		while((list1.size()-startingPosition) > sequence.size()){
			int continueFromPosition = 0;
			list = new ArrayList<Byte>();
			
			for(int j = startingPosition; j < list1.size(); ++j){
				if(continueFromPosition == list2.size() || list2.size()-continueFromPosition+list.size() <= sequence.size()) break;
				
				for(int k = continueFromPosition; k < list2.size(); ++k){
					if(list1.get(j).equals(list2.get(k))){
						if(list.size() == 0)	startingPosition = j+1;
						list.add(list1.get(j));
						continueFromPosition = k + 1;
						break;
					}
				}
			}
			if(list.size() == 0){
				break;
			}else if(sequence.size() < list.size()){				
				sequence = list;
			}
		}
		return sequence;
	}
	
	public static byte[] sequenceOfByteArrays(byte[] array1, byte[] array2){
		List<Byte> 
			list1 = new ArrayList<Byte>(),
			list2 = new ArrayList<Byte>(),
			list;
		for(byte i : array1)	list1.add(i);
		for(byte i: array2)	list2.add(i);	
		list = sequenceOfByteLists(list1, list2);
		byte[] sequence = new byte[list.size()];
		for(int i = 0; i < list.size(); ++i)	sequence[i] = list.get(i);
		return sequence;
	}
	
	public static List<Character> sequenceOfCharacterLists(List<Character> list1, List<Character> list2){
		List<Character> list = null;
		List<Character> sequence = new ArrayList<Character>();
		int startingPosition = 0;
		
		while((list1.size()-startingPosition) > sequence.size()){
			int continueFromPosition = 0;
			list = new ArrayList<Character>();
			
			for(int j = startingPosition; j < list1.size(); ++j){
				if(continueFromPosition == list2.size() || list2.size()-continueFromPosition+list.size() <= sequence.size()) break;
				
				for(int k = continueFromPosition; k < list2.size(); ++k){
					if(list1.get(j).equals(list2.get(k))){
						if(list.size() == 0)	startingPosition = j+1;
						list.add(list1.get(j));
						continueFromPosition = k + 1;
						break;
					}
				}
			}
			if(list.size() == 0){
				break;
			}else if(sequence.size() < list.size()){				
				sequence = list;
			}
		}
		return sequence;
	}
	
	public static char[] sequenceOfCharArrays(char[] array1, char[] array2){
		List<Character> 
			list1 = new ArrayList<Character>(),
			list2 = new ArrayList<Character>(),
			list;
		for(char i : array1)	list1.add(i);
		for(char i: array2)	list2.add(i);	
		list = sequenceOfCharacterLists(list1, list2);
		char[] sequence = new char[list.size()];
		for(int i = 0; i < list.size(); ++i)	sequence[i] = list.get(i);	
		return sequence;
	}
	
	public static List<String> sequenceOfStringLists(List<String> list1, List<String> list2){
		List<String> list = null;
		List<String> sequence = new ArrayList<String>();
		int startingPosition = 0;
		
		while((list1.size()-startingPosition) > sequence.size()){
			int continueFromPosition = 0;
			list = new ArrayList<String>();
			
			for(int j = startingPosition; j < list1.size(); ++j){
				if(continueFromPosition == list2.size() || list2.size()-continueFromPosition+list.size() <= sequence.size()) break;
				
				for(int k = continueFromPosition; k < list2.size(); ++k){
					if(list1.get(j).equals(list2.get(k))){
						if(list.size() == 0)	startingPosition = j+1;
						list.add(list1.get(j));
						continueFromPosition = k + 1;
						break;
					}
				}
			}
			if(list.size() == 0){
				break;
			}else if(sequence.size() < list.size()){				
				sequence = list;
			}
		}
		return sequence;
	}
	
	public static String[] sequenceOfStringArrays(String[] array1, String[] array2){
		List<String> 
			list1 = Arrays.asList(array1),
			list2 = Arrays.asList(array2),
			list;	
		list = sequenceOfStringLists(list1, list2);
		String[] sequence = new String[list.size()];
		list.toArray(sequence);			
		return sequence;
	}
	
	public static String sequenceOfStrings(String st1, String st2){
		List<Character> list = null;
		String sequence = new String();
		int startingPosition = 0;
		char[]
			array1 = st1.toCharArray(),
			array2 = st2.toCharArray();
		
		while((array1.length-startingPosition) > sequence.length()){
			int continueFromPosition = 0;
			list = new ArrayList<Character>();
			
			for(int j = startingPosition; j < array1.length; ++j){
				if(continueFromPosition == array2.length || array2.length-continueFromPosition+list.size() <= sequence.length()) break;
				
				for(int k = continueFromPosition; k < array2.length; ++k){
					if(array1[j] == array2[k]){
						if(list.size() == 0)	startingPosition = j+1;
						list.add(array1[j]);
						continueFromPosition = k + 1;
						break;
					}
				}
			}
			if(list.size() == 0){
				break;
			}else if(sequence.length() < list.size()){				
				StringBuilder sb = new StringBuilder();
				for(Character c : list){
					sb.append(c);
				}
				sequence = sb.toString();
			}
		}
		return sequence;
	}
}
