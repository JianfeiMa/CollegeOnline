package team.yjcollege.matchproject.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.os.StrictMode;
import android.util.Log;

/**
 * 客户端
 * 
 * @author way
 * 
 */
public class Client {

	private Socket client;
	private ClientThread clientThread;
	private String ip;
	private int port;

	public Client(String ip, int port) {
		Log.v("Client", "执行Client类中的构造函数,实质上是赋值ip和port");
		this.ip = ip;
		this.port = port;
	}

	/**
	 * 次函数是创建socket并连接，如果连接成功则创建自己内部类中ClientThread客户端线程并开始
	 * 
	 * @return
	 */
	public boolean start() {
		Log.v("Client.start()", "次函数是创建socket并连接，如果连接成功则创建自己内部类中ClientThread客户端线程并开始");
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
        	.detectDiskReads()  
        	.detectDiskWrites()  
        	.detectNetwork()  
        	.penaltyLog()  
        	.build());   
	    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
        	.detectLeakedSqlLiteObjects()  
        	.detectLeakedClosableObjects()  
        	.penaltyLog()  
        	.penaltyDeath()  
        	.build());  
	
		try {
			client = new Socket();
			// client.connect(new InetSocketAddress(Constants.SERVER_IP,
			// Constants.SERVER_PORT), 3000);
			client.connect(new InetSocketAddress(ip, port), 3000);
			boolean wheatherConnectSuccess=client.isConnected();
			if (wheatherConnectSuccess==true) {
				Log.v("Client", "正在连接中...测试是否执行这句话并输出连接是否成功:"+wheatherConnectSuccess);
				// System.out.println("Connected..");
				clientThread = new ClientThread(client);
				clientThread.start();
			}else if(wheatherConnectSuccess==false){
				return false;
			}
		} catch (IOException e) {
			Log.v("Client", "我试下是否抛出异常");
			e.printStackTrace();
			return false;
		} 
		return true;
	}

	// 直接通过client得到读线程
	public ClientInputThread getClientInputThread() {
		return clientThread.getIn();
	}

	// 直接通过client得到写线程
	public ClientOutputThread getClientOutputThread() {
		return clientThread.getOut();
	}

	// 直接通过client停止读写消息
	public void setIsStart(boolean isStart) {
		clientThread.getIn().setStart(isStart);
		clientThread.getOut().setStart(isStart);
	}
	
	/**
	 * 这是一个继承Thread的内部类,构造函数是创建一个ClientInputThread对象
	 * 和创建一个ClientOutputThread对象
	 * @author show-mark
	 *
	 */
	public class ClientThread extends Thread {

		private ClientInputThread in;
		private ClientOutputThread out;

		public ClientThread(Socket socket) {
			in = new ClientInputThread(socket);
			out = new ClientOutputThread(socket);
		}

		public void run() {
			in.setStart(true);
			out.setStart(true);
			in.start();
			out.start();
		}

		// 得到读消息线程
		public ClientInputThread getIn() {
			return in;
		}

		// 得到写消息线程
		public ClientOutputThread getOut() {
			return out;
		}
	}
}
