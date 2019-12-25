package splender;
import java.util.Scanner;
import java.util.Vector;

public class User {
	String Color[] = {"����","�ʷ�","�Ķ�","����", "�Ͼ�"};
	int color[] = {0, 0, 0, 0, 0};// ����, �ʷ�, �Ķ�, ����, �Ͼ� ������ ����� ��ü�� �ڿ� ���ڸ� ����
	int card[] = {0, 0, 0, 0, 0};//���� ������ ����� ��ü�� ī�� ���ڸ� ����
	int option, rank=0, number=0, user_n= 0, score = 0, total_card = 0;
	int color_input, color_input_1, color_input_2, color_input_3; // ����ڰ� ������ �ڿ�
	card getcard;
	noble_card getnoble_card;
	cardManager cm = new cardManager();
	Scanner scanner = new Scanner(System.in);
	public User(int n) { user_n = n; }
	public boolean turn() {
		
		/*���� ���µǾ� �ִ� ī��, �ڽ��� �ڿ��� ī�带 ������ */
		cm.open();
		show();
		System.out.println();
		System.out.print("�ൿ�� ������ 1)�ڿ� ȹ��  2)ī�� ����  3)���� ����>>");
		
		option = scanner.nextInt();
		
		/* �ڿ��� ���� ��� */
		if(option == 1) {
			System.out.println("���� �ڿ� 2�� Ȥ�� ���� �ٸ� �ڿ� 3���� ������ �� �ֽ��ϴ�.");
			System.out.print("ù��° �ڿ��� ������>>");
			color_input_1 = get_color();
			System.out.print("�ι�° �ڿ��� ������");
			color_input_2 = get_color();
			if(color_input_1 == color_input_2) {//���� ������ �ڿ� 2�� ���� ���
				System.out.println("���� ����Ǿ����ϴ�.");
			}
			else {//���� �ٸ� �ڿ��� 3�� ���� ���
				color_input_3 = get_color();
				if(color_input_1 == color_input_3 || color_input_2 == color_input_3) {
					do {
						System.out.println("���ǿ� ���� �ʴ� �ൿ�дϴ�. ���� �ڿ� 2�� Ȥ�� ���� �ٸ� �ڿ� 3���� ������ �� �ֽ��ϴ�.");
						return_color(color_input_3);
						System.out.println("����° �ڿ��� ������");
						color_input_3 = get_color();
					}while(color_input_1 == color_input_3 || color_input_2 == color_input_3);
				}
				System.out.println("���� ����Ǿ����ϴ�.");
			}
			show();
			System.out.println();
			return true;
		}
		
		/* ī�带 ��� ������ �� ��� */
		else if(option == 2) {
			System.out.print("������ ī���� �߿䵵�� �Է��Ͻÿ�>>");
			rank = scanner.nextInt();
			System.out.print("������ ī���� ��ȣ�� �Է��Ͻÿ�>>");
			number = scanner.nextInt();
			
			/*����ڰ� ������ ī���� ������ �о�� */
			getcard = ((Vector<card>)cardManager.open.get(rank-1)).get(number-1);

			/*ī�带 �����ϱ⿡ �ڿ��� �������� Ȯ�� */
			if(getcard.need[0] <= (this.card[0]+this.color[0]) && getcard.need[1] <= (this.card[1]+this.color[1])  
					&& getcard.need[2] <= (this.card[2]+this.color[2]) && getcard.need[3] <= (this.card[3]+this.color[3])
					&& getcard.need[4] <= (this.card[4]+this.color[4])) {
				
				/* ����ڰ� ������ ī�带 ������� ������ �̵� ��Ŵ */
				cm.move2user((Vector<card>)cardManager.hide.get(rank-1), (Vector<card>)
				cardManager.open.get(rank-1), (Vector<card>)cardManager.user.get(user_n), number);

				/*ī�� ��� ����*/
				for(int i=0; i<5; i++) {
					color[i] = pay(getcard.need[i], this.color[i], this.card[i], i);
				}
				
				/* ���� ī���� ȿ�� ����ڿ��� �߰� */
				card[getcard.effect-1] += 1;
				
				/* ���� ī���� ���� ����ڿ��� �߰� */
				if(getcard.score>0) {
					while(getcard.score!=0) {
						score++;
						getcard.score--;
						System.out.println("");
					}
				}
				
				total_card += 1;// ����� ��ü�� ī�� ���� ������Ŵ
				System.out.println(getcard.Effect[getcard.effect-1]+ " ȿ���� ī�带 �����ϼ̽��ϴ�.");
				for(int i=0; i<3; i++) {
					check_noble(i);
				}
				System.out.println("���� ����Ǿ����ϴ�.");
				System.out.println();
			}
			else {
				GameManager.retry = true;//���� �ٽ� ����ǵ��� ���� ����
				System.out.println("�ش� ī�带 �����ϱ� ���� �ڿ��� �����մϴ�. �ٽ�  �����ϼ���");
				System.out.println();
			}
		}
		
		/* ������ �����ϴ� ���*/
		else if(option == 3) {
			return false;
		}
		return true;
	}
	
