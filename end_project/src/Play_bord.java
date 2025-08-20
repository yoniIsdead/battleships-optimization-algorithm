public class Play_bord {
private char[][] bord;
private int turn;
public Play_bord() {
	this.bord=new char [10][10];
	for(int i=0;i<10;i++) {
		for(int j=0;j<10;j++) {
			this.bord[i][j]='O';
		}
		
	}
	turn=0;
}
public int get_random_number(int min, int max) {
	 return (int) ((Math.random() * (max - min)) + min);}

public void print() {
	System.out.println("  0 1 2 3 4 5 6 7 8 9" );
	for(int i=0;i<10;i++) {
		System.out.print(i);
		System.out.print(' ');
		for(int j=0;j<10;j++) {
			System.out.print(bord[i][j]);
			System.out.print(" ");
		}
		System.out.println();
	}
}
public int switch_tile(int x ,int y,Hidden_bord h) {
	if (h.get_vis(x, y)=='f') {
		return 0;
	}
	this.bord[x][y]=h.get_vis(x, y);
	return 1;
	
}
public char tile_at(int x,int y) {
	return this.bord[x][y];
}
public int[] rand_play_to_hit(Hidden_bord h) {
	int x=get_random_number(0,10);
	int y=get_random_number(0,10);
	while(this.tile_at(x, y)!='O') {
		x=get_random_number(0,10);
		y=get_random_number(0,10);
	}
		turn++;
		int [] re=new int[3] ;
		re[0]=h.bomb(x, y);
		re[1]=x;
		re[2]=y;
		return re;
}


}