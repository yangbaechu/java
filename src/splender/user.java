package splender;
import java.util.Scanner;

public class user {
	int red =0, green = 0, blue = 0, black = 0, white = 0;
	int red_card =0, green_card = 0, blue_card = 0, black_card = 0, white_card = 0, total_card = 0;
	int input1, input2, i, rank=0, number=0, user_n= 0, score = 0;
	card getcard;
	boolean retry;
	cardManager cm = new cardManager();
	Scanner scanner = new Scanner(System.in);
	public user(int n) { user_n = n; }
	public boolean turn() {
		
		/*���� ���µǾ� �ִ� ī��, �ڽ��� �ڿ��� ī�带 ������ */
		cm.open();
		System.out.println("���� �ڿ��� ������ ����: " + red + ", "+
		"�ʷ�: " + green + ", "+"�Ķ�: " + blue + ", "+"����: " + 
				black + ", "+"�Ͼ�: " + white + " �Դϴ�.");
		System.out.println("���� ī���� ������ ����: " + red_card + ", "+
				"�ʷ�: " + green_card + ", "+"�Ķ�: " + blue_card + ", "+"����: " + 
						black_card + ", "+"�Ͼ�: " + white_card + " �Դϴ�.");
		System.out.println();
		System.out.print("�ൿ�� ������ 1)�ڿ� ȹ��  2)ī�� ����  3)���� ����>>");
		
		input1 = scanner.nextInt();
		
		/* �ڿ��� ���� ��� */
		if(input1 == 1) {
			for(i = 1; i<4; i++) {
				System.out.println(i + "��° �ڿ��� ������");
				System.out.println("����: 1, �ʷ�: 2, �Ķ�: 3, ����: 4, �Ͼ�: 5");
				input2 = scanner.nextInt();
				if(input2 == 1) {
					System.out.println("������ ȹ���߽��ϴ�"); 
					red++;
				}
				else if(input2 == 2) {
					System.out.println("�ʷ��� ȹ���߽��ϴ�"); 
					green++;
				}
				else if(input2 == 3) {
					System.out.println("�Ķ��� ȹ���߽��ϴ�"); 
					blue++;
				}
				else if(input2 == 4) {
					System.out.println("������ ȹ���߽��ϴ�"); 
					black++;
				}
				else if(input2 == 5) {
					System.out.println("�Ͼ��� ȹ���߽��ϴ�"); 
					white++;
				}
				System.out.println();
			}
			System.out.println("���� ����Ǿ����ϴ�.");
			System.out.println("���� �ڿ��� ������ ����: " + red + ", "+
			"�ʷ�: " + green + ", "+"�Ķ�: " + blue + ", "+"����: " + 
					black + ", "+"�Ͼ�: " + white + " �Դϴ�.");
			System.out.println();
			return true;
		}
		
		/* ī�带 ��� ������ �� ��� */
		else if(input1 == 2) {
			retry = false;
			System.out.println("������ ī���� �߿䵵�� �Է��Ͻÿ�");
			rank = scanner.nextInt();
			System.out.println("������ ī���� ��ȣ�� �Է��Ͻÿ�");
			number = scanner.nextInt();
			
			/* ����ڰ� ������ ī�带 ������� ������ �̵� ��Ŵ */
			if(user_n == 1) {
				if(rank == 1) {
					cm.move2user(cardManager.dec_1, cardManager.open_1, cardManager.user_1, number);
				}
				else if(rank == 2) {
					cm.move2user(cardManager.dec_2, cardManager.open_2, cardManager.user_1, number);
				}
				else if(rank == 3) {
					cm.move2user(cardManager.dec_3, cardManager.open_3, cardManager.user_1, number);
				}
				getcard = cardManager.user_1.get(total_card);
			}
				
			else if(user_n == 2) {
				if(rank == 1) {
					cm.move2user(cardManager.dec_1, cardManager.open_1, cardManager.user_2, number);
				}
				else if(rank == 2) {
					cm.move2user(cardManager.dec_2, cardManager.open_2, cardManager.user_2, number);
				}
				else if(rank == 3) {
					cm.move2user(cardManager.dec_3, cardManager.open_3, cardManager.user_2, number);
				}
				getcard = cardManager.user_2.get(total_card);
			}
			
			pay();//ī�� ��� ����
			
			for(i=0; i<5; i++) {
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
				System.out.println("�ڿ��� �����մϴ�.�ٽ� �����ϼ���");
			}
		}
		
		/* ������ �����ϴ� ���*/
		else if(input1 == 3) {
			return false;
		}
		return true;
	}
	
	/*���� ī���� ������ �����ϴ� �޼ҵ� */
	public void pay() {
		int red_pay, green_pay, blue_pay, black_pay, white_pay; // ī���� ���� 
		int red_card_pay = red_card, green_card_pay = green_card, blue_card_pay = blue_card,
				black_card_pay = black_card, white_card_pay = white_card;//���ҿ� ����� ī�� ����
		red_pay = getcard.red;		green_pay = getcard.green;		blue_pay = getcard.blue;
		black_pay = getcard.black;	white_pay = getcard.white;
		
		/* ���� �ڿ� ��� ���� */
		while(red_pay>0) {
			if(red_card_pay>0) {
				red_card_pay--;
				red_pay--;
			}
			else if(red>0) {
				red--;
				red_pay--;
			}
			if (red_pay == 0) { break; }
			else {
				GameManager.miss[0] = true;
				break;
			}
		}
		
		/* �ʷ� �ڿ� ��� ���� */
		while(green_pay>0) {
			if(green_card_pay>0) {
				green_card_pay--;
				green_pay--;
			}
			else if(green>0) {
				green--;
				green_pay--;
			}
			if (green_pay == 0) { break; }
			else {
				GameManager.miss[1] = true;
				break;
			}
		}
		
		/* �Ķ� �ڿ� ��� ���� */
		while(blue_pay>0) {
			if(blue_card_pay>0) {
				blue_card_pay--;
				blue_pay--;
			}
			else if(blue>0) {
				blue--;
				blue_pay--;
			}
			if (blue_pay == 0) { break; }
			else {
				GameManager.miss[2] = true;
				break;
			}
		}
		
		/* ���� �ڿ� ��� ���� */
		while(black_pay>0) {
			if(black_card_pay>0) {
				black_card_pay--;
				black_pay--;
			}
			else if(black>0) {
				black--;
				black_pay--;
			}
			if (black_pay == 0) { break; }
			else {
				GameManager.miss[3] = true;
				break;
			}
		}
		
		/*�Ͼ� �ڿ� ��� ���� */
		while(white_pay>0) {
			if(white_card_pay>0) {
				white_card_pay--;
				white_pay--;
			}
			else if(white>0) {
				white--;
				white_pay--;
			}
			if (white_pay == 0) { break; }
			else {
				GameManager.miss[4] = true;
				break;
			}
		}
	}
}
