package splender;

public class GameManager {
	public static void main(String arvg[]) {

		user user1 = new user(1);
		user user2 = new user(2);
		boolean r1=true, r2=true;
		System.out.println("���÷����� ���� ���� ȯ���մϴ�!");
		System.out.println("���÷����� ������ �ڿ��� �̿��ؼ� ī�带 ������ ������ ��� �¸��ϴ� �����Դϴ�.");
		System.out.println("ī��� �ش� ī�� �����ϴ� ȿ���� ���� �ٸ� ī���� ���ź���� �ٿ��ݴϴ�.");
		System.out.println();
		while(r1&&r2) {
			System.out.println("����� 1�� ���Դϴ�.");
			r1 = user1.turn();
			System.out.println("����� 2�� ���Դϴ�.");
			r2 = user2.turn();
			
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


