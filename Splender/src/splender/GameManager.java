package splender;

import java.util.Arrays;

public class GameManager {
	static boolean miss[] = {false,false,false,false,false};//����ڰ� ���ſ� ����� �ڿ��� ������� Ȯ���� ����� ����
	static boolean retry = false;
	
	public static void main(String arvg[]) {
		User user1 = new User(1);
		User user2 = new User(2);
		Arrays.fill(miss, true);
		int i;
		boolean r1=true, r2=true, retry = false;
		System.out.println("���÷����� ���� ���� ȯ���մϴ�!");
		System.out.println("���÷����� ������ �ڿ��� �̿��ؼ� ī�带 ������ ������ ��� �¸��ϴ� �����Դϴ�.");
		System.out.println("ī��� �ش� ī�� �����ϴ� ȿ���� ���� �ٸ� ī���� ���ź���� �ٿ��ݴϴ�.");
		System.out.println("�ڿ��� ���� �ٸ� �ڿ� 3�� �Ǵ� ���� �ڿ� 2���� ������ �� �ֽ��ϴ�.");
		System.out.println();
		
		while(r1&&r2) {

			r1 = check_retry(user1, 1);
			if(r1 == false) { System.out.println("����� 2�� �ϱ��� ��ġ�� ������ �����մϴ�."); }
			
			r2 = check_retry(user2, 2);
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
	
	/* ������� ���� �����ϰ� ���� ���������� �Ϸ� �ɶ����� ���� �����ϴ� �޼ҵ� */
	public static boolean check_retry(User user, int num) {
		boolean r1;
		System.out.println("����� " + num + "�� ���Դϴ�.");
		do {
			retry = false;
			for(int i=0;i<5;i++) {//�� ���� ����Ǳ� ����  ���� �ʱ�ȭ
				miss[i] = false;
			}
			r1 = user.turn();
			for(int i=0; i<5; i++) {
				if (miss[i] == true) {//�ʿ��� �ڿ��� �� ������ ����ڰ� ���ٸ� ���� �ٽ� ����
					retry = true;
				}
			}
		}while(retry);
		return r1;
	}
}