package splender;

import java.util.StringTokenizer;

/*ī���� ������ �����ϴ� Ŭ����*/
public class card {
	int red, green, blue, black, white; //��µ� �ʿ��� ����
	int score;
	int effect; // ī���� ȿ��
	String Effect;
	
	/* ī�� ���� �Է� �޴� ������ */
	public card(int score, int effect, int number) {
		this.score = score;		this.effect = effect;
		red = number/10000;		number -= red*10000;
		green = number/1000;	number -= green*1000;
		blue = number/100;		number -= blue*100;
		black = number/10;		number -= black*10;
		white = number;
		if(effect == 1) Effect = "����";
		else if(effect == 2) Effect = "�ʷ�";
		else if(effect == 3) Effect = "�Ķ�";
		else if(effect == 4) Effect = "����";
		else if(effect == 5) Effect = "�Ͼ�";
		
	}
	
	/* ī�� ���� ��� ���ִ� �޼ҵ� */ 
	public String toString() {
		return "����: " +  score + "  " + "ȿ��: " + Effect + "   " + "����: " + red + ", " +
				"�ʷ�: " + green + ", "+"�Ķ�: " + blue + ", "+"����: " + black + ", "+"�Ͼ�: " + white;
	}
}
/* ���� ī���� ������ �����ϴ� Ŭ����*/
class noble_card{
	int red, green, blue, black, white;
	String name;
	
	/*���� ī�� ���� �Է¹޴� ������*/
	public noble_card(String name, int number) {
		StringTokenizer st = new StringTokenizer(name,"_");
		int count = st.countTokens();
		this.name="";
		for(int i=0; i<count; i++) {
			this.name += st.nextToken();
			this.name += " ";
		}
		red = number/10000;		number -= red*10000;
		green = number/1000;	number -= green*1000;
		blue = number/100;		number -= blue*100;
		black = number/10;		number -= black*10;
		white = number;
	}

	public String toString() {
		return name + " ����: " + red + ", "+
				"�ʷ�: " + green + ", "+"�Ķ�: " + blue + ", "+"����: " + black + ", "+"�Ͼ�: " + white;
	}
}