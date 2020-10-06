// Sarah Parks
// 827006487
// Resources: Dr. Lee, Dr. Lee's slides, Ken Arnold Java txtbook


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.*;

// Notes
// Client
	//printRequest = submits messages to be output
		// puts message into the print job queue
// Manager
	//dequeues messages from teh job queue and outputs them to the screen


// can have an overdrafting problem, so need to synchronize the accesses to the queue and check whether
// the queue is empty before trying to remove a message
	// if the queue is empty, the Manager thread needs to wait until the Client threads add some messages
	// and the client thread, after adding a message, will elt the manager thread know by signaling it

	// so only Manager can remove an element



// Version 1
// using lock and condition objects explicitly


public class PrintServerV1 implements Runnable // need to fix synchronization issues
{ 
	private static final Queue<String> requests = new LinkedList<String>(); // made static so that the job queue can be shared, but this causes synchronization issues
	// lock to guard requests queue
	private static Lock requestsChangeLock = new ReentrantLock();
	private static Condition sufficientRequestsCondition = requestsChangeLock.newCondition();
	String name;
	int id;
	public int serialNumber = 1;

	public PrintServerV1(String name, int id) 
	{ 
		this.name = name;
		this.id = id;
		new Thread(this).start();
	} 


	public void printRequest(String s)  
	{
		requestsChangeLock.lock();
		try 
		{
			requests.add(s);
			serialNumber++;
			sufficientRequestsCondition.signalAll();
		} 
		finally 
		{
			requestsChangeLock.unlock();
		} 
	} 
	public void run() 
	{ 
		try 
		{
	
			//for(int i = 0; i < 5; i++) // for temp testing
			for(;;)
			{
				if((this.name).equals("manager"))
				{
					requestsChangeLock.lock();
					try{    
						while(requests.size() <= 0)
						{
							sufficientRequestsCondition.await();
						}
						
						realPrint(requests.remove()); 
						sufficientRequestsCondition.signalAll(); // added this, seemed to work before?
					}
					finally{
						requestsChangeLock.unlock();
					}
				}
				else // is a client
				{
					String message = "Class info: " + getClass() + " || Client: " + name + " || This is " + name + "'s message # " + serialNumber + ": I am a client named " + name + ", and my id is " + id + ".";
					printRequest(message);
				}
			}
		}
		catch(InterruptedException e ){}
		catch(NoSuchElementException e){}
	} 
	private void realPrint(String s) throws InterruptedException
	{ // do the real work of outputting the string to the screen 
		
		System.out.println(s);
		System.out.println();
	} 

	public static void main(String[] args) 
	{ 
		// for PrintServerV1 
		// The following invocations of the constructor, 
		// the first argument is title and the second argument is ID 
		PrintServerV1 m = new PrintServerV1("manager", 1); 
		PrintServerV1 c1 = new PrintServerV1("client1", 2); 
		PrintServerV1 c2 = new PrintServerV1("client2", 3); 
	}
}
