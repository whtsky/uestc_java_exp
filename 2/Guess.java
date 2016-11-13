import java.util.Scanner;


public class Guess {
	public static void main(String[] args) {
		int target = (int) (Math.random()*100+1);
		int guessed;
		Scanner input=new Scanner(System.in);
		System.out.println("请输入一个在1~100之间的数字：");
		while((guessed = input.nextInt()) != target) {
			if (guessed > target) {
				System.out.println("大了！");
			} else {
				System.out.println("小了！");
			}
			System.out.print("再试一下: ");
		}
		input.close();
		System.out.println("你猜对了，真棒！");
	}
}
