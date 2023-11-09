import java.util.*;

public class Fila {

    private String vetorFila[];
    private int fim;

    public Fila(int maxSize) {
        vetorFila = new String[maxSize];
        fim = -1;
    }

    //Verifica se a Fila esta vazia
    public boolean isEmpty() {
        return (fim == -1);
    }

    //Verifica se a Fila esta cheia
    public boolean isFull() {
        return (fim == vetorFila.length - 1);
    }

    //Retorna o primeiro elemento da Fila
    public String peek(){
        if (!isEmpty())
            return vetorFila[0];
        else
            return "Fila vazia.";
    }

    //Insere elemento na Fila
    public void enqueue(String nome){
        if (!isFull()) {
            fim++;
            vetorFila[fim] = nome;
        }
    }

    //Remove elemento da Fila
    public String dequeue(){
        int cont;
        String n;

        if (!isEmpty()) {
            n = vetorFila[0];
            for (cont = 0; cont < fim; cont++) {
                vetorFila[cont] = vetorFila[cont + 1];
            }
            fim--;
            return n;
        } 
        else {
            return " ";
        }
    }

    // Método para verificar e mover clientes comuns que esperaram mais de 20 minutos para a fila VIP
    public static void verificarEsperaComum(Fila filaComum, Fila filaVip, String[] clientesComum, long[] temposDeEntradaComum, int tamanhoFilaComum) {
        long tempoAtual = System.currentTimeMillis();
        int index = 0;

        while (index < tamanhoFilaComum) {
            String clienteComum = clientesComum[index];
            long tempoInicioEspera = temposDeEntradaComum[index];

            if (tempoAtual - tempoInicioEspera > 20 * 60 * 1000) {
                // Move o cliente da fila comum para a fila VIP
                String clienteMovido = clientesComum[index];
                for (int i = index; i < tamanhoFilaComum - 1; i++) {
                    clientesComum[i] = clientesComum[i + 1];
                    temposDeEntradaComum[i] = temposDeEntradaComum[i + 1];
                }
                tamanhoFilaComum--;
                filaVip.enqueue(clienteMovido);
            } else {
                index++;
            }
        }
    }

    // Método para chamar o próximo cliente com base na prioridade
    public static void chamarCliente(Fila filaComum, Fila filaVip, String[] clientesComum, long[] temposDeEntradaComum, int tamanhoFilaComum) {
        if (!filaVip.isEmpty()) {
            String clienteVip = filaVip.dequeue();
            System.out.println("Cliente VIP chamado: " + clienteVip);
            // Realize as ações necessárias ao atender um cliente VIP
        } else if (tamanhoFilaComum > 0) {
            String clienteComum = clientesComum[0];
            for (int i = 0; i < tamanhoFilaComum - 1; i++) {
                clientesComum[i] = clientesComum[i + 1];
                temposDeEntradaComum[i] = temposDeEntradaComum[i + 1];
            }
            tamanhoFilaComum--;
            System.out.println("Cliente comum chamado: " + clienteComum);
            // Realize as ações necessárias ao atender um cliente comum
        }
    }

    public static void adicionarClienteComum(String nomeCliente, String[] clientesComum, long[] temposDeEntradaComum, int tamanhoFilaComum) {
        if (tamanhoFilaComum < 100) {
            clientesComum[tamanhoFilaComum] = nomeCliente;
            temposDeEntradaComum[tamanhoFilaComum] = System.currentTimeMillis();
            tamanhoFilaComum++;
            System.out.println("Cliente " + nomeCliente + " adicionado à fila comum.");
        } else {
            System.out.println("A fila comum está cheia. Não é possível adicionar mais clientes comuns.");
        }
    }
}