public class Netflix {
    private String id;
    private String titulo;
    private String show_type;
    private String descricao;
    private int release_year;
    private String age_certification;
    private int runtime;
    private String generos; //lista de string
    private String production_countries; // lista de string
    private double temporadas;
    private String imdb_id;
    private double imdb_score;
    private double imdb_votes;
    private double tmdb_popularity;
    private double tmdb_score;

    public Netflix(String id, String titulo, String show_type, String descricao, int release_year, String age_certification, int runtime, String generos, String production_countries, int temporadas, String imdb_id, double imdb_score, double imdb_votes, int tmdb_popularity, int tmdb_score) {
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

    public int getAge_certification() {
        return age_certification;
    }

    public void setAge_certification(int age_certification) {
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

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
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

    public int getTmdb_popularity() {
        return tmdb_popularity;
    }

    public void setTmdb_popularity(int tmdb_popularity) {
        this.tmdb_popularity = tmdb_popularity;
    }

    public int getTmdb_score() {
        return tmdb_score;
    }

    public void setTmdb_score(int tmdb_score) {
        this.tmdb_score = tmdb_score;
    }
}