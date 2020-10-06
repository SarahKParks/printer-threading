// Sarah Parks
// 827006487
// Resources: Dr. Lee, Dr. Lee's slides, Ken Arnold Java txtbook

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Time {
	public Time() {
		time_counter = 0;
		counterChangeLock = new ReentrantLock();
		sufficientCondition = counterChangeLock.newCondition();
	}

	int getTime()
	{
		return time_counter;
	}

	public void increment() {
		counterChangeLock.lock(); // acquires lock
		try {
			time_counter++; // increment counter
			System.out.print(time_counter + " "); // output value
			sufficientCondition.signalAll(); // signals threads
		} finally {
			counterChangeLock.unlock(); // releases lock
		}
	}

	public void checkCondition(int messageTime) throws InterruptedException {
		counterChangeLock.lock(); // acquires lock
		try {
			while((time_counter % messageTime) != 0) // if message_time secs have not elapsed
			{
				sufficientCondition.await(); // invoke await
			}
			System.out.println();
			System.out.println("-- " + messageTime + " second message"); // outputs message

		} finally {
			counterChangeLock.unlock(); // releases lock
		}
	}

	private int time_counter;
	private Lock counterChangeLock;
	private Condition sufficientCondition;

	public static void main(String args[]) {
		Time counter = new Time();
		// counter is shared among all three runnables below
		TimePrinting tp = new TimePrinting(counter);
		MessagePrinting mp5 = new MessagePrinting(counter, 5);
		MessagePrinting mp11 = new MessagePrinting(counter, 11);
	}
}

class TimePrinting implements Runnable {
	public TimePrinting(Time counter) {
		currentCounter = counter;
		new Thread(this).start();
	}

	public void run() {
		try {
			for(;;)
			//for (int i = 0; i < 60; i++) // temporary for testing
			{
				Thread.sleep(DELAY);// put thread asleep for 1 second before updating counter
				currentCounter.increment();
			}
		} catch (InterruptedException e) {} // because of sleep
	}

	private Time currentCounter;
	private static final int DELAY = 1000;
}

class MessagePrinting implements Runnable {
	public MessagePrinting(Time counter, int seconds) {
		currentCounter = counter;
		message_time = seconds;
		new Thread(this).start();
	}

	public void run() {
		try {
			for(;;)
			//for(int i = 0; i < 60; i++)
			{
				Thread.sleep(DELAY);
				currentCounter.checkCondition(message_time); // check after each update
				
			}	
		} catch (InterruptedException e) {}
	}

	private int message_time; // how many seconds before outputting message
	private Time currentCounter;
	private static final int DELAY = 1010; // to help avoid duplicate messages because thread needs some time to check counter
}