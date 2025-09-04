package app;

import dao.DisciplinaDAO;
import dao.DisciplinaDAOImp;
import model.Disciplina;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainApp {

    static DisciplinaDAO disciplinaDao = new DisciplinaDAOImp();

    @SuppressWarnings("resource")
    public static void main(String[] args) throws InterruptedException, ParseException {
        Scanner console = new Scanner(System.in);
        
        disciplinaDao.insert(new Disciplina(1, "BD1", 200));
        disciplinaDao.insert(new Disciplina(2, "POO1", 240));
        disciplinaDao.insert(new Disciplina(3, "BD1", 220));
        disciplinaDao.insert(new Disciplina(4, "POO2", 230));
        
        menuDisciplina(console);
        console.close();
    }

    public static Scanner menuDisciplina(Scanner console) throws InterruptedException, ParseException {
        int opcao = 0;
        do {
            System.out.println("\n\n");
            System.out.println("    ### Tela: Disciplina  ###");
            System.out.println("    =========================");
            System.out.println("    |     1 - Cadastrar     |");
            System.out.println("    |     2 - Listar        |");
            System.out.println("    |     3 - Alterar       |");
            System.out.println("    |     4 - Excluir       |");
            System.out.println("    |     0 - Sair          |");
            System.out.println("    =========================");
            System.out.print("    Opção -> ");
            opcao = console.nextInt();
            console.nextLine();

            switch (opcao) {
                case 1: console = cadastrar(console); break;
                case 2: console = listar(console); break;
                case 3: console = alterar(console); break;
                case 4: console = excluir(console); break;
                case 0: System.out.println("Saindo do programa..."); break;
                default:
                    System.out.println("Opção inválida!");
                    TimeUnit.SECONDS.sleep(1);
            }
        } while (opcao != 0);
        return console;
    }

    private static Scanner cadastrar(Scanner console) throws ParseException {
        Disciplina d = new Disciplina(0, "", 0);

        System.out.println("\n\n");
        System.out.println("    ### Disciplina - Cadastrar ###");
        System.out.println("    ================================================");

        System.out.print("    |     ID: ");
        d.setIdDisciplina(console.nextInt());
        console.nextLine(); // consumir newline

        System.out.print("    |     Nome: ");
        d.setNomeDisciplina(console.nextLine());

        System.out.print("    |     Carga Horária: ");
        d.setCargaHoraria(console.nextInt());
        console.nextLine();

        System.out.println("    ================================================");

        disciplinaDao.insert(d);
        System.out.println("Disciplina cadastrada com sucesso.");
        return console;
    }

    private static Scanner listar(Scanner console) {
        List<Disciplina> disciplinas = disciplinaDao.findAll();

        System.out.println("\n\n");
        System.out.println("    ###  Disciplina - Listar   ###");
        System.out.println("    ================================================");
        System.out.println("    |     Id\tCarga Horária\tNome");
        for (Disciplina d : disciplinas) {
            System.out.println("    |     " + d.getIdDisciplina()
                    + "\t" + d.getCargaHoraria()
                    + "\t\t" + d.getNomeDisciplina());
        }
        System.out.println("    ================================================");
        System.out.println("    #  pressione ENTER para retornar   #");
        console.nextLine();
        return console;
    }

    private static Scanner alterar(Scanner console) throws ParseException {
        
        System.out.println("\n\n");
        System.out.println("    ###  Disciplina - Alterar  ###");
        System.out.println("    ================================================");

        System.out.print("    |     Id: ");
        int id = console.nextInt();
        console.nextLine();

        Disciplina d = disciplinaDao.findById(id);

        if (d != null) {
            
            System.out.print("    |     Nome: ");
            d.setNomeDisciplina(console.nextLine());

            System.out.print("    |     Carga Horária: ");
            d.setCargaHoraria(console.nextInt());
            console.nextLine();

            System.out.println("    ================================================");

            disciplinaDao.update(d);
            System.out.println("\nDisciplina alterada com sucesso.");
            
        } else {
            System.out.println("\nDisciplina não encontrada.");
        }

        return console;
    }

    private static Scanner excluir(Scanner console) throws ParseException {
        System.out.println("\n\n");
        System.out.println("    ###  Disciplina - Excluir  ###");
        System.out.println("    ================================================");
        System.out.print("    |     Digite o Id: ");
        int id = console.nextInt();
        console.nextLine();
        System.out.println("    ================================================");

        Disciplina d = disciplinaDao.findById(id);

        if (d != null) {
            disciplinaDao.deleteById(id);
            System.out.println("Disciplina excluída com sucesso.");
        } else {
            System.out.println("Disciplina não encontrada.");
        }

        return console;
    }
}