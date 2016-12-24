abstract class Vehicle {
	abstract String NoOfWheels();
	
	public static void main(String[] args) {
		Car c = new Car();
		System.out.println("Car 是" + c.NoOfWheels());
		Motorbike m = new Motorbike();
		System.out.println("Motorbike 是" + m.NoOfWheels());
	}
}

class Car extends Vehicle {
	String NoOfWheels() {
		return "四轮车！";
	}
}

class Motorbike extends Vehicle {
	String NoOfWheels() {
		return "双轮车！";
	}
}

