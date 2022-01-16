import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import curso.*;
import pessoa.*;

public class Main{
    public static void main(String args[]){
        Sistema sistema = new Sistema();
        Scanner input = new Scanner(System.in);
        ArrayList<Periodo> periodos = new ArrayList<>();
        String areas[] = {"Ciências humanas", "Ciências exatas", "Ciências da natureza"};
        String tipos[] = {"Básica", "Eletiva grupo 1", "Eletiva grupo 2"};
        String dias_semana[] = {"Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira"};
        String turnos[] = {"Matutino", "Vespertino", "Noturno"};
        String informacoes_aluno = "";
        String mensagem_erro = "";
        String mensagem_aviso = "";
        boolean matriculado = false;
        boolean periodo_finalizado = true;
        boolean atribuido;
        boolean prerequisito_satisfeito;
        boolean conflito_horario;
        int periodo_atual = 1;
        int operacao = -1;
        int confirmacao;
        Aluno aluno;

        //########## PRECADASTRO DE DISCIPLINAS ##########
        periodos.add(new Periodo(1));
        periodos.add(new Periodo(2));
        periodos.add(new Periodo(3)); 
        //Período 1
        Disciplina disciplina_1 =  new Disciplina(1,  "Introdução à computação",      "Ciências exatas",  "Básica", "Segunda-feira", "Vespertino");
        Disciplina disciplina_2 =  new Disciplina(2,  "Algoritmos I",                 "Ciências exatas",  "Básica", "Terça-feira",   "Vespertino");
        Disciplina disciplina_3 =  new Disciplina(3,  "Cálculo I",                    "Ciências exatas",  "Básica", "Quarta-feira",  "Vespertino");
        Disciplina disciplina_4 =  new Disciplina(4,  "Cálculo vetorial",             "Ciências exatas",  "Básica", "Quinta-feira",  "Vespertino");
        Disciplina disciplina_5 =  new Disciplina(5,  "Ética e cidadania",            "Ciências humanas", "Básica", "Sexta-feira",   "Vespertino");
        periodos.get(0).addDisciplina(disciplina_1, disciplina_2, disciplina_3, disciplina_4, disciplina_5);
        //Período 2
        Disciplina disciplina_6 =  new Disciplina(6,  "Física I",                     "Ciências exatas",  "Básica", "Segunda-feira", "Vespertino", disciplina_3);
        Disciplina disciplina_7 =  new Disciplina(7,  "Linguagem de programação I",   "Ciências exatas",  "Básica", "Terça-feira",   "Vespertino", disciplina_2);
        Disciplina disciplina_8 =  new Disciplina(8,  "Matemática discreta e lógica", "Ciências exatas",  "Básica", "Quarta-feira",  "Vespertino");
        Disciplina disciplina_9 =  new Disciplina(9,  "Cálculo II",                   "Ciências exatas",  "Básica", "Quinta-feira",  "Noturno",    disciplina_3);
        Disciplina disciplina_10 = new Disciplina(10, "Álgebra linear I",             "Ciências exatas",  "Básica", "Sexta-feira",   "Vespertino", disciplina_4);
        periodos.get(1).addDisciplina(disciplina_6, disciplina_7, disciplina_8, disciplina_9, disciplina_10);
        //Período 3
        Disciplina disciplina_11 = new Disciplina(11, "Física III",                   "Ciências exatas",  "Básica", "Segunda-feira", "Vespertino", disciplina_6);
        Disciplina disciplina_12 = new Disciplina(12, "Arquitetura de computadores",  "Ciências exatas",  "Básica", "Terça-feira",   "Vespertino", disciplina_8);
        Disciplina disciplina_13 = new Disciplina(13, "Estrutura de dados I",         "Ciências exatas",  "Básica", "Quarta-feira",  "Vespertino", disciplina_7);
        Disciplina disciplina_14 = new Disciplina(14, "Linguagem de programação II",  "Ciências exatas",  "Básica", "Quinta-feira",  "Vespertino", disciplina_2);
        Disciplina disciplina_15 = new Disciplina(15, "Cálculo III",                  "Ciências exatas",  "Básica", "Sexta-feira",   "Noturno",    disciplina_9);
        periodos.get(2).addDisciplina(disciplina_11, disciplina_12, disciplina_13, disciplina_14, disciplina_15);

        //Todas as disciplinas cadastradas
        ArrayList<Disciplina> disciplinas = new ArrayList<>(Arrays.asList(new Disciplina[] {disciplina_1, disciplina_2, disciplina_3, disciplina_4, disciplina_5, disciplina_6, disciplina_7, disciplina_8, disciplina_9, disciplina_10, disciplina_11, disciplina_12, disciplina_13, disciplina_14, disciplina_15}));
        //Disciplinas que ainda não foram cursadas
        ArrayList<Disciplina> disciplinas_disponiveis = new ArrayList<>(Arrays.asList(new Disciplina[] {disciplina_1, disciplina_2, disciplina_3, disciplina_4, disciplina_5, disciplina_6, disciplina_7, disciplina_8, disciplina_9, disciplina_10, disciplina_11, disciplina_12, disciplina_13, disciplina_14, disciplina_15}));

        //Início do programa
        System.out.print("\033[2J \033[H");
        System.out.println(" [Cadastro do aluno]");
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("Área de interesse: ");
        String area_interesse = input.nextLine();
        aluno = new Aluno(nome, area_interesse);

        while(true){
            System.out.print("\033[2J \033[H");

            if(!mensagem_aviso.equals("")) System.out.println("aviso: "+mensagem_aviso+"\n"); ////Imprime um aviso caso exista
            if(!mensagem_erro.equals("")) System.out.println("erro: "+mensagem_erro+"\n"); //Imprime mensagem de erro caso exista     

            System.out.println("       [Menu]");
            System.out.println("Aluno: "+aluno.getNome()+" | Período: "+periodo_atual+"\n");
            System.out.println("1. Cadastrar disciplina");
            System.out.println("2. Realizar matrícula");
            System.out.println("3. Finalizar matrícula");
            System.out.println("4. Atribuir notas");
            System.out.println("5. Finalizar período");
            System.out.println("6. Consultar sequência aconselhada");
            System.out.println("7. Consultar sugestão de disciplinas");

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
            
            mensagem_erro = "";
            mensagem_aviso = "";
            System.out.print("\033[2J \033[H");
            switch(operacao){
                case 1: //Cadastrar disciplina
                    System.out.println(" [Cadastro de disciplina]");
                    System.out.print("Nome: ");
                    nome = input.next();

                    int area = sistema.menu("- Área da disciplina", new String[] {"Ciências humanas", "Ciências exatas", "Ciências da natureza"}, false);
                    int tipo = sistema.menu("- Tipo de disciplina", new String[] {"Básica", "Eletiva grupo 1", "Eletiva grupo 2"}, false);
                    int dia_semana = sistema.menu("- Dia da semana", new String[] {"Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira"}, false);
                    int turno = sistema.menu("- Turno da disciplina", new String[] {"Matutino", "Vespertino", "Noturno"}, false);
                    int prerequisito = sistema.menu("- Pré-requisito (digite 0 caso não haja nenhum)", sistema.listarDisciplinas(disciplinas), true);

                    if(prerequisito == 0){
                        disciplinas.add(new Disciplina(disciplinas.size(), nome, areas[area-1], tipos[tipo-1], dias_semana[dia_semana-1], turnos[turno-1]));
                        disciplinas_disponiveis.add(new Disciplina(disciplinas.size(), nome, areas[area-1], tipos[tipo-1], dias_semana[dia_semana-1], turnos[turno-1]));
                    }else{
                        disciplinas.add(new Disciplina(disciplinas.size(), nome, areas[area-1], tipos[tipo-1], dias_semana[dia_semana-1], turnos[turno-1], disciplinas.get(prerequisito-1)));
                        disciplinas_disponiveis.add(new Disciplina(disciplinas.size(), nome, areas[area-1], tipos[tipo-1], dias_semana[dia_semana-1], turnos[turno-1], disciplinas.get(prerequisito-1)));
                    }

                    mensagem_aviso = "Nova disciplina cadastrada.";
                    System.out.print("\033[2J \033[H");
                break; 

                case 2: //Realizar matrícula
                    System.out.print("\033[2J \033[H");

                    //Verifica se o período atual já foi finalizado para realizar a matrícula nas novas disciplinas
                    if(!periodo_finalizado){ 
                        mensagem_erro = "É necessário finalizar o período atual para matricular-se em novas disciplinas.";
                        break;
                    }

                    //Verifica se existe pelo menos uma disciplina cadastrada no sistema
                    if(disciplinas_disponiveis.size() == 0){ 
                        mensagem_erro = "Não há disciplinas disponíveis para a matrícula.";
                        break;
                    }

                    operacao = -1;
                    while(operacao != 0){
                        System.out.print("\033[2J \033[H");
                        if(!mensagem_erro.equals("")) System.out.println("erro: "+mensagem_erro+"\n"); //Imprime mensagem de erro caso exista

                        operacao = sistema.menu("          [Atribuir disciplinas]", sistema.listarDisciplinasStatus(aluno, disciplinas_disponiveis), true);
                        
                        //Comando para voltar para o menu principal
                        if(operacao == 0) break; 
                        //Verifica se a disciplina escolhida existe na lista
                        else if(operacao > 0 && operacao <= disciplinas_disponiveis.size()){ 
                            
                            //Verifica se a disciplina já foi atribuída
                            atribuido = false;
                            for(int disciplina = 0; disciplina < aluno.getDisciplinas().size(); disciplina++){
                                if(disciplinas_disponiveis.get(operacao-1).equals(aluno.getDisciplinas().get(disciplina))){
                                    atribuido = true;
                                    break;
                                }
                            }

                            //Verifica se o pré-requisito foi satisfeito
                            prerequisito_satisfeito = false;
                            if(disciplinas_disponiveis.get(operacao-1).getPreRequisito() != null){
                                for(int disciplina = 0; disciplina < aluno.getDisciplinasCursadas().size(); disciplina++){
                                    if(disciplinas_disponiveis.get(operacao-1).getPreRequisito().equals(aluno.getDisciplinasCursadas().get(disciplina))){
                                        prerequisito_satisfeito = true;
                                        break;
                                    }
                                }
                            }else{
                                prerequisito_satisfeito = true;
                            }
        
                            //Verifica se há conflito de horários
                            conflito_horario = false;
                            for(int disciplina = 0; disciplina < aluno.getDisciplinas().size(); disciplina++){
                                if(disciplinas_disponiveis.get(operacao-1).getDia().equals(aluno.getDisciplinas().get(disciplina).getDia())){
                                    if(disciplinas_disponiveis.get(operacao-1).getTurno().equals(aluno.getDisciplinas().get(disciplina).getTurno())){
                                        conflito_horario = true;
                                        break;
                                    }
                                }
                            }
        
                            
                            if(atribuido){
                                aluno.remDisciplina(disciplinas_disponiveis.get(operacao-1));
                                continue;
                            }

                            if(!prerequisito_satisfeito){
                                mensagem_erro = "Não é possível atribuir disciplina, pré-requisito não satisfeito.";
                                continue;
                            }

                            if(conflito_horario){
                                mensagem_erro = "Não é possível atribuir disciplina, há conflito de horários.";
                                continue;
                            }
                            
                            aluno.addDisciplina(disciplinas_disponiveis.get(operacao-1)); //Matrícula da disciplina
                        }
                    }
                    mensagem_erro = "";
                       
                    
                break;

                case 3: //Finalizar matrícula

                    //Verifica o status do aluno de matriculado
                    if(matriculado){
                        mensagem_erro = "O aluno já está matriculado.";
                        break;
                    }

                    //Verifica se o aluno está matriculado em pelo menos uma disciplina
                    if(aluno.getDisciplinas().size() == 0){
                        mensagem_erro = "O aluno não está matriculado em nenhuma disciplina.";
                        break;
                    }

                    String nomes_disciplinas[] = sistema.listarDisciplinas(aluno.getDisciplinas());

                    System.out.println("- Disciplinas atuais");
                    for(int disciplina = 0; disciplina < aluno.getDisciplinas().size(); disciplina++){
                        System.out.println((disciplina+1)+". "+nomes_disciplinas[disciplina]);
                    }

                    confirmacao = sistema.menu("\nDeseja finalizar a matrícula?", new String[] {"Sim", "Não"}, false);

                    if(confirmacao == 1){
                        matriculado = true;
                        periodo_finalizado = false;
                        mensagem_aviso = "Matrícula finalizada.";
                    }else{
                        matriculado = false;
                    }
                break;

                case 4: //Atribuir notas
                    if(!matriculado){
                        mensagem_erro = "Não é possível atribuir notas, o aluno não está matriculado.";
                        break;
                    }
                    
                    while(true){
                        System.out.print("\033[2J \033[H");
                        if(!mensagem_erro.equals("")) System.out.println("erro: "+mensagem_erro+"\n"); //Imprime mensagem de erro caso exista
                        if(!mensagem_aviso.equals("")) System.out.println("aviso: "+mensagem_aviso+"\n"); //Imprime um aviso caso exista

                        int disciplina = sistema.menu("     [Atribuir notas]", sistema.listarDisciplinas(aluno.getDisciplinas()), true);
                        if(disciplina == 0) break;
                        else{
                            System.out.println("     [Atribuir notas]");
                            System.out.println("Disciplina: "+aluno.getDisciplinas().get(disciplina-1).getNome());
                            System.out.print("Nota(0 a 10 pontos): ");
                            
                            float nota;
                            while(true){
                                try{
                                    nota = input.nextFloat();
                                    break;
                                }catch(InputMismatchException e){
                                    System.out.print("Nota(0 a 10 pontos): ");
                                    input.nextLine();
                                }
                            }

                            if(nota >= 0 && nota <= 10){
                                aluno.getDisciplinas().get(disciplina-1).setNota(nota);
                                mensagem_aviso = "Nota atribuída.";
                            }else{
                                mensagem_erro = "Insira uma nota entre 0 e 10 pontos.";
                            }
                        }
                    }
                    mensagem_erro = "";
                    mensagem_aviso = "";
                    
                break;

                case 5: //Finalizar período

                    if(!matriculado){
                        mensagem_erro = "O aluno ainda não faz parte de uma turma, é necessário matriculá-lo.";
                        break;
                    }
                    if(periodo_finalizado){
                        mensagem_erro = "O período atual já foi finalizado.";
                        break;
                    }

                    String notas_disciplinas[] = sistema.listarDisciplinasNotas(aluno.getDisciplinas());

                    System.out.println("      [Finalizar período]");
                    for(int i = 0; i < aluno.getDisciplinas().size(); i++){
                        System.out.println((i+1)+". "+notas_disciplinas[i]);
                    }

                    confirmacao = sistema.menu("\nDeseja finalizar o período?", new String[] {"Sim", "Não"}, false);

                    if(confirmacao == 1){ //Finalização do período
                        periodo_finalizado = true;
                        matriculado = false;
                        periodo_atual += 1;

                        for(Disciplina disciplina_atual : aluno.getDisciplinas()){
                            if(disciplina_atual.getNota() >= 7){
                                //Insere disciplina finalizada no histórico do aluno
                                aluno.getDisciplinasCursadas().add(disciplina_atual);
                                //Indisponibiliza a disciplina para cursar novamente     
                                disciplinas_disponiveis.remove(disciplina_atual); 
                            }else{
                                disciplina_atual.setNota(0.0f);
                            }
                        }
                        aluno.getDisciplinas().clear();

                        mensagem_aviso = "Período finalizado.";
                    }else{
                        periodo_finalizado = false;  
                    } 
                break;

                case 6: //Sequência aconselhada
                    System.out.println("         [Sequencia aconselhada]");
                    
                    for(int i = 0; i < periodos.size(); i++){
                        //Periodo periodo = periodos.get(i);
                        String disciplinas_recomendadas[] = sistema.listarDisciplinas(periodos.get(i).getDisciplinas());
                        System.out.println("- Período "+(i+1));
                        for(int j = 0; j < periodos.get(i).getDisciplinas().size(); j++){
                            System.out.println(periodos.get(i).getDisciplinas().get(j).getNome());
                        }
                        System.out.println();
                    }
                    
                    System.out.print("Pressione ENTER para voltar.");
                    input.nextLine();
                    input.nextLine();
                break;

                case 7: //Sugestão de disciplinas para o próximo período
                    while(true){
                        System.out.print("\033[2J \033[H");
                        if(!mensagem_erro.equals("")) System.out.println("erro: "+mensagem_erro+"\n"); //Imprime mensagem de erro caso exista     

                        System.out.println("                      [Sugestão de disciplinas]");
                        System.out.println("Quantas disciplinas deseja cursar no próximo período? (Considere de 2 ~ 8)");
                        System.out.print("-> ");
                        
                        int quantidade_disciplinas;
                        while(true){
                            try{
                                quantidade_disciplinas = input.nextInt();
                                break;
                            }catch(InputMismatchException e){
                                System.out.print("-> ");
                                input.nextLine();
                            }
                        }
    
                        if(quantidade_disciplinas >= 2 && quantidade_disciplinas <= 8){
                            if(quantidade_disciplinas > disciplinas_disponiveis.size()){
                                quantidade_disciplinas = disciplinas_disponiveis.size();
                            }
    
                            ArrayList<Disciplina> sugestao_disciplinas = new ArrayList<>();
                            for(int i = 0; i < disciplinas_disponiveis.size(); i++){
                                
                                //Verificar se satisfaz o prerequisito
                                prerequisito_satisfeito = false;
                                if(disciplinas_disponiveis.get(i).getPreRequisito() != null){
                                    for(int j = 0; j < aluno.getDisciplinasCursadas().size(); j++){
                                        if(disciplinas_disponiveis.get(i).getPreRequisito().equals(aluno.getDisciplinasCursadas().get(j))){
                                            prerequisito_satisfeito = true;
                                            break;
                                        }
                                    }
                                }else{
                                    prerequisito_satisfeito = true;
                                }
                                
                                //Verificar se há conflito de horário
                                conflito_horario = false;
                                for(int j = 0; j < sugestao_disciplinas.size(); j++){
                                    if(disciplinas_disponiveis.get(i).getDia().equals(sugestao_disciplinas.get(j).getDia())){
                                        if(disciplinas_disponiveis.get(i).getTurno().equals(sugestao_disciplinas.get(j).getTurno())){
                                            conflito_horario = true;
                                        }
                                    }
                                }
    
                                if(prerequisito_satisfeito){
                                    if(!conflito_horario){
                                        sugestao_disciplinas.add(disciplinas_disponiveis.get(i));
                                        if(quantidade_disciplinas == sugestao_disciplinas.size()){
                                            break;
                                        }
                                    }
                                }
                            }
                            System.out.println("- Disciplinas");
                            String nomes_sugestao_disciplinas[] = sistema.listarDisciplinas(sugestao_disciplinas);
                            for(int i = 0; i < sugestao_disciplinas.size(); i++){
                                System.out.println((i+1)+". "+nomes_sugestao_disciplinas[i]);
                            }
    
                            System.out.print("Pressione ENTER para voltar.");
                            input.nextLine();
                            input.nextLine();
                            break;
    
                        }else{
                            mensagem_erro = "Insira um valor válido.";
                        }
                    }
                break;
                /*
                case 8: //Imprimir disciplinas disponíveis
                    System.out.print("\033[2J \033[H");
                    System.out.println("[disciplinas_disponiveis]");
                    for(int i = 0; i < disciplinas_disponiveis.size(); i++){
                        System.out.println(disciplinas_disponiveis.get(i).getNome());
                    }
                    System.out.println("");
                    
                    System.out.print("Pressione ENTER para voltar.");
                    input.nextLine();
                    input.nextLine();
                break;
                
                case 9: //Imprimir disciplinas cursadas
                    System.out.print("\033[2J \033[H");
                    System.out.println("[aluno.getDisciplinasCursadas()]");
                    for(int i = 0; i < aluno.getDisciplinasCursadas().size(); i++){
                        System.out.println(aluno.getDisciplinasCursadas().get(i).getNome());
                    }
                    System.out.println("");

                    System.out.print("Pressione ENTER para voltar.");
                    input.nextLine();
                    input.nextLine();
                break;
                
                case 10: //Imprimir disciplinas atuais
                    System.out.print("\033[2J \033[H");
                    System.out.println("[aluno.getDisciplinas()]");
                    for(int i = 0; i < aluno.getDisciplinas().size(); i++){
                        System.out.println(aluno.getDisciplinas().get(i).getNome());
                    }
                    System.out.println("");

                    System.out.print("Pressione ENTER para voltar.");
                    input.nextLine();
                    input.nextLine();
                break;
                */
                default:
                    mensagem_erro = "Insira uma opção válida.";
                    break;
            }
        }
    }
}