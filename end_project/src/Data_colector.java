
public class Data_colector {
private int []data;

public Data_colector(){
 this.data= new int [100];	
}
public void inc(int x) {
	data[x-1]++;
}
public void print() {
	for(int i=0;i<100;i++) {
		System.out.print(i+1);
		System.out.print("	");
		System.out.println(data[i]);
	}
}
}
