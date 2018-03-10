
public class Producer extends Thread {
	ProducerConsumerImpl pc;


    public Producer(ProducerConsumerImpl sharedObject,String name) {
        super("PRODUCER " + name);
        this.pc = sharedObject;
  
    }

    @Override
    public void run() {
        try {
            pc.produce();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    

}
