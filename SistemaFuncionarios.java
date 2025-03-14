// Classe abstrata Funcionario
abstract class Funcionario {
    protected String nome;
    protected String cpf;
    protected double salarioBase;

    public Funcionario(String nome, String cpf, double salarioBase) {
        this.nome = nome;
        this.cpf = cpf;
        this.salarioBase = salarioBase;
    }

    public abstract double calcularSalario();

    public void exibirInfo() {
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("Salário: R$" + calcularSalario());
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf + ", Salário: " + calcularSalario();
    }
}

// Classe Administrativo que herda de Funcionario
class Administrativo extends Funcionario {
    private String departamento;

    public Administrativo(String nome, String cpf, double salarioBase, String departamento) {
        super(nome, cpf, salarioBase);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    @Override
    public double calcularSalario() {
        return salarioBase * 2.25;
    }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Departamento: " + departamento);
    }
}

// Classe Professor que herda de Funcionario
class Professor extends Funcionario {
    private String titulacao;
    private int horasAula;
    private static final double VALOR_HORA_AULA = 200.0;

    public Professor(String nome, String cpf, double salarioBase, String titulacao, int horasAula) {
        super(nome, cpf, salarioBase);
        this.titulacao = titulacao;
        this.horasAula = horasAula;
    }

    public String getTitulacao() {
        return titulacao;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (horasAula * VALOR_HORA_AULA);
    }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Titulação: " + titulacao);
        System.out.println("Horas Aula: " + horasAula);
    }
}

// Classe Tecnico que herda de Funcionario
class Tecnico extends Funcionario {
    private String cargo;
    private static final double ADICIONAL_TECNICO = 500.0;

    public Tecnico(String nome, String cpf, double salarioBase, String cargo) {
        super(nome, cpf, salarioBase);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + ADICIONAL_TECNICO;
    }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Cargo: " + cargo);
    }
}

// Classe Principal para testar o sistema
public class SistemaFuncionarios {
    public static void main(String[] args) {
        Funcionario adm = new Administrativo("João Silva", "123.456.789-00", 3000.0, "RH");
        Funcionario prof = new Professor("Maria Souza", "987.654.321-00", 3500.0, "Doutor", 20);
        Funcionario tec = new Tecnico("Carlos Lima", "111.222.333-44", 2800.0, "TI");

        adm.exibirInfo();
        System.out.println("-----------------------");
        prof.exibirInfo();
        System.out.println("-----------------------");
        tec.exibirInfo();
    }
}
