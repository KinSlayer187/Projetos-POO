import java.util.ArrayList;

// Superclasse MaterialBiblioteca
abstract class MaterialBiblioteca {
    protected String titulo;
    protected String autor;
    protected int anoPublicacao;

    public MaterialBiblioteca(String titulo, String autor, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnoPublicacao() { return anoPublicacao; }
    
    public abstract String getInformacoes();
}

// Subclasse Livro
class Livro extends MaterialBiblioteca {
    private int numeroPaginas;

    public Livro(String titulo, String autor, int anoPublicacao, int numeroPaginas) {
        super(titulo, autor, anoPublicacao);
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String getInformacoes() {
        return "Livro: " + titulo + " - " + autor + " (" + anoPublicacao + ") - " + numeroPaginas + " páginas";
    }
}

// Subclasse Revista
class Revista extends MaterialBiblioteca {
    private int edicao;

    public Revista(String titulo, String autor, int anoPublicacao, int edicao) {
        super(titulo, autor, anoPublicacao);
        this.edicao = edicao;
    }

    @Override
    public String getInformacoes() {
        return "Revista: " + titulo + " - " + autor + " (" + anoPublicacao + ") - Edição " + edicao;
    }
}

// Subclasse Ebook
class Ebook extends MaterialBiblioteca {
    private String formatoArquivo;

    public Ebook(String titulo, String autor, int anoPublicacao, String formatoArquivo) {
        super(titulo, autor, anoPublicacao);
        this.formatoArquivo = formatoArquivo;
    }

    @Override
    public String getInformacoes() {
        return "Ebook: " + titulo + " - " + autor + " (" + anoPublicacao + ") - Formato: " + formatoArquivo;
    }
}

// Subclasse Audiolivro
class Audiolivro extends MaterialBiblioteca {
    private int duracaoMinutos;

    public Audiolivro(String titulo, String autor, int anoPublicacao, int duracaoMinutos) {
        super(titulo, autor, anoPublicacao);
        this.duracaoMinutos = duracaoMinutos;
    }

    @Override
    public String getInformacoes() {
        return "Audiolivro: " + titulo + " - " + autor + " (" + anoPublicacao + ") - Duração: " + duracaoMinutos + " min";
    }
}

// Classe Biblioteca
class Biblioteca {
    private ArrayList<MaterialBiblioteca> materiais;

    public Biblioteca() {
        materiais = new ArrayList<>();
    }

    public void adicionarMaterial(MaterialBiblioteca material) {
        materiais.add(material);
    }

    public void removerMaterial(String titulo) {
        materiais.removeIf(material -> material.getTitulo().equalsIgnoreCase(titulo));
    }

    public void exibirInformacoesMaterial(String titulo) {
        for (MaterialBiblioteca material : materiais) {
            if (material.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println(material.getInformacoes());
                return;
            }
        }
        System.out.println("Material não encontrado.");
    }

    public void listarTodosMateriais() {
        for (MaterialBiblioteca material : materiais) {
            System.out.println(material.getInformacoes());
        }
    }
}

// Classe Main
public class BibliotecaDigital {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Livro livro = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954, 1178);
        Revista revista = new Revista("National Geographic", "Diversos", 2023, 250);
        Ebook ebook = new Ebook("1984", "George Orwell", 1949, "PDF");
        Audiolivro audiolivro = new Audiolivro("Harry Potter e a Pedra Filosofal", "J.K. Rowling", 1997, 500);

        biblioteca.adicionarMaterial(livro);
        biblioteca.adicionarMaterial(revista);
        biblioteca.adicionarMaterial(ebook);
        biblioteca.adicionarMaterial(audiolivro);

        System.out.println("Lista de Materiais na Biblioteca:");
        biblioteca.listarTodosMateriais();
        
        System.out.println("\nExibindo informações de um material específico:");
        biblioteca.exibirInformacoesMaterial("1984");
        
        System.out.println("\nRemovendo um material:");
        biblioteca.removerMaterial("National Geographic");
        biblioteca.listarTodosMateriais();
    }
}
