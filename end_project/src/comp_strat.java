import java.util.ArrayList;
import java.util.List;
public class comp_strat {
	private static List<int[]>hitted=new ArrayList<int[]>();
	public static  int random_hit_strat(Hidden_bord h) {
		int x= h.get_random_number(0, 10);
		int y= h.get_random_number(0, 10);
		int hit=h.bomb(x, y);
		if(hit==1) {
		hitted.add(new int []{x,y});
		after_first_hit_simp(x,y,h);
		}
		return 1;
	}
	public static int pure_point_systom(Hidden_bord h) {
		int bord[][]=h.score_the_bord();
		while(h.is_game_over()==0) {
		int best_x=0;
		int best_y=0;
		int best_score=0;
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(bord[i][j]>best_score) {
					 best_x=i;
					 best_y=j;
					 best_score=bord[i][j];	
				}
			}
		}
		if(h.bomb(best_x, best_y)==1) {
			hitted.add(new int []{best_x,best_y});
			after_first_hit_simp(best_x,best_y,h);
			bord=h.score_the_bord();
		}
		else {
			h.score_bord_update(bord, best_x, best_y);
		}
		}
		return 1;
	}
	public static int random_modolar(Hidden_bord h) {
		int smallest_ship=h.get_smallest_ship_left();
		int y=h.get_random_number(0, 10);
		int x=h.get_random_number(0, 10/smallest_ship+1);
		x=x*smallest_ship+y%smallest_ship;
		int hit=h.bomb(x, y);
		if(hit==1) {
		hitted.add(new int []{x,y});
		after_first_hit_simp(x,y,h);
		}
		return 1;
	}
	public static int modolar_boming(Hidden_bord h) {
		int x=0;
		int y=0;
		int smallest_ship=h.get_smallest_ship_left();
		
		while( h.is_game_over()==0) {
			if(h.bomb(x, y)==1) {
				hitted.add(new int []{x,y});
				after_first_hit_simp(x,y,h);
				smallest_ship=h.get_smallest_ship_left();
				
			}
			if(x%smallest_ship==y%smallest_ship) {
			x+=smallest_ship;
			}
			else {
				x+=smallest_ship -(x%smallest_ship-y%smallest_ship);
			}
			if(x>9) {
				y+=1;
				x=y%smallest_ship;
			}
			if(y>9) {
				y=0;
				x=0;
			}
		}
		return 1;
	}
	public static int random_chess_strat(Hidden_bord h) {
		int y;
		int x= h.get_random_number(0, 10);
		if(x%2==0) {
			 y= h.get_random_number(0, 5)*2+1;
		}
		else {
			 y= h.get_random_number(0, 5)*2;
		}
		int hit=h.bomb(x, y);
		if(hit==1) {
			hitted.add(new int []{x,y});
			after_first_hit_simp(x,y,h);
			}
			return 1;
		
	}
	public static  int after_first_hit_simp(int x,int y,Hidden_bord h) {
	int hit=1;
	int i=1;
	while(hit==1 &&h.get_state(x+i, y)==1) {
		hit=h.bomb(x+i, y);
		if(hit>=1) {
			hitted.add(new int []{x+i,y});
			}
		i++;
	}
	if(hit<=1&&h.get_state(x-1, y)==1) {
		i=1;
		hit=1;
		while(hit==1 &&h.get_state(x-i, y)==1) {
			hit=h.bomb(x-i, y);
			if(hit>=1) {
				hitted.add(new int []{x-i,y});
				}
			i++;
	}	
}
	if(hit<=1&&h.get_state(x, y+1)==1) {
		i=1;
		hit=1;
		while(hit==1 &&h.get_state(x, y+i)==1) {
			hit=h.bomb(x, y+i);
			if(hit>=1) {
				hitted.add(new int []{x,y+i});
				}
			i++;
	}
}
	if(hit<=1&&h.get_state(x, y-1)==1) {
		i=1;
		hit=1;
		while(hit==1 &&h.get_state(x, y-i)==1) {
			hit=h.bomb(x, y-i);
			if(hit>=1) {
			hitted.add(new int []{x,y-i});
			}
			i++;
	}
		
}
	if(hit>1) {
	int last_x=hitted.get(hitted.size()-1)[0];
	int last_y=hitted.get(hitted.size()-1)[1];
	int [] ship_point=h.get_ship_start_and_end(last_x, last_y);
	for(int j=0;j<hitted.size();j++) {
		if(hitted.get(j)[0]>=ship_point[0]&&hitted.get(j)[0]<=ship_point[2]&&hitted.get(j)[1]>=ship_point[1]&&hitted.get(j)[1]<=ship_point[3]) {
			hitted.remove(j);
			j--;
		}
	}
	}
	if(hitted.size()!=0) {
		int xto_del=hitted.get(0)[0];
		int yto_del=hitted.get(0)[1];
		hitted.remove(0);
		hitted.add(new int [] {xto_del,yto_del});
		after_first_hit_simp( xto_del,yto_del, h);
		
	}
	return 1;
}


}

