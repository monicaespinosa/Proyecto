package proyecto;

public class Casilla {
    private int valor;
    private int fila;
    private int columna;
    private int caja;
    private boolean posibilidades[]= new boolean[10];
    
    //Bob
    public Casilla() {
        this.caja=0;
        this.fila=0;
        this.columna=0;
        this.valor=0;
        this.posibilidades[0]=false;
        for (int i = 1; i < 10; i++) {
            posibilidades[i]=true;
        }
    }
    
    //Getters & Setters
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
    public int getFila() {
        return fila;
    }
    public void setFila(int fila) {
        this.fila = fila;
    }
    public int getColumna() {
        return columna;
    }
    public void setColumna(int columna) {
        this.columna = columna;
    }
    public int getCaja() {
        return caja;
    }
    public void setCaja(int caja) {
        this.caja = caja;
    }
    public boolean[] getPosibilidades() {
        return posibilidades;
    }
    public void setPosibilidades(boolean[] posibilidades) {
        this.posibilidades = posibilidades;
    }
    
    //retorna el numero de posibilidades por casilla
    public int nPosibilidades(){
        if(this.valor!=0){
            return 0;
        }
        int res=0;
        for (int i = 1; i < 10; i++) {
            if(this.posibilidades[i]==true){
                res++;
            }
        }
        return res;
    }
    
    // descarta alguna posibilidad
    public void cambiarPosibilidades(int posicion){
        this.posibilidades[posicion]=false;
    }
    
    //Si la casilla solo tiene una posibilidad, retorna el valor que le corresponde
    public int unicaPosibilidad (){
        if(this.nPosibilidades()==1){
            for (int i = 1; i < 10; i++) {
                if(this.posibilidades[i]==true){
                    return i;
                }
            }
        }
        return 0;
    }
    
    //dado un valor, cambia todas las posibilidades excepto la del valor
    public void ponerUnicaPosibilidad(int numero){
        for (int i = 1; i < 10; i++) {
            this.posibilidades[i]=false;
        }
        this.posibilidades[numero]=true;
    }
    
    public void volverATodasLasPosibilidades(){
        for (int i = 1; i < 10; i++) {
            this.posibilidades[i]=true;
        }
    }
}
