package movie;

import java.util.List;
import java.util.Scanner;

public class updateTiming{
	protected List<Integer> time;
	Scanner sc = new Scanner(System.in);
	public updateTiming(List<Integer> T) {
		time = T;
	}
	
	public void toUpdate() {
		int num;
		System.out.println("(1) Change timing ");
		System.out.println("(2) Add new timing");
		System.out.println("(3) Exit ");
		num = sc.nextInt();
		while(num==1 || num==2) {
			switch(num) {
			case 1:
				System.out.println("Enter timeslot number to be updated (0 to exit):");
				int x = sc.nextInt();
				while(x!=0) {
					System.out.println("Enter timing to update with:");
					Integer t = sc.nextInt();
					time.set(x-1, t);
					System.out.println("Timing updated!");
					System.out.println("Enter timeslot number to be updated (0 to exit):");
					x = sc.nextInt();
				}
				break;
			case 2:
				System.out.println("Enter new timing:");
				Integer newTime = sc.nextInt();
				time.add(newTime);
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
			System.out.println("(1) Change timing");
			System.out.println("(2) Add new timing");
			System.out.println("(3) Exit ");
			num = sc.nextInt();
		}
		System.out.println("Updates completed!");
	}
	
	public List<Integer> returnUpdated(){
		return time;
	}
}
