class Person{
	String name;
	char sex;
	int age;
	
	Person(String name, char sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	
	public void setData(String name, char sex, int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	
	public String getData() {
		return "Name: " + this.name + " Sex: " + this.sex + " Age: " + this.age;
	}
}


public class Student extends Person {
	int sID;
	int classNo;

	Student(String name, char sex, int age, int sID, int classNo) {
		super(name, sex, age);
		this.sID = sID;
		this.classNo = classNo;
	}
	
	public void setData(String name, char sex, int age, int sID, int classNo) {
		this.setData(name, sex, age);
		this.sID = sID;
		this.classNo = classNo;
	}
	
	@Override
	public String getData() {
		return super.getData() + " studentID: " + this.sID + " classNumber:" + this.classNo; 
	}
	
	public static void main(String[] args) {
		Student s = new Student("123", 'M', 16, 12345, 54321);
		System.out.println(s.getData());
		s.setData("new", 'F', 46, 32, 555);
		System.out.println(s.getData());
	}
	
}
