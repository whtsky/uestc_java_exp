public class Rodent {
	String getName() {
		return "啮齿动物";
	}
	
	public static void main(String[] args) {
		Rodent[] a = new Rodent[4];
		a[0] = new Rodent();
		a[1] = new Mouse();
		a[2] = new Gerbil();
		a[3] = new Hamster();
		for(Rodent r : a) {
			System.out.println("这是一只" + r.getName());
		}
	}
}

class Mouse extends Rodent {
	@Override
	String getName() {
		return "老鼠";
	}
}

class Gerbil extends Mouse {
	@Override
	String getName() {
		return "鼹鼠";
	}
}

class Hamster extends Gerbil {
	@Override
	String getName() {
		return "大颊鼠";
	}
}
