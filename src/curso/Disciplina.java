package curso;

public class Disciplina {
    private int id;
    private String nome;
    private String area;
    private String tipo;
    private String dia;
    private String turno;
    private Disciplina prerequisito;
    private float nota;

    public Disciplina(int id, String nome, String area, String tipo, String dia, String turno, Disciplina prerequisito){
        this.id = id;
        this.nome = nome;
        this.area = area;
        this.tipo = tipo;
        this.dia = dia;
        this.turno = turno;
        this.prerequisito = prerequisito;
        this.nota = 0.0f;
    }

    public Disciplina(int id, String nome, String area, String tipo, String dia, String turno){
        this.id = id;
        this.nome = nome;
        this.area = area;
        this.tipo = tipo;
        this.dia = dia;
        this.turno = turno;
    }

    public int getID(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public String getArea(){
        return this.area;
    }

    public String getTipo(){
        return this.tipo;
    }

    public String getDia(){
        return this.dia;
    }

    public String getTurno(){
        return this.turno;
    }

    public Disciplina getPreRequisito(){
        return this.prerequisito;
    }

    public float getNota(){
        return this.nota;
    }

    public void setPreRequisito(Disciplina prerequisito){
        this.prerequisito = prerequisito;
    }

    public void setNota(float nota){
        this.nota = nota;
    }
}
