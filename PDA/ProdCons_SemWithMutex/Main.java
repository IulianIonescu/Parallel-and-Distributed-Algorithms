
public class Main {

	public static void main(String[] args) throws InterruptedException{
		
		// Object on which producer and consumer thread will operate
        ProducerConsumerImpl sharedObject = new ProducerConsumerImpl();

        // creating producer and consumer threads
        Producer p = new Producer(sharedObject,"p");
        Consumer c = new Consumer(sharedObject,"c");

        // starting producer and consumer threads
        p.start();
        c.start();
		
        p.join();
        c.join();
 

	}
}
