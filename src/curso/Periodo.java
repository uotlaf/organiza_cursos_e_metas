package curso;

import java.util.ArrayList;

public class Periodo {
    private int periodo; //Número na ordem
    private ArrayList<Disciplina> disciplinas; //Disciplinas cadastradas no período

    public Periodo(int periodo){
        this.periodo = periodo;
        this.disciplinas = new ArrayList<>();
    }

    public int getPeriodo(){
        return this.periodo;
    }

    public ArrayList<Disciplina> getDisciplinas(){
        return this.disciplinas;
    }
    /*
    public void addDisciplina(Disciplina[] disciplinas){
        for(int i = 0; i < disciplinas.length; i++){
            this.disciplinas.add(disciplinas[i]);
        }
    }  
    */
    public void addDisciplina(Disciplina... disciplinas){
        for(Disciplina disciplina : disciplinas){
            this.disciplinas.add(disciplina);
        }
    }
}
