import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    
        ABB<Netflix> arvore = new ABB<>();
        Leitor leitor = new Leitor();


        Scanner sc = new Scanner(System.in);
        int opcao = sc.nextInt(); 
        sc.nextLine(); // limpar o buffer, sem BO

        switch (opcao) {
            case 1:
                carregarArquivo(ABB<Netflix> arvore);
                break;
        }
    sc.close();
    }
}