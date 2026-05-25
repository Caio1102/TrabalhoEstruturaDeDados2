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

public class InserirPrograma {

    public void inserirNovoPrograma(ABB<Netflix> arvore, Scanner sc) {
        System.out.println("Inserir novo programa");

        String showType;

        while (true) {
            System.out.print("Digite o tipo do programa (MOVIE ou SHOW): ");
            showType = sc.nextLine().trim().toUpperCase();

            if (showType.equals("MOVIE") || showType.equals("SHOW")) {
                break;
            }

            System.out.println("Tipo invalido. Digite MOVIE ou SHOW.");
        }

        String novoId = gerarId(arvore, showType);

        System.out.print("Digite o nome do titulo: ");
        String titulo = sc.nextLine().trim();

        System.out.print("Digite a descricao: ");
        String descricao = sc.nextLine().trim();

        System.out.print("Digite o ano de lancamento: ");
        int releaseYear = lerInt(sc);

        System.out.print("Digite a certificacao de idade: ");
        String ageCertification = sc.nextLine().trim();

        System.out.print("Digite a duracao em minutos: ");
        int runtime = lerInt(sc);

        System.out.print("Digite os generos. Exemplo: ['drama', 'crime']: ");
        String generos = sc.nextLine().trim();

        System.out.print("Digite o pais de producao. Exemplo: ['US']: ");
        String productionCountries = sc.nextLine().trim();

        double temporadas = 0;

        if (showType.equals("SHOW")) {
            System.out.print("Digite a quantidade de temporadas: ");
            temporadas = lerDouble(sc);
        }

        System.out.print("ID do IMDb. Exemplo: tt1234567: ");
        String imdbId = sc.nextLine().trim();

        System.out.print("Nota IMDb: ");
        double imdbScore = lerDouble(sc);

        System.out.print("Votos IMDb: ");
        double imdbVotes = lerDouble(sc);

        System.out.print("Popularidade TMDB: ");
        double tmdbPopularity = lerDouble(sc);

        System.out.print("Nota TMDB: ");
        double tmdbScore = lerDouble(sc);

        Netflix novoPrograma = new Netflix(novoId, titulo, showType, descricao, releaseYear,
                ageCertification, runtime, generos, productionCountries, temporadas,
                imdbId, imdbScore, imdbVotes, tmdbPopularity, tmdbScore);

        arvore.inserir(novoPrograma);

        System.out.println("Programa cadastrado com sucesso.");
        System.out.println("ID gerado: " + novoId);
        System.out.println(novoPrograma);
    }

    // Mantive esse metodo para nao quebrar caso alguem chame com o nome antigo.
    public void InserirNovoPrograma(ABB<Netflix> arvore, Scanner sc) {
        inserirNovoPrograma(arvore, sc);
    }

    public String gerarId(ABB<Netflix> arvore, String showType) {
        String prefixo = showType.equals("SHOW") ? "ts" : "tm";
        int[] maiorNumeroContador = new int[] { 0 };

        percorrerEmOrdem(arvore.getRaiz(), prefixo, maiorNumeroContador);

        return prefixo + (maiorNumeroContador[0] + 1);
    }

    public void percorrerEmOrdem(Node<Netflix> no, String prefixo, int[] maiorNumeroContador) {
        if (no == null) {
            return;
        }

        percorrerEmOrdem(no.getFilhoEsquerdo(), prefixo, maiorNumeroContador);

        Netflix programaAtual = no.getValue();
        String idAtual = programaAtual.getId().trim();

        if (idAtual.startsWith(prefixo)) {
            try {
                int numero = Integer.parseInt(idAtual.substring(2));

                if (numero > maiorNumeroContador[0]) {
                    maiorNumeroContador[0] = numero;
                }
            } catch (NumberFormatException e) {
                // Se algum id estiver fora do padrao, so ignora para nao travar.
            }
        }

        percorrerEmOrdem(no.getFilhoDireito(), prefixo, maiorNumeroContador);
    }

    private int lerInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Valor invalido. Digite novamente: ");
            }
        }
    }

    private double lerDouble(Scanner sc) {
        while (true) {
            try {
                String texto = sc.nextLine().trim().replace(",", ".");
                return Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                System.out.print("Valor invalido. Digite novamente: ");
            }
        }
    }
}
