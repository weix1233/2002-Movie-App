package movie;

import java.util.List;

public class Showtime {
	private int n;
	private createTiming create;
	private updateTiming update;
	private deleteTiming delete;
	private printTimings printT;
	protected List<Integer> time; 

	public Showtime(List<Integer> T) {
		 time = T;
	}
	
	public List<Integer> createT(){
		create = new createTiming();
		time = create.timings;
		return time;
	}
	
	public List<Integer> updateT(){
		update = new updateTiming(time);
		update.toUpdate();
		time = update.returnUpdated();
		return time;
	}
	
	public List<Integer> deleteT(){
		delete = new deleteTiming(time);
		delete.deleteTime();
		time = delete.returnDeleted();
		return time;
	}
	
	public void print_Timings(List<Integer> time) {
		printT = new printTimings(time);
		printT.printList();
	}
	

}
