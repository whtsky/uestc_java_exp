import java.util.Scanner;


public class Guess {
	public static void main(String[] args) {
		int target = (int) (Math.random()*100+1);
		int guessed;
		Scanner input=new Scanner(System.in);
		System.out.println("������һ����1~100֮������֣�");
		while((guessed = input.nextInt()) != target) {
			if (guessed > target) {
				System.out.println("���ˣ�");
			} else {
				System.out.println("С�ˣ�");
			}
			System.out.print("����һ��: ");
		}
		input.close();
		System.out.println("��¶��ˣ������");
	}
}
