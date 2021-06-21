public class Posicion {
	
	private int x;
	private int y;

	public Posicion()
	{
	}
	
	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean sonConsecutivas(Posicion p) {
		return true;
	}
	
	@Override
	public String toString() {
		return " X : "+ x + " Y : " + y ;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
