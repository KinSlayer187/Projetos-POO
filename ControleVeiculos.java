import java.util.*;

abstract class Veiculo {
    protected String marca;
    protected String modelo;
    protected int ano;
    protected double preco;
    protected static int totalVeiculos = 0;

    public Veiculo(String marca, String modelo, int ano, double preco) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
        totalVeiculos++;
    }

    public static int getTotalVeiculos() {
        return totalVeiculos;
    }

    @Override
    public String toString() {
        return "Marca: " + marca + ", Modelo: " + modelo + ", Ano: " + ano + ", Preço: " + preco;
    }
}

class VeiculoNovo extends Veiculo {
    private int garantiaAnos;

    public VeiculoNovo(String marca, String modelo, int ano, double preco, int garantiaAnos) {
        super(marca, modelo, ano, preco);
        this.garantiaAnos = garantiaAnos;
    }

    @Override
    public String toString() {
        return super.toString() + ", Garantia: " + garantiaAnos + " anos";
    }
}

class VeiculoUsado extends Veiculo {
    private int quilometragem;
    private boolean unicoDono;

    public VeiculoUsado(String marca, String modelo, int ano, double preco, int quilometragem, boolean unicoDono) {
        super(marca, modelo, ano, preco);
        this.quilometragem = quilometragem;
        this.unicoDono = unicoDono;
    }

    @Override
    public String toString() {
        return super.toString() + ", Quilometragem: " + quilometragem + " km, Único Dono: " + unicoDono;
    }
}

class Concessionaria {
    private Map<String, Veiculo> veiculos = new HashMap<>();
    private List<Veiculo> vendas = new ArrayList<>();

    public void cadastrarVeiculo(Veiculo veiculo) {
        String chave = veiculo.marca + "-" + veiculo.modelo + "-" + veiculo.ano;
        veiculos.put(chave, veiculo);
    }

    public void registrarVenda(String chave) {
        if (veiculos.containsKey(chave)) {
            Veiculo veiculoVendido = veiculos.remove(chave);
            vendas.add(veiculoVendido);
            System.out.println("Veículo vendido com sucesso!");
        } else {
            System.out.println("Veículo não encontrado!");
        }
    }

    public void exibirEstoque() {
        if (veiculos.isEmpty()) {
            System.out.println("Estoque vazio.");
        } else {
            for (Veiculo v : veiculos.values()) {
                System.out.println(v);
            }
        }
    }

    public void exibirVendas() {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda realizada.");
        } else {
            for (Veiculo v : vendas) {
                System.out.println(v);
            }
        }
    }
}

public class ControleVeiculos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Concessionaria concessionaria = new Concessionaria();
        int opcao;

        do {
            System.out.println("\n1. Cadastrar Veículo\n2. Registrar Venda\n3. Exibir Estoque\n4. Exibir Vendas\n5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Ano: ");
                    int ano = scanner.nextInt();
                    System.out.print("Preço: ");
                    double preco = scanner.nextDouble();
                    System.out.print("Novo ou Usado (N/U)? ");
                    char tipo = scanner.next().charAt(0);

                    if (tipo == 'N' || tipo == 'n') {
                        System.out.print("Garantia (anos): ");
                        int garantia = scanner.nextInt();
                        concessionaria.cadastrarVeiculo(new VeiculoNovo(marca, modelo, ano, preco, garantia));
                    } else {
                        System.out.print("Quilometragem: ");
                        int km = scanner.nextInt();
                        System.out.print("Único dono (true/false): ");
                        boolean unicoDono = scanner.nextBoolean();
                        concessionaria.cadastrarVeiculo(new VeiculoUsado(marca, modelo, ano, preco, km, unicoDono));
                    }
                    System.out.println("Veículo cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.print("Digite a chave (marca-modelo-ano) do veículo a vender: ");
                    String chave = scanner.next();
                    concessionaria.registrarVenda(chave);
                    break;
                case 3:
                    System.out.println("\nEstoque de Veículos:");
                    concessionaria.exibirEstoque();
                    break;
                case 4:
                    System.out.println("\nVeículos Vendidos:");
                    concessionaria.exibirVendas();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);

        scanner.close();
    }
}
