
abstract class Vehicle {
	abstract String NoOfWheels();
	
	public static void main(String[] args) {
		Car c = new Car();
		System.out.println("Car ��" + c.NoOfWheels());
		Motorbike m = new Motorbike();
		System.out.println("Motorbike ��" + m.NoOfWheels());
	}
}

class Car extends Vehicle {
	String NoOfWheels() {
		return "���ֳ���";
	}
}

class Motorbike extends Vehicle {
	String NoOfWheels() {
		return "˫�ֳ���";
	}
}

