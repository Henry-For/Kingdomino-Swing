public class Tablero {

	private Casillero[][] tablero = new Casillero[5][5];
	private int[][] matrizDePuntos = new int[5][5];
	private int auxCoronas = 0; // aux;

	public Tablero() {
		//tablero[2][2] = new Casillero(Casillero.TERRENO_REY, 0, new Posicion(2, 2));
		//tablero[2][2] = new Casillero("Fichas/castillo_rojo.jpg");
	}
	
	Tablero(Casillero[][] tablero) {
		this.tablero = tablero;
	}

	public boolean posicionarFicha(Ficha f, Posicion posCasillero1, Posicion posCasillero2) {
		if (tieneTerrenoCorrelativo(f, posCasillero1, posCasillero2)) {
			f.getCasilleros()[0].setPosicion(posCasillero1);
			f.getCasilleros()[1].setPosicion(posCasillero2);
			tablero[posCasillero1.getX()][posCasillero1.getY()] = f.getCasilleros()[0];
			tablero[posCasillero2.getX()][posCasillero2.getY()] = f.getCasilleros()[1];
			return true;
		}

		System.out.println("Posicion(es) ocupada(s) o tipos de terreno no posibles de conectar.");
		return false;
	}

	public boolean tieneTerrenoCorrelativo(Ficha f, Posicion posCasillero1, Posicion posCasillero2) {

		if(posCasillero1.getX() == posCasillero2.getX()) {

			int x1 = posCasillero1.getX();
			int y1 = posCasillero1.getY();
			int x2 = posCasillero2.getX();
			int y2 = posCasillero2.getY();

			if (x1 - 1 >= 0 && tablero[x1 - 1][y1] != null && f.getCasilleros()[0].sonTerrenosConsecutivos(tablero[x1 - 1][y1]))
				return true;
			if (x1 + 1 < 5 && tablero[x1 + 1][y1] != null && f.getCasilleros()[0].sonTerrenosConsecutivos(tablero[x1 + 1][y1]))
				return true;
			if (y1 - 1 >= 0 && tablero[x1][y1 - 1] != null && f.getCasilleros()[0].sonTerrenosConsecutivos(tablero[x1][y1 - 1]))
				return true;
			if (x2 - 1 >= 0 && tablero[x2 - 1][y2] != null && f.getCasilleros()[1].sonTerrenosConsecutivos(tablero[x2 - 1][y2]))
				return true;
			if (x2 + 1 < 5 && tablero[x2 + 1][y2] != null && f.getCasilleros()[1].sonTerrenosConsecutivos(tablero[x2 + 1][y2]))
				return true;
			if (y2 + 1 < 5 && tablero[x2][y2 + 1] != null && f.getCasilleros()[1].sonTerrenosConsecutivos(tablero[x2][y2 + 1]))
				return true;
			return false;
		} else {
			
			int x1 = posCasillero1.getX();
			int y1 = posCasillero1.getY();
			int x2 = posCasillero2.getX();
			int y2 = posCasillero2.getY();

			if (x1 - 1 >= 0 && tablero[x1 - 1][y1] != null && f.getCasilleros()[0].sonTerrenosConsecutivos(tablero[x1 - 1][y1]))
				return true;
			if (y1 - 1 >= 0 && tablero[x1][y1 - 1] != null && f.getCasilleros()[0].sonTerrenosConsecutivos(tablero[x1][y1 - 1]))
				return true;
			if (y1 + 1 < 5 && tablero[x1][y1 + 1] != null && f.getCasilleros()[0].sonTerrenosConsecutivos(tablero[x1][y1 + 1]))
				return true;
			if (x2 + 1 < 5 && tablero[x2 + 1][y2] != null && f.getCasilleros()[1].sonTerrenosConsecutivos(tablero[x2 + 1][y2]))
				return true;
			if (y2 - 1 >= 0 && tablero[x2][y2 - 1] != null && f.getCasilleros()[1].sonTerrenosConsecutivos(tablero[x2][y2 - 1]))
				return true;
			if (y2 + 1 < 5 && tablero[x2][y2 + 1] != null && f.getCasilleros()[1].sonTerrenosConsecutivos(tablero[x2][y2 + 1]))
				return true;
			return false;
			
		}
	}
	
	public void agregarCastillo(String ruta) {
		tablero[2][2] = new Casillero(ruta);
	}
	
	public Casillero getCastillo() {
		return tablero[2][2];
	}

	public void generarMatrizDePuntos() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (tablero[i][j] != null) {
					switch (tablero[i][j].getTipoTerreno()) {
					case "trigo":
						matrizDePuntos[i][j] = TipoTerreno.trigo;// 1
						break;
					case "agua":
						matrizDePuntos[i][j] = TipoTerreno.agua;// 2
						break;
					case "mina":
						matrizDePuntos[i][j] = TipoTerreno.mina;// 3
						break;
					case "pasto":
						matrizDePuntos[i][j] = TipoTerreno.pasto;// 4
						break;
					case "bosque":
						matrizDePuntos[i][j] = TipoTerreno.bosque;// 5
						break;
					case "tierra":
						matrizDePuntos[i][j] = TipoTerreno.tierra;//6
						break;
					default:
						matrizDePuntos[i][j] = 0;
					}
				}

			}
		}
	}

	public int calcularPuntaje() {
		generarMatrizDePuntos();
		int puntosTrigo = 0;// 1
		int puntosAgua = 0; // 2
		int puntosMina = 0;// 3
		int puntosPasto = 0;// 4
		int puntosBosque = 0;// 5
		int puntosTierra = 0;//6
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				switch (matrizDePuntos[i][j]) {
				case TipoTerreno.trigo:
					puntosTrigo += contarPuntosR(i, j, TipoTerreno.trigo) * auxCoronas;
					break;
				case TipoTerreno.agua:
					puntosAgua += contarPuntosR(i, j, TipoTerreno.agua) * auxCoronas;
					break;
				case TipoTerreno.mina:
					puntosMina += contarPuntosR(i, j, TipoTerreno.mina) * auxCoronas;
					break;
				case TipoTerreno.pasto:
					puntosPasto += contarPuntosR(i, j, TipoTerreno.pasto) * auxCoronas;
					break;
				case TipoTerreno.bosque:
					puntosBosque += contarPuntosR(i, j, TipoTerreno.bosque) * auxCoronas;
					break;
				case TipoTerreno.tierra:
					puntosTierra += contarPuntosR(i, j, TipoTerreno.tierra) * auxCoronas;
					break;
				}
				auxCoronas = 0;
			}
		}
		return puntosTrigo + puntosAgua + puntosMina + puntosPasto + puntosBosque + puntosTierra;
	}

	public int contarPuntosR(int i, int j, int t) {
		if (i >= 5 || i < 0 || j >= 5 || j < 0 || matrizDePuntos[i][j] != t) {
			return 0;
		}
		matrizDePuntos[i][j] = 0;
		auxCoronas += tablero[i][j].getCantCoronas();
		return (1 + contarPuntosR(i, j + 1, t) + contarPuntosR(i, j - 1, t) + contarPuntosR(i - 1, j, t)
				+ contarPuntosR(i + 1, j, t));
	}

}