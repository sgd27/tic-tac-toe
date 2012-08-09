
public class Player extends Contestant {
	public void putMarker(Cell cell){
		if (role == 'x'){
			cell.setIcon(Marker.X);
		}
		if (role == 'o'){
			cell.setIcon(Marker.O);
			
		}
		cell.setToken(role);
		cell.setEnabled(false);
	}
	
}
