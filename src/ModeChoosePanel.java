import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class ModeChoosePanel extends JFrame{
	private JLabel title;
	private JLabel subtitle;
	private JLabel playerLbl;
	private JLabel winLbl;
	private JLabel loseLbl;
	private JLabel tieLbl;
	private JLabel modeLbl;
	private JPanel modePnl;
	public JButton pvpBtn;
	public JButton pveBtn;
	
	private Font titleFont = new Font("Calibri", Font.BOLD, 50);
	private Font tipFont = new Font("Calibri", Font.BOLD, 25);
	
	public ModeChoosePanel (){
		title = new JLabel("Welcome!");
		subtitle = new JLabel("Player Info");
		playerLbl = new JLabel("Player Name:");
		winLbl = new JLabel("Win Games:");
		loseLbl = new JLabel("Lose Games:");
		tieLbl = new JLabel("Tie Games:");
		modeLbl = new JLabel("Game Mode:",SwingConstants.CENTER);
		pveBtn = new JButton("Player VS Com");
		pvpBtn = new JButton("Player VS Player");
		modePnl = new JPanel(new GridLayout(1,3,10,10));
		
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		
		modePnl.add(modeLbl);
		modePnl.add(pveBtn);
		modePnl.add(pvpBtn);
		
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createRigidArea(new Dimension(0, 5)));
		add(title);
		add(Box.createRigidArea(new Dimension(0, 10)));
		subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(subtitle);
		add(Box.createRigidArea(new Dimension(0, 10)));
		playerLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(playerLbl);
		add(Box.createRigidArea(new Dimension(0, 10)));
		winLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(winLbl);
		add(Box.createRigidArea(new Dimension(0, 10)));
		loseLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(loseLbl);
		add(Box.createRigidArea(new Dimension(0, 10)));
		tieLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(tieLbl);
		add(Box.createRigidArea(new Dimension(0, 10)));
		modePnl.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(modePnl);
		
		title.setFont(titleFont);
		modeLbl.setFont(tipFont);
//		setVisible(true);
	}
	
	public void addName( String name){
		playerLbl.setText(playerLbl.getText()+ " " + name);
	}
	
	public void addWins( String num){
		winLbl.setText(winLbl.getText() + " "+ num);
	}
	
	public void addLoses( String num){
		loseLbl.setText(loseLbl.getText() + " "+ num);
	}
	
	public void addTies( String num){
		tieLbl.setText(tieLbl.getText() + " " + num);
	}
	
	public static void main(String[] args){
		ModeChoosePanel mc = new ModeChoosePanel();
		mc.addWins("4");
	}
}
