public class Tarefa {
    private String titulo;
    private String descricao;
    private int prioridade;
    private String prazo;

    // Construtor que aceita apenas título e prioridade, com valores padrão para descrição e prazo
    public Tarefa(String titulo, int prioridade) {
        this.titulo = titulo;
        this.descricao = "Sem descrição"; // Valor padrão
        this.prazo = "Sem prazo";         // Valor padrão
        if (isPrioridadeValida(prioridade)) {
            this.prioridade = prioridade;
        } else {
            throw new IllegalArgumentException("A prioridade deve ser entre 1 e 5.");
        }
    }

    // Método privado para validar a prioridade
    private boolean isPrioridadeValida(int prioridade) {
        return prioridade >= 1 && prioridade <= 5;
    }

    // Getters e Setters (se necessário)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        if (isPrioridadeValida(prioridade)) {
            this.prioridade = prioridade;
        } else {
            throw new IllegalArgumentException("A prioridade deve ser entre 1 e 5.");
        }
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }
}