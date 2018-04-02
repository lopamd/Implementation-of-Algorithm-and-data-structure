package cs6301.g45.Q1;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;

/**
 * Java Program of two Linked Lists implementing sorted sets performing set operations intersect, union and set difference
 * @author Lopamudra Muduli
 */

public class LinkedListSetOperations {
	
	/**
	 * Return elements common to l1 and l2, in sorted order in the output list
	 * @param l1: List<T> - a non-empty list passed from main()
	 * @param l2: List<T> - a non-empty list passed from main()
	 * @param outList: List<T> - an empty list passed from main() which will contain the elements common to l1 and l2
	 */
	public static<T extends Comparable<? super T>> void intersect(List<T> l1, List<T> l2, List<T> outList) {
		
		//If the list is empty then the intersection is empty
		if(l1.size() == 0) {
			outList = l1;
			System.out.println("Intersection Output List is empty as List1 is empty!");
			return;
		}
		
		if(l2.size() == 0) {
			outList = l2;
			System.out.println("Intersection Output List is empty as List2 is empty!");
			return;
		}
		
		//To iterate over the elements in the list
		Iterator<T> it1 = l1.iterator();	
		Iterator<T> it2 = l2.iterator();
		
		//To get the first elements of the list
		T list1 = next(it1);
		T list2 = next(it2);
		
		while(list1 != null && list2 != null) {
			
			if(list1.compareTo(list2) < 0) {
				list1 = next(it1); //list1 element is less than the list2 element so iterate it to get the next element
			} 
			else if (list1.compareTo(list2) > 0) {
				list2 = next(it2); //list2 element is less than the list1 element so iterate it to get the next element
			} 
			else {
				outList.add(list1);	//list1 element is equal to the list2 element so add it to the output list and iterate both list to get their next element
				list1 = next(it1);
				list2 = next(it2);
			}
		}
		
		System.out.print("\n"+"Intersect Output List: ");
		for(T opl:outList) {
			System.out.print(opl+" ");	
		}
		
		if(outList.size() == 0) {
			System.out.print("\n"+"Intersect Output List is empty as there are no common elements in both the lists!"+"\n");
		}
	}
	
	/**
	 * Return the union of l1 and l2, in sorted order in the output list
	 * @param l1: List<T> - a non-empty list passed from main()
	 * @param l2: List<T> - a non-empty list passed from main()
	 * @param outList: List<T> - an empty list passed from main() which will contain the union of l1 and l2
	 */
	public static<T extends Comparable<? super T>> void union(List<T> l1, List<T> l2, List<T> outList) {
		
		//If one list is empty then union is the other list
		if(l1.size() == 0) {
			outList = l2;
			System.out.println("Union Output List is equal to List2 as List1 is empty! \n Output: "+outList);
			return;
		}
		
		if(l2.size() == 0) {
			outList = l1;
			System.out.println("Union Output List is equal to List1 as List2 is empty! \n Output: "+outList);
			return;
		}
		
		//To iterate over the elements in the list
		Iterator<T> it1 = l1.iterator(); 
		Iterator<T> it2 = l2.iterator();
		
		//To get the first elements of the list
		T list1 = next(it1);
		T list2 = next(it2);
		
		while(list1 != null && list2 != null) {
			
			if(list1.compareTo(list2) < 0) {
				if(!outList.contains(list1)) {
					outList.add(list1);	//If list1 is less than list2 then list1 is added into output list if it is not already present in it and iterate it
				}
				list1 = next(it1);
			}
			else if(list1.compareTo(list2) > 0) {
				if(!outList.contains(list2)) {
					outList.add(list2); //If list2 is less than list1 then list2 is added into output list if it is not already present in it and iterate it
				}
				list2 = next(it2);
			}
			else {
				if(!outList.contains(list1)) {
					outList.add(list1); //If list1 and list2 are equal then one is added into output list and iterate both to get their next element
				}
				list1 = next(it1);
				list2 = next(it2);
			}
		}
		
		//If one list element is larger in size than the other and it is not present in the output list then add it
		while(list1 != null || list2 != null) 
		{
			if(list1 != null && list2 == null) {
				if(!outList.contains(list1)) {
					outList.add(list1);
				}
				list1 = next(it1);
			}
			else if(list1 == null && list2 != null) {
				if(!outList.contains(list2)) {
					outList.add(list2);
				}
				list2 = next(it2);
			}
		}
		
		System.out.print("\n"+"Union Output List: ");
		for(T opl:outList) {
			System.out.print(opl+" ");
		}
		
		if(outList.size() == 0) {
			System.out.print("\n"+"Union Output List is empty as there are no elements in both the lists!"+"\n");
		}
	}

