import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;


public class GameController implements ActionListener, Runnable{
	private Board board;
	private EndOptionPanel endOptionPanel;
	private Player player;
	private Judger judger;
	private Database db;
	ServerSocket server;
	private DataInputStream datain;
	private DataOutputStream dataout;
	private int rowSelected;
	private int columnSelected;
	private boolean isServer = false; //whether to start as server
	private boolean myTurn = false;
	private boolean waiting = true;
	private char otherplayer;
	private char winner=' ';
	

	public GameController(String name){

		board = new Board();
		endOptionPanel = new EndOptionPanel();
		player = new Player();	
		db = new Database();
		db.connect();
		player.setName(name);
		board.setMyName(name);
		
		for (int i=0;i<3;i++){   //add listener to cells
			for (int j=0;j<3;j++){
				board.getCell()[i][j].addActionListener(this);
			}
		}
		
		board.getPlayerNameButton().addActionListener( new ActionListener() { //click for player info
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(board.getPlayerNameButton().getText().equals("")==false){
					PlayerInfoPanel pi = new PlayerInfoPanel();
					pi.addName(player.getName());
					pi.addWins(db.getWins(player.getName()));
					pi.addLoses(db.getLoses(player.getName()));
					pi.addTies(db.getTies(player.getName()));
				}
			}
		});
		
