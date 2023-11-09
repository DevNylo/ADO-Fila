import java.util.LinkedList;
import java.util.Queue;

class Recepcionista {
    Queue<Cliente> filaVip = new LinkedList<>();
    Queue<Cliente> filaComun = new LinkedList<>();

    public void addCliente(Cliente cliente) {
        if (cliente.vip.equalsIgnoreCase("Sim")) {
            filaVip.offer(cliente);
            System.out.println("Cliente VIP " + cliente.nome + " adicionado à lista VIP.");
        } else {
            filaComun.offer(cliente);
            System.out.println("Cliente comum " + cliente.nome + " adicionado à lista comum.");
        }
    }

    public void proximo() {
        if (!filaVip.isEmpty()) {
            Cliente clienteVip = filaVip.poll();
            System.out.println("Cliente VIP " + clienteVip.nome + " chamado para a mesa.");
        } else if (!filaComun.isEmpty()) {
            Cliente clienteComum = filaComun.poll();
            System.out.println("Cliente comum " + clienteComum.nome + " chamado para a mesa.");
        } else {
            System.out.println("Não há clientes na fila.");
        }
    }

    public void liberarMesa() {
        // Liberar mesa após 20 minutos.
        long tempoAtual = System.currentTimeMillis();
        while (!filaComun.isEmpty() && tempoAtual - filaComun.peek().chegada > 20 * 60 * 1000) {
            Cliente clienteComum = filaComun.poll();
            System.out.println("Cliente comum " + clienteComum.nome + " esperou mais de 20 minutos e foi chamado para a mesa.");
        }
        // Se a lista VIP não estiver vazia.
        if (!filaVip.isEmpty()) {
            Cliente clienteVip = filaVip.poll();
            System.out.println("Cliente VIP " + clienteVip.nome + " chamado para a mesa.");
        }
    }
}