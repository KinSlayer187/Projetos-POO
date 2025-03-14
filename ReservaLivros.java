import java.util.ArrayList;
import java.util.List;

class Livro {
    private String titulo;
    private String autor;
    private int anoPublicacao;

    public Livro(String titulo, String autor, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        setAnoPublicacao(anoPublicacao);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        if (anoPublicacao > 0) {
            this.anoPublicacao = anoPublicacao;
        } else {
            System.out.println("Ano de publicação inválido. Deve ser maior que zero.");
        }
    }

    public void mostrarInfo() {
        System.out.println("Título: " + titulo + ", Autor: " + autor + ", Ano: " + anoPublicacao);
    }
}

class Biblioteca {
    private List<Livro> livros;
    private int capacidade;

    public Biblioteca(int capacidade) {
        this.capacidade = capacidade > 0 ? capacidade : 1;
        this.livros = new ArrayList<>();
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        if (capacidade > 0) {
            this.capacidade = capacidade;
        } else {
            System.out.println("Capacidade inválida. Deve ser maior que zero.");
        }
    }

    public void adicionarLivro(Livro livro) {
        if (livros.size() < capacidade) {
            livros.add(livro);
            System.out.println("Livro adicionado: " + livro.getTitulo());
        } else {
            System.out.println("Biblioteca cheia. Não é possível adicionar mais livros.");
        }
    }

    public void removerLivro(Livro livro) {
        if (livros.remove(livro)) {
            System.out.println("Livro removido: " + livro.getTitulo());
        } else {
            System.out.println("Livro não encontrado na biblioteca.");
        }
    }

    public void mostrarLivros() {
        if (livros.isEmpty()) {
            System.out.println("A biblioteca está vazia.");
        } else {
            System.out.println("Livros na biblioteca:");
            for (Livro livro : livros) {
                livro.mostrarInfo();
            }
        }
    }
}

public class ReservaLivros {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(3);

        Livro livro1 = new Livro("1984", "George Orwell", 1949);
        Livro livro2 = new Livro("Dom Quixote", "Miguel de Cervantes", 1605);
        Livro livro3 = new Livro("O Hobbit", "J.R.R. Tolkien", 1937);
        Livro livro4 = new Livro("A Revolução dos Bichos", "George Orwell", 1945);

        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.adicionarLivro(livro3);
        biblioteca.adicionarLivro(livro4); // Excedendo a capacidade

        biblioteca.mostrarLivros();

        biblioteca.removerLivro(livro2);
        biblioteca.mostrarLivros();
    }
}
