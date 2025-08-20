import java.util.Scanner;

public class Ran_game {

	public static void main(String[] args) {
		Data_colector dat=new Data_colector();
		int[]v= {2,3,3,4,5};
		
for(int i=0;i<10000000;i++) { 
 Play_bord p =new Play_bord();
 Hidden_bord f= new Hidden_bord(v);
 f.comp_build();
 comp_strat.pure_point_systom(f);
 //System.out.println(i);
// int j=0;
// while(f.is_game_over()==0) {
//	 j++;
//	 comp_strat.random_modolar(f);
////	 comp_strat.modolar_boming(f);
////	 comp_strat.random_chess_strat(f);
////comp_strat.random_hit_strat(f);
//// for player game
////	 Scanner s=new Scanner(System.in);
////	
////	 try {
////		 int x=s.nextInt();
////		 int y=s.nextInt();
////		 int bombed=f.bomb(x, y);
////		if(bombed ==-1) {throw new Exception ();} 
////		p.switch_tile(x, y, f);
////		 p.print();
////		 
////	 }
////	 catch(Exception e){
////		 System.out.println("invalid input");
////		 
////	 }
//	if(j%1000==0) {
//		System.out.println(f.get_turns());
//		System.out.println(i);
//	}
// }
dat.inc(f.is_game_over());
}
dat.print();
	}
	

}
