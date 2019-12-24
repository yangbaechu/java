package splender;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Random;

public class cardManager{
	int level, score, effect, need, n=0, rand, i;
	String name;
	card c, c1;
	noble_card nc, nc1;
	/* 카드의 정보들을 저장할 벡터 */
	//숨겨진 카드가 저장될 벡터
	static Vector<card> hide_1 = new Vector<card>();
	static Vector<card> hide_2 = new Vector<card>();
	static Vector<card> hide_3 = new Vector<card>();
	//공개된 카드가 저장될 벡터
	static Vector<card> open_1 = new Vector<card>(4);
	static Vector<card> open_2 = new Vector<card>(4);
	static Vector<card> open_3 = new Vector<card>(4);
	//사용자가 획득한  카드가 저장될 벡터
	static Vector<card> user_1 = new Vector<card>();
	static Vector<card> user_2 = new Vector<card>();
	//귀족 카드가 저장될 벡터
	static Vector<noble_card>noble_hide = new Vector<noble_card>();
	static Vector<noble_card>noble_open = new Vector<noble_card>();
	static Vector<noble_card>noble_user_1 = new Vector<noble_card>();
	static Vector<noble_card>noble_user_2 = new Vector<noble_card>();
	
	Random r = new Random();
	
	public cardManager() {
		String card_query = new String("1 1 3 40000\r\n" + 
			"1 0 3 11011\r\n" + 
			"1 0 3 13100\r\n" + 
			"1 0 3 00021\r\n" +
			"1 0 5 00220\r\n" + 
			"1 0 5 02210\r\n" + 
			"1 0 5 00300\r\n" + 
			"1 0 1 01022\r\n" + 
			"1 0 1 01111\r\n" + 
			"1 0 1 20002\r\n" + 
			"1 0 4 02002\r\n" + 
			"1 0 4 10202\r\n" + 
			"1 0 4 11101\r\n" + 
			"1 0 4 31010\r\n" + 
			"1 1 2 00040\r\n" + 
			"1 0 2 20200\r\n" + 
			"1 0 2 01301\r\n" + 
			"1 0 2 00102\r\n" + 
			"1 1 4 00400\r\n" + 
			"1 0 2 30000\r\n" + 
			"1 0 5 11110\r\n" + 
			"1 0 5 00113\r\n" + 
			"1 0 5 12110\r\n" + 
			"1 1 5 04000\r\n" + 
			"1 0 1 00003\r\n" + 
			"1 1 1 00004\r\n" + 
			"1 0 1 01200\r\n" + 
			"1 0 1 01112\r\n" + 
			"1 0 3 22001\r\n" + 
			"1 0 3 21011\r\n" + 
			"1 0 3 02020\r\n" + 
			"1 0 3 00030\r\n" + 
			"1 0 2 10111\r\n" + 
			"1 0 2 10121\r\n" + 
			"1 0 5 20010\r\n" + 
			"1 0 4 03000\r\n" + 
			"1 0 4 11201\r\n" + 
			"1 0 4 12000\r\n" + 
			"1 0 1 10031\r\n" + 
			"1 0 2 20120\r\n" + 
			"2 3 1 60000\r\n" + 
			"2 1 1 20330\r\n" + 
			"2 1 3 32200\r\n" + 
			"2 2 4 00005\r\n" + 
			"2 2 2 00214\r\n" + 
			"2 3 3 00600\r\n" + 
			"2 2 2 05000\r\n" + 
			"2 3 4 00060\r\n" + 
			"2 2 3 00600\r\n" + 
			"2 1 4 00323\r\n" + 
			"2 2 1 02401\r\n" + 
			"2 1 5 30302\r\n" + 
			"2 2 1 00053\r\n" + 
			"2 2 2 03500\r\n" + 
			"2 1 1 20032\r\n" + 
			"2 3 5 00006\r\n" + 
			"2 1 2 32003\r\n" + 
			"2 1 4 02203\r\n" + 
			"2 2 4 35000\r\n" + 
			"2 2 5 50030\r\n" + 
			"2 3 2 06000\r\n" + 
			"2 2 5 50000\r\n" + 
			"2 2 1 00050\r\n" + 
			"2 2 4 24100\r\n" + 
			"2 1 3 03230\r\n" + 
			"2 2 3 00305\r\n" + 
			"2 2 3 10042\r\n" + 
			"2 1 2 00322\r\n" + 
			"2 2 5 41020\r\n" + 
			"2 1 5 23020\r\n" + 
			"3 3 3 33053\r\n" + 
			"3 4 2 03603\r\n" + 
			"3 4 5 00070\r\n" + 
			"3 5 5 00073\r\n" + 
			"3 4 4 70000\r\n" + 
			"3 4 3 00336\r\n" + 
			"3 3 1 03533\r\n" + 
			"3 4 1 07000\r\n" + 
			"3 3 4 35303\r\n" + 
			"3 5 2 03700\r\n" + 
			"3 4 4 63030\r\n" + 
			"3 4 5 30063\r\n" + 
			"3 5 3 00307\r\n" + 
			"3 5 1 37000\r\n" + 
			"3 5 4 70030\r\n" + 
			"3 3 2 30335\r\n" + 
			"3 4 3 00007\r\n" + 
			"3 4 1 36300\r\n" + 
			"3 3 5 53330\r\n" + 
			"3 4 2 00700\r\n");
	String noble_query = new String("칼_5세  30033\r\n" + 
				"프랑수아_1세  33030\r\n" + 
				"쉴레이만_1세  04400\r\n" + 
				"이사벨_1세  00044\r\n" + 
				"앤_불린 03303\r\n" + 
				"엘리자베스_1세  00333\r\n" + 
				"마키아벨리  00404\r\n" + 
				"헨리_8세 40040\r\n" + 
				"메리_여왕  44000\r\n" + 
				"카트린_드_메디시스  33300");
	StringTokenizer card_st = new StringTokenizer(card_query, "\r\n ");
	StringTokenizer noble_st = new StringTokenizer(noble_query, "\r\n ");
	
	/* 귀족 카드를 덱에 쌓아둠 */
	int noble_count = noble_st.countTokens();
	for(i=0; i<noble_count; i++) {
		String token = noble_st.nextToken();
		if(i%2 == 0) {
			name = token;
		}
		else {
			need = Integer.parseInt(token);
			noble_hide.add(new noble_card(name, need));	
		}
	}
	for(i=0; i<3; i++) {
		move2open_noble(noble_hide, noble_open);
	}
	
	/* 카드를 레벨별로 카드가 공개되지 않는 덱에 쌓아둠 */
	int count = card_st.countTokens();
	for(i = 0; i<count; i++) {
		int token = Integer.parseInt(card_st.nextToken());
		if(n%4==0) {
			level = token;
		}
		else if(n%4==1)	score = token;
		else if (n%4 == 2)	effect = token;
		else {
			need = token;
			/*레벨에 맞는 카드 비공개 덱에 저장*/
			if(level == 1) {
				hide_1.add(new card(score, effect, need));
			}
			else if(level == 2) {
				hide_2.add(new card(score, effect, need));
			}
			else if(level == 3) {
				hide_3.add(new card(score, effect, need));
			}
		}
		n++;
	}
	/* 카드를 랜덤하게 레벨별로 4개씩 공개된 덱으로 이동 */
	for(int i = 0; i<4; i++) {
		move2open(hide_1, open_1);
		move2open(hide_2, open_2);
		move2open(hide_3, open_3);
	}
	}
	
