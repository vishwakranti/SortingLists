//This is a custom linked list class that uses a recursive datatype IntListNode 
//to store an element and a node which points to the next node.

public class IntList{
	private IntListNode head;
	
	//constructor for the IntList
	public IntList(){
		head = null;
	}
	
	//Method to add an element to front of the list
	public void addToFront(int in){
	  IntListNode tmp = new IntListNode(in, null);
	  tmp.setNext(head);
	  head =tmp;
	}
	//Method to add an element to end of the list
	public void addToLast(int in){
		if (head == null) 
			addToFront(in);
		else {
			IntListNode curr = head;
			IntListNode prev = null;
			while (curr != null ){ 
				prev = curr;
				curr = curr.next;
			}
			IntListNode tmp = new IntListNode(in, null);
			prev.setNext(tmp) ;
		}
	}
	
	//Method to determine the length of list
	public int length(){
	  IntListNode tmp = head;
	  int count = 0;
	  while (tmp != null) {
	    count++;
	    tmp = tmp.getNext();
	  }
	  return count;
	}
	
	//Method to check for the existence of an element in the list
	public boolean hasElement(int in){
	  IntListNode tmp = head;
	  while (tmp != null) {
	    if (in ==tmp.getValue()) 
  	      return true;
	    tmp = tmp.getNext();
	  }
	  return false;
	}
	
	//Method to check if list is empty
	public boolean isEmpty(){
		IntListNode tmp = head;
		if(tmp == null)
			return true;
		else
			return false;
	}
	
	//Method to print each element in the list
	public void showList(){
		IntListNode tmp = head;
		while(tmp !=null ){
			System.out.println(tmp.getValue() + " ");
			tmp = tmp.getNext();
		}
	}
	
	//Method to retrieve the index of an element in the list
	public int getIndex(int in){
		IntListNode tmp = head;
		int count = 0;
		while (tmp != null){
			if (in == tmp.getValue())
				break;
			tmp = tmp.getNext();
			count++;
		}
		return count;
	}

	//Method to append a list to another list
	public void appendList(IntList in){
		IntListNode curr = head;
		IntListNode prev = null;
		IntListNode inNode = in.head;
		
		while (curr !=null){
			prev = curr;
			curr = curr.next;
		}
		if (prev!=null) 
			prev.setNext(inNode);
	}
	
	//Method to make copy of the list
	public IntList makeCopy(){
		IntListNode curr = head;
		IntList xCopy = new IntList();
		
		while (curr != null){
			xCopy.addToLast(curr.getValue());
			curr = curr.next;
		}
		return xCopy;
	}
	
	//Method to sort the list using insertion sort algorithm
	public int insertionSort(){
		IntList sortedList = new IntList(); //Declare an empty sorted list
		IntListNode unsortedCurr = head;
		int compareCount = 0; //variable to store number of comparison done while sorting
		while (unsortedCurr != null){
			head = head.getNext(); //Move head of unsorted list to next element
			unsortedCurr.next = null; //Set next of unsortedCurr to null (very imp step!)
			IntListNode sortedCurr = sortedList.head;
			IntListNode sortedPrev = null;
			
			if (sortedList.head == null){
				unsortedCurr.setNext(sortedCurr);
				sortedList.head = unsortedCurr;	
				sortedCurr = sortedList.head; //update sortedCurr to point to head again
			}
			else{
				while (sortedCurr != null){
					int sortedVal = sortedCurr.getValue();
					if (unsortedCurr.getValue() <= sortedVal) {
						if(sortedPrev == null) { //if there is only one element in the list
							unsortedCurr.setNext(sortedCurr);
							sortedList.head = unsortedCurr;
						}
						else{
							unsortedCurr.next = sortedCurr;
							sortedPrev.next = unsortedCurr;
						}
						compareCount++; //increment comparison count by 1
						break;
					}
					//if the value is not smaller than any in the list then append it 
					//at the end of the list
					if (sortedCurr.next == null){
						sortedCurr.setNext(unsortedCurr);
						break;
					}
					sortedPrev = sortedCurr;
					sortedCurr = sortedCurr.next;
				}
			}	
			unsortedCurr = head; //get the head value from the unsorted list
		}
		this.head = sortedList.head; //Point the head of the unsorted list to the sorted list
		//System.err.println("\n\nNumber of comparisons made: " + compareCount+"\n"); 
		return compareCount;
	}
	

	
	//Method to sort the list based on quick sort algorithm. Returns an int type containing the 
	//number of comparisons carried out while using quick sort algorithm
	public int quickSort(){
		int compareCount = 0 ;
		if (this.length() <= 1)  //Implement a base case for recursion in the if clause
			return 0;
		else{
			int pivot = this.head.getValue(); //first element of list is selected as pivot
			IntListNode curr = this.head.getNext();
			IntList pivotList = new IntList(); //list to keep pivot value(s)
			pivotList.addToLast(pivot);
			IntList smaller = new IntList(); //list to keep values smaller than pivot
			IntList bigger = new IntList(); //list to keep values bigger than pivot
			
			//separate list values based on pivot and add them to smaller/bigger/pivot
			//lists accordingly
			while (curr !=null){
				if (curr.getValue() < pivot){
					smaller.addToFront(curr.getValue());
					compareCount++;
				}
				else if (curr.getValue() > pivot){
					bigger.addToFront(curr.getValue());
					compareCount++;
				}
				else 
					pivotList.addToFront(curr.getValue());
				curr = curr.next;
			}
			
			//Recursive call to sort the bigger list
			int bCompareCount = bigger.quickSort(); //variable to store the number of comparisons
			if (bigger.head != null) {
				pivotList.appendList(bigger);
				head = pivotList.head;
			}
			
			//Recursive call to sort the smaller list
			int sCompareCount = smaller.quickSort(); //variable to store the number of comparisons
			if (smaller.head !=null) {
				smaller.appendList(pivotList);
				head = smaller.head;
			}
			compareCount = compareCount + sCompareCount + bCompareCount; //Sum up the total number of comparisons made for each quick sort call
		}
		//System.err.println("Total number of comparisons: " + compareCount);
		return compareCount;
	}
}
