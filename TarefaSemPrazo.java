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

    public void exibirDetalhes() {
        System.out.println("Título: " + this.titulo);
        System.out.println("Descrição: " + this.descricao);
        System.out.println("Prazo: " + this.prazo);
        System.out.println("Prioridade: " + this.prioridade);
    }

    // Método para calcular dias restantes
    public long calcularDiasRestantes() {
        LocalDate dataPrazo = LocalDate.parse(this.prazo);
        LocalDate hoje = LocalDate.now();

        return ChronoUnit.DAYS.between(hoje, dataPrazo);
    }
}

// Classe TarefaSemPrazo que herda de Tarefa
class TarefaSemPrazo extends Tarefa {
    // Construtor para TarefaSemPrazo, reutiliza o construtor de Tarefa
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
        if (getPrazo().isEmpty()) {
            System.out.println("Prazo: Não definido");
        } else {
            System.out.println("Prazo: " + getPrazo());
        }
        System.out.println("Prioridade: " + getPrioridade());
    }
}

// Classe Main para testar o código
public class Main {
    public static void main(String[] args) {
        // Criando uma Tarefa comum
        Tarefa tarefa1 = new Tarefa("Estudar POO", "Revisar os conceitos de classes e objetos", "2024-10-15", 1);
        tarefa1.exibirDetalhes();
        System.out.println("Dias restantes para a tarefa 1: " + tarefa1.calcularDiasRestantes());

        // Criando uma TarefaSemPrazo
        System.out.println("\n=============== Tarefa sem prazo ===============");
        TarefaSemPrazo tarefaSemPrazo = new TarefaSemPrazo("Aprender Design Patterns", "Estudar padrões de projeto em Java", 2);
        tarefaSemPrazo.exibirDetalhes();
        System.out.println("Dias restantes para a tarefa sem prazo: " + tarefaSemPrazo.calcularDiasRestantes());
    }
}