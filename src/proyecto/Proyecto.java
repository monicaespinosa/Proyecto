package proyecto;

public class Proyecto {

    public static void main(String[] args) {
        Sudoku s= new Sudoku();
        
        
        /*
        s.agregarCasilla(9, 0, 2);
        s.agregarCasilla(8, 0, 4);
        s.agregarCasilla(5, 1, 1);
        s.agregarCasilla(4, 1, 2);
        s.agregarCasilla(9, 1, 3);
        s.agregarCasilla(7, 1, 8);
        s.agregarCasilla(6, 2, 3);
        s.agregarCasilla(4, 2, 8);
        s.agregarCasilla(7, 3, 1);
        s.agregarCasilla(1, 3, 2);
        s.agregarCasilla(9, 3, 7);
        s.agregarCasilla(8, 3, 8);
        s.agregarCasilla(6, 4, 2);
        s.agregarCasilla(3, 4, 5);
        s.agregarCasilla(1, 4, 7);
        s.agregarCasilla(4, 5, 0);
        s.agregarCasilla(8, 5, 1);
        s.agregarCasilla(5, 6, 0);
        s.agregarCasilla(3, 6, 8);
        s.agregarCasilla(1, 7, 5);
        s.agregarCasilla(8, 8, 0);
        s.agregarCasilla(7, 8, 2);
        s.agregarCasilla(4, 8, 5);
        s.agregarCasilla(1, 8, 6);
        */
               
        
        s.agregarCasilla(6, 0, 0);
        s.agregarCasilla(1, 0, 3);
        s.agregarCasilla(2, 0, 5);
        s.agregarCasilla(7, 0, 6);
        s.agregarCasilla(7, 1, 0);
        s.agregarCasilla(9, 1, 2);
        s.agregarCasilla(3, 1, 4);
        s.agregarCasilla(8, 1, 8);
        s.agregarCasilla(8, 2, 2);
        s.agregarCasilla(4, 2, 3);
        s.agregarCasilla(7, 4, 3);
        s.agregarCasilla(4, 4, 4);
        s.agregarCasilla(9, 4, 5);
        s.agregarCasilla(2, 4, 6);
        s.agregarCasilla(2, 5, 3);
        s.agregarCasilla(5, 5, 6);
        s.agregarCasilla(5, 6, 2);
        s.agregarCasilla(2, 6, 4);
        s.agregarCasilla(8, 6, 5);
        s.agregarCasilla(1, 6, 6);
        s.agregarCasilla(2, 7, 1);
        s.agregarCasilla(3, 7, 2);
        s.agregarCasilla(5, 7, 3);
        s.agregarCasilla(7, 7, 4);
        s.agregarCasilla(4, 7, 8);
        s.agregarCasilla(9, 8, 0);
        

        System.out.println("Sudoku Ingresado ");
        s.mostrarSudoku();
        s.calcularPosibilidades();
        s.mostrarPosiliblidades();
        s.mostrarUnicaPosibilidad();
        System.out.println("--------------------------------------");
        System.out.println("Sudoku Resuelto");
        s.resolverSudoku();
        s.mostrarSudoku();
        s.calcularPosibilidades();
        s.mostrarPosiliblidades();
        s.mostrarUnicaPosibilidad();
        System.out.println(s.resolverSudoku());
        
        
    
    }
    
}
