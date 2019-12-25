package splender;

import java.util.StringTokenizer;

/*카드의 정보를 저장하는 클래스*/
public class card {
	int need[] = {0, 0, 0, 0, 0};//사는데 필요한 자원, 빨강 초록 파랑 검정 하양 순으로
	int score;
	int effect; // 카드의 효과
	String Effect[] = {"빨강","초록","파랑","검정", "하양"};
	
	/* 카드 정보 입력 받는 생성자 */
	public card(int score, int effect, int number) {
		this.score = score;		this.effect = effect;
		need[0] = number/10000;		number -= need[0]*10000;
		need[1] = number/1000;	number -= need[1]*1000;
		need[2] = number/100;	number -= need[2]*100;
		need[3] = number/10;		number -= need[3]*10;
		need[4] = number;
	}
	
	/* 카드 정보 출력 해주는 메소드 */ 
	public String toString() {
		return "점수: " +  score + "  " + "효과: " + Effect[effect-1] + "   " + "빨강: " + need[0] + ", " +
				"초록: " + need[1] + ", "+"파랑: " +need[2] + ", "+"검정: " + need[3] + ", "+"하양: " + need[4];
	}
}
/* 귀족 카드의 정보를 저장하는 클래스*/
class noble_card{
	int red, green, blue, black, white;
	String name;
	
	/*귀족 카드 정보 입력받는 생성자*/
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
		return name + " 빨강: " + red + ", "+
				"초록: " + green + ", "+"파랑: " + blue + ", "+"검정: " + black + ", "+"하양: " + white;
	}
}