class q                         //CLASS FOR QUEUE
{ 
	qnode enq(qnode head,int enqdata)  //ENQUEUE FUNCTION
	{
		qnode curr=head;     //CREATING CURRENT
		if(head==null)       //CONDITION IF QUEUE IS EMPTY
		{
			head=new qnode(enqdata);						
		}
		else                //CONDITION IS QUEUE IS NOT EMPTY
		{
			while(curr.next!=null)  //TRAVERSING TILL END
				curr=curr.next;
			curr.next=new qnode(enqdata); 
			curr=curr.next;		//UPDATING CURRENT
		}
		return head;          //RETURNING HEAD
	}
	qnode deq(qnode head)     //DEQUEUE FUNCTION
	{
		return head.next;
	}
	int peek(qnode head)       //FUNCTION TO PEEK WITHOUT DEQUEUE
	{
		return head.data;
	}
	boolean isEmpty(qnode head)  //FUNCTION TO CHECK QUEUE IS EMPTY OR NOT
	{
		if(head==null)
			return true;
		return false;
	}
}
