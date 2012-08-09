

public class Contestant  {
	private String name;
	protected char role;
	
	public void setRole (char role){
		this.role = role;
	}
	
	public char getRole (){
		return this.role;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
}
