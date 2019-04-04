import java.applet.*;
import java.awt.*;
public class app extends Applet implements Runnable
{
	int n,side,adjmat[][],end,show;
	cel cell[][];
	int inc;
	Thread t;
	solver solve;
	/*
	private Image offScreenImage;
	private Dimension offScreenSize;
	private Graphics offScreenGraphics;
	public final synchronized void update (Graphics g)
	{
		Dimension d = new Dimension(side,side);
		if((offScreenImage == null) || (d.width != offScreenSize.width) || (d.height != offScreenSize.height))
		{
			offScreenImage = createImage(d.width, d.height);
			offScreenSize = d;
			offScreenGraphics = offScreenImage.getGraphics();
		}
		offScreenGraphics.clearRect(0, 0, d.width, d.height);
		paint(offScreenGraphics);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	*/
	public void init()
	{		
		show=0;
		side=650;
		n=50;
		cell=new cel[n][n];
		adjmat=new int[n*n][n*n];
		inc=side/n;
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				cell[i][j]=new cel(j*inc+20,i*inc+20,i,j,inc,n);
			}
		}		
		cell[0][0].wtop=false;
		cell[n-1][n-1].wbot=false;
		t=new Thread(this);
		solve=new solver(0,n*n-1,n,t,this);				
		t.start();
	}	
	public void run()
	{
		try{
			t.sleep(1000);
		}catch(InterruptedException e){}
		prim obj=new prim(cell,n);
		obj.algo(t,this,adjmat);
		end=1;
		solve.setadj(adjmat);
		solve.setgrid(cell);
		solve.bfs();
		repaint();
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		int tx,ty;
		cel temp;
		g.drawString("START",cell[0][0].x-10,cell[0][0].y-10);
		g.drawString("END",cell[n-1][n-1].x+inc+10,cell[n-1][n-1].y+inc+10);	
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				temp=cell[i][j];
				tx=temp.x;
				ty=temp.y;
				//g.drawString(temp.i+","+temp.j,temp.x+20,temp.y+20);
				if(show==1)
					g.drawString(temp.numb+"",temp.cx,temp.cy);
				if(temp.wtop)
					g.drawLine(tx,ty,tx+inc,ty);								
				if(temp.wright)
					g.drawLine(tx+inc,ty,tx+inc,ty+inc);								
				if(temp.wbot)
					g.drawLine(tx+inc,ty+inc,tx,ty+inc);								
				if(temp.wleft)
					g.drawLine(tx,ty+inc,tx,ty);				
			}
		}		
		if(show==1 && end==1)
		{
			g.setColor(Color.blue);
			for(int i=0;i<n*n;i++)
			{
				for(int j=0;j<n*n;j++)
				{
					if(adjmat[i][j]>0 )
					{
						g.drawLine(cell[i/n][i%n].cx,cell[i/n][i%n].cy,cell[j/n][j%n].cx,cell[j/n][j%n].cy);						
					}
				}
			}
		}
		g.setColor(Color.red);
		if(end==1)
		{
			for(int i=0;i<n*n;i++)
			{
				for(int j=0;j<n*n;j++)
				{
					if(adjmat[i][j]==2)
					{
						g.drawLine(cell[i/n][i%n].cx,cell[i/n][i%n].cy,cell[j/n][j%n].cx,cell[j/n][j%n].cy);						
					}
				}
			}
		}
	}
}
