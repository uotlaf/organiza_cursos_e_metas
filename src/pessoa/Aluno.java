package pessoa;

import java.util.ArrayList;
import curso.*;

public class Aluno extends Pessoa{
    private String area_interesse;
    private ArrayList<Disciplina> disciplinas; //Disciplinas em que o aluno está matriculado atualmente
    private ArrayList<Disciplina> disciplinas_cursadas;

    public Aluno(String nome, String area_interesse){
        super(nome);
        this.area_interesse = area_interesse;
        disciplinas = new ArrayList<>();
        disciplinas_cursadas = new ArrayList<>();
    }

    public String getAreaInteresse(){
        return this.area_interesse;
    }

    public ArrayList<Disciplina> getDisciplinas(){
        return this.disciplinas;
    }

    public ArrayList<Disciplina> getDisciplinasCursadas(){
        return this.disciplinas_cursadas;
    }

    public void setAreaInteresse(String area_interesse){
        this.area_interesse = area_interesse;
    }

    public void addDisciplina(Disciplina disciplina){
        boolean existe = false;

        for(int i = 0; i < disciplinas.size(); i++){
            if(disciplina.equals(disciplinas.get(i))){
                existe = true;
                break;
            }
        }
        
        if(!existe) this.disciplinas.add(disciplina);
        else this.disciplinas.remove(disciplina);
    }

    public void remDisciplina(Disciplina disciplina){
        this.disciplinas.remove(disciplina);
    }
}
