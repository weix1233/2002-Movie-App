package movie;

import java.util.Scanner;

public class Cineplex {
	private String name;
	Cineplex(String n){
		this.name = n;
	}
	Cineplex(){
		this("Cathay");
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Cinema chooseLocation() {
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		Cinema cp = null;
		do {
			System.out.println("Choose your cineplex: 1 - Jurong, 2 - Boon Lay, 3 - Orchard");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				cp = new Jurong();
				choice++;
				break;
			case 2:
				cp = new BoonLay();
				choice++;
				break;
			case 3:
				cp = new Orchard();
				choice++;
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
		} while(choice == -1);
		return cp;
	}
}
