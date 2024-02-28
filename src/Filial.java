import java.util.ArrayList;
import java.util.Scanner;
public class Filial {
    String nome, endereco, telefone;
    int codigo;
    ArrayList<Livro> livros;
    public Scanner input = new Scanner(System.in);
    public Filial(int codigo,String nome,String endereco,String telefone){
        //this.id = "#FL";
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.livros = new ArrayList<>();
    }
    public Filial(){
        get();
        livros = new ArrayList<>();
    }
    public void get(){
        System.out.print("\nInforme o código da filial: ");
        this.codigo = input.nextInt();
        input.nextLine();
        for (Filial filial : Livraria.filiais){
            if (this.codigo == filial.codigo) {
                break;
            }
        }
        System.out.print("\nInforme o nome da filial: ");
        this.nome = input.nextLine();

        System.out.print("\nInforme o endereço da filial: ");
        this.endereco = input.nextLine();

        System.out.print("\nInforme o telefone de contato da filial: ");
        this.telefone = input.nextLine();

        System.out.println("\nNova filial registrada!");
    }
    public void info() {
        System.out.print("\n#FL"+codigo + "," + nome + "," + endereco + "," + telefone + "\n");
        System.out.println("Livros em estoque: \n");
    }
}
