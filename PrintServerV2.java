// Sarah Parks
// 827006487
// Resources: Dr. Lee, Dr. Lee's slides, Ken Arnold Java txtbook


import java.util.*;

public class PrintServerV2 implements Runnable
{ 
	private static final Queue<String> requests = new LinkedList<String>(); // made static so that the job queue can be shared, but this causes synchronization issues
	
	String name;
	int id;
	public int serialNumber = 1;

	public PrintServerV2(String name, int id) 
	{ 
		this.name = name;
		this.id = id;
		new Thread(this).start();
	} 


	public void printRequest(String s)  
	{
		synchronized(requests)
		{
			requests.add(s);
			serialNumber++;
			requests.notifyAll();
		}	
	} 
	public void run() 
	{ 
		try 
		{
	
			//for(int i = 0; i < 5; i++)
			for(;;)
			{
				if((this.name).equals("manager"))
				{
					synchronized(requests)
					{
						while(requests.size() <= 0)
						{
							requests.wait();
						}
						
						realPrint(requests.remove());

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
		// for PrintServerV2
		// The following invocations of the constructor, 
		// the first argument is title and the second argument is ID 
		PrintServerV2 m = new PrintServerV2("manager", 1); 
		PrintServerV2 c1 = new PrintServerV2("client1", 2); 
		PrintServerV2 c2 = new PrintServerV2("client2", 3); 
	}
}

