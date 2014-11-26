import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver extends Thread {

	DatagramSocket sock;

	public Receiver(String hostAddr, int port) throws IOException {
		this.sock = new DatagramSocket(port);
	}

	@Override
	public void run() {
		try {
			while (true) {
				byte[] recBuf = new byte[1024];
				DatagramPacket recPacket = new DatagramPacket(recBuf,
						recBuf.length);
				sock.receive(recPacket);
				String response = new String(recBuf);
				response = response.substring(0, recPacket.getLength());
				System.out.println(response);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
