import java.util.Random;


public class Solver {
	
	private Sudoku sudoku;
	private boolean[][][] allowed;
	
	public Solver(Sudoku n_sudoku){
		sudoku = n_sudoku;
		allowed = new boolean[9][9][9];
		
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++)
				for(int k=0; k<9; k++)
					allowed[i][j][k] = true;
	}
	
	public Sudoku getSudoku(){
		return sudoku;
	}
	
	public boolean[][][] getAllowed(){
		return allowed;
	}
	
	public void setAllowed(boolean[][][] n_allowed){
		allowed = n_allowed;
	}
	
	public void setupSolver(){
		int [][] tablero = sudoku.getTablero();
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++)
				if(tablero[i][j] != 0)
					updateAllowance(i, j, tablero[i][j]);
	}
	
	// llena una casilla del tablero y actualiza las restricciones
	public void setSquare(int x, int y, int val){
		if(sudoku.setSquare(x, y, val))
			updateAllowance(x, y, val);
	}
	
	// actualiza los numeros posibles para cada casilla
	public void updateAllowance(int x, int y, int val){
		for(int i=0; i<9; i++){
			allowed[x][i][val]=false;
			allowed[i][y][val]=false;
		}
		int x_init = (x/3)*3;
		int y_init = (y/3)*3;
		for(int i=x_init; i<x_init+3; i++)
			for(int j=y_init; j<y_init+3; j++)
				allowed[i][j][val]=false;
		
		for(int i=0; i<9; i++)
			allowed[x][y][i]=false;
		allowed[x][y][val]=true;
	}
	
	// recorre todo el tablero y asigna el valor para los que solo tengan una posible alternativa
	public boolean solveTrivial(){
		int [][] tablero = sudoku.getTablero();
		boolean res = false;
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++){
				if(tablero[i][j]==0){
					int c = 0;
					int it = 0;
					for(int k=0; k<9; k++)
						if(allowed[i][j][k]){
							c++;
							it=k;
						}
					if(c==1){
						setSquare(i, j, it);
						res=true;
					}
				}
			}
		return res;
	}
	
	
	public boolean solveSimpleX(){
		boolean res = false;
		for(int i=0; i<9; i++){ //recorre las filas
			int count = 0;
			boolean[] vals = {false,false,false,false,false,false,false,false,false};
			for(int val=1; val<=9; val++) //recorre los valores
				if(!sudoku.checkX(i, val)){ //verifica si el valor no esta
					count++;
					vals[val]=true;
				}
			if(count == 1)
				for(int val=0; val<9; val++) // se ubica el valor
					if(vals[val])
						for(int j=0; j<9; j++)
							if(sudoku.getTablero()[i][j]==0){
								setSquare(i, j, val+1);
								res = true;
							}
		}
		return res;
	}
	
	public boolean solveSimpleY(){
		boolean res = false;
		for(int i=0; i<9; i++){ //recorre las columnas
			int count = 0;
			boolean[] vals = {false,false,false,false,false,false,false,false,false};
			for(int val=1; val<=9; val++) //recorre los valores
				if(!sudoku.checkY(i, val)){ //verifica si el valor no esta
					count++;
					vals[val]=true;
				}
			if(count == 1)
				for(int val=0; val<9; val++) // se ubica el valor
					if(vals[val])
						for(int j=0; j<9; j++)
							if(sudoku.getTablero()[j][i]==0){
								setSquare(j, i, val+1);
								res = true;
							}
		}
		return res;
	}
	
	public boolean solveSimple3x3(){
		boolean res = false;
		for(int x=0; x<9; x+=3)
			for(int y=0; y<9; y+=3){
				int count = 0;
				boolean[] vals = {false,false,false,false,false,false,false,false,false};
				for(int val=1; val<=9; val++)
					if(!sudoku.check3x3(x, y, val)){
						count++;
						vals[val] = true;
					}
				if(count == 1){
					for(int i=x; i<x+3; i++)
						for(int j=y; j<y+3; j++)
							if(sudoku.getTablero()[i][j]==0)
								for(int k=0; k<9; k++)
									if(vals[k]){
										setSquare(i, j, k);
										res = true;
									}
				}
			}
		return res;
	}
	
	public int[][] backtrack(){
		if(stuck())
			return null;
		for (int x = 0; x < 9; x++)
			for (int y = 0; y < 9; y++)
				if(sudoku.getTablero()[x][y]==0)
					for(int i=0; i<9; i++)
						if(allowed[x][y][i]){
							try{
								Sudoku sud = new Sudoku();
								sud.setSudoku(sudoku.getTablero(), sudoku.getClaves());
								Solver tmp = new Solver(sud);
								int [][] t = tmp.solve();
								if(t != null)
									return t;
							}catch(Exception e){
								
							}
						}
		return null;
	}
	
	public int[][] solve(){
		localSolve();
		if(stuck())
			return null;
		if(sudoku.checkWin())
			return sudoku.getTablero();
		return backtrack();
	}
	
	public boolean localSolve(){
		boolean res = false;
		boolean flag = true;
		while(flag){
			flag = solveTrivial();
			flag = solveSimpleX() ||flag;
			flag = solveSimpleY() ||flag;
			flag = solveSimple3x3() ||flag;
			res = flag;
		}
		return res;
	}
	
	public boolean stuck(){
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++)
				if(sudoku.getTablero()[i][j]==0){
					boolean c = false;
					for(int k=0; k<9; k++)
						c |= allowed[i][j][k];
					if(!c)
						return true;
				}
		return false;
	}
	
	//genera un rompecabezas 
	public void randomPuzzle(int n_clues){
		//borra el tablero
		if(n_clues < 17)
			return;
		
		try {
			int [][] tablero = new int[9][9];
			boolean [][] claves = new boolean[9][9];
			for(int i=0; i<9; i++)
				for(int j=0; j<9; j++){
					tablero[i][j]=0;
					claves[i][j]=false;
				}
			sudoku.setSudoku(tablero, claves);
			
			//generar "semilla"
			Random r = new Random();
			for(int i=1; i<=9; i++){// se coloca un valor
				int x = r.nextInt()%9;
				int y = r.nextInt()%9;
				sudoku.setSquare(x, y, i);
			}
			
			// se obtiene una solución para el tablero
			setupSolver();
			tablero = solve();
			sudoku.setSudoku(tablero, claves);
			int n_c = 81;
			
			while(n_c> n_clues){ // se retiran de forma aleatoria valores hasta quedar unicamente con las claves
				int x = r.nextInt()%9;
				int y = r.nextInt()%9;
				if( sudoku.setSquare(x, y, 0))
					n_c--;
			}
			
			for(int i=0; i<9; i++)
				for(int j=0; j<9; j++)
					claves[i][j] = sudoku.getTablero()[i][j]!=0;
			
			sudoku.setSudoku(tablero, claves);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
