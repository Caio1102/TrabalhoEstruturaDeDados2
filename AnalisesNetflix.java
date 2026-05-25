/*
 * Estrutura de Dados 
 * 
 * Integrantes:
 * Ana Lessa Ferreira - 10732666
 * Caio Vinicius Mussi Trindade - 10735885
 * Julia Oliveira Longhi - 10736801
 * Vitor Kenzo M. Ochida - 10737201
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AnalisesNetflix {

    public void certificacaoPorGeneros(ABB<Netflix> arvore, Scanner sc) {
        if (arvore.isEmpty()) {
            System.out.println("Antes de analisar, carregue o arquivo na opcao 1.");
            return;
        }

        System.out.println("Digite a certificacao etaria desejada. Exemplo: TV-MA, TV-14, R, PG-13");
        String certificacao = sc.nextLine().trim();

        HashMap<String, Integer> contagem = new HashMap<String, Integer>();
        contarGenerosEmLargura(arvore.getRaiz(), certificacao, contagem);

        ArrayList<Map.Entry<String, Integer>> ranking = new ArrayList<Map.Entry<String, Integer>>(contagem.entrySet());

        Collections.sort(ranking, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return b.getValue().compareTo(a.getValue());
            }
        });

        System.out.println("\nRanking de generos para a certificacao " + certificacao + ":");

        if (ranking.isEmpty()) {
            System.out.println("Nenhum resultado encontrado.");
            return;
        }

        int posicao = 1;
        for (Map.Entry<String, Integer> item : ranking) {
            System.out.println(posicao + ". " + item.getKey() + " - " + item.getValue() + " ocorrencias");
            posicao++;
        }
    }

    private void contarGenerosEmLargura(Node<Netflix> raiz, String certificacao, HashMap<String, Integer> contagem) {
        if (raiz == null) {
            return;
        }

        java.util.Queue<Node<Netflix>> fila = new java.util.LinkedList<Node<Netflix>>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            Node<Netflix> atual = fila.poll();
            Netflix programa = atual.getValue();

            if (programa.getAge_certification().equalsIgnoreCase(certificacao)) {
                String[] generos = separarGeneros(programa.getGeneros());

                for (String genero : generos) {
                    if (!genero.isEmpty()) {
                        Integer qtd = contagem.get(genero);

                        if (qtd == null) {
                            contagem.put(genero, 1);
                        } else {
                            contagem.put(genero, qtd + 1);
                        }
                    }
                }
            }

            if (atual.getFilhoEsquerdo() != null) {
                fila.add(atual.getFilhoEsquerdo());
            }

            if (atual.getFilhoDireito() != null) {
                fila.add(atual.getFilhoDireito());
            }
        }
    }

    public void titulosSubestimados(ABB<Netflix> arvore, Scanner sc) {
        if (arvore.isEmpty()) {
            System.out.println("Antes de analisar, carregue o arquivo na opcao 1.");
            return;
        }

        System.out.print("Nota minima do IMDb: ");
        double imdbMinimo = lerDouble(sc);

        System.out.print("Popularidade maxima do TMDB: ");
        double tmdbMaximo = lerDouble(sc);

        System.out.print("Quantidade de titulos desejada: ");
        int limite = lerInteiro(sc);

        ArrayList<Netflix> resultados = new ArrayList<Netflix>();
        buscarSubestimadosPosOrdem(arvore.getRaiz(), imdbMinimo, tmdbMaximo, resultados);

        Collections.sort(resultados, new Comparator<Netflix>() {
            @Override
            public int compare(Netflix a, Netflix b) {
                int porImdb = Double.compare(b.getImdb_score(), a.getImdb_score());

                if (porImdb != 0) {
                    return porImdb;
                }

                return Double.compare(a.getTmdb_popularity(), b.getTmdb_popularity());
            }
        });

        System.out.println("\nTitulos subestimados encontrados:");

        imprimirTopSubestimados(resultados, limite);
    }

    private void buscarSubestimadosPosOrdem(Node<Netflix> no, double imdbMinimo, double tmdbMaximo,
            ArrayList<Netflix> resultados) {
        if (no == null) {
            return;
        }

        buscarSubestimadosPosOrdem(no.getFilhoEsquerdo(), imdbMinimo, tmdbMaximo, resultados);
        buscarSubestimadosPosOrdem(no.getFilhoDireito(), imdbMinimo, tmdbMaximo, resultados);

        Netflix programa = no.getValue();

        if (programa.getImdb_score() >= imdbMinimo && programa.getTmdb_popularity() <= tmdbMaximo) {
            resultados.add(programa);
        }
    }

    private void imprimirTopSubestimados(ArrayList<Netflix> resultados, int limite) {
        if (resultados.isEmpty()) {
            System.out.println("Nenhum resultado encontrado.");
            return;
        }

        int total = Math.min(limite, resultados.size());

        for (int i = 0; i < total; i++) {
            Netflix p = resultados.get(i);

            System.out.println((i + 1) + ". " + p.getTitulo() +
                    " | " + p.getShow_type() +
                    " | " + p.getRelease_year() +
                    " | IMDb: " + p.getImdb_score() +
                    " | Popularidade TMDB: " + p.getTmdb_popularity());
        }
    }

    public void recomendacaoPorGeneros(ABB<Netflix> arvore, Scanner sc) {
        if (arvore.isEmpty()) {
            System.out.println("Antes de analisar, carregue o arquivo na opcao 1.");
            return;
        }

        System.out.println("Digite ate 3 generos separados por virgula. Exemplo: comedy, action, fantasy");
        String entrada = sc.nextLine().trim();

        String[] generosDesejados = entrada.split(",");

        ArrayList<Netflix> resultados = new ArrayList<Netflix>();
        buscarFilmesPreOrdem(arvore.getRaiz(), generosDesejados, resultados);

        System.out.println("\nFilmes encontrados com a combinacao informada:");

        if (resultados.isEmpty()) {
            System.out.println("Nenhum resultado encontrado.");
            return;
        }

        for (int i = 0; i < resultados.size(); i++) {
            Netflix p = resultados.get(i);
            System.out.println((i + 1) + ". " + p.getTitulo() +
                    " | Duracao: " + p.getRuntime() + " min" +
                    " | Generos: " + p.getGeneros());
        }
    }

    private void buscarFilmesPreOrdem(Node<Netflix> no, String[] generosDesejados, ArrayList<Netflix> resultados) {
        if (no == null) {
            return;
        }

        Netflix programa = no.getValue();

        if (programa.getShow_type().equalsIgnoreCase("MOVIE") && contemTodosGeneros(programa, generosDesejados)) {
            resultados.add(programa);
        }

        buscarFilmesPreOrdem(no.getFilhoEsquerdo(), generosDesejados, resultados);
        buscarFilmesPreOrdem(no.getFilhoDireito(), generosDesejados, resultados);
    }

    public void seriesLongevasComAltaAvaliacao(ABB<Netflix> arvore) {
        if (arvore.isEmpty()) {
            System.out.println("Antes de analisar, carregue o arquivo na opcao 1.");
            return;
        }

        ArrayList<Netflix> resultados = new ArrayList<Netflix>();
        buscarSeriesPosOrdem(arvore.getRaiz(), resultados);

        Collections.sort(resultados, new Comparator<Netflix>() {
            @Override
            public int compare(Netflix a, Netflix b) {
                int porTemporada = Double.compare(b.getTemporadas(), a.getTemporadas());

                if (porTemporada != 0) {
                    return porTemporada;
                }

                return Double.compare(b.getTmdb_score(), a.getTmdb_score());
            }
        });

        System.out.println("\nTop 10 series de maior longevidade com alta avaliacao:");

        if (resultados.isEmpty()) {
            System.out.println("Nenhum resultado encontrado.");
            return;
        }

        int total = Math.min(10, resultados.size());

        for (int i = 0; i < total; i++) {
            Netflix p = resultados.get(i);
            System.out.println((i + 1) + ". " + p.getTitulo() +
                    " | Temporadas: " + (int) p.getTemporadas() +
                    " | TMDB Score: " + p.getTmdb_score());
        }
    }

    private void buscarSeriesPosOrdem(Node<Netflix> no, ArrayList<Netflix> resultados) {
        if (no == null) {
            return;
        }

        buscarSeriesPosOrdem(no.getFilhoEsquerdo(), resultados);
        buscarSeriesPosOrdem(no.getFilhoDireito(), resultados);

        Netflix programa = no.getValue();

        if (programa.getShow_type().equalsIgnoreCase("SHOW")
                && programa.getTemporadas() >= 5
                && programa.getTmdb_score() > 8.5) {
            resultados.add(programa);
        }
    }

    public void topPorGeneroIMDb(ABB<Netflix> arvore, Scanner sc) {
        if (arvore.isEmpty()) {
            System.out.println("Antes de analisar, carregue o arquivo na opcao 1.");
            return;
        }

        System.out.print("Digite o genero desejado: ");
        String genero = sc.nextLine().trim();

        System.out.print("Quantidade de titulos desejada: ");
        int limite = lerInteiro(sc);

        ArrayList<Netflix> resultados = new ArrayList<Netflix>();
        buscarGeneroEmOrdem(arvore.getRaiz(), genero, resultados);

        Collections.sort(resultados, new Comparator<Netflix>() {
            @Override
            public int compare(Netflix a, Netflix b) {
                return Double.compare(b.getImdb_score(), a.getImdb_score());
            }
        });

        System.out.println("\nTop titulos do genero " + genero + " pelo IMDb:");

        if (resultados.isEmpty()) {
            System.out.println("Nenhum resultado encontrado.");
            return;
        }

        int total = Math.min(limite, resultados.size());

        for (int i = 0; i < total; i++) {
            Netflix p = resultados.get(i);

            System.out.println((i + 1) + ". " + p.getTitulo() +
                    " | " + p.getShow_type() +
                    " | " + p.getRelease_year() +
                    " | IMDb: " + p.getImdb_score());
        }
    }

    private void buscarGeneroEmOrdem(Node<Netflix> no, String genero, ArrayList<Netflix> resultados) {
        if (no == null) {
            return;
        }

        buscarGeneroEmOrdem(no.getFilhoEsquerdo(), genero, resultados);

        Netflix programa = no.getValue();

        if (contemGenero(programa, genero)) {
            resultados.add(programa);
        }

        buscarGeneroEmOrdem(no.getFilhoDireito(), genero, resultados);
    }

    private boolean contemTodosGeneros(Netflix programa, String[] generosDesejados) {
        int limite = Math.min(generosDesejados.length, 3);

        for (int i = 0; i < limite; i++) {
            String genero = generosDesejados[i].trim();

            if (!genero.isEmpty() && !contemGenero(programa, genero)) {
                return false;
            }
        }

        return true;
    }

    private boolean contemGenero(Netflix programa, String generoProcurado) {
        String generoLimpo = normalizar(generoProcurado);
        String[] generos = separarGeneros(programa.getGeneros());

        for (String genero : generos) {
            if (genero.equals(generoLimpo)) {
                return true;
            }
        }

        return false;
    }

    private String[] separarGeneros(String texto) {
        if (texto == null) {
            return new String[0];
        }

        texto = texto.replace("[", "");
        texto = texto.replace("]", "");
        texto = texto.replace("'", "");
        texto = texto.replace("\"", "");

        String[] partes = texto.split(",");

        for (int i = 0; i < partes.length; i++) {
            partes[i] = normalizar(partes[i]);
        }

        return partes;
    }

    private String normalizar(String texto) {
        if (texto == null) {
            return "";
        }

        return texto.trim().toLowerCase();
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

    private int lerInteiro(Scanner sc) {
        while (true) {
            try {
                int valor = Integer.parseInt(sc.nextLine().trim());

                if (valor > 0) {
                    return valor;
                }

                System.out.print("Digite um numero maior que zero: ");
            } catch (NumberFormatException e) {
                System.out.print("Valor invalido. Digite novamente: ");
            }
        }
    }
}
