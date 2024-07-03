import br.com.dio.desafio.dominio.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Bootcamp bootcamp = new Bootcamp();

    public static void main(String[] args) {
        bootcamp.setNome("Bootcamp Java Developer");
        bootcamp.setDescricao("Descrição Bootcamp Java Developer");

        while (true) {
            System.out.println("=== Menu ===");
            System.out.println("1 - Adicionar Curso");
            System.out.println("2 - Adicionar Mentoria");
            System.out.println("3 - Adicionar Dev e Inscrever no Bootcamp");
            System.out.println("4 - Progredir Dev");
            System.out.println("5 - Ver Status do Dev");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    adicionarCurso();
                    break;
                case 2:
                    adicionarMentoria();
                    break;
                case 3:
                    adicionarDev();
                    break;
                case 4:
                    progredirDev();
                    break;
                case 5:
                    verStatusDev();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
    }

    private static void adicionarCurso() {
        Curso curso = new Curso();
        System.out.print("Digite o título do curso: ");
        curso.setTitulo(scanner.nextLine());
        System.out.print("Digite a descrição do curso: ");
        curso.setDescricao(scanner.nextLine());
        System.out.print("Digite a carga horária do curso: ");
        curso.setCargaHoraria(scanner.nextInt());
        scanner.nextLine(); // Consumir a nova linha

        bootcamp.getConteudos().add(curso);
        System.out.println("Curso adicionado com sucesso!");
    }

    private static void adicionarMentoria() {
        Mentoria mentoria = new Mentoria();
        System.out.print("Digite o título da mentoria: ");
        mentoria.setTitulo(scanner.nextLine());
        System.out.print("Digite a descrição da mentoria: ");
        mentoria.setDescricao(scanner.nextLine());
        mentoria.setDate(LocalDate.now());

        bootcamp.getConteudos().add(mentoria);
        System.out.println("Mentoria adicionada com sucesso!");
    }

    private static void adicionarDev() {
        Dev dev = new Dev();
        System.out.print("Digite o nome do dev: ");
        dev.setNome(scanner.nextLine());
        dev.inscreverBootcamp(bootcamp);

        System.out.println("Dev adicionado e inscrito no bootcamp com sucesso!");
    }

    private static void progredirDev() {
        System.out.print("Digite o nome do dev: ");
        String nomeDev = scanner.nextLine();

        Dev dev = buscarDevPorNome(nomeDev);
        if (dev != null) {
            dev.progredir();
            System.out.println("Progresso realizado com sucesso!");
        } else {
            System.out.println("Dev não encontrado!");
        }
    }

    private static void verStatusDev() {
        System.out.print("Digite o nome do dev: ");
        String nomeDev = scanner.nextLine();

        Dev dev = buscarDevPorNome(nomeDev);
        if (dev != null) {
            System.out.println("Conteúdos Inscritos " + dev.getNome() + ": " + dev.getConteudosInscritos());
            System.out.println("Conteúdos Concluídos " + dev.getNome() + ": " + dev.getConteudosConluidos());
            System.out.println("XP: " + dev.calcularTotalXp());
        } else {
            System.out.println("Dev não encontrado!");
        }
    }

    private static Dev buscarDevPorNome(String nomeDev) {
        for (Dev dev : bootcamp.getDevsInscritos()) {
            if (dev.getNome().equalsIgnoreCase(nomeDev)) {
                return dev;
            }
        }
        return null;
    }
}
