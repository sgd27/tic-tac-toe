
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;



public class GameStart implements Runnable {
	
	private static final int PVP_MODE = 1;
	private static final int PVE_MODE = 2;
	
	private NameInputPanel nameInputPanel;
	private ModeChoosePanel modeChoosePanel;
	
	private Database db;
	
	private boolean waiting = true;
	private int mode;
	private String playername;
	
	public GameStart(){

		nameInputPanel = new NameInputPanel();
		modeChoosePanel = new ModeChoosePanel();
		db = new Database();
		db.connect();
		
		nameInputPanel.getNameField().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playername = nameInputPanel.getNameField().getText().trim();
				if(playername.equals("AI")==true){
					JOptionPane.showMessageDialog(null, "Reserved player name for computer AI!");
				}
				if (playername.equals("")==false && playername.equals("AI")==false){
					db.getRecode(playername);
					modeChoosePanel.addName(playername);
					modeChoosePanel.addWins(db.getWins(playername));
					modeChoosePanel.addLoses(db.getLoses(playername));
					modeChoosePanel.addTies(db.getTies(playername));
					nameInputPanel.setVisible(false);
					modeChoosePanel.setVisible(true);
				}
			}
		});
		
		ActionListener modeClicked = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource()==modeChoosePanel.pvpBtn){
					mode = PVP_MODE;
				}else if (e.getSource()==modeChoosePanel.pveBtn){
					mode = PVE_MODE;
				}
				
				modeChoosePanel.setVisible(false);
				waiting = false;	//start game!
			}
		};
		modeChoosePanel.pveBtn.addActionListener(modeClicked);
		modeChoosePanel.pvpBtn.addActionListener(modeClicked);
		Thread thread = new Thread(this);
		thread.start();
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameStart();
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			waitForPlayerAction();
			if (mode == PVP_MODE){
				new GameController(playername);
				
			}else if (mode == PVE_MODE){
				new LocalGame(playername);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void waitForPlayerAction() throws InterruptedException { 
		while (waiting) {
			Thread.sleep(100); 
		}
		waiting = true; 
	}
}

