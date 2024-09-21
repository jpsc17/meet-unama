import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException;

class Tarefa {
    // Atributos privados
    private String titulo;
    private String descricao;
    private String prazo;
    private int prioridade;

    // Método para validar prazo
    private boolean isPrazoValido(String prazo) {
        try {
            LocalDate.parse(prazo);
            return true;
        } catch (DateTimeParseException e){
            return false;
        }
    }

    // Construtor 1: Construtor completo.
    public Tarefa(String titulo, String descricao, String prazo, int prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.prioridade = prioridade;
    }

    // Construtor 2: Construtor com menos parâmetros.
    public Tarefa(String titulo, String prazo) {
        this.titulo = titulo;
        this.prazo = prazo;
        this.descricao = "";    // Descrição padrão
        this.prioridade = 0;    // Prioridade padrão
    }

    // Métodos públicos para acessar os atributos
    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrazo() {
        return this.prazo;
    }

    public void setPrazo(String prazo) {
        if (isPrazoValido(prazo)) {
            this.prazo = prazo;
        } else {
            System.out.println("Data inválida.");
        }
    }

    public int getPrioridade() {
        return this.prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    // Método para calcular dias restantes
    public long calcularDiasRestantes() {
        if (this.prazo.isEmpty()) {
            return Long.MAX_VALUE; // Retorna um valor alto para tarefas sem prazo definido
        }
        LocalDate dataPrazo = LocalDate.parse(this.prazo);
        LocalDate hoje = LocalDate.now();

        return ChronoUnit.DAYS.between(hoje, dataPrazo);
    }

    // Método para verificar se a tarefa é urgente
    public boolean isUrgente() {
        long diasRestantes = calcularDiasRestantes();
        return (diasRestantes <= 3 && diasRestantes >= 0) || this.prioridade == 1;
    }

    // Método para exibir se a tarefa é urgente ou flexível
    public String getClassificacao() {
        if (isUrgente()) {
            return "Urgente";
        } else {
            return "Flexível";
        }
    }

    // Método para exibir detalhes da tarefa
    public void exibirDetalhes() {
        System.out.println("Título: " + this.titulo);
        System.out.println("Descrição: " + this.descricao);
        if (this.prazo.isEmpty()) {
            System.out.println("Prazo: Não definido");
        } else {
            System.out.println("Prazo: " + this.prazo);
        }
        System.out.println("Prioridade: " + this.prioridade);
        System.out.println("Classificação: " + getClassificacao());
    }
}

// Classe TarefaSemPrazo que herda de Tarefa
class TarefaSemPrazo extends Tarefa {
    // Construtor para TarefaSemPrazo
    public TarefaSemPrazo(String titulo, String descricao, int prioridade) {
        super(titulo, descricao, "", prioridade); // Define o prazo como vazio
    }

    // Sobrescreve o método calcularDiasRestantes
    @Override
    public long calcularDiasRestantes() {
        if (getPrazo().isEmpty()) {
            System.out.println("Esta tarefa não possui prazo definido.");
            return -1; // Retorna -1 para indicar que não há prazo
        } else {
            return super.calcularDiasRestantes(); // Se houver prazo, usa o método original
        }
    }

    // Sobrescreve o método exibirDetalhes para indicar a ausência de prazo
    @Override
    public void exibirDetalhes() {
        System.out.println("Título: " + getTitulo());
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Prazo: Não definido");
        System.out.println("Prioridade: " + getPrioridade());
        System.out.println("Classificação: Flexível");
    }
}

// Classe Main para testar o código
public class Main {
    public static void main(String[] args) {
        // Criando uma lista de tarefas
        Tarefa tarefa1 = new Tarefa("Estudar POO", "Revisar os conceitos de classes e objetos", "2024-10-15", 1);
        Tarefa tarefa2 = new Tarefa("Entregar Projeto", "Finalizar o projeto de matemática", "2024-09-22", 2);
        Tarefa tarefa3 = new Tarefa("Comprar material", "Comprar material para o curso", "2024-09-19", 3);
        TarefaSemPrazo tarefaSemPrazo = new TarefaSemPrazo("Aprender Design Patterns", "Estudar padrões de projeto em Java", 2);

        // Exibindo detalhes de cada tarefa
        System.out.println("=============== Lista de Tarefas ===============");
        tarefa1.exibirDetalhes();
        System.out.println("--------------------------------------------");
        tarefa2.exibirDetalhes();
        System.out.println("--------------------------------------------");
        tarefa3.exibirDetalhes();
        System.out.println("--------------------------------------------");
        tarefaSemPrazo.exibirDetalhes();
    }
}