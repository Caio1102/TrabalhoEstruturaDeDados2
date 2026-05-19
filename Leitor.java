import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Leitor { // Ou o nome da classe onde você vai colocar a leitura
    public void carregarArquivo(String nomeArquivo, ABB<Netflix> arvore) {
        
        // try-with-resources (fecha o arquivo automaticamente)
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            br.readLine(); // pula o cabeçalho
            int inseridos = 0, descartados = 0;

            while ((linha = br.readLine()) != null) {
                // Regex para dividir CSV considerando aspas (idêntico à sua foto)
                String[] campos = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                // Se validarCampos retornar true, os dados são inseridos
                if (validarCampos(campos)) { 
                    try {
                        Netflix p = new Netflix(
                            campos[0].trim(),  // id (String)
                            campos[1].trim(),  // título (String)
                            campos[2].trim(),  // show_type (String)
                            campos[3].trim(),  // descrição (String)
                            Integer.parseInt(campos[4].trim()), // release_year (int)
                            campos[5].trim(),  // age_certification (String)
                            Integer.parseInt(campos[6].trim()), // runtime (int)
                            campos[7].trim(),  // gêneros (String)
                            campos[8].trim(),  // production_countries (String)
                            parseDoubleSafe(campos[9].trim()),     // temporadas (double) - uso do safe pois filmes não tem temporada
                            campos[10].trim(), // imdb_id (String)
                            parseDoubleSafe(campos[11].trim()), // imdb_score (double)
                            parseDoubleSafe(campos[12].trim()), // imdb_votes (double)
                            parseDoubleSafe(campos[13].trim()), // tmdb_popularity (double)
                            parseDoubleSafe(campos[14].trim())  // tmdb_score (double)
                        );

                        arvore.inserir(p); // Chave de inserção é o ID
                        inseridos++;
                        
                    } catch (NumberFormatException e) {
                        // Se o Java tentar converter uma letra para número e falhar, ele descarta a linha
                        descartados++;
                    }
                } else {
                    descartados++;
                }
            }
            
            System.out.println("Insercao concluida! Inseridos: " + inseridos + " | Descartados: " + descartados);

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    // --- MÉTODOS AUXILIARES (Que aparecem no fim da sua tela) ---

    // método de validação da inserção
    private boolean validarCampos(String[] campos) {
        // O dataset original tem 15 colunas. Se quebrar errado, descarta.
        if (campos.length != 15) {
            return false;
        }
        
        // Verifica se há algum campo totalmente vazio
        // for (String campo : campos) {
        //     if (campo == null || campo.trim().isEmpty()) {
        //         return false;
        //     }
        // }

        // ID
        if (campos[0].trim().isEmpty()) {
            return false;
        }

        // TITLE
        if (campos[1].trim().isEmpty()) {
            return false;
        }

        // SHOW_TYPE
        if (campos[2].trim().isEmpty()) {
            return false;
        }

        // RELEASE_YEAR
        if (campos[4].trim().isEmpty()) {
            return false;
        }
        return true;
    }

    // Métodos "Safe" que você usou na foto para evitar que o programa quebre 
    // se tentar ler um número vazio no CSV.
    private double parseDoubleSafe(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(valor.trim());
    }

    private int parseIntSafe(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(valor.trim());
    }
}