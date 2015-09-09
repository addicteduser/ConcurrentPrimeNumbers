import java.util.Scanner;

public class ConcurrentPrimeNumbers {
	// 74747474747474747
	private static long testNumber = 2147483647L;	// Number to check.
    private static long start, end, elapsedTime;
    
	public static int isPrimeNumber = -1; // -1 = unknown | 0 = False, 1 = True

    public static void main(String[]args){
    	System.out.println("Welcome to the ConcurrentPrimeNumbers app.");
    	System.out.println("The number to check is " + testNumber);
    	
    	boolean useSqrt = false;	// Use sqrt before checking for each possible number or not.
    	int numberOfThreads = 1;	// Number of threads to use.
    	
    	// Use Sqrt ?
        Scanner getInput = new Scanner(System.in);
       	System.out.println("Use Sqrt? (Y/N) ");
       	String iSqrt = getInput.nextLine();
       	if(iSqrt.toUpperCase().equals("Y")){
       		useSqrt = true;
       	}
       	
       	// Get Number of threads
       	while(true){
           	System.out.println("Enter number of threads to use: ");
           	int iNumberOfThreads = getInput.nextInt();
           	if(true){
           		numberOfThreads = iNumberOfThreads;
           		break;
           	}
       	}
       	
       	getInput.close();
       	
        // Start measuring.
        start = System.currentTimeMillis();
        
        // Start checking.
        System.out.println("Processing... Please wait.");
        System.out.println("");
        
        checkPrime(testNumber, useSqrt, numberOfThreads);
    }
    
    public static void checkPrime(long number, boolean useSqrt, int numberOfThreads){
    	
    	long limit = number;
		if(useSqrt){
			limit = (long)Math.sqrt(number);
			System.out.println(limit);
		}
		
    	if(numberOfThreads == 1){
    		for(long y = 2L; y < limit; y++){
    			if(number % y == 0){
    	            System.out.println("Divisible by " + y);
    	            isPrimeNumber = 0;
    	            printResult();
    	            return;
    			}
    	    }
    		
    		isPrimeNumber = 1;
    		printResult();
    	} else{
    		// Execute a multi-threaded process.
    		long count = 2L;
    		long dividedLimit = limit / numberOfThreads;
    		
    		PrimeIterator[] iterators = new PrimeIterator[numberOfThreads];
    		
    		for(int x = 0; x < numberOfThreads; x++){
        		iterators[x] = new PrimeIterator(
        				count, 
        				count += x == numberOfThreads - 1 ? dividedLimit - 1 : dividedLimit, // Prevent dividing by itself.
        				testNumber);
        		
        		iterators[x].start();
    		}

    		// A thread for checking if all the other threads are finished checking.
    		new PrimeChecker(iterators).start();
    	}
    }
    
    public static void printResult(){
        // End measuring.
        end = System.currentTimeMillis();

        // Get elapsed time.
        elapsedTime = end - start;
        
        // Print results.
        System.out.print("");
        System.out.println("Prime: " + (isPrimeNumber == 1 ? "true" : "false"));
        System.out.println(elapsedTime + " milliseconds =  " + (elapsedTime / 1000 + " seconds"));
    }
    
    public static boolean tryParseInt(String value) {  
        try {  
        	Integer.parseInt(value);  
            return true;
        } catch (NumberFormatException e) {  
            return false;  
        }  
   }
}
