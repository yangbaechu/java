package splender;
import java.util.StringTokenizer;
import java.util.Vector;

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
		return "����: " +  score + "  " + "ȿ��: " + Effect + "  " + "����: " + red + ", "+
				"�ʷ�: " + green + ", "+"�Ķ�: " + blue + ", "+"����: " + black + ", "+"�Ͼ�: " + white;
	}
}
