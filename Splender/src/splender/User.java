package splender;
import java.util.Scanner;

public class User {
	int red =0, green = 0, blue = 0, black = 0, white = 0;
	int red_card =0, green_card = 0, blue_card = 0, black_card = 0, white_card = 0, total_card = 0;
	int option, rank=0, number=0, user_n= 0, score = 0;
	int color_input, color_input_1, color_input_2, color_input_3; // ����ڰ� ������ �ڿ�
	card getcard;
	boolean retry = false;
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
			System.out.println("ù��° �ڿ��� ������");
			color_input_1 = get_color();
			System.out.println("�ι�° �ڿ��� ������");
			color_input_2 = get_color();
			if(color_input_1 == color_input_2) {//���� ������ �ڿ� 2�� ���� ���
				System.out.println("���� ����Ǿ����ϴ�.");
				show();
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
			System.out.println();
			return true;
		}
		
		/* ī�带 ��� ������ �� ��� */
		else if(option == 2) {
			retry = false;
			System.out.println("������ ī���� �߿䵵�� �Է��Ͻÿ�");
			rank = scanner.nextInt();
			System.out.println("������ ī���� ��ȣ�� �Է��Ͻÿ�");
			number = scanner.nextInt();
			
			/* ����ڰ� ������ ī�带 ������� ������ �̵� ��Ŵ */
			if(user_n == 1) {
				if(rank == 1) {
					cm.move2user(cardManager.hide_1, cardManager.open_1, cardManager.user_1, number);
				}
				else if(rank == 2) {
					cm.move2user(cardManager.hide_2, cardManager.open_2, cardManager.user_1, number);
				}
				else if(rank == 3) {
					cm.move2user(cardManager.hide_3, cardManager.open_3, cardManager.user_1, number);
				}
				getcard = cardManager.user_1.get(total_card);
			}
				
			else if(user_n == 2) {
				if(rank == 1) {
					cm.move2user(cardManager.hide_1, cardManager.open_1, cardManager.user_2, number);
				}
				else if(rank == 2) {
					cm.move2user(cardManager.hide_2, cardManager.open_2, cardManager.user_2, number);
				}
				else if(rank == 3) {
					cm.move2user(cardManager.hide_3, cardManager.open_3, cardManager.user_2, number);
				}
				getcard = cardManager.user_2.get(total_card);
			}
			
			/*ī�� ��� ����*/
			red = pay(getcard.red, this.red, this.red_card, 0);
			green = pay(getcard.green, this.green, this.green_card, 1);
			blue = pay(getcard.blue, this.blue, this.blue_card, 2);
			black = pay(getcard.black, this.black, this.black_card, 3);
			white = pay(getcard.white, this.white, this.white_card, 4);
			
			for(int i=0; i<5; i++) {
				if (GameManager.miss[i] == true) {
					retry = true;
				}
			}
			
			/*ī�带 �����ϱ⿡ �ڿ��� �������� Ȯ�� */
			if(!retry) {
				
				/* ���� ī���� ȿ�� ����ڿ��� �߰� */
				if (getcard.effect == 1) { red_card ++; }
				else if (getcard.effect == 1) { green_card ++; }
				else if (getcard.effect == 1) { blue_card ++; }
				else if (getcard.effect == 1) { black_card ++; }
				else { white_card ++; }
				
				/* ���� ī���� ���� ����ڿ��� �߰� */
				if(getcard.score>0) {
					while(getcard.score!=0) {
						score++;
						getcard.score--;
						System.out.println("");
					}
				}
				
				total_card += 1;
				System.out.println(getcard.Effect+ " ȿ���� ī�带 �����ϼ̽��ϴ�.");
				System.out.println("���� ����Ǿ����ϴ�.");
				System.out.println();
			}
			else {
				/* ����ڰ� �����Դ� ī�带 �ݳ� */
				if(user_n == 1) {
					if(rank == 1) {
						cm.moveuser2open(cardManager.hide_1, cardManager.user_1, cardManager.open_1, total_card, number);
					}
					else if(rank == 2) {
						cm.moveuser2open(cardManager.hide_2, cardManager.user_1, cardManager.open_2, total_card, number);
					}
					else if(rank == 3) {
						cm.moveuser2open(cardManager.hide_3, cardManager.user_1, cardManager.open_3, total_card, number);
					}
				}
					
				else if(user_n == 2) {
					if(rank == 1) {
						cm.moveuser2open(cardManager.hide_1, cardManager.user_2, cardManager.open_1, total_card, number);
					}
					else if(rank == 2) {
						cm.moveuser2open(cardManager.hide_2, cardManager.user_2, cardManager.open_2, total_card, number);
					}
					else if(rank == 3) {
						cm.moveuser2open(cardManager.hide_3, cardManager.user_2, cardManager.open_3, total_card, number);
					}
				}
				//System.out.println("�ڿ��� �����մϴ�.�ٽ� �����ϼ���");
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
				color_c--;
				price--;
			}
			while(color > 0  && price > 0) {//�ش� �ڿ��� ī�尡 �����ϰ� �ڿ��� ���� ���
				color--;
				price--;
			}
			if (price != 0) {// �ش� �ڿ��� ī�尡 ��� ���� ���
				GameManager.miss[i] = true;
				System.out.print(i + "�� �ڿ��� " + price + "��ŭ �����մϴ�. ");
			}
		}
		return color;
	}
	
	/* �ڿ� ī�带 ��� �޼ҵ� */
	public int get_color() {
		System.out.println("����: 1, �ʷ�: 2, �Ķ�: 3, ����: 4, �Ͼ�: 5");
		color_input = scanner.nextInt();
		if(color_input== 1) {
			System.out.println("������ ȹ���߽��ϴ�"); 
			red++;
		}
		else if(color_input == 2) {
			System.out.println("�ʷ��� ȹ���߽��ϴ�"); 
			green++;
		}
		else if(color_input == 3) {
			System.out.println("�Ķ��� ȹ���߽��ϴ�"); 
			blue++;
		}
		else if(color_input == 4) {
			System.out.println("������ ȹ���߽��ϴ�"); 
			black++;
		}
		else if(color_input == 5) {
			System.out.println("�Ͼ��� ȹ���߽��ϴ�"); 
			white++;
		}
		System.out.println();
		return color_input;
	}

	/* �ڿ� ī�带 �ǵ��� �ִ� �޼ҵ� */
	public int return_color(int color_input) {
		if(color_input == 1) { red--; }
		else if(color_input == 2) { green--; }
		else if(color_input == 3) { blue--; }
		else if(color_input == 4) { black--; }
		else if(color_input == 5) {	white--; }
		System.out.println("�ڿ��� ��ȯ�մϴ�.");
		return color_input;
	}
	
	/* ������� ���� ������ ������ִ� �޼ҵ� */
	public void show() {
		System.out.println("���� �ڿ��� ������ ����: " + red + ", "+ "�ʷ�: " + green + ", "+"�Ķ�: " + blue 
				+ ", "+"����: " + black + ", "+"�Ͼ�: " + white + " �Դϴ�.");
		System.out.println("���� ī���� ������ ����: " + red_card + ", "+ "�ʷ�: " + green_card + ", "+"�Ķ�: " 
				+ blue_card + ", "+"����: " + black_card + ", "+"�Ͼ�: " + white_card + " �Դϴ�.");
	}
}