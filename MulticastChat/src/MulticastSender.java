import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSender extends Thread {

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

				String test = InetAddress.getLocalHost().getHostAddress();
				byte[] sendBuf = test.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendBuf,
						sendBuf.length, InetAddress.getByName(hostAddr), port);

				sock.send(sendPacket);
				Thread.sleep(1000);

			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
