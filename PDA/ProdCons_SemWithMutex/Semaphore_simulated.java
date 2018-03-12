public class Semaphore_simulated {

	private Integer number_of_permits;
	private String name;
	private final int MAX ;

	
	public Semaphore_simulated(int number_of_permits,String name) {
		super();
		MAX = number_of_permits;
		this.number_of_permits = number_of_permits;
		this.name = name;
		
	}
	
	public void acquire() {	
			synchronized(this) {
			
				if(number_of_permits > 0) {
					//Down 
					number_of_permits -- ;
					
					System.out.printf("Semaphore %s Taken %n",name);
				}
				if(number_of_permits == 0)
				{
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		

	}
	
	public void release() {
		
		synchronized(this) {
			if(number_of_permits == 0) {
				
				//Up
				number_of_permits ++;
				notify();
			}
			
			if(number_of_permits < MAX - 2 && number_of_permits > 1) {
				number_of_permits ++;
			}
			System.out.printf("Semaphore %s Released %n",name);
		}
	}
}
