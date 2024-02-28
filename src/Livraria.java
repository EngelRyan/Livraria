import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Livraria {
    static ArrayList<Filial>filiais = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    static String linha;


    public static void main(String[] args) throws IOException {
        open();
        boolean condition = true;

        while(condition){
            System.out.println(
                    """

                            1 – Cadastrar novo livro
                            2 – Listar livros
                            3 – Buscar livros por nome
                            4 – Buscar livros por categoria
                            5 – Buscar livros por preço
                            6 – Busca por quantidade em estoque
                            7 – Valor total no estoque
                            8 - Listagem de estoque
                            9 - Atualizar estoque
                            10 - Cadastrar nova filial
                            11 - Busca livro por código
                            0 – Encerrar atividades""");
            System.out.print("\n O que deseja fazer? ");

            String option = input.next();

            switch (option) {
                case "0" -> {
                    System.out.println("""

                            Deseja sair sem atualizar o estoque?
                            0) Sair sem salvar
                            9) Atualizar estoque""");
                    option = input.next();
                    if (option.equals("0")) {
                        condition = false;
                    } else if (option.equals("9")) {
                        save_on_file();
                        condition = false;
                    }
                }
                case "1" -> add_livro();
                case "2" -> list_Livros();
                case "3" -> search_Name();
                case "4" -> search_Categoia();
                case "5" -> search_Preco();
                case "6" -> search_Estoque();
                case "7" -> total_Value_Estoque();
                case "8" -> list_Estoque();
                case "9" -> save_on_file();
                case "10" -> new_Filial();
                case "11" -> search_codigo();
                default -> System.out.println("\nOpção inválida!\n");
            }
        }
    }
    public static void save_on_file() throws IOException {
        FileOutputStream escrever_arquivo = new FileOutputStream("src/Estoque de Livros.txt", true);
        PrintWriter writer = new PrintWriter(escrever_arquivo);
        clear_File();
        for(Filial filial : filiais){
            linha = "#FL" + filial.codigo + "," +
                    filial.nome + "," +
                    filial.endereco + "," +
                    filial.telefone;
            writer.println(linha);

            for (Livro livro : filial.livros) {
                linha = livro.codigo + "," +
                        livro.title + "," +
                        livro.ano + "," +
                        livro.categoria + "," +
                        livro.editora + ",R$" +
                        livro.valor + "," +
                        livro.qtdestoque;
                writer.println(linha);
            }
        }
        writer.close();
        System.out.println("\nLivros exportados com sucesso para o estoque");
    }
    public static void open() throws FileNotFoundException {
        FileInputStream file = new FileInputStream("src/Estoque de Livros.txt");
        Scanner read = new Scanner(file);
        String[] SL;
        Filial atualFilial = null;

        while(read.hasNextLine()){
            linha = read.nextLine();
            SL = linha.split(",");

            if(SL[0].startsWith("#FL")){
                atualFilial = new Filial(Integer.parseInt(SL[0].replace("#FL","")),SL[1],SL[2],SL[3]);
                atualFilial.livros = new ArrayList<>();
                filiais.add(atualFilial);
            }
            if (atualFilial != null  && SL.length == 7){
                atualFilial.livros.add(new Livro(SL[0], SL[1], Integer.parseInt(SL[2]), SL[3], SL[4], Double.parseDouble(SL[5].replace("R$", "").replace(",", "").trim()), Integer.parseInt(SL[6])));
            }
        }
    }
    public static void list_Estoque() {
        input.nextLine();
        System.out.print("\nInforme em qual filial deseja listar o estoque: ");
        int codfilial = input.nextInt();
        input.nextLine();
        boolean foundfilial = false;

        for(Filial filial : filiais){
            if (codfilial == filial.codigo) {
                foundfilial = true;
                if (filial.livros.isEmpty()) {
                    System.out.println("\nNão existem livros nesta filial!");
                } else {
                    filial.info();
                    for(Livro livro : filial.livros){
                        livro.info();
                    }
                    double totalestoque = 0;
                    for (Livro livro : filial.livros) {
                        totalestoque += (livro.valor * livro.qtdestoque);
                    }
                    System.out.println("\nO total de valor em estoque é R$ " + totalestoque);
                }
            }
        }
        if(!foundfilial){
            System.out.println("\nFilial inexistente!");
        }
    }
    public static void add_livro(){
        System.out.print("\nInforme em qual filial deseja adicionar o novo livro: ");
        int codfilial = input.nextInt();
        boolean foundfilial = false;
        for(Filial filial : filiais){
            if (codfilial == filial.codigo){
                foundfilial = true;
                filial.livros.add(new Livro());
            }
        }
        if(!foundfilial){
            System.out.println("\nFilial inexistente!");
        }
    }
    public static void list_Livros() {
        System.out.print("\nInforme em qual filial deseja ver os livros: ");
        int codfilial = input.nextInt();
        boolean foundfilial = false;
        for(Filial filial : filiais){
            if (codfilial == filial.codigo){
                foundfilial = true;
                filial.info();
                for (Livro livro : filial.livros) {
                    livro.info();
                }
                if(filial.livros.isEmpty()){
                    System.out.println("\nNão existem livros nesta filial!\n");
                }
            }
        }
        if(!foundfilial){
            System.out.println("\nFilial inexistente!");
        }
    }
    public static void search_Name() {
        input.nextLine();
        System.out.print("\nInforme em qual filial deseja ver os livros: ");
        int codfilial = input.nextInt();
        input.nextLine();
        boolean foundfilial = false;
        for(Filial filial : filiais){
            if (codfilial == filial.codigo) {
                foundfilial = true;
                if (filial.livros.isEmpty()) {
                    System.out.println("\nNão existem livros nesta filial!");
                } else {
                    System.out.print("\nDigite o nome do livro que deseja buscar: ");
                    String nome = input.nextLine();
                    boolean foundlivro = false;
                    for (Livro livro : filial.livros) {
                        if (nome.equals(livro.title)) {
                            livro.info();
                            foundlivro = true;
                        }
                    }
                    if (!foundlivro) {
                        System.out.println("\nNão existem livros com este título!\n");
                    }
                }
            }
        }
        if(!foundfilial){
            System.out.println("\nFilial inexistente!");
        }
    }
    public static void search_Categoia() {
        input.nextLine();
        System.out.print("\nInforme em qual filial deseja ver os livros: ");
        int codfilial = input.nextInt();
        input.nextLine();
        boolean foundfilial = false;
        for(Filial filial : filiais){
            if (codfilial == filial.codigo) {
                foundfilial = true;
                if (filial.livros.isEmpty()) {
                    System.out.println("\nNão existem livros nesta filial!");
                } else {
                    System.out.print("\nDigite a categoria do livro que deseja buscar: ");
                    String categoria = input.nextLine();
                    boolean foundcategoria = false;
                    boolean categoriaPrinted = false;

                    for (Livro livro : filial.livros) {
                        if (categoria.equals(livro.categoria)) {
                            foundcategoria = true;
                            if (!categoriaPrinted) {
                                categoriaPrinted = true;
                            }
                            livro.info();
                        }
                    }
                    if(!foundcategoria) {
                        System.out.println("\nNão existem livros desta categoria!\n");
                    }
                }
            }
        }
        if(!foundfilial){
            System.out.println("\nFilial inexistente!");
        }
    }
    public static void search_Preco() {
        input.nextLine();
        System.out.print("\nInforme em qual filial deseja ver os livros: ");
        int codfilial = input.nextInt();
        input.nextLine();
        boolean foundfilial = false;
        for(Filial filial : filiais){
            if (codfilial == filial.codigo) {
                foundfilial = true;
                if (filial.livros.isEmpty()) {
                    System.out.println("\nNão existem livros nesta filial!");
                } else {
                    System.out.print("\nDigite o preço limite dos livro que deseja: ");
                    double preco = input.nextDouble();
                    input.nextLine();
                    boolean foundpreco = false;
                    boolean precoprint = false;

                    for (Livro livro : filial.livros) {
                        if (preco >= livro.valor) {
                            foundpreco = true;
                            if (!precoprint) {
                                precoprint = true;
                            }
                            livro.info();
                        }
                    }
                    if(!foundpreco){
                        System.out.println("\nNão existem livros abaixo deste valor!\n");
                    }
                }
            }
        }
        if(!foundfilial){
            System.out.println("\nFilial inexistente!");
        }
    }
    public static void search_Estoque() {
        input.nextLine();
        System.out.print("\nInforme em qual filial deseja ver os livros: ");
        int codfilial = input.nextInt();
        input.nextLine();
        boolean foundfilial = false;
        for(Filial filial : filiais){
            if (codfilial == filial.codigo) {
                foundfilial = true;
                if (filial.livros.isEmpty()) {
                    System.out.println("\nNão existem livros nesta filial!");
                } else {
                    System.out.print("\nDigite a quantidade em estoque do livro que deseja buscar: ");
                    int estoque = input.nextInt();
                    input.nextLine();
                    boolean foundestoque = false;
                    boolean estoqueprint = false;

                    for (Livro livro : filial.livros) {
                        if (estoque <= livro.qtdestoque) {
                            foundestoque = true;
                            if (!estoqueprint) {
                                estoqueprint = true;
                            }
                            livro.info();
                        }
                    }
                    if(!foundestoque){
                        System.out.println("\nNão existe nenhum livro com mais que essa quantidade em estoque informada!");
                    }
                }
            }
        }
        if(!foundfilial){
            System.out.println("\nFilial inexistente!");
        }
    }
    public static void total_Value_Estoque() {
        input.nextLine();
        System.out.print("\nInforme em qual filial deseja ver os livros: ");
        int codfilial = input.nextInt();
        input.nextLine();
        boolean foundfilial = false;
        for(Filial filial : filiais){
            if (codfilial == filial.codigo) {
                foundfilial = true;
                if (filial.livros.isEmpty()) {
                    System.out.println("\nNão existem livros nesta filial!");
                } else {
                    double totalestoque = 0;
                    for (Livro livro : filial.livros) {
                        totalestoque += (livro.valor * livro.qtdestoque);
                    }
                    System.out.println("\nO total de valor em estoque é R$ " + totalestoque);
                }
            }
        }
        if(!foundfilial){
            System.out.println("\nFilial inexistente!");
        }
    }
    public static void new_Filial(){
        filiais.add(new Filial());
    }
    public static void clear_File() throws IOException {
        String clear_file = "src/Estoque de Livros.txt";
        FileWriter fileWriter = new FileWriter(clear_file, false);
        fileWriter.close();
    }
    public static void search_codigo(){
        String title = "";
        String editora = "";
        String categoria = "";
        int ano = 0;
        input.nextLine();
        System.out.print("\nInforme o código do livro que deseja buscar: ");
        String code = input.nextLine();
        boolean foundlivro = false;
        for(Filial filial : filiais){
            for(Livro livro : filial.livros){
                if(code.equals(livro.codigo)){
                    foundlivro = true;
                    title = livro.title;
                    editora = livro.editora;
                    categoria = livro.categoria;
                    ano = livro.ano;
                }
            }
        }
        if(foundlivro){
            double totalestoque = 0;
            System.out.println(">>>>>Cod#"+ code);
            System.out.println("Titulo/Editora: " + title+"/"+editora);
            System.out.println("Categoria: "+ categoria);
            System.out.println("Ano: "+ ano);
            for(Filial filial : filiais){
                for(Livro livros : filial.livros){
                    if(code.equals(livros.codigo)){
                        System.out.println("Valor: R$ "+ livros.valor+" >>>"+filial.nome+",estoque: "+ livros.qtdestoque+" unidades" );
                        totalestoque += (livros.qtdestoque* livros.valor);
                    }
                }
            }
            System.out.println("Valor total em estoque: "+totalestoque);
        }
        else{
            System.out.println("\nNão existem livros com esse código");
        }
    }
}
