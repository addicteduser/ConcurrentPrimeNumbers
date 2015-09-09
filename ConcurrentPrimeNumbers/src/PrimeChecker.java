public class PrimeChecker implements Runnable {
	private Thread t;
	private PrimeIterator[]  iterators;
	
	public PrimeChecker (PrimeIterator[] iterators){
		this.iterators = iterators;
	}
	
	@Override
	public void run() {
		while(true){
			boolean allIsDone = true;
			for(int x = 0; x < iterators.length; x++){
				if(!iterators[x].isDone){
					allIsDone = false;
					break;
				}
			}
			
			if(allIsDone){
				if(ConcurrentPrimeNumbers.isPrimeNumber < 0){
					ConcurrentPrimeNumbers.isPrimeNumber = 1;
				}
				
				break;
			}
		}
		
		// Print the results.
		ConcurrentPrimeNumbers.printResult();
	}

	public void start(){
		if(t == null){
			t = new Thread(this);
			t.start();
		}
	}
}
