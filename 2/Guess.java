import java.util.Scanner;


class A {
	int target;
	A() {
		target = (int) (Math.random()*100+1);
	}
	
	boolean correct(int guessed) {
		if (guessed == target) {
			return true;
		}
		if (guessed > target) {
			System.out.println("大了！");
		} else {
			System.out.println("小了！");
		}
		return false;
	}
}


public class Guess {
	public static void main(String[] args) {
		A a = new A();
		int guessed;
		Scanner input = new Scanner(System.in);
		System.out.println("请输入一个在1~100之间的数字：");
		while(!a.correct(guessed = input.nextInt())) {
			System.out.print("再试一下: ");
		}
		input.close();
		System.out.println("你猜对了，真棒！");
	}
}
