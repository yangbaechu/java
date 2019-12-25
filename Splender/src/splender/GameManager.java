package splender;

import java.awt.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class GameManager extends JFrame{
	static boolean miss[] = {false,false,false,false,false};//����ڰ� ���ſ� ����� �ڿ��� ������� Ȯ���� ����� ����
	static boolean retry = false;
	cardManager cm = new cardManager();
	public GameManager() {
		super("Splendor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(5,5));
		//JPanel pnl1=new JPanel();

		JButton btn3=new JButton("�߿䵵 3");
		contentPane.add(btn3);
		contentPane.setSize(1200,800);
		contentPane.setVisible(true);
		btn3.setText(((Vector<card>)cardManager.open.elementAt(0)).toString());
	}
	
	public static void main(String arvg[]) {
		new GameManager();
		User user []=new User[4];
		int i, cnt, order;
		System.out.println("�÷��̾��� ���ڸ� �Է��Ͽ� �ֽʽÿ�.");
		Scanner scan=new Scanner(System.in);
		do
		{
			i=scan.nextInt();
			if(i<2)System.out.println("�÷��̾��� ���� �ʹ� �����ϴ�!\n�ٽ� �Է��Ͽ� �ֽʽÿ�.");
			if(i>4)System.out.println("�÷��̾��� ���� �ʹ� �����ϴ�!\n�ٽ� �Է��Ͽ� �ֽʽÿ�.");
		}while(i<2||i>4);
		for(int j=0; j<i; j++) {
			user[j] = new User(j);
		}
		Arrays.fill(miss, true);
		boolean r1=true;
		System.out.println("���÷����� ���� ���� ȯ���մϴ�!");
		System.out.println("���÷����� ������ �ڿ��� �̿��ؼ� ī�带 ������ ������ ��� �¸��ϴ� �����Դϴ�.");
		System.out.println("ī��� �ش� ī�� �����ϴ� ȿ���� ���� �ٸ� ī���� ���ź���� �ٿ��ݴϴ�.");
		System.out.println("�ڿ��� ���� �ٸ� �ڿ� 3�� �Ǵ� ���� �ڿ� 2���� ������ �� �ֽ��ϴ�.");
		System.out.println();
		order=0;
		for(cnt=0; r1==true || cnt%i!=0; cnt++, order=cnt%i) {//����ڰ� ��� ���� �����ϰ� �Ѹ��̶� �����ϰڴٰ� �Է��Ҷ����� ����
			if(r1==true)
			{
				r1 = check_retry(user[order], order+1);
				if(r1==false)
					System.out.println("�����"+i+"�� �ϱ��� ��ġ�� ������ �����մϴ�.");
			}
			else
			{
				check_retry(user[order],order);
				System.out.println("�����"+i+"�� �ϱ��� ��ġ�� ������ �����մϴ�.");
			}
			/* �¸� ���� �޼��� */
			if(user[cnt%i].score >= 15) {
				System.out.println("�����մϴ�!" + user[order].score + "������  ����� "+order+"�� �¸��Դϴ�.");
				break;
			}
		}
		System.out.println("������ �����մϴ�.");
		scan.close();
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