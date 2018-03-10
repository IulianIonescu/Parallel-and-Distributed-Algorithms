
public class Consumer extends Thread {
	ProducerConsumerImpl pc;


    public Consumer(ProducerConsumerImpl sharedObject,String name) {
        super("CONSUMER " + name);
        this.pc = sharedObject;
    }

    @Override
    public void run() {
        try {
            pc.consume();
            
           
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
