package movie;

import java.util.List;

public class printTimings {
	private List<Integer> T;
	public printTimings(List<Integer> Timings) {
		T = Timings;
	}
	
	public void printList() {
		if(T.isEmpty()==false) {
			System.out.println("Timings are as follows:");
			for (int i = 0; i < T.size(); i++) 
		      { 		      
		          System.out.println("i+1: "+T.get(i)); 		
		      }   
		}
		else {
			System.out.println("There are no timings entered");
		}
	}
}
