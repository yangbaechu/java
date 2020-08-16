package splender;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Random;
import java.io.*;

public class cardManager{
	int level, score, effect, need, n=0, rand, i, j;
	int noble_count = 0, count = 0;
	String name;
	card c, c1;
	noble_card nc, nc1;
	
	/* ī���� �������� ������ ���� */
	static Vector<Vector<card>> hide = new Vector<Vector<card>>(3);//������ ī�尡 ����� ����
	static Vector<Vector<card>> open = new Vector<Vector<card>>(3);//������ ī�尡 ����� ����
	static Vector<Vector<card>> user = new Vector<Vector<card>>(4);//������� ī�尡 ����� ����
	static Vector<Vector<noble_card>> user_noble = new Vector<Vector<noble_card>>(4);//������� ����ī�尡 ����� ����

	//���� ī�尡 ����� ����
	static Vector<noble_card>noble_hide = new Vector<noble_card>();
	static Vector<noble_card>noble_open = new Vector<noble_card>();
	
	Random r = new Random();
	
	
	public cardManager() {
		for(i=0; i<3; i++) {//���� ī�带 ������ ���� ����
			hide.add(new Vector<card>());
			open.add(new Vector<card>());
		}
		for(i=0; i<4; i++) {//����� ī�带 ������ ���� ����
			user.add(new Vector<card>());
			user_noble.add(new Vector<noble_card>());
		}
		
		/* �ؽ�Ʈ ���Ͽ��� ī�� ������ �о���� */
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
		
		/* �ؽ�Ʈ ���Ͽ��� ���� ī�� ������ �о���� */
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
		
		String card_info[] = card_query.split(",");
		String noble_info[] = noble_query.split(",");;

		noble_count = noble_info.length;
		for (i = 0; i < noble_count - 1; i++) {
			String noble_token[] = noble_info[i].split(" ");

			name = noble_token[0];
			need = Integer.parseInt(noble_token[1]);
			noble_hide.add(new noble_card(name, need));
		}
		
		for(i=0; i<3; i++) {
			move2open_noble(noble_hide, noble_open);
		}
		
		/* ī�带 �������� ī�尡 �������� �ʴ� ���� �׾Ƶ� */
		count = card_info.length;
		for (i = 0; i < count - 1; i++) {
			String token[] = card_info[i].split(" ");

			level = Integer.parseInt(token[0]);
			score = Integer.parseInt(token[1]);
			effect = Integer.parseInt(token[2]);
			need = Integer.parseInt(token[3]);

			/* ������ �´� ī�� ����� ���� ���� */
			((Vector<card>) hide.get(level - 1)).add(new card(score, effect, need));
		}

		/* ī�带 �����ϰ� �������� 4���� ������ ������ �̵� */
		for (j = 0; j < 3; j++) {
			for (i = 0; i < 4; i++) {
				move2open((Vector<card>) hide.get(j), (Vector<card>) open.get(j));
			}
		}
	}	//������ ����
	
	/* ī�带 ������ ������ �ű�� �޼ҵ� */
	public void move2open(Vector<card> from, Vector<card> to) {
		rand = r.nextInt(from.size());
		c = (card) from.get(rand);//???
		from.remove(rand);
		to.add(c);
	}
	
	/* ���� ī�带 ������ ������ �ű�� �޼ҵ� */
	public void move2open_noble(Vector<noble_card> from, Vector<noble_card> to) {
		rand = r.nextInt(from.size());
		nc = (noble_card) from.get(rand);
		from.remove(rand);
		to.add(nc);
	}
	
	/*ī�带 ����ڿ��� �ű�� �޼ҵ�*/
	public void move2user(Vector<card> source, Vector<card> open, Vector<card> user, int i)
	{
		c = open.get(i-1);
		open.remove(i-1);
		user.add(c);//������ �� -> �����
		rand = r.nextInt(source.size());
		c = (card) source.get(rand);
		source.remove(rand);
		open.add(i-1, c);//������ �� -> ������ ��
	}
	
	/*���� ī�带 ����ڿ��� �ű�� �޼ҵ�*/
	public void move2user_noble(Vector<noble_card> source, Vector<noble_card> open, Vector<noble_card> user, int i)
	{
		nc = open.get(i-1);
		open.remove(i-1);
		user.add(nc);//������ �� -> �����
		rand = r.nextInt(source.size());
		nc = (noble_card) source.get(rand);
		source.remove(rand);
		open.add(i-1, nc);//������ �� -> ������ ��
	}
}