	/*���� ī���� ������ �����ϴ� �޼ҵ� */
	public int pay(int price, int color, int color_c, int i) {
		if(price>0) {//������ ������ ���� ���� ���� �ݺ�
			while(color_c > 0 && price > 0) {//�ش� �ڿ��� ī�尡 ���� ���
				color_c--;	price--;
			}
			while(color > 0  && price > 0) {//�ش� �ڿ��� ī�尡 �����ϰ� �ڿ��� ���� ���
				color--;	price--;
			}
			if (price != 0) {// �ش� �ڿ��� ī�尡 ��� ���� ���
				System.out.print((i+1) + "�� �ڿ��� " + price + "��ŭ �����մϴ�. ");
			}
		}
		return color;
	}
	
	/* �ڿ� ī�带 ��� �޼ҵ� */
	public int get_color() {
		System.out.println("����: 1, �ʷ�: 2, �Ķ�: 3, ����: 4, �Ͼ�: 5");
		color_input = scanner.nextInt();
		color[color_input-1] += 1;
		System.out.println(Color[color_input-1] + "�� ȹ���߽��ϴ�"); 
		System.out.println();
		return color_input;
	}

	/* �ڿ� ī�带 �ǵ��� �ִ� �޼ҵ� */
	public int return_color(int color_input) {
		color[color_input-1] -= 1;
		System.out.println("�ڿ��� ��ȯ�մϴ�.");
		return color_input;
	}
	
	/* ������� ���� ������ ������ִ� �޼ҵ� */
	public void show() {
		System.out.println("���� �ڿ��� ������ ����: " + color[0] + ", "+ "�ʷ�: " + color[1] + ", "+"�Ķ�: " + color[2] 
				+ ", "+"����: " + color[3] + ", "+"�Ͼ�: " + color[4] + " �Դϴ�.");
		System.out.println("���� ī���� ������ ����: " + card[0] + ", "+ "�ʷ�: " + card[1] + ", "+"�Ķ�: " 
				+ card[2] + ", "+"����: " + card[3] + ", "+"�Ͼ�: " + card[4] + " �Դϴ�.");
	}
	
	/* ����ī�带 ���� �� �ִ��� Ȯ���ϴ� �޼ҵ� */
	public void check_noble(int i) {
		getnoble_card = cardManager.noble_open.get(i);
		if(getnoble_card.red <= this.card[0] && getnoble_card.green <= this.card[1]  
			&& getnoble_card.blue <= this.card[2] && getnoble_card.black <= this.card[3]
			&& getnoble_card.white <= this.card[4]) {
			this.score += 3;
			cm.move2user_noble(cardManager.noble_hide, cardManager.noble_open, 
					(Vector<noble_card>)cardManager.user_noble.get(user_n), i);
			System.out.println(getnoble_card.name + "ī�带 ȹ���߽��ϴ�.");
			System.out.println("���� ������ "+ score + "�� �Դϴ�.");
		}
		else {}
	}
}