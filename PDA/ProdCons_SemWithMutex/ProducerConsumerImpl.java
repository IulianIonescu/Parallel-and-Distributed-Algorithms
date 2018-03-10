
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerImpl {
	// producer consumer problem data
    private static final int CAPACITY = 4;
    private final Queue<Integer> list = new LinkedList<>();
    private final Random theRandom = new Random();   
    private final Lock aLock = new ReentrantLock();
    
  
    
    Semaphore_simulated SemFree = new Semaphore_simulated(CAPACITY,"SemFree");
    Semaphore_simulated SemFull = new Semaphore_simulated(0,"SemFull");
    
    public void produce() throws InterruptedException {
    	while(true)
    	{
    		//Produce number
    		int number = theRandom.nextInt(10);
    		   		
    		aLock.lock();
    		
    		try {
    			// Down SemFree
        		SemFree.acquire();
        		
                //Enque item
                list.add(number);
                
                System.out.printf("%s added %d into queue %n", Thread
                        .currentThread().getName(), number);

                //Up SemFull
                SemFull.release();
                
    		}finally {
                aLock.unlock();
            }
        
            Thread.sleep(1000);
                        
    	}
        
    }

    public void consume() throws InterruptedException {
    	while(true)
    	{
		 
    		//Down SemFull
    		SemFull.acquire();
    		
    		 aLock.lock();
    		 try {
	    		 			 
    	    		//Deque
    	            Integer value = list.poll();
    	            
    	            //Consume
    	            if (value != null) {
    	                System.out.printf("%s consumed %d from queue %n", Thread
    	                        .currentThread().getName(), value);
    	  
    	              
    	            }
    	            
    	            //Up SemFree
    	            SemFree.release();
    	            
    	       	 Thread.sleep(2000); 
    	            
    		 }finally {
 	            aLock.unlock();
 	        }
    	  
    	}
       
    }

}
