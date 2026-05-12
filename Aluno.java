package TesteABB;

//Classe Aluno: encarregada de manipular um objeto do tipo Aluno
//contendo os atributos: RA, nome, sexo, media.
//
//Autor1: Ivan Carlos / Alcides / Charles
//Data da Criação: 04/14/2026. 15h.
public class Aluno implements Comparable<Aluno> {
    private String RA, nome;
    private char sexo;
    private float media;

    // Construtor de um objeto Aluno
    public Aluno(String RA, String nome, char sexo, float media) {
        this.RA = RA;
        this.nome = nome;
        this.sexo = sexo;
        this.media = media;
    }
    
    // Retorna a String contendo os atributos do objeto Aluno
    public String toString() {
        return RA + " - " + nome + ", sexo: " + sexo + ", média: " + media;
    }
    // Obtém o RA
    public String getRA() {
        return RA;
    }
    // Modifica o RA
    public void setRA(String RA) {
        this.RA = RA;
    }
    // Obtém o nome
    public String getNome() {
        return nome;
    }
    // Altera o nome
    public void setNome(String nome) {
        this.nome = nome;
    }
    // Obtém o sexo
    public char getSexo() {
        return sexo;
    }
    // Altera o sexo
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    // Obtém a média
    public float getMedia() {
        return media;
    }
    // Altera a média
    public void setMedia(float media) {
        this.media = media;
    }

    // Ordenação dos Alunos por RA (Chave da ABB)
    @Override
    public int compareTo(Aluno outro) {  // comparamos os RAs
        if(RA.compareTo(outro.getRA()) < 0) return -1;
        else if(RA.compareTo(outro.getRA()) == 0) return 0;
        else return  1;
    }
    
    /*
     * // nesta versão comparamos as médias
    @Override
    public int compareTo(Aluno outro) {  
        if(media < outro.getMedia())return -1;
        else if(media == outro.getMedia())return 0;
        else return  1;
    }    
    */
    
}
