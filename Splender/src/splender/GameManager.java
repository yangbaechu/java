package splender;

import java.awt.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.Vector;

public class GameManager extends JFrame{
	static boolean retry = false;
	cardManager cm = new cardManager();
	public GameManager() {
		super("Splendor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(5,5));
		//JPanel pnl1=new JPanel();

		JButton btn3=new JButton("중요도 3");
		contentPane.add(btn3);
		contentPane.setSize(1200,800);
		contentPane.setVisible(true);
		btn3.setText(((Vector<card>)cardManager.open.elementAt(0)).toString());
	}
	
	public static void main(String arvg[]) {
		new GameManager();
		User user []=new User[4];
		int i, cnt, order;
		System.out.println("플레이어의 숫자를 입력하여 주십시오.");
		Scanner scan=new Scanner(System.in);
		do
		{
			i=scan.nextInt();
			if(i<2)System.out.println("플레이어의 수가 너무 적습니다!\n다시 입력하여 주십시오.");
			if(i>4)System.out.println("플레이어의 수가 너무 많습니다!\n다시 입력하여 주십시오.");
		}while(i<2||i>4);
		for(int j=0; j<i; j++) {
			user[j] = new User(j);
		}
		boolean r1=true;
		System.out.println("스플렌더에 오신 것을 환영합니다!");
		System.out.println("스플렌더는 각각의 자원을 이용해서 카드를 구매해 점수를 얻어 승리하는 게임입니다.");
		System.out.println("카드는 해당 카드 제공하는 효과를 통해 다른 카드의 구매비용을 줄여줍니다.");
		System.out.println("자원은 서로 다른 자원 3개 또는 같은 자원 2개를 가져올 수 있습니다.");
		System.out.println();
		order=0;
		for(cnt=0; r1==true || cnt%i!=0; cnt++, order=cnt%i) {//사용자가 모두 턴을 진행하고 한명이라도 종료하겠다고 입력할때까지 진행
			if(r1==true)
			{
				r1 = check_retry(user[order], order+1);
				if(r1==false)
					System.out.println("사용자"+i+"의 턴까지 마치고 게임을 종료합니다.");
			}
			else
			{
				check_retry(user[order],order);
				System.out.println("사용자"+i+"의 턴까지 마치고 게임을 종료합니다.");
			}
			/* 승리 조건 달성시 */
			if(user[cnt%i].score >= 15) {
				System.out.println("축하합니다!" + user[order].score + "점으로  사용자 "+order+"의 승리입니다.");
				break;
			}
		}
		System.out.println("게임을 종료합니다.");
		scan.close();
	}
	
	/* 사용자의 턴을 진행하고 턴이 정상적으로 완료 될때까지 턴을 진행하는 메소드 */
	public static boolean check_retry(User user, int num) {
		boolean r;
		System.out.println("사용자 " + num + "의 턴입니다.");
		do {
			retry = false;
			r = user.turn();
		}while(retry);
		return r;
	}
}