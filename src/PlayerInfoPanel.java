import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class PlayerInfoPanel extends JFrame{
	private JLabel subtitle;
	private JLabel playerLbl;
	private JLabel winLbl;
	private JLabel loseLbl;
	private JLabel tieLbl;

	public PlayerInfoPanel (){
		subtitle = new JLabel("Player Info");
		playerLbl = new JLabel("Player Name:");
		winLbl = new JLabel("Win Games:");
		loseLbl = new JLabel("Lose Games:");
		tieLbl = new JLabel("Tie Games:");

		
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		setSize(200, 170);
		setResizable(false);
		setLocationRelativeTo(null);
		
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
	

		setVisible(true);
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
		PlayerInfoPanel mc = new PlayerInfoPanel();
		mc.addName("test");
		mc.addWins("4");
		mc.addLoses("5");
		mc.addTies("6");
	}
}
