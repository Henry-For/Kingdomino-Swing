import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Casillero {
	
	public static final String TERRENO_REY = "rey";
	private static int nroFicha = 1;
	
	private String tipoTerreno;
	private int cantCoronas;
	private Posicion posicion;
	private String ruta;
	private BufferedImage imagen;
	
	public Casillero(){
		
	}
	
	public Casillero(String tipoTerreno, int cantCoronas, Posicion posicion) {
		this.tipoTerreno = tipoTerreno;
		this.cantCoronas = cantCoronas;
		this.posicion = posicion;
		this.ruta = "Casilleros/" + String.format("%02d",Casillero.nroFicha) + ".jpg";
		nroFicha++;
		cargarImagen();
	}
	
	public Casillero(String rutaRey) {
		this.cantCoronas = 0;
		this.tipoTerreno = TERRENO_REY;
		this.posicion = new Posicion(2,2);
		this.ruta = rutaRey;
		cargarImagen();
	}
	
	public void cargarImagen() {
		try {
			imagen = ImageIO.read(getClass().getResource(ruta));
		} catch (IOException e) {
			System.out.println("Error al leer de archivo la ruta: " + ruta);
			e.printStackTrace();
		}
	}

	public boolean sonConsecutivos(Casillero c) {
		return true;
	}
	
	public boolean sonTerrenosConsecutivos(Casillero c) {
		return tipoTerreno.equals(c.tipoTerreno) || c.tipoTerreno.equals(TERRENO_REY);
	}
	
	@Override
	public String toString() {
		return "ruta: " + this.ruta + " t:" + this.tipoTerreno;
	}
	
	public Posicion getPosicion() {
		return posicion;
	}
	
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public int getCantCoronas() {
		return this.cantCoronas;
	}
	
	public String getTipoTerreno()
	{
		return this.tipoTerreno;
	}

	public String getRuta() {
		return ruta;
	}

	public BufferedImage getImagen() {
		return imagen;
	}
	
	public void rotate(Double degrees) {
		// Calculate the new size of the image based on the angle of rotaion
		double radians = Math.toRadians(degrees);
		double sin = Math.abs(Math.sin(radians));
		double cos = Math.abs(Math.cos(radians));
		int newWidth = (int) Math.round(imagen.getWidth() * cos + imagen.getHeight() * sin);
		int newHeight = (int) Math.round(imagen.getWidth() * sin + imagen.getHeight() * cos);

		// Create a new image
		BufferedImage rotate = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = rotate.createGraphics();
		// Calculate the "anchor" point around which the image will be rotated
		int x = (newWidth - imagen.getWidth()) / 2;
		int y = (newHeight - imagen.getHeight()) / 2;
		// Transform the origin point around the anchor point
		AffineTransform at = new AffineTransform();
		at.setToRotation(radians, x + (imagen.getWidth() / 2), y + (imagen.getHeight() / 2));
		at.translate(x, y);
		g2d.setTransform(at);
		// Paint the originl image
		g2d.drawImage(imagen, 0, 0, null);
		g2d.dispose();
		imagen = rotate;
	}
}
