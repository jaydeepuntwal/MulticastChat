import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender extends Thread {

	DatagramSocket sock;
	String hostAddr;
	int port;

	public Sender(String hostAddr, int port) throws IOException {
		sock = new DatagramSocket();
		this.hostAddr = hostAddr;
		this.port = port;
	}

	@Override
	public void run() {
		try {

			while (true) {

				String test = InetAddress.getLocalHost().getHostAddress();
				byte[] sendBuf = test.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendBuf,
						sendBuf.length,
						InetAddress.getByName("255.255.255.255"), port);

				sock.send(sendPacket);
				Thread.sleep(1000);

			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
