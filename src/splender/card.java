package splender;
import java.util.StringTokenizer;
import java.util.Vector;

/*카드의 정보를 저장하는 클래스*/
public class card {
	int red, green, blue, black, white; //사는데 필요한 광물
	int score;
	int effect; // 카드의 효과
	/* 카드 정보 입력 받는 생성자 */
	public card(int score, int effect, int number) {
		this.score = score;		this.effect = effect;
		red = number/10000;		number -= red*10000;
		green = number/1000;	number -= green*1000;
		blue = number/100;		number -= blue*100;
		black = number/10;		number -= black*10;
		white = number;
	}
}
