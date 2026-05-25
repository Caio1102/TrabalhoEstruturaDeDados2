/*
 * Estrutura de Dados 
 * 
 * Integrantes:
 * Ana Lessa Ferreira - 10732666
 * Caio Vinicius Mussi Trindade - 10735885
 * Julia Oliveira Longhi - 10736801
 * Vitor Kenzo M. Ochida - 10737201
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ArquivoNetflix {

    public void salvarArquivo(ABB<Netflix> arvore, Scanner sc) {
        if (arvore.isEmpty()) {
            System.out.println("Nao ha dados para salvar.");
            return;
        }

        System.out.println("Digite o nome do arquivo de saida: ");
        String nomeArquivo = sc.nextLine().trim();

        if (nomeArquivo.isEmpty()) {
            nomeArquivo = "titles_atualizado.csv";
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(nomeArquivo))) {
            pw.println("id,title,type,description,release_year,age_certification,runtime,genres,production_countries,seasons,imdb_id,imdb_score,imdb_votes,tmdb_popularity,tmdb_score");
            salvarEmOrdem(arvore.getRaiz(), pw);

            System.out.println("Arquivo salvo com sucesso: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    private void salvarEmOrdem(Node<Netflix> no, PrintWriter pw) {
        if (no == null) {
            return;
        }

        salvarEmOrdem(no.getFilhoEsquerdo(), pw);
        pw.println(no.getValue().toCSV());
        salvarEmOrdem(no.getFilhoDireito(), pw);
    }
}
