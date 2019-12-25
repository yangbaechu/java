package splender;

import java.util.StringTokenizer;

/*ī���� ������ �����ϴ� Ŭ����*/
public class card {
	int need[] = {0, 0, 0, 0, 0};//��µ� �ʿ��� �ڿ�, ���� �ʷ� �Ķ� ���� �Ͼ� ������
	int score;
	int effect; // ī���� ȿ��
	String Effect[] = {"����","�ʷ�","�Ķ�","����", "�Ͼ�"};
	
	/* ī�� ���� �Է� �޴� ������ */
	public card(int score, int effect, int number) {
		this.score = score;		this.effect = effect;
		need[0] = number/10000;		number -= need[0]*10000;
		need[1] = number/1000;	number -= need[1]*1000;
		need[2] = number/100;	number -= need[2]*100;
		need[3] = number/10;		number -= need[3]*10;
		need[4] = number;
	}
	
	/* ī�� ���� ��� ���ִ� �޼ҵ� */ 
	public String toString() {
		return "����: " +  score + "  " + "ȿ��: " + Effect[effect-1] + "   " + "����: " + need[0] + ", " +
				"�ʷ�: " + need[1] + ", "+"�Ķ�: " +need[2] + ", "+"����: " + need[3] + ", "+"�Ͼ�: " + need[4];
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