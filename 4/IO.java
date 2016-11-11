import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

class CountThread extends Thread {
	InputStream i;
	int result;
	CountThread(InputStream i) {
		this.i = i;
		this.result = 0;
	}
	@Override
	public void run() {
		this.result = IO.countPunctuation(this.i);
	}
	
	public int joinWithResult() {
		try {
			this.join();
		} catch (InterruptedException e) {}
		return result;
	}
	
}

public class IO {
	public static int countPunctuation(InputStream in) {
		int count = 0;
		int s;
		try {
			while((s = in.read()) != -1) {
				if (IsPunctuation(s)) {
					count++;
				}
			}
		} catch (IOException e) {
		}
		return count;
	}
	public static boolean IsPunctuation(int s) {
		for(char c: ",./?![]{} \"'����������������".toCharArray()) {
			if (s == c) {
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		try {
			InputStream in = new FileInputStream(new File("��ҹ��.txt"));
			long startMs = System.currentTimeMillis();
			System.out.println("������: " + countPunctuation(in));	
			long endMs = System.currentTimeMillis();
			System.out.println("���̺߳�ʱ:" + (double)(endMs - startMs) / 1000 + "��");
			try {
				in.close();				
			} catch(IOException e) {}
			
			in = new FileInputStream(new File("��ҹ��.txt"));
			CountThread t1 = new CountThread(in),
					t2 = new CountThread(in);
			startMs = System.currentTimeMillis();
			t1.start();
			t2.start();
			System.out.println("������: " + (t1.joinWithResult() + t2.joinWithResult()));
			endMs = System.currentTimeMillis();
			System.out.println("˫�̺߳�ʱ:" + (double)(endMs - startMs) / 1000 + "��");
		} catch(FileNotFoundException e) {
			System.err.println("No such file");
			System.exit(1);
		}
	}
}
