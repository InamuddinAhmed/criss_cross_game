import java.awt.*;
import java.awt.event.*;
import java.applet.*;
public class Game extends Applet implements ActionListener
{ 
	Label l1,l2,l3;
	int w;
	Button b[][]=new Button[3][3];
	Panel p,p1;
	check chk=new check();
	send s=new send();
	public void init()
	{
		w=0;
		Font f=new Font("Dialog",Font.BOLD,20);
		Font f1=new Font("Diag",Font.BOLD,16);
		setFont(f1);
		setForeground(Color.white);
		setBackground(Color.darkGray);
		for(int j=0;j<3;j++)
		{
			for(int k=0;k<3;k++)
			{
				b[j][k]=new Button("       ");
			}
		}
		setLayout(new BorderLayout());
		p=new Panel(new GridLayout(3,3,3,3));
		for(int j=0;j<3;j++)
		{
			for(int k=0;k<3;k++)
			{
				p.add(b[j][k]);
			}
		}
		add(p,BorderLayout.CENTER);
		for(int j=0;j<3;j++)
		{
			for(int k=0;k<3;k++)
			{
				b[j][k].addActionListener(this);
				b[j][k].setForeground(Color.black);
				b[j][k].setFont(f);
			}
		}
		p1=new Panel(new GridLayout(1,1));
		l1=new Label("computer : O ");
		l2=new Label("user :  X   ");
		p1.add(l1); p1.add(l2); add(p1,BorderLayout.SOUTH);
		l3=new Label("");
		add(l3,BorderLayout.NORTH);
	}
	public void start() {}
	public void actionPerformed(ActionEvent ae)
	{
		for(int j=0;j<3;j++)
		{
			for(int k=0;k<3;k++)
			{
				if(ae.getSource()==b[j][k])
				{
					b[j][k].setLabel("X");
					chk.setU(j,k);
					b[j][k].removeActionListener(this);
					if(chk.win()==1)
					{
						l2.setText("you wins");l3.setText("You win");w=1;
						for(int q=0;q<3;q++)
						{
							for(int qq=0;qq<3;qq++)
							{
								b[q][qq].removeActionListener(this);
							}
						}
					}
					if(chk.over()!=1)
					{
						s=chk.setC();
						b[s.i][s.j].setLabel("O");
						b[s.i][s.j].removeActionListener(this);
						if(chk.cwin()==1)
						{
							l1.setText("Computer wins");
							l3.setText("Computer wins");
							w=1;
							for(int q=0;q<3;q++)
							{
								for(int qq=0;qq<3;qq++)
								{
									b[q][qq].removeActionListener(this);
								}
							}
						}
					}
					else if (w==0)
					{
						l3.setText("Draw");
					}
				}
			}
		}
	}
	class check
	{
		int ar[][]=new int[3][3];
		int u,c;
		check()
		{
			u=0;c=0;
			for(int j=0;j<3;j++)
			{
				for(int k=0;k<3;k++)
				{
					ar[j][k]=0;
				}
			}
		}
		void setU(int j,int k)
		{
			send s=new send();
			ar[j][k]=2;
			u++;
		}
		send setC()
		{
			send s=new send();
			if(ar[1][1]==0)
			{
				ar[1][1]=1;
				s.i=1;s.j=1;
				c++;
				return s;
			}
			else
			{
				c++;
				send ss=new send();
				ss=next();
				if(ss.i==5)
				{
					send ss1=new send();
					ss1=unext();
					if(ss1.i!=5)
					{
						ar[ss1.i][ss1.j]=1;
						return ss1;
					}
					else
					{
						send ss2=new send();
						ss2=nextMove();
						ar[ss2.i][ss2.j]=1;
						return ss2;
					}
				}
				else
					ar[ss.i][ss.j]=1;
				return ss;
			}
		}
		send next()
		{
			send ss=new send();
			int i,j,k=0;
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					if(ar[i][j]==1)
					{
						k++;
					}
				}
				if(k==2)
				{
					for(int ii=0;ii<3;ii++)
					{
						if(ar[i][ii]==0)
						{
							send s=new send();
							s.i=i;
							s.j=ii;
							return s;
						}
					}
				}
				k=0;
			}
			k=0;
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					if(ar[j][i]==1)
					{
						k++;
					} 
				}
				if(k==2)
				{
					for(int ii=0;ii<3;ii++)
					{
						if(ar[ii][i]==0)
						{
							send s=new send();
							s.i=ii;
							s.j=i;
							return s;
						}
					
					}
				}
				k=0;
			}
			for(i=0;i<3;i++)
			{
				if(ar[i][i]==1)
				{
					k++;
				}
			}
			if(k==2)
			{
				for(i=0;i<3;i++)
				{
					if(ar[i][i]==0)
					{
						send s=new send();
						s.i=i;
						s.j=i;
						return s;
					}
				}
			}
			k=0;
			for(i=0;i<3;i++)
			{
				if(ar[i][2-i]==1)
				{
					k++;
				}
			}
			if(k==2)
			{
				for(i=0;i<3;i++)
				{
					if(ar[i][2-i]==0)
					{
						send s=new send();
						s.i=i;
						s.j=2-i;
						return s;
					}
				}
			}
			k=0;
			return ss; 
		}
		send unext()
		{
			int i,j,k=0;
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					if(ar[i][j]==2)
					{
						k++;
					}
				}
				if(k==2)
				{
					for(int ii=0;ii<3;ii++)
					{
						if(ar[i][ii]==0)
						{
							send s=new send();
							s.i=i;
							s.j=ii;
							return s;
						}
					}
				}
				k=0;
			}
			k=0;
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					if(ar[j][i]==2)
					{
						k++;
					} 
				}
				if(k==2)
				{
					for(int ii=0;ii<3;ii++)
					{
						if(ar[ii][i]==0)
						{
							send s=new send();
							s.i=ii;
							s.j=i;
							return s;
						}
					}
				}
				k=0;
			}
			for(i=0;i<3;i++)
			{
				if(ar[i][i]==2)
				{
					k++;
				}
			}
			if(k==2)
			{
				for(i=0;i<3;i++)
				{
					if(ar[i][i]==0)
					{
						send s=new send();
						s.i=i;
						s.j=i;
						return s;
					}
				}
			}
			k=0;
			for(i=0;i<3;i++)
			{
				if(ar[i][2-i]==2)
				{
					k++;
				}
			}
			if(k==2)
			{
				for(i=0;i<3;i++)
				{
					if(ar[i][2-i]==0)
					{
						send s=new send();
						s.i=i;
						s.j=2-i;
						return s;
					}
				}
			}
			k=0;
			send s1=new send();
			return s1;
		}
		send nextMove()
		{
			if((ar[0][1]==2)&&(ar[1][0]==2)&&(ar[0][0]==0))
			{
				send s=new send();
				s.i=0;
				s.j=0;
				return s;
			}
			else if((ar[0][1]==2)&&(ar[1][2]==2)&&(ar[0][2]==0))
			{
				send s=new send();
				s.i=0;
				s.j=2;
				return s;
			}
			else if((ar[1][0]==2)&&(ar[2][1]==2)&&(ar[2][0]==0))
			{
				send s=new send();
				s.i=2;
				s.j=0;
				return s;
			}
			else if((ar[1][2]==2)&&(ar[2][1]==2)&&(ar[2][2]==0))
			{
				send s=new send();
				s.i=2;
				s.j=2;
				return s;
			}
			else
			{
				send s=new send();
				int x=(int)(Math.random() *3);
				int y=(int)(Math.random() *3);
				s.i=x;
				s.j=y;
				while(ar[x][y]!=0)
				{
					x=(int)(Math.random() *3);
					y=(int)(Math.random() *3);
					s.i=x;
					s.j=y;
				}
				return s;
			}
		} 
		int over()
		{
			int k=0;
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					if(ar[i][j]!=0)
					{
						k++;
					}
				}
			}
			if(k==9)
			{
				return 1;
			}
			else
				return 0;
		}
		int win()
		{
			int i,j,k=0;
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					if(ar[j][i]==2)
					{ k++;} 
				}
				if(k==3)
				{
					return 1;
				}
				else
					k=0;
			}
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					if(ar[i][j]==2)
					{
						k++;
					} 
				}
				if(k==3)
				{
					return 1;
				}
				else
					k=0; 
			}
			for(i=0;i<3;i++)
			{
				if(ar[i][i]==2)
				{
					k++;
				}
			}
			if(k==3)
			{
				return 1;
			}
			else
				k=0;
			for(i=0;i<3;i++)
			{
				if(ar[i][2-i]==2)
				{
					k++;
				}
			}
			if(k==3)
			{
				return 1;
			}
			else
				k=0;
			return 0;
		}
		int cwin()
		{
			int i,j,k=0;
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					if(ar[j][i]==1)
					{
						k++;
					} 
				}
				if(k==3) 
				{
					return 1;
				}
				else
					k=0;
			}
			for(i=0;i<3;i++)
			{
				for(j=0;j<3;j++)
				{
					if(ar[i][j]==1)
					{
						k++;
					}
				}
				if(k==3)
				{
					return 1;
				}
				else
					k=0;
			}
			for(i=0;i<3;i++)
			{
				if(ar[i][i]==1)
				{
					k++;
				}
			}
			if(k==3)
			{
				return 1;
			}
			else
				k=0;
			for(i=0;i<3;i++)
			{
				if(ar[i][2-i]==1)
				{
					k++;
				}
			}
			if(k==3)
			{
				return 1;
			}
			else
			k=0;
			return 0;
		}
	}
	class send
	{
		int i;
		int j;
		send()
		{
			i=5;
			j=5;
		}
	}
}
