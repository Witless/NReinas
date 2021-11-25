public class NReinasBT {

    private static boolean aceptable(int[] candidatos, int candidato, int nivel, int N){

        // No puede estar en la misma columna;
        if(candidatos[candidato] != -1){
            return false;
        }

        // El nivel/fila 0 siempre puede poner dónde quiera
        if(nivel == 0) {
            return true;
        }

        // Si el candidato es el último de la lista de candidatos (última columna) sólo hay que mirar el anterior a su
        // izquierda
        if(candidato == candidatos.length-1) {
            return candidatos[candidato - 1] != (nivel - 1);
        }

        // Si el candidato es el primero de la lista de candidatos (primera columna) sólo hay que mirar al anterior a su
        // derecha
        if(candidato == 0){
            return candidatos[candidato + 1] != (nivel - 1);
        }

        // Si la columna de la izquierda o de la derecha del nivel anterior coincide con (nivel actual - 1), entonces
        // estaría coincidiendo la misma diagonal, por lo que hay que descartar esta posible solución
        if(candidatos[candidato-1] == (nivel - 1) || candidatos[candidato+1] == (nivel-1)){
            return false;
        }

        // En cualquier otro caso, al no haber más restricciones, se puede colocar la reina
        return true;
    }

    private static void nReinasAux(int x, int[] candidatos, int N, Booleano booleano, Entero entero){
        if(x == N){
            booleano.setValor(true);
        }else{
            int c = 0;
            while (c < N && !booleano.getValor()){
                if(aceptable(candidatos, c, x, N)){
                    //Anotar
                    candidatos[c] = x;

                    //Siguiente nivel/fila
                    nReinasAux(x+1, candidatos, N, booleano, entero);

                    //Desanotar
                    if(!booleano.getValor()) {
                        entero.setValor(entero.getValor()+1);
                        candidatos[c] = -1;
                    }
                }
                c++;
            }

        }
    }


    public static boolean nReinas(int n){
        Booleano booleano = new Booleano(false);
        Entero entero = new Entero(0);
        int[] candidatos = new int[n];

        for(int i = 0; i<n; i++){
            candidatos[i] = -1;
        }


        nReinasAux(0, candidatos, n, booleano, entero);

        if(candidatos[0] != -1) {
            System.out.println("Resultados:");
            for (int i = 0; i < n; i++) {
                System.out.println("Columna: " + i + " \\ Fila: " + candidatos[i]);
            }
        }

        System.out.println("Stats:\nVeces que se ha hecho backtracking: "+entero.getValor());

        return booleano.getValor();

    }

    public static void main(String[] args) {
        boolean nReinas = nReinas(7);
        System.out.println(nReinas);
    }
}
