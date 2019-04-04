class cel
{
	int x,y,i,j,inc,cx,cy,numb;
	boolean wleft,wright,wtop,wbot;
	cel adj;
	cel(int x,int y,int i,int j,int inc,int n)
	{	
		this.inc=inc;
		this.i=i;
		this.j=j;
		this.x=x;
		this.y=y;
		wleft=wright=wtop=wbot=true;
		cx=x+inc/2;
		cy=y+inc/2;
		numb=n*i+j;
	}
}
