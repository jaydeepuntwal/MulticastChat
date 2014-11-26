import java.io.IOException;

public class Client {

	public static void main(String[] args) throws IOException,
			InterruptedException {
		String hostAddr = args[0];
		int port = Integer.parseInt(args[1]);

		MulticastSender sender = new MulticastSender(hostAddr, port);
		MulticastReceiver receiver = new MulticastReceiver(hostAddr, port);

		sender.start();
		receiver.start();
	}
}