	/**
	 * Return l1 - l2 (i.e, items in l1 that are not in l2), in sorted order in the output list
	 * @param l1: List<T> - a non-empty list passed from main()
	 * @param l2: List<T> - a non-empty list passed from main()
	 * @param outList: List<T> - an empty list passed from main() which will contain the set difference of l1 and l2
	 */
	public static<T extends Comparable<? super T>> void difference(List<T> l1, List<T> l2, List<T> outList) {
		
		//If list1 is empty then set difference is empty list
		if(l1.size() == 0) {
			outList = l1;
			System.out.println("Difference Output List is empty as List1 is empty! \n Output: "+outList);
			return;
		}
		
		//If list2 is empty then set difference is list1
		if(l2.size() == 0) {
			outList = l1;
			System.out.println("Difference Output List is equal to List1 as List2 is empty! \n Output: "+outList);
			return;
		}
		
		//To iterate over the elements in the list
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2 = l2.iterator();
		
		//To get the first elements of the list
		T list1 = next(it1);
		T list2 = next(it2);
		
		while(list1 != null && list2 != null) {
			
			if(list1.compareTo(list2) < 0) {
				if(!outList.contains(list1)) {
					outList.add(list1);	//If list1 is smaller than list2 then add it to output list and iterate it to get the next list1 element
				}
				list1 = next(it1);
			}
			else if(list1.compareTo(list2) > 0) {
				list2 = next(it2);	//If list2 is smaller than list1 then just iterate it to get the next list2 element and do not add it to output list
			}
			else if(list1.compareTo(list2) == 0) {
				list1 = next(it1);	//If both are equal then iterate them to get their next element
				list2 = next(it2);
			}
		}
		
		//If list1 is larger than list2 
		while(list1 != null) 
		{
			if(!outList.contains(list1)) {
				outList.add(list1);
			}
			list1 = next(it1);
		}
		
		System.out.print("\n"+"Difference Output List: ");
		for(T l:outList) {
			System.out.print(l+" ");
		}
		
		if(outList.size() == 0) {
			System.out.print("\n"+"Difference Output List is empty as every element in List1 is present in List2!"+"\n");
		}
	}
	
	/**
	 * Returns the next element in the iteration
	 * @param it: Iterator<T> : iterator element to iterate over the list
	 * @return the next element in the iteration if it exists otherwise return null
	 */
	public static <T extends Comparable<? super T>> T next(Iterator<T> it) {
		if(it.hasNext()) {
			return it.next();
		} else {
			return null;
		}
	}
	
	public static void main(String args[]) {
		
		List<Integer> list1 = new LinkedList<>();
		List<Integer> list2 = new LinkedList<>();
		List<Integer> outIntList = new LinkedList<>();
		List<Integer> outUnionList = new LinkedList<>();
		List<Integer> outDiffList = new LinkedList<>();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of the first Linked Lists: ");
		int size1 = sc.nextInt();
		
		System.out.println("Enter elements for the first Linked List: ");
		for(int i=1; i<=size1; i++) {
			list1.add(sc.nextInt());
		}
		
		System.out.println("Enter the size of the second Linked Lists: ");
		int size2 = sc.nextInt();
		
		System.out.println("Enter elements for the second Linked List: ");
		for(int i=1; i<=size2; i++) {
			list2.add(sc.nextInt());
		}
		
		sc.close();
		
		System.out.print("List1: ");
		for(Integer l1:list1) {
			System.out.print(l1+" ");
		}
		
		System.out.print("\n"+"List2: ");
		for(Integer l2:list2) {
			System.out.print(l2+" ");
		}

		System.out.println("\n"+"-----Intersect Operation-----");
		intersect(list1, list2, outIntList);
		System.out.println("\n"+"-----Union Operation-----");
		union(list1, list2, outUnionList);
		System.out.println("\n"+"-----Set Difference Operation-----");
		difference(list1, list2, outDiffList);
	}
}
