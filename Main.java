import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);
        int opcao;

        ABB<Netflix> arvore = new ABB<>();
        Leitor leitor = new Leitor();
        InserirPrograma inserirPrograma = new InserirPrograma();

        do{
            System.out.println("Escolha uma opcao:");
            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    leitor.carregarArquivo(arvore);
                    break;
                case 3:
                    inserirPrograma.InserirNovoPrograma(arvore, sc);
                    break;
                case 0:
                    System.out.println("Encerrando o programa.");
                default:
                    System.out.println("Opção Inválida !");
                    break;
            } 
        } while (opcao != 0);
        
        sc.close();
    }
} 