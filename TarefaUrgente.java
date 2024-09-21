public class TarefaUrgente extends Tarefa implements Notificacao {
    private String razaoUrgencia;

    // Construtor da Tarefa Urgente
    public TarefaUrgente(String titulo, String descricao, String prazo, String razaoUrgencia) {
        super(titulo, descricao, prazo, 5);  // nova Tarefa
        this.razaoUrgencia = razaoUrgencia;
    }

    // Métodos getters e setters
    public String getRazaoUrgencia() {
        return this.razaoUrgencia;
    }

    public void setRazaoUrgencia(String razaoUrgencia) {
        this.razaoUrgencia = razaoUrgencia;
    }

    // Sobreescrevendo o método exibirDetalhes()
    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Razão da Urgência: " + this.razaoUrgencia);
        System.out.println("Tipo: Tarefa urgente");
    }

    @Override
    public void enviarNotificacao() {
        System.out.println("Notificação: A tarefa '" + super.getTitulo() + "' é urgente! - Razão: " + this.razaoUrgencia);
    }
}

interface Notificacao {
    void enviarNotificacao();
}