package splender;

import java.util.Arrays;

public class GameManager {
	static boolean miss[] = {false,false,false,false,false};//����ڰ� ���ſ� ����� �ڿ��� ������� Ȯ���� ����� ����
	static boolean retry = false;
	public static void main(String arvg[]) {
		user user1 = new user(1);
		user user2 = new user(2);
		Arrays.fill(miss, true);
		int i;
		boolean r1=true, r2=true, retry = false;
		System.out.println("���÷����� ���� ���� ȯ���մϴ�!");
		System.out.println("���÷����� ������ �ڿ��� �̿��ؼ� ī�带 ������ ������ ��� �¸��ϴ� �����Դϴ�.");
		System.out.println("ī��� �ش� ī�� �����ϴ� ȿ���� ���� �ٸ� ī���� ���ź���� �ٿ��ݴϴ�.");
		System.out.println();
		
		while(r1&&r2) {
			System.out.println("����� 1�� ���Դϴ�.");
			/* ����� 1�� �� ���� */
			do {
				retry = false;
				r1 = user1.turn();
				for(i=0; i<5; i++) {
					if (miss[i] == true) {//�ʿ��� �ڿ��� �� ������ ����ڰ� ���ٸ� ���� �ٽ� ����
						retry = true;
					}
				}
			}while(retry);
			if(r1 == false) { System.out.println("����� 2�� �ϱ��� ��ġ�� ������ �����մϴ�."); }
			
			/*����� 2�� �� ����*/
			System.out.println("����� 2�� ���Դϴ�.");
			do {
				retry = false;
				r2 = user2.turn();
				for(i=0; i<5; i++) {
					if (miss[i] == true) {
						retry = true;
					}
				}
			}while(retry);
			if(r2 == false) { System.out.println("������ �����մϴ�."); }
			
			/* �¸� ���� �޼��� */
			if(user1.score >= 15) {
				System.out.println("�����մϴ�!" + user1.score + "������  ����� 1�� �¸��Դϴ�.");
				break;
			}
			else if(user2.score >= 15) {
				System.out.println("�����մϴ�!" + user2.score + "������ ����� 2�� �¸��Դϴ�.");
				break;
			}
		}
	}
}



