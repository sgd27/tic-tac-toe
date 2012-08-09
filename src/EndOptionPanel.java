import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class EndOptionPanel extends JFrame{
	public JButton rematchBtn;
	public JButton closeBtn;
	private JLabel resultLbl;
	
	private Font lblFont = new Font("Calibri", Font.BOLD, 50);
	private Font btnFont = new Font("Calibri", Font.PLAIN, 25);
	
	public EndOptionPanel (){
		
		rematchBtn = new JButton("Rematch");
		closeBtn = new JButton("Close");
		resultLbl = new JLabel("Game Result",SwingConstants.CENTER);

		
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		
		resultLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(resultLbl);
		add(Box.createRigidArea(new Dimension(0, 10)));
		
		rematchBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(rematchBtn);
		add(Box.createRigidArea(new Dimension(0, 10)));
		closeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(closeBtn);
		
		resultLbl.setFont(lblFont);
		rematchBtn.setFont(btnFont);
		closeBtn.setFont(btnFont);
		
		closeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
//		setVisible(true);
		
	}
	
	public void setResult(String result){
		resultLbl.setText(result);
	}
	public static void main(String[] args){
		EndOptionPanel eo = new EndOptionPanel();
		eo.setVisible(true);
	}
}
