interface PunishBehavior {
	void punish();
}

class NoPunish implements PunishBehavior {
	public void punish() {
		System.out.println("��ͨ������Ȼ�����");
	}
}

class MoneyPunish implements PunishBehavior {
	public void punish() {
		System.out.println("��Ǯ��");
	}
}

class ChatPunish implements PunishBehavior {
	public void punish() {
		System.out.println("��������Ŀ");
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
		System.out.print("����<" + this.name + ">ѡ����");
		this.punishBehavior.punish();
	}
	
	public static void main(String[] args) {
		Police good = new Police("�þ���", new NoPunish());
		Police another = new Police("��һ������", new MoneyPunish());
		Police tt = new Police("̷̸", new ChatPunish());
		System.out.println("������һ��·�ڵ�ʱ��һ�������������ˡ�");
		good.PerformPunish();
		System.out.println("�ڶ��죬�����ֱ�һ���������������");
		another.PerformPunish();
		System.out.println("�����죬�����ֱ�һ�����������ˡ�");
		tt.PerformPunish();
	}

}
