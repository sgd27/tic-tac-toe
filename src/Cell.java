import javax.swing.JButton;


public class Cell extends JButton{
	private int row;
	private int column;
	private char token;
	public Cell(int row, int column){
		this.row = row;
		this.column = column;
	}
	
	public void setToken(char token){
		this.token = token;
		if (token == ' '){
			this.setEnabled(true);
		}else if (token == 'x'){
			this.setIcon(Marker.X);
			this.setEnabled(false);
		}else if (token == 'o'){
			this.setIcon(Marker.O);
			this.setEnabled(false);
		}
	}
	
	public char getToken(){
		return this.token;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getColumn(){
		return this.column;
	}
}

