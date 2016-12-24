public class MyTime {
	private int hour;
	private int minute;
	private int second;
	
	private void adjust() {
		int delta;
		if (second < 0) {
			delta = -(second / 60) + 1;
			second += delta * 60;
			minute -= delta;
		} else if (second > 60) {
			minute += second / 60;
			second %= 60;
		}
		if (minute < 0) {
			delta = -(minute / 60) + 1;
			minute += delta * 60;
			hour -= delta;
		} else if (minute > 60) {
			hour += minute / 60;
			minute %= 60;
		}
		
		if (hour < 0) {
			hour += (-(hour / 24) + 1) * 24;
		} else if (hour > 24) {
			hour %= 24;
		}
	}
	MyTime() {}
	MyTime(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.adjust();
	}
	
	public void addHour(int hour) {
		this.hour += hour;
		this.adjust();
	}
	
	public void subHour(int hour) {
		this.hour -= hour;
		this.adjust();
	}
	
	public void addMinute(int minute) {
		this.minute += minute;
		this.adjust();
	}
	
	public void subMinute(int minute) {
		this.minute -= minute;
		this.adjust();
	}
	
	public void addSecond(int second) {
		this.second += second;
		this.adjust();
	}
	
	public void subSecond(int second) {
		this.second -= second;
		this.adjust();
	}
	
	public void display() {
		System.out.format("%02d:%02d:%02d\n", hour, minute, second);
	}
	
	public static void main(String[] args) {
		new MyTime().display();
		MyTime t = new MyTime(25, 20, -45);
		t.display();
		t.addHour(4);
		t.display();
		t.subHour(48);
		t.display();
		t.addMinute(61);
		t.display();
		t.subMinute(2);
		t.display();
		t.addSecond(256);
		t.display();
		t.subSecond(180);
		t.display();
	}
}
