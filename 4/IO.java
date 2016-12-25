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
		for(char c: ",./?![]{} \"'！？“”【】『』".toCharArray()) {
			if (s == c) {
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		try {
			InputStream in = new FileInputStream(new File("白夜行.txt"));
			long startMs = System.currentTimeMillis();
			System.out.println("标点符号: " + countPunctuation(in));	
			long endMs = System.currentTimeMillis();
			System.out.println("单线程耗时:" + (double)(endMs - startMs) / 1000 + "秒");
			try {
				in.close();				
			} catch(IOException e) {}
			
			in = new FileInputStream(new File("白夜行.txt"));
			CountThread t1 = new CountThread(in),
					t2 = new CountThread(in);
			startMs = System.currentTimeMillis();
			t1.start();
			t2.start();
			System.out.println("标点符号: " + (t1.joinWithResult() + t2.joinWithResult()));
			endMs = System.currentTimeMillis();
			System.out.println("双线程耗时:" + (double)(endMs - startMs) / 1000 + "秒");
		} catch(FileNotFoundException e) {
			System.err.println("No such file");
			System.exit(1);
		}
	}
}