	/* 카드를 공개된 덱으로 옮기는 메소드 */
	public void move2open(Vector<card> from, Vector<card> to) {
		rand = r.nextInt(from.size());
		c = (card) from.get(rand);//???
		from.remove(rand);
		to.add(c);
	}
	
	/* 귀족 카드를 공개된 덱으로 옮기는 메소드 */
	public void move2open_noble(Vector<noble_card> from, Vector<noble_card> to) {
		rand = r.nextInt(from.size());
		nc = (noble_card) from.get(rand);//???
		from.remove(rand);
		to.add(nc);
	}
	
	/*카드를 사용자에게 옮기는 메소드*/
	public void move2user(Vector<card> source, Vector<card> open, Vector<card> user, int i)
	{//공개된 덱 -> 사용자
		c = open.get(i-1);
		open.remove(i-1);
		user.add(c);
		//숨겨진 덱 -> 공개된 덱
		rand = r.nextInt(source.size());
		c = (card) source.get(rand);
		source.remove(rand);
		open.add(i-1, c);
	}
	
	/*귀족 카드를 사용자에게 옮기는 메소드*/
	public void move2user_noble(Vector<noble_card> source, Vector<noble_card> open, Vector<noble_card> user, int i)
	{//공개된 덱 -> 사용자
		nc = open.get(i-1);
		open.remove(i-1);
		user.add(nc);
		//숨겨진 덱 -> 공개된 덱
		rand = r.nextInt(source.size());
		nc = (noble_card) source.get(rand);
		source.remove(rand);
		open.add(i-1, nc);
	}
	
	
	/*카드를 공개된 덱으로 반납하는 메소드*/
	public void moveuser2open(Vector<card> source, Vector<card> user, Vector<card> open, int i, int num) {
		c = user.get(i);//사용자가 잘못 가져온 카드 정보를 c에 저장
		user.remove(i);
		c1 = open.get(num-1);//잘못 공개된 덱에 가져온 카드 정보를 c1에 저장
		open.remove(num-1);
		open.add(num-1, c);
		source.add(c1);
	}
	
	/* 공개 덱의 카드를 출력하는 메소드 */
	public void open() {
		int i;
		for(i = 0; i < 3; i++) {
			System.out.println("귀족 카드 " + (i+1) + "번  - "+ noble_open.elementAt(i));
		}
		System.out.println();
		for(i = 0 ; i< 4 ; i++) {
			System.out.println("중요도  1 " + "카드 " + (i+1) + "번  - " + open_1.elementAt(i));
		}
		System.out.println();
		for(i = 0 ; i< 4 ; i++) {
			System.out.println("중요도  2 " + "카드 " + (i+1) + "번  - " + open_2.elementAt(i));
		}
		System.out.println();
		for(i = 0 ; i< 4 ; i++) {
			System.out.println("중요도  3 " + "카드 " + (i+1) + "번  - " + open_3.elementAt(i));
		}
		System.out.println();
	}
}
