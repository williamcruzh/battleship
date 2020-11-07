package battleship;
import java.util.List;

public class MockShip extends Ship {

	
	public MockShip(int numTrue, boolean numF) { 
		
		for(int i = 0; i < numTrue;i++) {
			this.zone.add(true);
		}
		
		if (numF) {
			this.zone.add(false);
		}
		
	}
	
	
}