		endOptionPanel.rematchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				waiting = false;
				endOptionPanel.setVisible(false);
			}
		});
		
		connectToServer();// try to start as client
		if(isServer){	//failed to start as client, start as server
			connectToClient();
			judger = new Judger();
		}

		Thread thread = new Thread(this);
		thread.start();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		int time = 0;
		if (isServer){
			while (true){
				try {
					if (time == 0){
						
						dataout.writeInt(player.getName().length());
						dataout.writeChars(player.getName());
						String opponentname= "";
						int length =datain.readInt();
						for (int i=0;i<length;i++){
							opponentname = opponentname+ datain.readChar();
						}
						System.out.print(opponentname);	
						board.setOpponentName(opponentname);
					}
					if (time != 0) {				//rematch
						waitForPlayerAction();
						gameReset();
					}
					System.out.println("start to connect");
					playerChoose();
					System.out.println(player.getRole());
					dataout.writeChar(otherplayer);
					
					board.setMyMarker(player.getRole()+"");
					board.setOpponentMarker(otherplayer+"");
					
					while (true){
						if (player.getRole() == 'x'){
							waitForPlayerAction();
							sendMove(rowSelected, columnSelected);
							sendWinner();
							if (judger.getWinner()!=' ')
								break;
							recieveMove();
							sendWinner();
							if (judger.getWinner()!=' ')
								break;
						}else if (player.getRole() == 'o'){
							recieveMove();
							sendWinner();
							if (judger.getWinner()!=' ')
								break;
							waitForPlayerAction();
							sendMove(rowSelected, columnSelected);
							sendWinner();
							if (judger.getWinner()!=' ')
								break;
						}
					}
					if (judger.getWinner() == player.getRole()){
						endOptionPanel.setResult("You win!");
						db.updateWins(player.getName());
					}
					else if (judger.getWinner() == 't'){
						endOptionPanel.setResult("It's a tie!");
						db.updateTies(player.getName());
					}else {
						endOptionPanel.setResult("You lose!");
						db.updateLoses(player.getName());
						}
					waiting = true;
					myTurn = false;
					time++;
					endOptionPanel.setVisible(true);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Connection Lost, wait for another player!");
					gameReset();
					board.getOpponentNameButton().setText("");
					time = 0;
					waintingforClient();
				} 
			}
		}else if (!isServer){
			while (true){
				
				try {
					if (time == 0){
						String opponentname= "";
						int length =datain.readInt();
						for (int i=0;i<length;i++){
							opponentname = opponentname+ datain.readChar();
						}
						System.out.print(opponentname);
						board.setOpponentName(opponentname);
						dataout.writeInt(player.getName().length());
						dataout.writeChars(player.getName());
					
						
					}
					if (time != 0){				//rematch
						waitForPlayerAction();
						gameReset();
					}
					char playerChoose = datain.readChar();
					System.out.println(playerChoose);
		
					if (playerChoose == 'x'){
						player.setRole('x');
						otherplayer = 'o';
						myTurn = true;
					}
					if (playerChoose == 'o'){
						player.setRole('o');
						otherplayer = 'x';
						myTurn = false;
					}
					board.setMyMarker(player.getRole()+"");
					board.setOpponentMarker(otherplayer+"");
					while (true){
						if (player.getRole() == 'x'){
							waitForPlayerAction();
							sendMove(rowSelected, columnSelected);
							recieveWinner();
							if (winner!=' ')
								break;
							recieveMove();
							recieveWinner();
							if (winner!=' ')
								break;
						}else if (player.getRole() == 'o'){
							recieveMove();
							recieveWinner();
							if (winner!=' ')
								break;
							waitForPlayerAction();
							sendMove(rowSelected, columnSelected);
							recieveWinner();
							if (winner!=' ')
								break;
						}
					}
					if (winner == player.getRole()){
						endOptionPanel.setResult("You win!");
						db.updateWins(player.getName());
					}
					else if (winner == 't'){
						endOptionPanel.setResult("It's a tie!");
						db.updateTies(player.getName());
					}else {
						endOptionPanel.setResult("You lose!");
						db.updateLoses(player.getName());
					}
					waiting = true;
					myTurn = false;
					time++;
					endOptionPanel.setVisible(true);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Connection Lost!");
					System.exit(2);
				}
			}	
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (myTurn == true){
			Cell cellClicked = (Cell) e.getSource();
			player.putMarker(cellClicked);
			rowSelected = cellClicked.getRow();
			columnSelected = cellClicked.getColumn();
			myTurn = false;
//			if (isServer){
//				judger.judge(board.getStatus());
//			}
			board.getStatus()[rowSelected][columnSelected] = cellClicked.getToken();
			System.out.println("cell"+ "("+cellClicked.getRow()+","+cellClicked.getColumn()+")"+ "clicked");
			waiting = false;
		}
	}
	
	public void gameReset(){
		board.setMyMarker("Waiting");
		board.setOpponentMarker("Waiting");
		winner = ' ';
		if (isServer){
			judger.setWinner(' ');
		}
		
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				board.getCell()[i][j].setToken(' ');
				board.getCell()[i][j].setIcon(null);
				board.getCell()[i][j].setEnabled(true);
				board.getStatus()[i][j] = ' ';
			}
		}
	}
	private void sendMove(int row, int column) throws IOException{
		dataout.writeInt(row);
		dataout.writeInt(column);
	}
	
	private void recieveMove() throws IOException{
		int row = datain.readInt();
		int column = datain.readInt();
		board.getCell()[row][column].setToken(otherplayer);
		board.getStatus()[row][column]= otherplayer;
		myTurn = true;
		if(isServer){
			judger.judge(board.getStatus());
		}
	}
	
	private void sendWinner() throws IOException{
		judger.judge(board.getStatus());
		dataout.writeChar(judger.getWinner());
	}
	
	private void recieveWinner() throws IOException{
		winner = datain.readChar();
		System.out.println("winner: " + winner);
	}
	
	private void waitForPlayerAction() throws InterruptedException { 
		while (waiting) {
			Thread.sleep(100); 
		}
		waiting = true; 
	}
	
	private void playerChoose(){   
		double e = Math.random();
		
		if (e<0.5){
			player.setRole('x');
			myTurn = true;
			otherplayer = 'o';
		}else {
			player.setRole('o');
			myTurn = false;
			otherplayer = 'x';
		}
		System.out.println(e);
	}
	
	private void connectToClient(){
		try {
			server = new ServerSocket(6666);
			Socket socket = server.accept();
			datain = new DataInputStream(socket.getInputStream());
			dataout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Socket is in use!");
			System.exit(1);		
		}
	}
	
	private void connectToServer(){
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(),6666);
			datain = new DataInputStream(socket.getInputStream());
			dataout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.print(e);
			System.out.println("try to start as server");
			isServer = true;
		}
	}
	
	public void waintingforClient(){
		
		try {
			Socket socket = server.accept();
			datain = new DataInputStream(socket.getInputStream());
			dataout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Board getBoard(){
		return this.board;
	}
	public Player getPlayer(){
		return this.player;
	}
	
	public static void main(String arg[]){
		new GameController("test1");
	}
}
