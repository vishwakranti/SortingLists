
//Program to sort a list of random numbers using quick and insertion sorts.
//Please refer to readMe.txt file for the proper syntax to run this program
import java.util.Random;

public class SortList{
	
	public static void main(String [] args){
		
		if (args.length != 2) {
			System.out.println("Program to sort list of integers");
			System.out.println("Syntax to execute the program: java SortList sortType number");
			System.out.println("number: any positive number");
			System.out.println("sortType: has to be 'q' or 'i' ");
			System.out.println("q -> quick sort , i -> insertion sort");
		}
		
		if (args.length == 2){
			try{
				IntList randomList = new IntList();
				int minRange = 1;
				int maxRange = Integer.parseInt(args[1]);
				String sortType = args[0];
				Random random = new Random();
				int randNumber = 0;
				
				//Generate random numbers and add to a linked list
				for (int i = 0; i < maxRange; i++){
					randNumber = random.nextInt(maxRange - minRange + 1) + minRange;
					randomList.addToFront(randNumber);
				}
				
				//System.out.println("List of random numbers: ");
				//randomList.showList();
				
				//Apply insertion or quick sort to random number list as per user input
				if (sortType.equals("i")){
					sortType = "INSERTION SORT";
					System.out.println("\n\nSort Type: "+ sortType);
					IntList iSortList = randomList.makeCopy();
					//iSortList.showList();
					System.out.println("Sorted list:");
					int compareCount = iSortList.insertionSort();
					iSortList.showList();
					System.err.println("\nTotal number of comparisons: " + compareCount);
				}	
				else if (sortType.equals("q")){
					sortType = "QUICK SORT";
					System.out.println("\n\nSort Type: "+ sortType);
					IntList qSortList = randomList.makeCopy();
					//qSortList.showList();
					System.out.println("Sorted List:");
					int compareCount = qSortList.quickSort();
					qSortList.showList();
					System.err.println("\nTotal number of comparisons: " + compareCount);
				}
				else{
					System.out.println("Syntax to execute the program: java SortList sortType number");
					System.out.println("number: any positive number");
					System.out.println("sortType: has to be 'q' or 'i' ");
					System.out.println("q -> quick sort , i -> insertion sort");
				}
				
			}
			catch (Exception e){
				System.out.println("Error: " + e);
			}
		}
	}
}