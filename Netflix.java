/*
 * Estrutura de Dados 
 * 
 * Integrantes:
 * Ana Lessa Ferreira - 10732666
 * Caio Vinicius Mussi Trindade - 10735885
 * Julia Oliveira Longhi - 10736801
 * Vitor Kenzo M. Ochida - 10737201
 */

public class Netflix implements Comparable<Netflix> {
    private String id;
    private String titulo;
    private String show_type;
    private String descricao;
    private int release_year;
    private String age_certification;
    private int runtime;
    private String generos;
    private String production_countries;
    private double temporadas;
    private String imdb_id;
    private double imdb_score;
    private double imdb_votes;
    private double tmdb_popularity;
    private double tmdb_score;

    public Netflix(String id) {
        this(id, "", "", "", 0, "", 0, "", "", 0, "", 0, 0, 0, 0);
    }

    public Netflix(String id, String titulo, String show_type, String descricao, int release_year,
            String age_certification, int runtime, String generos, String production_countries,
            double temporadas, String imdb_id, double imdb_score, double imdb_votes,
            double tmdb_popularity, double tmdb_score) {
        this.id = id;
        this.titulo = titulo;
        this.show_type = show_type;
        this.descricao = descricao;
        this.release_year = release_year;
        this.age_certification = age_certification;
        this.runtime = runtime;
        this.generos = generos;
        this.production_countries = production_countries;
        this.temporadas = temporadas;
        this.imdb_id = imdb_id;
        this.imdb_score = imdb_score;
        this.imdb_votes = imdb_votes;
        this.tmdb_popularity = tmdb_popularity;
        this.tmdb_score = tmdb_score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getShow_type() {
        return show_type;
    }

    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public String getAge_certification() {
        return age_certification;
    }

    public void setAge_certification(String age_certification) {
        this.age_certification = age_certification;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }

    public String getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(String production_countries) {
        this.production_countries = production_countries;
    }

    public double getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(double temporadas) {
        this.temporadas = temporadas;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public double getImdb_score() {
        return imdb_score;
    }

    public void setImdb_score(double imdb_score) {
        this.imdb_score = imdb_score;
    }

    public double getImdb_votes() {
        return imdb_votes;
    }

    public void setImdb_votes(double imdb_votes) {
        this.imdb_votes = imdb_votes;
    }

    public double getTmdb_popularity() {
        return tmdb_popularity;
    }

    public void setTmdb_popularity(double tmdb_popularity) {
        this.tmdb_popularity = tmdb_popularity;
    }

    public double getTmdb_score() {
        return tmdb_score;
    }

    public void setTmdb_score(double tmdb_score) {
        this.tmdb_score = tmdb_score;
    }

    @Override
    public int compareTo(Netflix outro) {
        return this.id.trim().compareTo(outro.id.trim());
    }

    @Override
    public String toString() {
        return "ID: " + id +
               "\nTitulo: " + titulo +
               "\nTipo: " + show_type +
               "\nDescricao: " + descricao +
               "\nAno de lancamento: " + release_year +
               "\nClassificacao etaria: " + age_certification +
               "\nDuracao: " + runtime +
               "\nGeneros: " + generos +
               "\nPaises de producao: " + production_countries +
               "\nTemporadas: " + temporadas +
               "\nIMDb ID: " + imdb_id +
               "\nIMDb Score: " + imdb_score +
               "\nIMDb Votes: " + imdb_votes +
               "\nTMDB Popularity: " + tmdb_popularity +
               "\nTMDB Score: " + tmdb_score;
    }

    public String linhaResumo() {
        return id + " | " + titulo + " | " + show_type + " | " + release_year +
               " | IMDb: " + imdb_score + " | TMDB: " + tmdb_score;
    }

    public String toCSV() {
        return csv(id) + "," +
               csv(titulo) + "," +
               csv(show_type) + "," +
               csv(descricao) + "," +
               release_year + "," +
               csv(age_certification) + "," +
               runtime + "," +
               csv(generos) + "," +
               csv(production_countries) + "," +
               temporadas + "," +
               csv(imdb_id) + "," +
               imdb_score + "," +
               imdb_votes + "," +
               tmdb_popularity + "," +
               tmdb_score;
    }

    private String csv(String valor) {
        if (valor == null) {
            return "";
        }

        String texto = valor.replace("\"", "\"\"");

        if (texto.contains(",") || texto.contains("\"") || texto.contains("\n")) {
            return "\"" + texto + "\"";
        }

        return texto;
    }
}
