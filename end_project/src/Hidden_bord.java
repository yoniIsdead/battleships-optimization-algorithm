import java.util.Scanner;
public class Hidden_bord {
private int[][] hid_bord;
private char[][] vis_bord;
private int end;
private int [] ships;
private int [] ships_real;
private int turns;
private int [][] start_to_end;
public int get_random_number(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
}
public int get_turns() {
	return turns;
}
public int get_smallest_ship_left() {
	int smallest_ship=11;
	for (int i=0;i<ships.length;i++) {
		if(ships[i]!=0&&ships_real[i]<smallest_ship) {
			smallest_ship=ships[i];
		}
	}
	return smallest_ship;
}
public Hidden_bord(int ships[]) {
	this.hid_bord=new int [10][10];
	this.vis_bord=new char [10][10];
	for(int i=0;i<10;i++) {
		for(int j=0;j<10;j++) {
			this.hid_bord[i][j]=-1;
			this.vis_bord[i][j]='O';
		}
		
	}
	this.ships=new int[ships.length];
	this.ships_real=new int[ships.length];
	this.start_to_end=new int[ships.length][4];
	for(int i=0;i<ships.length;i++) {
		this.ships[i]=ships[i];
		this.ships_real[i]=ships[i];
	}
	this.end=ships.length;
	this.turns=1;
}
public void print() {
	System.out.print("  0 1 2 3 4 5 6 7 8 9\n" );
	
	for(int i=0;i<10;i++) {
		System.out.print(i);
		System.out.print(' ');
		for(int j=0;j<10;j++) {
			System.out.print(vis_bord[i][j]);
			System.out.print(' ');
		}
		System.out.println();
	}
}
public void printh() {
	for(int i=0;i<10;i++) {
		for(int j=0;j<10;j++) {
			System.out.print(hid_bord[i][j]);
			System.out.print(' ');
		}
		System.out.println();
	}
}
public void plant_ships() {
	Scanner s=new Scanner(System.in);
	for(int i=0;i<ships.length;i++) {
	try {
	int x1=0,x2=0,y1=0,y2=0;
	System.out.println("insert the ship of size " +ships[i]+" first x cordinet ");
	x1=s.nextInt();
	System.out.println(x1);
	if(x1>9||x1<0){
		throw new Exception ("invalid");
	}
	System.out.println("insert the ship of size " +ships[i]+" first y cordinet ");
	y1=s.nextInt();
	System.out.println(y1);
	if(y1>9||y1<0){
		throw new Exception ("invalid");
	}
	
	System.out.println("insert the ship of size " +ships[i]+" second x cordinet ");
	x2=s.nextInt();
	System.out.println(x2);
	if(x2>10||x2<0){
		throw new Exception ("invalid");
	}
		System.out.println("insert the ship of size " +ships[i]+" second y cordinet ");
		y2=s.nextInt();
		System.out.println(y2);
		if(y2>10||y2<0){
			throw new Exception ("invalid");
		}
		if((x1!=x2)&&(y1!=y2)) {
			throw new Exception ("invalid");
		}
		else {
			int dif,small;
			if(x1==x2) {
				if(y1>y2) {
				dif=y1-y2;
				small=y2;
				}
				else {
					dif=y2-y1+1;
					small=y1;
				}
				if(dif!=ships[i]) {
					throw new Exception ("invalid");
				}
				else {
					for(int j=0;j<dif;j++) {
						if(hid_bord[x1][small+j]!=-1) {
						throw new Exception ("invalid");
						}
					}
					for(int j=0;j<dif;j++) {
					hid_bord[x1][small+j]=ships[i];
					vis_bord[x1][small+j]='S';
					}
				}
				
			}
			else {
				if(x1>x2) {
					dif=x1-x2;
					small=x2;
					}
					else {
						dif=x2-x1+1;
						small=x1;
					}
					if(dif!=ships[i]) {
						throw new Exception ("invalid");
					}
					else {
						for(int j=0;j<dif;j++) {
							if(hid_bord[small+j][y1]!=-1) {
								throw new Exception ("invalid");
							}
						}
						for(int j=0;j<dif;j++) {
							hid_bord[small+j][y1]=ships[i];
							vis_bord[small+j][y1]='S';
						}
					}
					
			}
			System.out.println("successfully plentet");
			this.print();
		}
	
	}
	catch(Exception e){
		System.out.println("invalid input try again");
		i--;
		s.nextLine();
	}
	
	}
}
public int bomb(int x,int y) {
if (x>9 || x<0 || y>9 || y<0||this.hid_bord[x][y]==-2) {
	return -1;
}
else {
	if(this.hid_bord[x][y]==-1) {
		this.hid_bord[x][y]=-2;
		this.vis_bord[x][y]='*';
		turns+=1;
		return 0;
	}
	else {
	int s=this.hid_bord[x][y];
	this.ships[s]--;
	this.hid_bord[x][y]=-2;
	this.vis_bord[x][y]='X';
	if(this.ships[s]==0) {
	end--;
	return ships_real[s];
	}
	else {
		return 1;
	}
	}
	
}
}
public int is_game_over(){
	if(end==0) {
		return turns;
	}
	return 0;
}
public int ship_left() {
	return end;
}
public char get_vis(int x,int y) {
	if(x>9||x<0||y>9||y<0) {
		return 'f';
	}
	return this.vis_bord[x][y];
}
public void comp_build() {
	for(int i=0;i<ships.length;i++) {
		try {
		int x_start;
		int x_end;
		int y_start;
		int y_end;
		int xory= get_random_number(0,2);
		int x=get_random_number(0,10);
		int y=get_random_number(0,10);
		if(this.hid_bord[x][y]!=-1) {
			throw new Exception ("invalid");
		}
		if(xory!=1) {
		int doru=get_random_number(0,2);
		int xto;
		if(doru==1) {
			xto=x-this.ships[i]+1;
			x_start=xto;
			x_end=x;
			y_start=y;
			y_end=y;
			if (xto>9||xto<0) {
				throw new Exception ("invalid");
			}
			for(int j=x;j>=xto;j--){
				if(this.hid_bord[j][y]!=-1) {
					throw new Exception ("invalid");
				}
			}
			for(int j=x;j>=xto;j--){
				hid_bord[j][y]=i;
				vis_bord[j][y]='S';
			}
		}
		else {
			xto=x+this.ships[i]-1;
			x_start=x;
			x_end=xto;
			y_start=y;
			y_end=y;
			if (xto>9||xto<0) {
				throw new Exception ("invalid");
			}
			for(int j=x;j<=xto;j++){
				if(this.hid_bord[j][y]!=-1) {
					throw new Exception ("invalid");
				}
			}
			for(int j=x;j<=xto;j++){
				hid_bord[j][y]=i;
				vis_bord[j][y]='S';
			}
			
		}
	}
		else {
			int doru=get_random_number(0,2);
			int yto;
			if(doru==1) {
				yto=y-this.ships[i]+1;
				x_start=x;
				x_end=x;
				y_start=yto;
				y_end=y;
				if (yto>9||yto<0) {
					throw new Exception ("invalid");
				}
				for(int j=y;j>=yto;j--){
					if(this.hid_bord[x][j]!=-1) {
						throw new Exception ("invalid");
					}
				}
				for(int j=y;j>=yto;j--){
					hid_bord[x][j]=i;
					vis_bord[x][j]='S';
				}
			}
			else {
				yto=y+this.ships[i]-1;
				x_start=x;
				x_end=x;
				y_start=y;
				y_end=yto;
				if (yto>9||yto<0) {
					throw new Exception ("invalid");
				}
				for(int j=y;j<=yto;j++){
					if(this.hid_bord[x][j]!=-1) {
						throw new Exception ("invalid");
					}
				}
				for(int j=y;j<=yto;j++){
					hid_bord[x][j]=i;
					vis_bord[x][j]='S';
				}
				
			}
		}
		this.start_to_end[i][0]=x_start;
		this.start_to_end[i][1]=y_start;
		this.start_to_end[i][2]=x_end;
		this.start_to_end[i][3]=y_end;
	}
	catch(Exception e){
		i--;
	}
	
}
}
public int get_state(int x ,int y) {
	if(x<=9 && x>=0 && y<=9 && y>=0 && hid_bord[x][y]!=-2) {
		return 1;
	}
	else {
		return 0;
	}
}
int[]get_ship_start_and_end(int x,int y){
	int to_ret=0;
	for (int i=0;i<this.ships.length;i++) {
			if(start_to_end[i][0]<=x&&x<=start_to_end[i][2]&&start_to_end[i][1]<=y&&y<=start_to_end[i][3]) {
				to_ret=i;
				break;
		}
		
	}
	return this.start_to_end[to_ret];
}
int [][] score_the_bord(){
	int [][] score_bord= new int [10][10];
	for(int i=0;i<10;i++) {
		for(int j=0;j<10;j++) {
			score_bord[i][j]=0;
		}
		
	}
	for(int k=0;k<this.ships.length;k++) {
		
	
	if(ships[k]!=0) {
		
	
	for(int i=0;i<10;i++) {
		for(int j=0;j<10;j++) {
			int flag_up=1;
			int flag_left=1;
			for (int valid_placement=0;valid_placement<ships[k];valid_placement++) {
				if((j+ships[k]<=10)) {
				if(hid_bord[i][j+valid_placement]==-2) {
					flag_left=0;
				}
				}
				else {
					flag_left=0;
				}
				if((i+ships[k]<=10)) {
				if(hid_bord[i+valid_placement][j]==-2) {
					flag_up=0;
				}
				}
				else {
					flag_up=0;
				}
				if(flag_up==0&&flag_left==0) {
					break;
				}
			}
			if(flag_up!=0 ||flag_left!=0) {
			for(int to_add=0;to_add<ships[k];to_add++) {
				if (flag_up!=0) {
					score_bord[i+to_add][j]+=1;
				}
				if(flag_left!=0) {
					score_bord[i][j+to_add]+=1;
				}
			}
			}
		}
	}
	
}
	}
	return score_bord;
}
public void score_bord_update(int[][]sb,int x ,int y) {
	for(int i =0;i<ships.length;i++) {
		if(ships[i]!=0) {
			for(int j =0;j<ships[i];j++) {
				int flag_left=1;
				int flag_up=1;
				if(x-ships[i]+j+1>=0 && x+j<=9) {
					for(int k=x-ships[i]+j+1;k<=x+j;k++) {
						if(sb[k][y]==0) {
							flag_left=0;
						}
					}
				}
				else {flag_left=0;}
					if(y-ships[i]+j+1>=0 && y+j<=9) {
						for(int k=y-ships[i]+j+1;k<=y+j;k++) {
							if(sb[x][k]==0) {
								flag_up=0;
							}
							
				}
			}
					else {flag_up=0;}
			if(flag_up!=0||flag_left!=0) {
				for(int k=0;k<ships[i];k++) {
					if(flag_left!=0) {
						sb[x-ships[i]+j+1+k][y]-=1;
					}
					if(flag_up!=0) {
						sb[x][y-ships[i]+j+1+k]-=1;	
					}
				}
			}
		}
	}
}
}
}
