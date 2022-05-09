import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Insira a primeira palavra: ");
        String p1 = scan.nextLine();
        System.out.println("Insira a segunda palavra: ");
        String p2 = scan.nextLine();

        int[][] tabela = new int[p1.length()+1][p2.length()+1];

        //Preenchendo a última linha da tabela para os casos base
        for (int j = 0; j < p2.length() + 1; j++){
            tabela[p1.length()][j] = p2.length() - j;
        }

        //Preenchendo a última coluna da tabela para os casos base
        for(int i = 0; i < p1.length() + 1; i++){
            tabela[i][p2.length()] = p1.length() - i;
        }

        //Resolvendo do fim da tabela para o começo
        for(int i = p1.length() - 1; i > -1; i--){
            for(int j = p2.length() - 1; j > -1; j--){
                //Se as letras forem iguais, basta andar uma casa na diagonal
                if(p1.charAt(i) == p2.charAt(j)){
                    tabela[i][j] = tabela[i+1][j+1];
                } //Se forem diferentes, pegar o valor mínimo entre as 3 direções possíveis
                  //Adicionamos 1 ao resultado pois houve uma operação
                  //Direita (inserir), para baixo (deletar), diagonal (substituir)
                else {
                    //Isso é muito feio mas infelizmente Java não tem Math.min com 3 parâmetros
                    tabela[i][j] = 1 + Math.min(Math.min(tabela[i+1][j], tabela[i][j+1]), tabela[i+1][j+1]);
                }
            }
        }

        //Imprimindo a tabela
        for (int[] ints : tabela) {
            for (int j = 0; j < tabela[0].length; j++) {
                System.out.print(ints[j]);;
            }
            System.out.print('\n');
        }

        //A distância mínima entre as palavras vai estar na primeira posição da tabela
        //Conseguimos ela através do backtracking feito
        System.out.println("Edit distance: " + tabela[0][0]);
    }
}
