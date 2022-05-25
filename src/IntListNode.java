//Create a Linked List node data structure to store a 
// number (integer) and a node which points to the next node.
public class IntListNode{
	 int number;
	 IntListNode next;

	public IntListNode(int in, IntListNode next){
	  this.number = in;
	  this.next = null;
	}

	//Method to get value of the number stored in the node
	public int getValue(){
	  return number;
	}
	
	//Method to get the address stored in the next node
	public IntListNode getNext(){
	  return next;	
	}

	//Method to set the address of the next node
	public void setNext(IntListNode n){
	  next = n;
	}
}
