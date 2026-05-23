import java.util.Scanner;

public class InserirPrograma {
    
    public void InserirNovoPrograma(ABB<Netflix> arvore, Scanner sc){
        System.out.println("Inserir novo programa");

        String showType = "";

        while(true){
            System.out.println("Digite o tipo do programa (MOVIE ou SHOW):");
            showType = sc.nextLine().trim().toUpperCase();
            
            if(showType.equals("MOVIE") || showType.equals("SHOW")){
                break;
            }
            System.out.println("Tipo inválido ! Digite 'MOVIE' ou 'SHOW'. "); 
        }

        String novoId = gerarId(arvore, showType);

        System.out.println("Digite o nome do título: ");
        String titulo = sc.nextLine().trim();

        System.out.println("Digite a descrição: ");
        String descricao = sc.nextLine().trim();

        System.out.println("Digite o ano de lançamento: ");
        int release_year = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite a certificação de idade: ");
        String age_certification = sc.nextLine();

        System.out.println("Digite a duração do filme (em minutos): ");
        int runtime = sc.nextInt();
        sc.nextLine();

        System.out.print("Digite o gênero: ");
        String generos = sc.nextLine().trim();

        System.out.print("Digite o pais de produção: ");
        String productionCountries = sc.nextLine().trim();

        double temporadas = 0;
        if(showType.equals("SHOW")){
            System.out.println("Digite a quantidade de temporadas: ");
            temporadas = sc.nextInt();
            sc.nextLine();
        }

        System.out.print("ID do IMDb (ex: tt1234567): ");
        String imdbId = sc.nextLine().trim();

        System.out.print("Nota IMDb (Score): ");
        double imdbScore = sc.nextDouble();

        System.out.print("Votos IMDb: ");
        double imdbVotes = sc.nextDouble();

        System.out.print("Popularidade TMDB: ");
        double tmdbPopularity = sc.nextDouble();

        System.out.print("Nota TMDB (Score): ");
        double tmdbScore = sc.nextDouble();
        sc.nextLine();

        Netflix novoPrograma = new Netflix(novoId, titulo, showType, descricao, release_year, age_certification,
            runtime, generos, productionCountries, temporadas, imdbId,
            imdbScore, imdbVotes, tmdbPopularity, tmdbScore);

        arvore.inserir(novoPrograma);
        System.out.println("Programa cadastrato com sucesso !");
        System.out.println(novoPrograma); 
    }

    public String gerarId(ABB<Netflix> arvore, String showType){
        String prefixo = showType.equals("SHOW") ? "ts" : "tm"; // if ternario 

        int[] maiorNumeroContador = new int[]{0}; // coloca 0 na primeira possicao, entao ele vai comparar com o zero
    
        percorrerEmOrdem(arvore.getRaiz(), prefixo, maiorNumeroContador);

        return prefixo + (maiorNumeroContador[0] + 1); 
    }

    public void percorrerEmOrdem(Node<Netflix> no, String prefixo, int[] maiorNumeroContador){
        if (no == null) return; // case base da recrusividade

        // percorre esquerda
        percorrerEmOrdem(no.getFilhoEsquerdo(), prefixo, maiorNumeroContador);

        //processa a raiz
        Netflix programaAtual = no.getValue();
        String idAtual = programaAtual.getId().trim();

        if(idAtual.startsWith(prefixo)){
            int num = Integer.parseInt(idAtual.substring(2));
            if (num > maiorNumeroContador[0]){
                maiorNumeroContador[0] = num;
            }
        }

        //percorre a direita
        percorrerEmOrdem(no.getFilhoDireito(), prefixo, maiorNumeroContador);
    }

}
