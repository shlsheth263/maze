class solver
{
	int adjmat[][],n,source,dest,visited[],parent[],vctr; //DECLARING VARIABLES GLOBALLY
	cel grid[][];  //MATRIX OF CEL CLASS
	app obj;   //VARIABLE OF APP CLASS
	Thread th;
	solver(int s,int d,int pn,Thread t,app ob)  //CONSTRUCTOR .. CALLER ???????????????
	{
		obj=ob;
		th=t;
		this.n=pn*pn;                          //.........???????????/
		source=s;
		dest=d;
		visited=new int[n];
		parent=new int[n];
	}
	void setadj(int adj[][])   //SETTER TO SET ADJMAT AS ADJ
	{
		adjmat=adj;
	}
	void setgrid(cel g[][])   //SETTER TO SET GRID AS G
	{
		grid=g;
	}
	boolean bvisited(int v)   //FUNCTION TO CHECK VISITED STATUS
	{
		for(int i=0;i<vctr;i++)  //RUNNING LOOP TILL VISITED ARRAY SIZE
		{
			if(visited[i]==v)    //CHECKING IF VISITED
				return true;
		}
		return false;            //IF NOT VISITED
	}
	void bfs()                  //BFS FUNCTION ==========???
	{
		int v,u;
		q queue=new q();
		visited[vctr]=source;  
		vctr++;
		parent[source]=-1;
		qnode head=queue.enq(null,source); //CREATING HEAD OF TYPE QNODE AND INITIALISING HEAD TO NULL WITH SOURCE VALUE
		while(queue.isEmpty(head)==false)
		{
			u=queue.peek(head);
			head=queue.deq(head);
			for(int i=0;i<n;i++)
			{
				if(adjmat[u][i]==1)				
				{
					v=i;
					if(bvisited(v)==false)
					{
						visited[vctr]=v;
						vctr++;
						parent[v]=u;
						head=queue.enq(head,v);
					}					
				}
			}
		}
		upadj();
	}
	void upadj()   //FUNCTION TO UPDATE ADJ. MATRIX========???
	{		
		int temp=parent[dest];
		while(temp!=source)
		{					
			adjmat[parent[temp]][temp]=2;
			adjmat[temp][parent[temp]]=2;
			temp=parent[temp];
			obj.repaint();
		}
	}
	void display()     //FUNCTION TO DISPLAY PARENT
	{
		for(int i=0;i<n;i++)
		{	
			System.out.println("parent of "+i+" is "+parent[i]);
		}
	}
	void displayadj()  //FUNCTION TO DISPLAY ADJ. MATRIX
	{
		for(int i=0;i<n;i++)		
		{	
			for(int j=0;j<n;j++)
			{				
				System.out.print(adjmat[i][j]);
			}
			System.out.println();
		}
	}
	void displayq(qnode head)  //FUNCTION TO DISPLAY QUEUE
	{
		System.out.println("queue:");
		qnode curr=head;
		while(curr!=null)
		{
			System.out.println(curr.data);
			curr=curr.next;
		}
	}
}
