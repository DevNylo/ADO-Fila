import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Recepcionista recepcionista = new Recepcionista();

        while (true) {

            System.out.println("\n< - - - - - - - Restaurante Cocina Mexico - - - - - - >");
            System.out.println("1. Adicionar cliente");
            System.out.println("2. Chamar próximo cliente");
            System.out.println("3. Liberar mesa");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.print("Nome do cliente: ");
                    String nome = scanner.next();
                    System.out.print("O cliente é VIP? (SIM / NÃO): ");
                    String vip = scanner.next();
                    long chegada = System.currentTimeMillis();
                    recepcionista.addCliente(new Cliente(nome, vip, chegada));
                    break;
                case 2:
                    recepcionista.proximo();
                    break;
                case 3:
                    recepcionista.liberarMesa();
                    break;
                case 4:
                    System.out.println("Finalizado!");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}