import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver implements Runnable {

	MulticastSocket sock;

	public MulticastReceiver(String hostAddr, int port) throws IOException {
		this.sock = new MulticastSocket(port);
		sock.joinGroup(InetAddress.getByName(hostAddr));
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
				System.out.println(response);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
