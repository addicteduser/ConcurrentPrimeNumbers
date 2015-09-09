
public class PrimeIterator implements Runnable {
	private Thread t;
	
	private long min, max, testNumber;
	public boolean isDone = false;
	
	public PrimeIterator(long min, long max, long testNumber){
		this.min = min;
		this.max = max;
		this.testNumber = testNumber;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(long y = min; y < max; y++){
			if(ConcurrentPrimeNumbers.isPrimeNumber >= 0)
				break;
			
			if(testNumber % y == 0){
	            System.out.println("Thread '" + t.getName() + "' ended. Divisible number " + y + " found!");
	            ConcurrentPrimeNumbers.isPrimeNumber = 0;
	            isDone = true;
	            return;
			}
	    }

        System.out.println("Thread '" + t.getName() + "' ended.");
        isDone = true;
	}

	public void start(){
		if(t == null){
			t = new Thread(this);
			t.setName(min + "-" + max);
			t.start();
		}
	}
}
