import java.util.Random;
import java.util.Scanner;
public class Dice {
	public static void main(String[] args) {
		System.out.println("Enter the no. of dice faces -> ");
		Scanner sc = new Scanner(System.in);
		Integer nod = sc.nextInt();
		Dice d = new Dice(nod);
		System.out.println("Face of dice -> " + d.roll());
	 }

	int faces;
	public Dice(int faces)
	    {
		this.faces = faces;
		}
	
	public String roll(){
		Random r = new Random();
		String[] HT = {"Head" , "Tail"};
		String[] other = {"One", "Two", "Three", "Four","Five","Six"};
		
		if(this.faces == 2){
			int  output = r.nextInt(this.faces);
			return HT[output];
		}
		else{
			int  output = r.nextInt(this.faces);
			return(other[output]);
		}
	}
}