package movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class createTiming {
	protected List<Integer> timings = new ArrayList<Integer>();
	private int num_slot = 0;
	
	Scanner sc = new Scanner(System.in);
	public createTiming() {
		System.out.println("Please enter number of timeslots");
		num_slot = sc.nextInt()
;		for(int i = 0; i < num_slot; i++) {
			System.out.println("Please enter timing of timeslot "+i+1);
			Integer x = sc.nextInt();
			timings.add(x);
		}
		System.out.println("Timings all set!");
	}
}
