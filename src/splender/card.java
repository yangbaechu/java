package splender;
import java.util.StringTokenizer;
import java.util.Vector;

/*카드의 정보를 저장하는 클래스*/
public class card {
	int red, green, blue, black, white; //사는데 필요한 광물
	int score;
	int effect; // 카드의 효과
	String Effect;
	
	/* 카드 정보 입력 받는 생성자 */
	public card(int score, int effect, int number) {
		this.score = score;		this.effect = effect;
		red = number/10000;		number -= red*10000;
		green = number/1000;	number -= green*1000;
		blue = number/100;		number -= blue*100;
		black = number/10;		number -= black*10;
		white = number;
		if(effect == 1) Effect = "빨강";
		else if(effect == 2) Effect = "초록";
		else if(effect == 3) Effect = "파랑";
		else if(effect == 4) Effect = "검정";
		else if(effect == 5) Effect = "하양";
		
	}
	
	/* 카드 정보 출력 해주는 메소드 */ 
	public String toString() {
		return "점수: " +  score + "  " + "효과: " + Effect + "  " + "빨강: " + red + ", "+
				"초록: " + green + ", "+"파랑: " + blue + ", "+"검정: " + black + ", "+"하양: " + white;
	}
}
