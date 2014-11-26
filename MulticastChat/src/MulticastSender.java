import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class MulticastSender implements Runnable {

	MulticastSocket sock;
	String hostAddr;
	int port;

	public MulticastSender(String hostAddr, int port) throws IOException {
		sock = new MulticastSocket();
		this.hostAddr = hostAddr;
		this.port = port;
	}

	@Override
	public void run() {
		try {

			while (true) {

				Scanner scan = new Scanner(System.in);
				String test = scan.nextLine();

				byte[] sendBuf = test.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendBuf,
						sendBuf.length, InetAddress.getByName(hostAddr), port);

				sock.send(sendPacket);
				Thread.sleep(1000);

				scan.close();

			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
