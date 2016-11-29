public class Sudoku {
	
	/*
	 * Describe el tablero donde se juega Sudoku
	 */
	private  int[][] tablero;

	/*
	 * Determina cuales de las casillas son claves para el susario
	 */
	private boolean[][] claves;
	
	public Sudoku(){
		tablero = new int[9][9];
		claves = new boolean[9][9];
	}
	
	public int[][] getTablero() {
		return tablero;
	}
	
	public boolean[][] getClaves() {
		return claves;
	}

	public void setSudoku(int[][] n_tablero, boolean[][] n_claves)throws Exception{
		if(n_tablero.length != 9 || n_claves.length != 9)
			throw new Exception("El tamaño del tablero es incorrecto ");
		for(int i=0; i<9; i++)
			if(n_tablero.length != 9 || n_claves.length != 9)
				throw new Exception("El tamaño del tablero es incorrecto");
		tablero = n_tablero;
		claves = n_claves;
	}
	
	/**
	 * llena una celda del sudoku siguiendo sus reglas
	 * @param x coordenada x
	 * @param y coordenada y
	 * @param val valor a introducir, el 0 se interpreta como vacío
	 * @return retorna true si se pudo insertar, false de lo contrario
	 */
	public boolean setSquare(int x, int y, int val){
		if(claves[x][y] || val > 9 || val < 0)
			return false;
		if(checkX(x, val) || checkY(y,val) || check3x3(x, y, val))
			return false;
		tablero[x][y]= val;
		return true;
	}
	
	//verifica si el numero ya esta en la columna indicada
	public boolean checkX(int x_location, int val){
		if(val == 0)
			return false;
		for(int i=0; i<9; i++){
			if(val == tablero[x_location][i])
				return true;
		}
		return false;
	}
	
	//verifica si el numero ya esta en la fila indicada
	public boolean checkY(int y_location, int val){
		if(val == 0)
			return false;
		for(int i=0; i<9; i++){
			if(val == tablero[i][y_location])
				return true;
		}
		return false;
	}
	
	//verifica si el numero ya esta en la sub cuadricula de la celda indicada
	public boolean check3x3(int x, int y, int val){
		if(val == 0)
			return false;
		int x_init = (x/3)*3;
		int y_init = (y/3)*3;
		for(int i=x_init; i<x_init+3; i++)
			for(int j=y_init; j<y_init+3; j++)
				if(val == tablero[i][j])
					return true;
		return false;
	}
	
	//verifica si el jugador ya ganó
	public boolean checkWin(){
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++)
				for(int k=1; k<9; k++)
					if(!checkX(i, k) || !checkY(j,k) || !check3x3(i, j, k))
						return false;
		return true;
	}
}
