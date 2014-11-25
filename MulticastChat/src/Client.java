import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Client extends Thread {

	String type;
	static String hostAddr;
	static int port;
	static MulticastSocket sock;

	Client(String type) {
		this.type = type;
	}

	public void run() {

		switch (type) {

		case "Send":

			try {

				while (true) {

					Scanner scan = new Scanner(System.in);
					String test = scan.nextLine();

					byte[] sendBuf = test.getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendBuf,
							sendBuf.length, InetAddress.getByName(hostAddr),
							port);

					sock.send(sendPacket);
					Thread.sleep(1000);

				}

			} catch (Exception e) {

			}

			break;

		case "Receive":

			try {

				while (true) {
					sock.joinGroup(InetAddress.getByName(hostAddr));
					byte[] recBuf = new byte[1024];
					DatagramPacket recPacket = new DatagramPacket(recBuf,
							recBuf.length);
					sock.receive(recPacket);
					String response = new String(recBuf);
					response = response.substring(0, recPacket.getLength());
					System.out.println(response);

				}

			} catch (Exception e) {

			}

			break;

		}

	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		hostAddr = args[0];
		port = Integer.parseInt(args[1]);
		sock = new MulticastSocket(port);
		Client s = new Client("Send");
		Client r = new Client("Receive");
		s.start();
		r.start();
	}
}
