import java.util.Random;


public class ComputerAI extends Contestant{
	private int row;
	private int column;
	
	public ComputerAI(){
		super.setName("AI");
	}
	
	public void chooseCell(char[][] status){
		Random r = new Random();
		int i;
		int j;
		if (status[1][1] == ' '){
			row = 1;
			column = 1;
		}else if (status[0][1] == status[0][2]&& status[0][1] != ' ' && status[0][0] == ' '){
			row = 0;
			column = 0;
		}else if(status[1][0] == status[2][0]&& status[1][0] != ' ' && status[0][0] == ' '){
			row = 0;
			column = 0;
		} else if(status[1][1] == status[2][2]&& status[1][1] != ' ' && status[0][0] == ' '){
			row = 0;
			column = 0;
		} else if(status[0][0] == status[0][2]&& status[0][0] != ' ' && status[0][1] == ' '){
			row = 0;
			column = 1;
		} else if(status[1][1] == status[2][1]&& status[1][1] != ' ' && status[0][1] == ' '){
			row = 0;
			column = 1;
		} else if(status[0][0] == status[0][1]&& status[0][0] != ' ' && status[0][2] == ' '){
			row = 0;
			column = 2;
		} else if(status[1][1] == status[2][0]&& status[1][1] != ' ' && status[0][2] == ' '){
			row = 0;
			column = 2;
		} else if(status[1][2] == status[2][2]&& status[1][2] != ' ' && status[0][2] == ' '){
			row = 0;
			column = 2;
		} else if(status[0][0] == status[2][0]&& status[0][0] != ' ' && status[1][0] == ' '){
			row = 1;
			column = 0;
		} else if(status[1][1] == status[1][2]&& status[1][1] != ' ' && status[1][0] == ' '){
			row = 1;
			column = 0;
		} else if(status[0][0] == status[2][2]&& status[0][0] != ' ' && status[1][1] == ' '){
			row = 1;
			column = 1;
		} else if(status[1][0] == status[1][2]&& status[1][0] != ' ' && status[1][1] == ' '){
			row = 1;
			column = 1;
		} else if(status[0][1] == status[2][1]&& status[0][1] != ' ' && status[1][1] == ' '){
			row = 1;
			column = 1;
		} else if(status[1][0] == status[1][1]&& status[1][0] != ' ' && status[1][2] == ' '){
			row = 1;
			column = 2;
		} else if(status[0][2] == status[2][2]&& status[0][2] != ' ' && status[1][2] == ' '){
			row = 1;
			column = 2;
		} else if(status[0][0] == status[1][0]&& status[0][0] != ' ' && status[2][0] == ' '){
			row = 2;
			column = 0;
		} else if(status[2][1] == status[2][2]&& status[2][1] != ' ' && status[2][0] == ' '){
			row = 2;
			column = 0;
		} else if(status[0][2] == status[1][1]&& status[0][2] != ' ' && status[2][0] == ' '){
			row = 2;
			column = 0;
		} else if(status[0][1] == status[1][1]&& status[0][1] != ' ' && status[2][1] == ' '){
			row = 2;
			column = 1;
		} else if(status[2][0] == status[2][2]&& status[2][0] != ' ' && status[2][1] == ' '){
			row = 2;
			column = 1;
		} else if(status[0][0] == status[1][1]&& status[0][0] != ' ' && status[2][2] == ' '){
			row = 2;
			column = 2;
		} else if(status[2][0] == status[2][1]&& status[2][0] != ' ' && status[2][2] == ' '){
			row = 2;
			column = 2;
		} else if(status[0][2] == status[1][2]&& status[0][2] != ' ' && status[2][2] == ' '){
			row = 2;
			column = 2;
		}else{
			do{
				i = r.nextInt(3);
				j = r.nextInt(3);
			}while(status[i][j] !=' ');
			
			row = i;
			column = j;
		}		
	}
	public void putMarker(Cell[][] cell, char[][] status){
		chooseCell(status);
		if (role == 'x'){
			cell[row][column].setIcon(Marker.X);
		}
		if (role == 'o'){
			cell[row][column].setIcon(Marker.O);
			
		}
		cell[row][column].setToken(role);
		cell[row][column].setEnabled(false);
		status[row][column] = role;
		
	}
}
