public class Rodent {
	String getName() {
		return "���ݶ���";
	}
	
	public static void main(String[] args) {
		Rodent[] a = new Rodent[4];
		a[0] = new Rodent();
		a[1] = new Mouse();
		a[2] = new Gerbil();
		a[3] = new Hamster();
		for(Rodent r : a) {
			System.out.println("����һֻ" + r.getName());
		}
	}
}

class Mouse extends Rodent {
	@Override
	String getName() {
		return "����";
	}
}

class Gerbil extends Mouse {
	@Override
	String getName() {
		return "����";
	}
}

class Hamster extends Gerbil {
	@Override
	String getName() {
		return "�����";
	}
}
