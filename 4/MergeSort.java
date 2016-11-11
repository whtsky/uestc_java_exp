import java.util.Arrays;
import java.util.Random;

class SortThread extends Thread {
	int[] arr;
	int start, end;
	SortThread(int[] arr, int start, int end) {
		this.arr = arr;
		this.start = start;
		this.end = end;
	}
	
	public int getStart() {
		return this.start;
	}
	
	public int getEnd() {
		return this.end;
	}
	
	@Override
	public void run() {
		MergeSort.sort(arr, start, end);
	}
}

public class MergeSort {
	static void printArray(int[] t) {
		for(int x: t) {
			System.out.print(x + " ");
		}
		System.out.println();
	}
	
	static int[] randArray(int length) {
		int[] rv = new int[length];
		Random r = new Random();
		for(int p=0; p<length; p++) {
			rv[p] = r.nextInt(10000);
		}
		return rv;
	}
	
	static void merge(int[] rv, int start, int mid, int end) {
		int[] a = Arrays.copyOfRange(rv, start, mid + 1);
		int[] b = Arrays.copyOfRange(rv, mid + 1, end + 1);
		int posA = 0, posB = 0, posTotal = start;
		while(posA < a.length && posB < b.length) {
			if (a[posA] > b[posB]) {
				rv[posTotal++] = a[posA++];
			} else {
				rv[posTotal++] = b[posB++];
			}
		}
		while(posA < a.length) {
			rv[posTotal++] = a[posA++];
		}
		while(posB < b.length) {
			rv[posTotal++] = b[posB++];
		}
	}

	static void sort(int[] a, int concurrency) {
		SortThread[] t = new SortThread[concurrency];
		int start=0, step=a.length/concurrency;
		for(int i=0; i < concurrency - 1; i++) {
			t[i] = new SortThread(
					a,
					start + i * step,
					start + (i + 1) * step
			);
			t[i].start();
		}
		t[concurrency - 1] =  new SortThread(
				a,
				start + (concurrency-1) * step,
				a.length - 1
		);
		t[concurrency - 1].start();
		
		for(SortThread s: t) {
			try {
				s.join();
			} catch (InterruptedException e) {}
			MergeSort.merge(a, 0, s.getStart(), s.getEnd());
		}
	}
	
	static void sort(int[] a, int start, int end) {
		if(end <= start) {
			return;
		}
		int mid = (start + end) / 2;
		if (mid > start) {
			sort(a, start, mid);
		}
		if (end > mid) {
			sort(a, mid + 1, end);			
		}
		merge(a, start, mid, end);
	}
	
	public static void main(String[] args) {
		int length = 10000000;
		int[] a;
		System.out.println("数组长度：" + length);
		for(int concurrency=1; concurrency < 12; concurrency++) {
			a = randArray(length);
			long startMs = System.currentTimeMillis();
			sort(a, concurrency);
			long endMs = System.currentTimeMillis();
			System.out.println("并发数： " + concurrency + "; 耗时： " + (endMs - startMs) + "毫秒");
		}
	}
}
