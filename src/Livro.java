import java.util.Scanner;
public class Livro {
    String title, codigo, editora, categoria;
    int ano , qtdestoque;
    double valor;
    static Scanner input = new Scanner(System.in);
    public Livro(String codigo,String title,int ano ,String categoria,String editora,double valor, int qtdestoque){
        this.title = title;
        this.codigo = codigo;
        this.editora = editora;
        this.categoria = categoria;
        this.ano = ano;
        this.qtdestoque = qtdestoque;
        this.valor = valor;
    }
    public Livro(){
        get();
    }
    public void get(){
        System.out.print("\nInforme o título do livro: ");
        this.title = input.nextLine();

        System.out.println("\nInforme o código do livro: Ex(1234)");
        /*this.codigo = input.nextLine();
        for(Filial filial : Livraria.filiais){
            for(Livro livro : filial.livros){
                do {
                    if(this.codigo.equals(livro.codigo)) {
                        System.out.println("\nEsse código ja existe!");
                        this.codigo = input.nextLine();
                    }
                }while(this.codigo.equals(livro.codigo));
            }
        }*/
        do{
            this.codigo = input.nextLine();
            if(codigo.length() != 4){
                System.out.print("\nInforme um código válido!");
            }
        } while(codigo.length() != 4);

        System.out.print("\nInforme o nome da editora: ");
        this.editora = input.nextLine();

        System.out.print("\nInforme a categoria do livro: ");
        this.categoria = input.nextLine();
        try {
            System.out.print("\nInforme o ano de lançamento:Ex(2023) ");
            do {
                this.ano = input.nextInt();
                input.nextLine();
                if (ano < 1500 || ano > 2023) {
                    System.out.print("\nInforme um ano válido!");
                }
            } while (ano < 1500 || ano > 2023);
        }catch (java.util.InputMismatchException exception) {
            System.out.print("\nEscreva corretamente");
            input.nextLine();
        }
        System.out.print("\nInforme o preço do livro: ");
        this.valor = input.nextDouble();
        input.nextLine();

        System.out.print("\nInforme a quantidade em estoque do livro: ");
        this.qtdestoque = input.nextInt();
        input.nextLine();

        System.out.println("\nLivro registrado");
    }
    public void info(){

        System.out.println(">>>>> Cod#" + this.codigo);
        System.out.println("Titulo/Editora: " + this.title+"/"+this.editora);
        System.out.println("Categoria: " + this.categoria);
        System.out.println("Ano: "+ this.ano);
        System.out.println("Valor: R$ "+ this.valor);
        System.out.println("Estoque: "+ this.qtdestoque);
        System.out.println("Valor total em estoque: R$ "+ this.valor * this.qtdestoque);
        System.out.println("-----------------------------------");
    }
}
