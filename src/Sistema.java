import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import curso.*;
import pessoa.*;

public class Sistema {
    Scanner input = new Scanner(System.in);

    public String[] listarDisciplinas(ArrayList<Disciplina> disciplinas){
        String nomes_disciplinas[] = new String[disciplinas.size()];

        for(int i = 0; i < disciplinas.size(); i++){
            nomes_disciplinas[i] = disciplinas.get(i).getNome();
        }
        return nomes_disciplinas;
    }

    public String[] listarDisciplinasNotas(ArrayList<Disciplina> disciplinas){
        String nota_disciplinas[] = new String[disciplinas.size()];

        for(int i = 0; i < disciplinas.size(); i++){
            nota_disciplinas[i] = disciplinas.get(i).getNome()+" (Nota: "+disciplinas.get(i).getNota()+")";
        }
        return nota_disciplinas;
    }

    public String[] listarDisciplinasStatus(Aluno aluno, ArrayList<Disciplina> disciplinas){
        String lista_disciplinas[] = new String[disciplinas.size()];
        String status;

        for(int i = 0; i < disciplinas.size(); i++){
            status = "não atribuído";
            for(int j = 0; j < aluno.getDisciplinas().size(); j++){
                if(disciplinas.get(i).equals(aluno.getDisciplinas().get(j))){
                    status = "atribuído";
                    break;
                }
            }
            lista_disciplinas[i] = disciplinas.get(i).getNome()+" ["+disciplinas.get(i).getDia()+"/"+disciplinas.get(i).getTurno()+"] - "+status;
        }
        return lista_disciplinas;
    }
    
    public int menu(String titulo, String[] opcoes, boolean voltar){
        int operacao = -1;
        while(true){
            System.out.println(titulo);
            for(int i = 0; i < opcoes.length; i++){
                System.out.println("["+(i+1)+"] " + opcoes[i]);
            }
            if(voltar == true) System.out.println("\n[0] Voltar");
            System.out.print("-> ");
            
            while(true){
                try{
                    operacao = input.nextInt();
                    break;
                }catch(InputMismatchException e){
                    System.out.print("-> ");
                    input.nextLine();
                }
            }
            
            if(voltar == true && operacao == 0) return operacao;
            for(int i = 0; i < opcoes.length; i++){
                if(operacao == i+1) return operacao;
            }
        }
    }
}

