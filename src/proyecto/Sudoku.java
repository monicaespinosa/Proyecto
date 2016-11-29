package proyecto;

public class Sudoku {
    private Casilla casillas [][]= new Casilla [9][9];
    
    //Bob
    public Sudoku() {
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                this.casillas[fila][columna]=new Casilla();
                this.casillas[fila][columna].setCaja(columna/3+(3*(fila/3)));
                this.casillas[fila][columna].setColumna(columna);
                this.casillas[fila][columna].setFila(fila);                
            }
        }
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }
    public void setCasillas(Casilla[][] casillas) {
        this.casillas = casillas;
    }
        
    //Vacia un sudoku
    public void vaciarSudoku(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.agregarCasilla(0, i, j);
                this.casillas[i][j].volverATodasLasPosibilidades();
            }
        }
    }

    //Agrega un valor en una casilla vacía
    public void agregarCasilla (int valor, int fila, int columna){
        this.casillas[fila][columna].setValor(valor);
    }
    
    // calcula las posibilidades de cada casilla según el sudoku
    public void calcularPosibilidades(){
        //Método 1
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.casillas[i][j].volverATodasLasPosibilidades();
            }
        }
        for (int fila = 0; fila < 9; fila++) {
            for (int columna = 0; columna < 9; columna++) {
                for (int i = 0; i < 9; i++){
                    for (int j = 0; j < 9; j++) {
                        if(this.casillas[fila][columna].getValor()==0&& this.casillas[i][j].getValor()!=0){
                            if(this.casillas[i][j].getFila()==this.casillas[fila][columna].getFila()){
                                this.casillas[fila][columna].cambiarPosibilidades(this.casillas[i][j].getValor());
                            }
                            if(this.casillas[i][j].getColumna()==this.casillas[fila][columna].getColumna()){
                                this.casillas[fila][columna].cambiarPosibilidades(this.casillas[i][j].getValor());
                            }
                            if(this.casillas[i][j].getCaja()==this.casillas[fila][columna].getCaja()){
                                this.casillas[fila][columna].cambiarPosibilidades(this.casillas[i][j].getValor());
                            }
                        }
                    }
                }
            }
        }        
        
        //Metodo 2
        for (int numero = 1; numero < 10; numero++) {            
            for (int fila = 0; fila < 9; fila++) {
                int cont=0;
                int f=0;
                int c=0;
                for (int columna = 0; columna < 9; columna++) {
                    if (this.casillas[fila][columna].getPosibilidades()[numero]==true
                            && this.casillas[fila][columna].getValor()==0) {
                        cont++;
                        f=fila;
                        c=columna;
                    }
                }
                if (cont==1) {
                    this.casillas[f][c].ponerUnicaPosibilidad(numero);
                }
            }            
            for (int columna = 0; columna < 9; columna++) {
                int cont=0;
                int f=0;
                int c=0;
                for (int fila = 0; fila < 9; fila++) {
                    if (this.casillas[fila][columna].getPosibilidades()[numero]==true
                            && this.casillas[fila][columna].getValor()==0) {
                        cont++;
                        f=fila;
                        c=columna;
                    }
                }
                if (cont==1) {
                    this.casillas[f][c].ponerUnicaPosibilidad(numero);
                }
            }            
            for (int caja = 0; caja < 9; caja++) {
                int cont=0;
                int f=0;
                int c=0;
                for (int fila = 0; fila < 9; fila++) {
                    for (int columna = 0; columna < 9; columna++) {
                        if (this.casillas[fila][columna].getPosibilidades()[numero]==true &&
                                this.casillas[fila][columna].getCaja()==caja
                                && this.casillas[fila][columna].getValor()==0) {
                            cont++;
                            f=fila;
                            c=columna;
                        }
                    }
                }
                if (cont==1) {
                    this.casillas[f][c].ponerUnicaPosibilidad(numero);
                }
                
                //método 3
                if (cont>1) {
                    int filas[]= new int[cont];
                    int columnas[]=new int[cont];
                    int contador=0;
                    for (int fila = 0; fila < 9; fila++) {
                        for (int columna = 0; columna < 9; columna++) {
                            if (this.casillas[fila][columna].getPosibilidades()[numero]==true &&
                                    this.casillas[fila][columna].getCaja()==caja
                                 && this.casillas[fila][columna].getValor()==0) {
                                filas[contador]=fila;
                                columnas[contador]=columna;
                                contador++;
                            }
                        }
                    }
                    boolean resf=true;
                    for(int i=1;i<filas.length;i++){
                        resf=resf&&filas[i]==filas[i-1];
                    }
                    boolean resc=true;
                    for(int i=1;i<columnas.length;i++){
                        resc=resc&&columnas[i]==columnas[i-1];
                    }
                    if (resf) {
                        for (int columna = 0; columna < 9; columna++) {
                            if (this.casillas[filas[1]][columna].getPosibilidades()[numero]==true 
                                 && this.casillas[filas[1]][columna].getValor()==0
                                    && this.casillas[filas[1]][columna].getCaja()!=caja) {
                                this.casillas[filas[1]][columna].cambiarPosibilidades(numero);
                            }
                        }                        
                    }
                    if (resc) {
                        for (int fila = 0; fila < 9; fila++) {
                            if (this.casillas[fila][columnas[1]].getPosibilidades()[numero]==true 
                                 && this.casillas[fila][columnas[1]].getValor()==0
                                    &&this.casillas[fila][columnas[1]].getCaja()!=caja){
                                this.casillas[fila][columnas[1]].cambiarPosibilidades(numero);
                            }
                        }
                    }
                }                
            }
        }
    }
    
    // cuando hay una única posibilidada, la agraga al sudoku
    public void resolver(){
        this.calcularPosibilidades();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(this.casillas[i][j].getValor()==0){
                    this.agregarCasilla(this.casillas[i][j].unicaPosibilidad(), i, j);
                }
            }
        }
    }
    
    //retorna el numero de posibilidades unicas que hay en el sudoku
    public int posibilidadesUnicas(){
        int cont =0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.casillas[i][j].unicaPosibilidad()!=0) {
                    cont++;
                }
            }
        }
        return cont;
    }
    
    //retorna si una fila esta llena
    public boolean filaLlena(int posicion){
        int cont=0;
        for (int i = 0; i < 9; i++){
            cont+=this.casillas[posicion][i].getValor();
        }
        return cont==45;
    }
    
    //retorna si una columna esta llena
    public boolean columnaLlena(int posicion){
        int cont=0;
        for (int i = 0; i < 9; i++) {
            cont+=this.casillas[i][posicion].getValor();
        }
        return cont==45;
    }
    
    //retorna si una caja esta llena
    public boolean cajaLlena(int posicion){
        int cont=0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(this.casillas[i][j].getCaja()==posicion){
                    cont+=this.casillas[i][j].getValor();
                }
            }
        }
        return cont==45;
    }
    
    //retorna si un sudoku ha sido resuelto
    public boolean haySudoku(){
        boolean res=true;
        for (int i = 0; i < 9; i++) {
            res = res && this.cajaLlena(i);
            res = res && this.columnaLlena(i);
            res = res && this.filaLlena(i);
        }
        return res;
    }
    
    //Resuelve un sudoku completamente, retorna si resolvió el sudoku
    public boolean resolverSudoku(){
        do {
            this.resolver();
            this.calcularPosibilidades();
        } while (this.posibilidadesUnicas()!=0);
        
        this.calcularPosibilidades();
        if (!this.haySudoku()) {
            int respaldo [][]= new int[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    respaldo[i][j]=this.casillas[i][j].getValor();
                }
            } 
            for (int i = 0; i < 9; i++) {
                boolean contador=false;
                for (int j = 0; j < 9; j++) {
                    if (this.casillas[i][j].getValor()==0 && this.casillas[i][j].nPosibilidades()==2) {
                        for (int k = 9; k > 0; k--) {
                            if (this.casillas[i][j].getPosibilidades()[k]==true) {
                                this.agregarCasilla(k, i, j);
                                this.calcularPosibilidades();
                                contador=true;
                                break;
                            }
                        }
                    }
                    if (contador) {
                        break;
                    }
                }
                if (contador) {
                    break;
                }
            }  
            
            
            do {
                this.resolver();
                this.calcularPosibilidades();
            } while (this.posibilidadesUnicas()!=0);
            this.calcularPosibilidades();
            
            
            if (!this.haySudoku()) {
                this.vaciarSudoku();
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        this.agregarCasilla(respaldo[i][j], i, j);
                        this.calcularPosibilidades();
                    }
                }
                for (int i = 0; i < 9; i++) {
                    boolean contador=false;
                    for (int j = 0; j < 9; j++) {
                        if (this.casillas[i][j].getValor()==0 && this.casillas[i][j].nPosibilidades()==2){
                            for (int k = 0; k < 10; k++) {
                                if (this.casillas[i][j].getPosibilidades()[k]==true) {
                                    this.agregarCasilla(k, i, j);
                                    contador=true;
                                    break;
                                }
                            }
                        }
                        if (contador) {
                            break;
                        }
                    }
                    if (contador) {
                        break;
                    }
                }
                do {
                    this.resolver();
                    this.calcularPosibilidades();
                } while (this.posibilidadesUnicas()!=0);
        	this.calcularPosibilidades();
                if(!haySudoku()){
                    this.vaciarSudoku();
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            this.agregarCasilla(respaldo[i][j], i, j);
                            this.calcularPosibilidades();
                        }
                    }                    
                    return false;
                }
            }
        }
        return true;
    }
    
    //Resuelve el sudoku todas las veces posibles
    public void resolverSudokuCompletamente(){
        do {
            this.resolverSudoku();
        } while (this.puedeSeguirResolviendo());
        
    }
    
    //Retorna cuantas casillas tienen posibilidades 1 o 2
    public boolean puedeSeguirResolviendo(){
        int cont=0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.casillas[i][j].nPosibilidades()==1||this.casillas[i][j].nPosibilidades()==2) {
                    cont++;
                }
            }
        }
        if (cont==0) {
            return false;
        }else{
            return true;
        }
    }
    
    //Retorna si en una fila no hay numeros repetidos
    public boolean filaLlenadaCorrectamente(int fila){
        for (int numero = 1; numero < 10; numero++) {
            int cont=0;
            for (int columna = 0; columna < 9; columna++) {
                if (this.casillas[fila][columna].getValor()==numero) {
                    cont++;
                }
            }
            if (cont>1) {
                return false;
            }
        }
        return true;
    }
    
    //Retorna si en una columna no hay numeros repetidos
    public boolean columnaLlenadaCorrectamente(int columna){
        for (int numero = 1; numero < 10; numero++) {
            int cont=0;
            for (int fila = 0; fila < 9; fila++) {
                if (this.casillas[fila][columna].getValor()==numero) {
                    cont++;
                }
            }
            if (cont>1) {
                return false;
            }
        }
        return true;
    }
    
    //Retorna si en una caja no hay numeros repetidos
    public boolean cajaLlenadaCorrectamente(int caja){
        for (int numero = 1; numero < 10; numero++) {
            int cont=0;
            for (int fila = 0; fila < 9; fila++) {
                for (int columna = 0; columna < 9; columna++) {
                    if (this.casillas[fila][columna].getCaja()==caja&&this.casillas[fila][columna].getValor()==numero) {
                        cont++;
                    }
                }
            }
            if (cont>1) {
                return false;
            }
        }
        return true;
    }
    
    //Retorna si en el sudoku no hay numeros repetidos
    public boolean sudokuLlenadoCorrectamente(){
        for (int i = 0; i < 9; i++) {
            if (!this.filaLlenadaCorrectamente(i)) {
                return false;
            }
            if (!this.columnaLlenadaCorrectamente(i)) {
                return false;
            }
            if (!this.cajaLlenadaCorrectamente(i)) {
                return false;
            }
        }
        return true;
    }
     
    //MOSTRAR
    public void mostrarPosiciones (){
        System.out.println("Posiciones");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print("["+this.casillas[i][j].getFila()+"]"+
                        "["+this.casillas[i][j].getColumna()+"]["+this.casillas[i][j].getCaja()+"] ");
            }
            System.out.println("");
        }
    }
    public void mostrarSudoku(){
        System.out.println("Sudoku");
        for (int i = 0; i < 9; i++) {
            if (i%3==0) {
                System.out.println("---------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j%3==0) {
                    System.out.print("|");
                }
                if(this.casillas[i][j].getValor()==0){
                    System.out.print("  ");
                }else{
                    System.out.print(this.casillas[i][j].getValor()+" ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }    
    public void mostrarPosiliblidades(){
        System.out.println("posibilidades");
        for (int i = 0; i < 9; i++) {
            if (i%3==0) {
                System.out.println("--------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j%3==0) {
                    System.out.print("|");
                }
                if (this.casillas[i][j].nPosibilidades()==0) {
                    System.out.print("  ");
                }else{
                    System.out.print(this.casillas[i][j].nPosibilidades()+" ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }    
    public void mostrarUnicaPosibilidad(){
        System.out.println("Unica posibilidad");
        for (int i = 0; i < 9; i++) {
            if (i%3==0) {
                System.out.println("--------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j%3==0) {
                    System.out.print("|");
                }
                if (this.casillas[i][j].unicaPosibilidad()==0) {
                    System.out.print("  ");
                }else{
                    System.out.print(this.casillas[i][j].unicaPosibilidad()+" ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }  
}