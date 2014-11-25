import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Client extends Thread {

	String type;
	static String hostAddr;

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

					MulticastSocket sock = new MulticastSocket();
					byte[] sendBuf = test.getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendBuf,
							sendBuf.length, InetAddress.getByName(hostAddr),
							5000);

					sock.send(sendPacket);
					Thread.sleep(1000);
					sock.close();
					scan.close();
				}

			} catch (Exception e) {

			}

			break;

		case "Receive":

			try {

				while (true) {
					MulticastSocket sock = new MulticastSocket(5000);
					sock.joinGroup(InetAddress.getByName(hostAddr));
					byte[] recBuf = new byte[1024];
					DatagramPacket recPacket = new DatagramPacket(recBuf,
							recBuf.length);
					sock.receive(recPacket);
					String response = new String(recBuf);
					response.trim();
					System.out.println(response);
					sock.leaveGroup(InetAddress.getByName(hostAddr));
					sock.close();
				}

			} catch (Exception e) {

			}

			break;

		}

	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		hostAddr = args[0];
		Client s = new Client("Send");
		Client r = new Client("Receive");
		s.start();
		r.start();
	}
}
