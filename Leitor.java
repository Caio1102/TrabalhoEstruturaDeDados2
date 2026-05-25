/*
 * Estrutura de Dados 
 * 
 * Integrantes:
 * Ana Lessa Ferreira - 10732666
 * Caio Vinicius Mussi Trindade - 10735885
 * Julia Oliveira Longhi - 10736801
 * Vitor Kenzo M. Ochida - 10737201
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Leitor {

    public void carregarArquivo(ABB<Netflix> arvore, Scanner sc) {
        System.out.println("Digite o nome do arquivo que deseja ser lido:");
        String nomeArquivo = sc.nextLine().trim();

        if (nomeArquivo.isEmpty()) {
            nomeArquivo = "titles.csv";
        }

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            br.readLine(); // pula o cabecalho

            int inseridos = 0;
            int descartados = 0;
            String linha;

            while ((linha = lerRegistroCsv(br)) != null) {
                String[] campos = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (validarCampos(campos)) {
                    try {
                        Netflix programa = new Netflix(
                                limparTexto(campos[0]),
                                limparTexto(campos[1]),
                                limparTexto(campos[2]),
                                limparTexto(campos[3]),
                                parseIntSafe(campos[4]),
                                limparTexto(campos[5]),
                                parseIntSafe(campos[6]),
                                limparTexto(campos[7]),
                                limparTexto(campos[8]),
                                parseDoubleSafe(campos[9]),
                                limparTexto(campos[10]),
                                parseDoubleSafe(campos[11]),
                                parseDoubleSafe(campos[12]),
                                parseDoubleSafe(campos[13]),
                                parseDoubleSafe(campos[14]));

                        arvore.inserir(programa);
                        inseridos++;
                    } catch (NumberFormatException e) {
                        descartados++;
                    }
                } else {
                    descartados++;
                }
            }

            System.out.println("Leitura concluida.");
            System.out.println("Inseridos na ABB: " + inseridos);
            System.out.println("Descartados: " + descartados);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    // Algumas descricoes do CSV podem quebrar linha dentro de aspas.
    // Entao aqui a gente junta ate o registro ficar com as aspas fechadas.
    private String lerRegistroCsv(BufferedReader br) throws IOException {
        String linha = br.readLine();

        if (linha == null) {
            return null;
        }

        while (!aspasFechadas(linha)) {
            String proximaLinha = br.readLine();

            if (proximaLinha == null) {
                break;
            }

            linha = linha + "\n" + proximaLinha;
        }

        return linha;
    }

    private boolean aspasFechadas(String texto) {
        int qtdAspas = 0;

        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == '"') {
                qtdAspas++;
            }
        }

        return qtdAspas % 2 == 0;
    }

    private boolean validarCampos(String[] campos) {
        if (campos.length != 15) {
            return false;
        }

        String tipo = limparTexto(campos[2]);

        for (int i = 0; i < campos.length; i++) {
            // Para filmes, seasons vem vazio no dataset. No codigo, guardamos como 0.
            if (i == 9 && tipo.equalsIgnoreCase("MOVIE")) {
                continue;
            }

            if (campos[i] == null || campos[i].trim().isEmpty()) {
                return false;
            }
        }

        return true;
    }

    private String limparTexto(String valor) {
        if (valor == null) {
            return "";
        }

        valor = valor.trim();

        if (valor.length() >= 2 && valor.startsWith("\"") && valor.endsWith("\"")) {
            valor = valor.substring(1, valor.length() - 1);
        }

        valor = valor.replace("\"\"", "\"");

        return valor;
    }

    private double parseDoubleSafe(String valor) {
        valor = limparTexto(valor);

        if (valor.isEmpty()) {
            return 0.0;
        }

        return Double.parseDouble(valor);
    }

    private int parseIntSafe(String valor) {
        valor = limparTexto(valor);

        if (valor.isEmpty()) {
            return 0;
        }

        return Integer.parseInt(valor);
    }
}
