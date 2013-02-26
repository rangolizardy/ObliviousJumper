package src.util.IO;

import java.util.LinkedList;

public class IOQueue implements Runnable {
	//When Kill() is invoked running get's set to false.
	private boolean running;
	
	Thread IThread; //One thread running the show. 
	
	LinkedList<TypeInterface> list; //What's in the queue FIFO style
	//Type interface is what Text/binary etc. implement
	
	
	public IOQueue() {
		IThread = new Thread(this,"Main Queue Thread");
		IThread.start(); // Start the thread 
		
	}
	
	//Functions, Status, Add, Remove, Kill etc.

	//Kill the thread
	public boolean kill(){
		running = false;
		return true;
	}
	
	//Status,
	public int status(){
		return 1; //TODO:Status, For the queue
	}
	
	
	//The thread runs this,
	public void run() {
		running = true;
		while(running){
			//TODO Check the queue, Implement a 30Ms Delay.
			//If empty rinse and repeat,
			//Else run IO job,
		
		}
		
	}
}
/*
	Failed attemt, Would like to keep this just a little while longer.
	
	QueueImplementation = new IOQueue();
	QueueThread = new Thread(QueueImplementation);
	
	//Start it
	QueueThread.start();
	private IOQueue QueueImplementation;
	private Thread QueueThread;
*/