import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner; 

public class Leitor { 
    
    
    public void carregarArquivo(ABB<Netflix> arvore) {
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o nome do arquivo que deseja ser lido:");
        String nomeArquivo = sc.nextLine();


        // try-with-resources (fecha o arquivo automaticamente)
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            br.readLine(); // pula o cabeçalho
            int inseridos = 0;
            int descartados = 0;

            while ((linha = br.readLine()) != null) {
                // Regex para dividir CSV considerando aspas (idêntico à sua foto)
                String[] campos = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // regra matematica que tira nao zuar as virgulas 

                // Se validarCampos retornar true, os dados são inseridos
                if (validarCampos(campos)) { 
                    try {
                        Netflix p = new Netflix(
                            campos[0].trim(),  // id (String)
                            campos[1].trim(),  // título (String)
                            campos[2].trim(),  // show_type (String)
                            campos[3].trim(),  // descrição (String)
                            parseIntSafe(campos[4].trim()), // release_year (int)
                            campos[5].trim(),  // age_certification (String)
                            parseIntSafe(campos[6].trim()), // runtime (int)
                            campos[7].trim(),  // gêneros (String)
                            campos[8].trim(),  // production_countries (String)
                            parseDoubleSafe(campos[9].trim()),     // temporadas (double) - uso do safe pois filmes não tem temporada
                            campos[10].trim(), // imdb_id (String)
                            parseDoubleSafe(campos[11].trim()), // imdb_score (double)
                            parseDoubleSafe(campos[12].trim()), // imdb_votes (double)
                            parseDoubleSafe(campos[13].trim()), // tmdb_popularity (double)
                            parseDoubleSafe(campos[14].trim())  // tmdb_score (double)
                        );

                        arvore.inserir(p); // Chave de inserção é o ID // Kenzo entendeu ? 
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
        sc.close();
    }



    // metodo para validar se pode ser inserido na arvore
    private boolean validarCampos(String[] campos) {

        if (campos.length != 15) { // kenzo: mesmo em filmes, a coluna fica vazia(!= null) e completa 15 :)
            return false;
        }

        String tipo = campos[2].trim();

        for (int i = 0; i <campos.length; i++){
            if (i == 9 && tipo.equalsIgnoreCase("MOVIE")) { // se for filme ele pode ter a coluna seasons vazia
                continue;
            }

            if (campos[i] == null || campos[i].trim().isEmpty()){ // se alguma outra coluna estiver fazia, sera descartada
                return false;
            }
        }
        return true; 
    }


    //Validacao se o numeros estiverem vazios 

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