import javax.swing.JOptionPane;


public class Judger {
//	private boolean end = false;
	private char winner = ' ';
	public void judge(char[][] status){
		
		//check if anyone wins
		if (status[0][0]==status[1][1] && status[1][1]==status[2][2] && status[0][0] != ' '){
			winner = status[0][0];
		}
		if (status[0][2]==status[1][1] && status[1][1]==status[2][0] && status[0][2] != ' '){
			winner = status[0][2];
		}
		
		for(int i=0;i<3;i++){
			if (status[i][0]==status[i][1] && status[i][1]==status[i][2] && status[i][0] != ' '){
				winner = status[i][0];
			}else if (status[0][i]==status[1][i] && status[1][i]==status[2][i] && status[0][i] != ' '){
				winner = status[0][i];
			}
		}
		
		//check if the board is full
		boolean noMove = true;
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				if (status[i][j] == ' '){
					noMove = false;
					break;
				}
			
			}	
			if (noMove == false)
				break;	
		}
		if (noMove == true&winner ==' '){
			winner = 't';
		}

//		
//		if(end == true){
//			annWinner();
//		}
	}
	public char getWinner(){
		return this.winner;
	}
	
	public void setWinner(char winner){
		this.winner = winner;
	}
}
