import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Fila filaComum = new Fila(100);
        Fila filaVip = new Fila(100);
        String[] clientesComum = new String[100];
        long[] temposDeEntradaComum = new long[100];
        int tamanhoFilaComum = 0;

        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Fila.verificarEsperaComum(filaComum, filaVip, clientesComum, temposDeEntradaComum, tamanhoFilaComum);
            }
        }, 0, 20 * 60 * 1000);

         System.out.println("Deseja adicionar um novo cliente? (Sim/Não): ");
        String resposta = "null";
        

        while(resposta.equalsIgnoreCase("sim") || resposta.equalsIgnoreCase("não")){
            resposta = scanner.nextLine();
        }
        

        if (resposta.equalsIgnoreCase("Sim")) {
            System.out.println("Nome do cliente: ");
            String nomeCliente = scanner.nextLine();
            Fila.adicionarClienteComum(nomeCliente, clientesComum, temposDeEntradaComum, tamanhoFilaComum);
        }
    

         while (true) {
            // Verifique e mova clientes comuns que esperaram mais de 20 minutos para a fila VIP
            Fila.verificarEsperaComum(filaComum, filaVip, clientesComum, temposDeEntradaComum, tamanhoFilaComum);

            // Chame o próximo cliente com base na prioridade
            Fila.chamarCliente(filaComum, filaVip, clientesComum, temposDeEntradaComum, tamanhoFilaComum);

            // Simule algum tempo de espera antes de verificar novamente (em milissegundos)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        

       
    
}