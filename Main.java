/*
 * Estrutura de Dados 
 * 
 * Integrantes:
 * Ana Lessa Ferreira - 10732666
 * Caio Vinicius Mussi Trindade - 10735885
 * Julia Oliveira Longhi - 10736801
 * Vitor Kenzo M. Ochida - 10737201
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ABB<Netflix> arvore = new ABB<Netflix>();
        Leitor leitor = new Leitor();
        InserirPrograma inserirPrograma = new InserirPrograma();
        AnalisesNetflix analises = new AnalisesNetflix();
        ArquivoNetflix arquivo = new ArquivoNetflix();

        int opcao = -1;

        do {
            mostrarMenu();

            try {
                opcao = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    leitor.carregarArquivo(arvore, sc);
                    break;

                case 2:
                    analises.certificacaoPorGeneros(arvore, sc);
                    break;

                case 3:
                    analises.titulosSubestimados(arvore, sc);
                    break;

                case 4:
                    analises.recomendacaoPorGeneros(arvore, sc);
                    break;

                case 5:
                    analises.seriesLongevasComAltaAvaliacao(arvore);
                    break;

                case 6:
                    analises.topPorGeneroIMDb(arvore, sc);
                    break;

                case 7:
                    inserirPrograma.inserirNovoPrograma(arvore, sc);
                    break;

                case 8:
                    buscarPrograma(arvore, sc);
                    break;

                case 9:
                    removerPrograma(arvore, sc);
                    break;

                case 10:
                    mostrarAltura(arvore);
                    break;

                case 11:
                    arquivo.salvarArquivo(arvore, sc);
                    break;

                case 0:
                    System.out.println("Encerrando a aplicacao.");
                    break;

                default:
                    System.out.println("Opcao invalida.");
                    break;
            }

            System.out.println();
        } while (opcao != 0);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("======================================");
        System.out.println(" Dataset Netflix com ABB ");
        System.out.println("======================================");
        System.out.println("1  - Ler dados de arquivo");
        System.out.println("2  - Certificacoes etarias mais associadas a generos");
        System.out.println("3  - Titulos subestimados");
        System.out.println("4  - Recomendacao de filmes por combinacao de generos");
        System.out.println("5  - Top 10 series de maior longevidade com alta avaliacao");
        System.out.println("6  - Top N titulos de um genero pelo IMDb");
        System.out.println("7  - Inserir novo programa");
        System.out.println("8  - Buscar programa por ID");
        System.out.println("9  - Remover programa por ID");
        System.out.println("10 - Exibir altura da ABB");
        System.out.println("11 - Salvar dados em arquivo");
        System.out.println("0  - Encerrar");
        System.out.print("Escolha uma opcao: ");
    }

    private static void buscarPrograma(ABB<Netflix> arvore, Scanner sc) {
        if (arvore.isEmpty()) {
            System.out.println("Antes de buscar, carregue o arquivo na opcao 1.");
            return;
        }

        System.out.print("Digite o ID do programa: ");
        String id = sc.nextLine().trim();

        int[] comparacoes = new int[] { 0 };

        long inicio = System.nanoTime();
        Node<Netflix> encontrado = arvore.searchComComparacoes(new Netflix(id), comparacoes);
        long fim = System.nanoTime();

        double tempoMs = (fim - inicio) / 1000000.0;

        if (encontrado == null) {
            System.out.println("Programa nao encontrado.");
        } else {
            System.out.println("Programa encontrado:");
            System.out.println(encontrado.getValue());
        }

        System.out.println("Comparacoes realizadas: " + comparacoes[0]);
        System.out.printf("Tempo de busca: %.6f ms%n", tempoMs);
    }

    private static void removerPrograma(ABB<Netflix> arvore, Scanner sc) {
        if (arvore.isEmpty()) {
            System.out.println("Antes de remover, carregue o arquivo na opcao 1.");
            return;
        }

        System.out.print("Digite o ID do programa que deseja remover: ");
        String id = sc.nextLine().trim();

        boolean removeu = arvore.eliminar(new Netflix(id));

        if (removeu) {
            System.out.println("Programa removido com sucesso.");
        } else {
            System.out.println("ID nao encontrado. Nenhum programa foi removido.");
        }
    }

    private static void mostrarAltura(ABB<Netflix> arvore) {
        if (arvore.isEmpty()) {
            System.out.println("Arvore vazia. Altura: -1");
            return;
        }

        System.out.println("Quantidade de nos na BST: " + arvore.totalNos());
        System.out.println("Altura da BST: " + arvore.altura());
    }
}
