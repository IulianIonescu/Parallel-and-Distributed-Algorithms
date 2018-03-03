import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerImpl {
	// producer consumer problem data
    private static final int CAPACITY = 2;
    private final Queue<Integer> list = new LinkedList<>();
    private final Random theRandom = new Random();

    // lock and condition variables
    private final Lock aLock = new ReentrantLock();
    private final Condition bufferNotFull = aLock.newCondition();
    private final Condition bufferNotEmpty = aLock.newCondition();

    public void produce() throws InterruptedException {
    	while(true)
    	{
    		aLock.lock();
            try {
                while (list.size() == CAPACITY) {
                    bufferNotEmpty.await();
                }

                int number = theRandom.nextInt(10);
                boolean isAdded = list.add(number);
                if (isAdded) {
                    System.out.printf("%s added %d into queue %n", Thread
                            .currentThread().getName(), number);

                    // signal consumer thread that, queue has element now
                    bufferNotFull.signalAll();
                
                    Thread.sleep(2000);
                }
            } finally {
                aLock.unlock();
            }
    	}
        
    }

    public void consume() throws InterruptedException {
    	while(true)
    	{
    		 aLock.lock();
    	        try {
    	            while (list.size() == 0) {
    	                bufferNotFull.await();
    	            }

    	            Integer value = list.poll();
    	            if (value != null) {
    	                System.out.printf("%s consumed %d from queue %n", Thread
    	                        .currentThread().getName(), value);

    	                // signal producer thread that, queue may be empty now
    	                bufferNotEmpty.signalAll();
    	                
    	                Thread.sleep(2000);
    	            }

    	        } finally {
    	            aLock.unlock();
    	        }
    	}
       
    }

}
