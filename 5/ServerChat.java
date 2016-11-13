import java.util.*;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;

class ServerThread extends Thread {
	private static final int MAX_CLIENTS = 256;
	public static ServerThread[] all = new ServerThread[MAX_CLIENTS];
	public static int activeThreads = 0;
	private int pos;
	private boolean running;
	private Socket socket;
	private DataInputStream in;
	private PrintWriter out;
	private String name;
	String readUntil(char end) {
		int i = 0;
		byte ch;
		byte[] serverReceive = new byte[256];
		try {
			i = 0;
			while((ch = (byte)in.read()) != end) {
				serverReceive[i] = ch;
				i++;
			}
			return new String(serverReceive, 0, i);
		} catch (IOException e) {
			System.err.println("Server error:" + e);
			return "";
		} catch (Exception e) {
			close();
			return "";
		}
	}
	void close() {
		if (!this.running) {
			return;
		}
		this.running = false;
		ServerThread.all[pos] = null;
		Server.frame.setActiveClients(--activeThreads);
		if (name != null && name.length() == 0) {
			broadcast("Leaved the chat room");	
		}
	}
	ServerThread(Socket s) {
		this.socket = s;
		Server.frame.setActiveClients(++activeThreads);
		for(int i=0; i < MAX_CLIENTS; i++) {
			if (ServerThread.all[i] == null) {
				pos = i;
				ServerThread.all[pos] = this;
				break;
			}
		}
	}
	
    public void run() {
    	running = true;
    	try {
			in = new DataInputStream(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("Chat Server:  " + new Date());
			out.println("Input your name: ");
			name = readUntil('\n').trim();
			if(name.length() == 0) {
				close();
			} else {
				out.println("OK, you're " + name + "\n");
		    	broadcast("Joined the chat room");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	while(running) {
    		String received = readUntil('\n');
    		if (received.length() > 0) {
    			Server.frame.incMessageReceived();
    			writeToAll(received);
    		}
    	}
    }
    
    void write(String s) {
    	out.println(s);
    }
    
    void broadcast(String s) {
    	for(ServerThread server: ServerThread.all) {
    		if (server != null) {
    			server.write("User " + name + " " + s);
    		}
    	}
    }
    
    void writeToAll(String s) {
    	for(ServerThread server: ServerThread.all) {
    		if (server != null) {
    			server.write("[" + name + "]: " + s);
    		}
    	}
    }
}

class ServerFrame extends Frame {
	private static final long serialVersionUID = 38151820714065009L;
	int port;
	int active;
	int messageReceived;
	private Label portLabel;
	private Label activeLabel;
	private Label messageLabel;

	ServerFrame(int port) {
		super();
		setSize(200, 100);
		setLayout(new GridLayout(3, 1));
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		portLabel = new Label();
		activeLabel = new Label();
		messageLabel = new Label();
		messageLabel.setText("已收到消息： 0条");
		setPort(port);
		setActiveClients(0);
		add(portLabel);
		add(activeLabel);
		add(messageLabel);
		setTitle("Chat Server");
		setVisible(true);
	}
	
	public void setPort(int port) {
		this.port = port;
		this.portLabel.setText("监听端口:" + port);
	}
	
	public void setActiveClients(int active) {
		this.active = active;
		this.activeLabel.setText("活跃用户数:" + active);
	}
	
	public void incMessageReceived() {
		this.messageLabel.setText("已收到消息： " + (++messageReceived) + "条");
	}
}

class Server {
	private ServerSocket server;
	private int port;
	public static ServerFrame frame;
	public Server(int port) {
		this.port = port;
		try {
			server = new ServerSocket(port);
		} catch(IOException e) {
			System.out.println("Server constructor Error:" + e);
		}
	}
	
	public void run() {
		frame = new ServerFrame(port);

		System.out.println("-- Chat Server online! --");
		System.out.println("Listen on port: " + port);
		Socket socket;
		while(true) {
			try {
				socket = server.accept();
				ServerThread t = new ServerThread(socket);
				t.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

public class ServerChat {
	final static int SERVER_PORT = 1234;
	public static void main(String[] args) {
		Server server;
		server = new Server(SERVER_PORT);
		server.run();
	}
}
