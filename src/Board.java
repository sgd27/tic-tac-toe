
import java.awt.*;
import javax.swing.*;


public class Board extends JFrame {
	
	private char status[][] = new char [3][3]; 
	
	private static int BOARD_WIDTH = 450;
	private static int BOARD_HEIGHT = 510;
	private JPanel playersPanel;
	private JLabel myMarker;
	private JLabel opponentMarker;
	private JButton playerNameBtn;
	private JButton opponentNameBtn;
	private JPanel grid;
	private Cell[][] cell;
	private Font font = new Font("Calibri", Font.BOLD, 30);
	private Font font1 = new Font("Calibri", Font.BOLD, 20);
	
	//constructor
	public Board(){				
		//set the board
//		super("Tic-Tac-Toe");
		cell = new Cell[3][3];
		myMarker = new JLabel("Waiting");
		opponentMarker = new JLabel("Waiting");
		playerNameBtn = new JButton("");
		opponentNameBtn = new JButton("");
		
		playersPanel = new JPanel(new GridLayout(1, 4, 10, 10));
		grid = new JPanel(new GridLayout(3, 3));
		
		setSize(BOARD_WIDTH,BOARD_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout(10,10));
		setLocationRelativeTo(null);
		
		
		playersPanel.add(playerNameBtn);
		playersPanel.add(myMarker);
		playersPanel.add(opponentNameBtn);
		playersPanel.add(opponentMarker);
		add(playersPanel, BorderLayout.NORTH);
		add(grid,BorderLayout.CENTER);	
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				grid.add(cell[i][j] = new Cell(i,j));
				cell[i][j].setToken(' ');
				status[i][j] = ' ';
			}
		}
		myMarker.setFont(font);
		opponentMarker.setFont(font);
		playerNameBtn.setFont(font1);
		opponentNameBtn.setFont(font1);
		setVisible(true);
		//add the cells
		

	}

	public void setMyMarker(String marker){
		myMarker.setText(marker);
	}
	
	public void setOpponentMarker(String marker){
		opponentMarker.setText(marker);
	}
	
	public void setMyName(String name){
		playerNameBtn.setText(name);
	}
	
	public void setOpponentName(String name){
		opponentNameBtn.setText(name);
	}
	
	public char[][] getStatus(){
		return this.status;
	}
	
	public Cell[][] getCell(){
		return this.cell;
	}
	
	public JButton getPlayerNameButton(){
		return this.playerNameBtn;
	}
	public JButton getOpponentNameButton(){
		return this.opponentNameBtn;
	}
	public static void main(String[] args){
		new Board();
	}
	
}

