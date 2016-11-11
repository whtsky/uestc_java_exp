package duck;

interface FlyBehavior{
	void fly();
}

class FlyWithWings implements FlyBehavior {
	public void fly() {
		System.out.println("I can fly with wings!");
	}
}

class FlyNoWay implements FlyBehavior {
	public void fly() {
		System.out.println("Errr.. I can't fly.");
	}
}

interface QuackBehavior {
	void quack();
}

class Quack1 implements QuackBehavior {
	public void quack() {
		System.out.println("I Quack: Duck, duck");
	}
}

class Quack2 implements QuackBehavior {
	public void quack() {
		System.out.println("I Quack: Duck, duck, duck");
	}
}

class Quack3 implements QuackBehavior {
	public void quack() {
		System.out.println("I Quack: Duck, duck, duck, duck");
	}
}

public class Duck {
	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;
	
	void PerformFly() {
		if (this.flyBehavior != null) {
			this.flyBehavior.fly();			
		} else {
			System.out.println("No, I can't fly.");
		}
	}
	
	void SetFlyBehavior(FlyBehavior f) {
		this.flyBehavior = f;
	}
	
	void PerformQuack() {
		if (this.quackBehavior != null) {
			this.quackBehavior.quack();
		} else {
			System.out.println("No, I can't quack.");
		}
	}
	
	void SetQuackBehavior(QuackBehavior q) {
		this.quackBehavior = q;
	}
	
	void swim() {
		System.out.println("Swim, swim");
	}
	
	void display() {
		this.swim();
		this.PerformFly();
		this.PerformQuack();
	}
	
	public static void main(String[] args) {
		Duck d1 = new MallardDuck();
		Duck d2 = new RedheadDuck();
		Duck d3 = new RubberDuck();
		d1.display();
		d2.display();
		d3.display();
		System.out.println("But.. Let's give rubber duck a wing!");
		d3.SetFlyBehavior(new FlyWithWings());
		d3.display();
	}
}

class MallardDuck extends Duck {
	MallardDuck() {
		this.SetFlyBehavior(new FlyWithWings());
		this.SetQuackBehavior(new Quack1());
	}
	
	@Override
	void display() {
		System.out.println("I'm a Mallard Duck.");
		super.display();
	}
}

class RedheadDuck extends Duck {
	RedheadDuck() {
		this.SetFlyBehavior(new FlyWithWings());
		this.SetQuackBehavior(new Quack3());
	}
	
	@Override
	void display() {
		System.out.println("I'm a Redhead Duck.");
		super.display();
	}
}

class RubberDuck extends Duck {
	RubberDuck() {
		this.SetFlyBehavior(new FlyNoWay());
		this.SetQuackBehavior(new Quack2());
	}
	
	@Override
	void display() {
		System.out.println("I'm a Rubber Duck.");
		super.display();
	}
}


