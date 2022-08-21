import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;


public class TcpServer extends Thread {

	public static final int SERVERPORT = 9999;
	private boolean running = false;
	private PrintWriter bufferSender;
	private OnMessageReceived messageListener;
	private ServerSocket serverSocket;
	private Socket client;


	public TcpServer(OnMessageReceived messageListener) {
		this.messageListener = messageListener;
	}

	public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {

		MainScreen frame = new MainScreen();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}

	public void close() {
		running = false;

		if (bufferSender != null) {
			System.out.println("Sender service terminated!");
			bufferSender.flush();
			bufferSender.close();
			bufferSender = null;
		}

		try {
			client.close();
			serverSocket.close();
		} catch (Exception e) {
			System.out.println("Service terminated & socked closed.");
		}

		System.out.println("Client Disconnected");
		serverSocket = null;
		client = null;

	}


	public void sendMessage(String message) {
		if (bufferSender != null && !bufferSender.checkError()) {
			bufferSender.println(message);
			bufferSender.flush();
		}
	}

	public boolean hasCommand(String message) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
		if (message != null) {
			if (message.contains(Constants.CLOSED_CONNECTION)) {
				messageListener.messageReceived(message.replaceAll(Constants.CLOSED_CONNECTION, "") + " disconnected from room.");
				close();
				runServer();
				return true;
			} else if (message.contains(Constants.LOGIN_NAME)) {
				messageListener.messageReceived(message.replaceAll(Constants.LOGIN_NAME, "") + " connected to room.");
				return true;
			}
		}

		return false;
	}


	private void runServer() {
		running = true;

		try {
			System.out.println("S: Connecting...");
			serverSocket = new ServerSocket(SERVERPORT);
			client = serverSocket.accept();

			System.out.println("S: Receiving...");

			try {
				bufferSender = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);

				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

				while (running) {

					String message = null;
					try {
						message = in.readLine();
					} catch (IOException e) {
						System.out.println("Error reading message: " + e.getMessage());
					}

					if (hasCommand(message)) {
						continue;
					}

					if (message != null && messageListener != null) {
						messageListener.messageReceived(message);
					}
				}

			} catch (Exception e) {
				System.out.println("S: Error");
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println("S: Error");
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		super.run();
		runServer();
	}

	public interface OnMessageReceived {
		public void messageReceived(String message) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException;
	}

}