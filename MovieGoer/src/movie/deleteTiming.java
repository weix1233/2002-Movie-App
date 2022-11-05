package movie;

import java.util.List;
import java.util.Scanner;

public class deleteTiming{
	protected List<Integer> T;
	Scanner sc = new Scanner(System.in);
	public deleteTiming(List<Integer> timings) {
		T = timings;
	}
	
	public void deleteTime() {
		System.out.println("(1) Delete selected timings");
		System.out.println("(2) Delete all timngs");
		int x = sc.nextInt();
		while(x==1 || x==2) {
			switch(x) {
				case 1:
					System.out.println("Enter timeslot number to delete:");
					int num = sc.nextInt();
					T.remove(num-1);
					break;
				case 2:
					T.removeAll(T);
			}
		}
	}
	
	public List<Integer> returnDeleted() {
		return T;
	}
}
