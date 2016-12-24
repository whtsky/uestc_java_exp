interface PunishBehavior {
	void punish();
}

class NoPunish implements PunishBehavior {
	public void punish() {
		System.out.println("沟通教育，然后放走");
	}
}

class MoneyPunish implements PunishBehavior {
	public void punish() {
		System.out.println("罚钱！");
	}
}

class ChatPunish implements PunishBehavior {
	public void punish() {
		System.out.println("聊天做节目");
	}
}

public class Police {
	PunishBehavior punishBehavior;
	String name;
	Police(String name, PunishBehavior p) {
		this.name = name;
		this.punishBehavior = p;
	}
	
	void SetPunish(PunishBehavior p) {
		this.punishBehavior = p;
	}
	
	void PerformPunish() {
		System.out.print("警察<" + this.name + ">选择了");
		this.punishBehavior.punish();
	}
	
	public static void main(String[] args) {
		Police good = new Police("好警察", new NoPunish());
		Police another = new Police("另一个警察", new MoneyPunish());
		Police tt = new Police("谭谈", new ChatPunish());
		System.out.println("李雷在一个路口的时候被一个警察拦下来了。");
		good.PerformPunish();
		System.out.println("第二天，李雷又被一个警察给拦下来了");
		another.PerformPunish();
		System.out.println("第三天，李雷又被一个警察拦下了。");
		tt.PerformPunish();
	}

}
