import java.io.IOException;

public class Client {

	public static void main(String[] args) throws IOException,
			InterruptedException {
		String hostAddr = args[0];
		int port = Integer.parseInt(args[1]);

		Sender sender = new Sender(hostAddr, port);
		Receiver receiver = new Receiver(hostAddr, port);

		sender.start();
		receiver.start();
	}
}
