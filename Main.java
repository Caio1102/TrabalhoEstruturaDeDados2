import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    
        ABB<Netflix> arvore = new ABB<>();
        Leitor leitor = new Leitor();


        Scanner sc = new Scanner(System.in);

        System.out.println("Escolha uma opcao:");
        int opcao = sc.nextInt(); 
        sc.nextLine(); // limpar o buffer, sem BO

        switch (opcao) {
            case 1:
                leitor.carregarArquivo(arvore);
                break;
            default:
                System.out.println("Sem nada mapeado ainda ");
                break;
        }
    sc.close();
    }
} 