import java.util.Random;
class prim
{
	cel grid[][];
	int n,vsize,fsize;
	cel visited[];
	cel frontier[];
	Random rand=new Random();	
	prim(cel arr[][],int numb)
	{
		n=numb;
		grid=arr;		
		visited=new cel[n*n];
		frontier=new cel[n*n];
	}
	void display(int arr[][],int n)
	{
		for(int a=0;a<n;a++)
		{
			for(int b=0;b<n;b++)				
				System.out.print(arr[a][b]+",");
			System.out.println();
		}
		System.out.println("---------------");		
	}
	void algo(Thread t,app obj,int adjmat[][])
	{
		int r=rand.nextInt(n*n);
		visited[0]=grid[r/n][r%n];
		obj.showStatus("Started at:"+r/n+","+r%n);
		vsize++;
		do
		{			
			upfrontier(vsize-1);
			/*System.out.println("front:");
			display(frontier,fsize);*/
			r=rand.nextInt(fsize);
			//System.out.println("f:"+fsize+" v:"+vsize);
			cel rf=frontier[r];			
			if(rf.i==rf.adj.i+1)
			{
				rf.adj.wbot=false;
				rf.wtop=false;
				adjmat[rf.adj.numb][rf.numb]=1;
				adjmat[rf.numb][rf.adj.numb]=1;			
			}
			else if(rf.j==rf.adj.j-1)
			{
				rf.adj.wleft=false;
				rf.wright=false;
				adjmat[rf.adj.numb][rf.numb]=1;
				adjmat[rf.numb][rf.adj.numb]=1;
			}
			else if(rf.j==rf.adj.j+1)
			{
				rf.adj.wright=false;
				rf.wleft=false;
				adjmat[rf.adj.numb][rf.numb]=1;
				adjmat[rf.numb][rf.adj.numb]=1;
			}
			else if(rf.i==rf.adj.i-1)
			{
				rf.adj.wtop=false;
				rf.wbot=false;
				adjmat[rf.adj.numb][rf.numb]=1;
				adjmat[rf.numb][rf.adj.numb]=1;
			}
			visit(rf,r);
			//display(adjmat,n*n);
			/*System.out.println("visited:");
			display(visited,vsize);*/
			try{
				obj.repaint();
				t.sleep(2);
			}catch(InterruptedException e){}			
		}while(vsize!=n*n);
		obj.repaint();
		//display(adjmat,n*n);
		System.out.println("MAZE GENERATED!");
	}
	void visit(cel front,int r)
	{
		visited[vsize]=front;
		vsize++;
		frontier[r]=frontier[fsize-1];
		fsize--;
	}
	void upfrontier(int r)
	{
		int i=visited[r].i;
		int j=visited[r].j;
		cel temp;
		if(i-1>-1 && i-1<n)		
		{
			temp=grid[i-1][j];
			if(validfront(temp))
			{
				frontier[fsize]=temp;
				fsize++;
				temp.adj=visited[r];
			}
		}
		if(i+1>-1 && i+1<n)		
		{
			temp=grid[i+1][j];
			if(validfront(temp))
			{
				frontier[fsize]=temp;
				fsize++;
				temp.adj=visited[r];
			}
		}
		if(j-1>-1 && j-1<n)		
		{
			temp=grid[i][j-1];
			if(validfront(temp))
			{
				frontier[fsize]=temp;
				fsize++;
				temp.adj=visited[r];
			}
		}
		if(j+1>-1 && j+1<n)		
		{
			temp=grid[i][j+1];
			if(validfront(temp))
			{
				frontier[fsize]=temp;
				fsize++;
				temp.adj=visited[r];
			}
		}
	}
	boolean validfront(cel front)
	{
		for(int i=0;i<vsize;i++)
		{
			if(visited[i]==front)
				return false;
		}
		for(int i=0;i<fsize;i++)
		{
			if(frontier[i]==front)
				return false;
		}
		return true;
	}		
}
