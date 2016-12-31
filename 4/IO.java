import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

class CountThread extends Thread {
	BufferedReader i;
	int result;
	CountThread(BufferedReader i) {
		this.i = i;
		this.result = 0;
	}
	@Override
	public void run() {
		this.result = IO.countPunctuation(this.i, false);
	}
	
	public int joinWithResult() {
		try {
			this.join();
		} catch (InterruptedException e) {}
		return result;
	}
	
}

public class IO {
	public static int countPunctuation(BufferedReader in) {
		return countPunctuation(in, true);
	}

	public static int countPunctuation(BufferedReader in, Boolean print) {
		int count = 0;
		String s;
		char ch;
		try {
			for(;(s=in.readLine())!=null;){
				for(int i=0;i<s.length();i++){
					ch = s.charAt(i);
					if (IsPunctuation(ch)) {
						if (print) {
							System.out.printf("%c ", ch);						
						}
						count++;
					}
				}
			}
		} catch(IOException e) {
			return 0;
		}
		if (print) {
			System.out.println("");
		}
		return count;
	}
	public static boolean IsPunctuation(char s) {
		for(char c: ",./?![]{}：…'！？“”【】『』，。“”".toCharArray()) {
			if (s == c) {
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("白夜行.txt"),"UTF-8"));
			countPunctuation(in, true);

			in = new BufferedReader(new InputStreamReader(new FileInputStream("白夜行.txt"),"UTF-8"));			
			long startMs = System.currentTimeMillis();
			System.out.println("标点符号: " + countPunctuation(in, false));	
			long endMs = System.currentTimeMillis();
			System.out.println("单线程耗时:" + (double)(endMs - startMs) / 1000 + "秒");
			in = new BufferedReader(new InputStreamReader(new FileInputStream("白夜行.txt"),"UTF-8"));
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
		} catch(UnsupportedEncodingException e) {
			System.err.println("Don't support UTF-8");
			System.exit(1);			
		}
	}
}
