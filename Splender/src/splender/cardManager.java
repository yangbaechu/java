package splender;

import java.util.Vector;
import java.util.Random;
import java.io.*;

public class cardManager{
	int level, score, effect, need, n=0, rand, i, j;
	int noble_count = 0, count = 0;
	String name;
	card c, c1;
	noble_card nc, nc1;
	
	/* 카드의 정보를 저장할 벡터 */
	static Vector<Vector<card>> hide = new Vector<Vector<card>>(3);		//숨겨진 카드 저장
	static Vector<Vector<card>> open = new Vector<Vector<card>>(3);		//공개된 카드 저장
	static Vector<Vector<card>> user = new Vector<Vector<card>>(4);		//사용자의 카드 저장
	
	/* 귀족 카드의 정보를 저장할 벡터 */
	static Vector<noble_card>noble_hide = new Vector<noble_card>();
	static Vector<noble_card>noble_open = new Vector<noble_card>();
	static Vector<Vector<noble_card>> user_noble = new Vector<Vector<noble_card>>(4);

	Random r = new Random();
	
	
	public cardManager() {
		for(i=0; i<3; i++) {
			hide.add(new Vector<card>());
			open.add(new Vector<card>());
		}
		for(i=0; i<4; i++) {
			user.add(new Vector<card>());
			user_noble.add(new Vector<noble_card>());
		}
		
		/* 텍스트 파일에서 카드 정보 읽어오기  */
		File card_file = new File("C:\\Users\\gunhee\\Desktop\\Splender\\card.txt");
		String card_query = "";
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(card_file))) {
		    while ((line = br.readLine()) != null) {
		    	card_query += line;
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		String card_info[] = card_query.split(",");
		
		/*카드 덱에 저장 */
		count = card_info.length;
		for (i = 0; i < count - 1; i++) {
			String token[] = card_info[i].split(" ");

			level = Integer.parseInt(token[0]);
			score = Integer.parseInt(token[1]);
			effect = Integer.parseInt(token[2]);
			need = Integer.parseInt(token[3]);

			/*레벨에 맞는 비공개 덱에 저장*/
			((Vector<card>) hide.get(level - 1)).add(new card(score, effect, need));
		}

		/* 레벨마다 4개씩의 카드를  공개 덱에 저장 */
		for (j = 0; j < 3; j++) {
			for (i = 0; i < 4; i++) {
				move2open((Vector<card>) hide.get(j), (Vector<card>) open.get(j));
			}
		}
		
		
		/* 텍스트 파일에서 귀족 카드 정보 읽어오기 */
		File noble_file = new File("C:\\Users\\gunhee\\Desktop\\Splender\\noble.txt");
		String noble_query = "";
		String line_1 = "";
		try (BufferedReader br = new BufferedReader(new FileReader(noble_file))) {
		    while ((line_1 = br.readLine()) != null) {
		    	noble_query += line_1;
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		String noble_info[] = noble_query.split(",");;

		noble_count = noble_info.length;
		for (i = 0; i < noble_count - 1; i++) {
			String noble_token[] = noble_info[i].split(" ");

			name = noble_token[0];
			need = Integer.parseInt(noble_token[1]);
			/* 비공개 덱에 귀족카드 저장 */
			noble_hide.add(new noble_card(name, need));
		}
		
		/* 공개 덱에 귀족카드 저장 */
		for(i=0; i<3; i++) {
			move2open_noble(noble_hide, noble_open);
		}
		
		
	}	//생성자 종료
	
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
		nc = (noble_card) from.get(rand);
		from.remove(rand);
		to.add(nc);
	}
	
	/*카드를 사용자에게 옮기는 메소드*/
	public void move2user(Vector<card> source, Vector<card> open, Vector<card> user, int i)
	{
		c = open.get(i-1);
		open.remove(i-1);
		user.add(c);//공개된 덱 -> 사용자
		rand = r.nextInt(source.size());
		c = (card) source.get(rand);
		source.remove(rand);
		open.add(i-1, c);//숨겨진 덱 -> 공개된 덱
	}
	
	/*귀족 카드를 사용자에게 옮기는 메소드*/
	public void move2user_noble(Vector<noble_card> source, Vector<noble_card> open, Vector<noble_card> user, int i)
	{
		nc = open.get(i-1);
		open.remove(i-1);
		user.add(nc);//공개된 덱 -> 사용자
		rand = r.nextInt(source.size());
		nc = (noble_card) source.get(rand);
		source.remove(rand);
		open.add(i-1, nc);//숨겨진 덱 -> 공개된 덱
	}
}
