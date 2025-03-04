package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;

// Task C
public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {
		TemperatureSensor sn = new TemperatureSensor();
		
		Client client = new Client("sensor", Common.BROKERHOST, Common.BROKERPORT);
		
		client.connect();
		
		for (int i = 0; i < COUNT; i++) {
			int temp = sn.read();
			System.out.println("READING: " + temp);
			
			client.publish(Common.TEMPTOPIC, String.valueOf(temp));
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("Interrupted");
			}
		}
		
		client.disconnect();
		System.out.println("Temperature device stopping ... ");

	}
}
