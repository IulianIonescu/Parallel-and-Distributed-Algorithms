public class Semaphore_simulated {

	private Integer number_of_permits;
	private String name;

	
	public Semaphore_simulated(int number_of_permits,String name) {
		this.number_of_permits = number_of_permits;
		this.name = name;
		
	}
	
	public void acquire() {
			
		boolean semaphoreNotAquired = true;
		
		while (semaphoreNotAquired){ 
			synchronized(number_of_permits) {
			
				if(number_of_permits > 0) {
					//Down 
					number_of_permits -- ;
					semaphoreNotAquired = false;
					System.out.printf("Semaphore %s Taken %n",name);
				}
			}
		}

	}
	
	public void release() {
		
		synchronized(number_of_permits) {
			
			//Up
			number_of_permits ++;
			
			System.out.printf("Semaphore %s Released %n",name);
		}
	}
}
