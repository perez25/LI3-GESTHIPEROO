/**
 * Classe Responsável por imprimir todos os dados de output.
 *
 */
package gesthiper.oo.menu;

import gesthiper.oo.Hipermercado;
import gesthiper.oo.ParClienteNumProdutosComprados;
import gesthiper.oo.ParProdutoNumVezesClienteComprou;
import gesthiper.oo.TriploClienteVezesQuefoiCompradoTotalGasto;
import gesthiper.oo.TriploProdutoUnidadesVendidas_e_clientes_que_compraram;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author Perez_25
 */
public class Menu {

    private Hipermercado hiper;
    private Input input;
    private Crono tempos;

    public Menu() {
        this.hiper = new Hipermercado();
        this.input = new Input();
        this.tempos = new Crono();
    }

    public Menu(Hipermercado hiper) {
        this.hiper = hiper.clone();
        this.input = new Input();
        this.tempos = new Crono();
    }

    /**
     * Menu Principal
     *
     * @throws IOException
     */
    public void menu_principal() throws IOException {
        Input i = new Input();
        System.out.println("   ____   __________   ____   __________ ____    ________________   __________ ________               ____       ____   \n"
                + "  6MMMMb/ `MMMMMMMMM  6MMMMb\\ MMMMMMMMMM `MM'    `MM'`MM'`MMMMMMMb. `MMMMMMMMM `MMMMMMMb.            6MMMMb     6MMMMb  \n"
                + " 8P    YM  MM      \\ 6M'    ` /   MM   \\  MM      MM  MM  MM    `Mb  MM      \\  MM    `Mb           8P    Y8   8P    Y8 \n"
                + "6M      Y  MM        MM           MM      MM      MM  MM  MM     MM  MM         MM     MM          6M      Mb 6M      Mb\n"
                + "MM         MM    ,   YM.          MM      MM      MM  MM  MM     MM  MM    ,    MM     MM          MM      MM MM      MM\n"
                + "MM         MMMMMMM    YMMMMb      MM      MMMMMMMMMM  MM  MM    .M9  MMMMMMM    MM    .M9          MM      MM MM      MM\n"
                + "MM     ___ MM    `        `Mb     MM      MM      MM  MM  MMMMMMM9'  MM    `    MMMMMMM9'          MM      MM MM      MM\n"
                + "MM     `M' MM              MM     MM      MM      MM  MM  MM         MM         MM  \\M\\    MMMMMMM MM      MM MM      MM\n"
                + "YM      M  MM              MM     MM      MM      MM  MM  MM         MM         MM   \\M\\           YM      M9 YM      M9\n"
                + " 8b    d9  MM      / L    ,M9     MM      MM      MM  MM  MM         MM      /  MM    \\M\\           8b    d8   8b    d8 \n"
                + "  YMMMM9  _MMMMMMMMM MYMMMM9     _MM_    _MM_    _MM__MM__MM_       _MMMMMMMMM _MM_    \\M\\_          YMMMM9     YMMMM9  \n\n");

        System.out.println("  \t\t\t\t--------------------------------");
        System.out.println("  \t\t\t\t| 1 - Ler ficheiros              |");
        System.out.println("  \t\t\t\t--------------------------------");
        System.out.println("  \t\t\t\t| 2 - Gravar                     |");
        System.out.println("  \t\t\t\t--------------------------------");
        System.out.println("  \t\t\t\t| 3 - Carregar dados             |");
        System.out.println("  \t\t\t\t--------------------------------");
        System.out.println("  \t\t\t\t| 0 - Sair                       |");
        System.out.println("  \t\t\t\t--------------------------------");
        print_linha_comandos();
        int opcao = i.lerInteiro();
        switch (opcao) {
            case 1:
                print_linha("Deseja ler ficheiros default ? (S ou N)");
                String ler_ficheiros_default = " ";
                print_linha_comandos();
                ler_ficheiros_default = input.lerString();
                if (ler_ficheiros_default.equals("N")) {
                    System.out.print("Ficheiro de produtos: ");
                    String f1 = input.lerString();
                    System.out.print("Ficheiro de clientes: ");
                    String f2 = input.lerString();
                    System.out.print("Ficheiro de Compras: ");
                    String f3 = input.lerString();
                    this.tempos.start();
                    this.hiper.leitura_de_ficheiros(f1, f2, f3);
                    this.tempos.stop();
                    print_linha("A leitura dos ficheiros demorou : " + this.tempos.print());

                } else {
                    this.tempos.start();
                    this.hiper.leitura_de_ficheiros("FichProdutos.txt", "FichClientes.txt", "Compras.txt");
                    this.tempos.stop();
                    print_linha("A leitura dos ficheiros demorou : " + this.tempos.print());
                }
                apresenta_estatisticas_de_leitura_1_1();
                menu_after_leitura();
                break;

            case 2:
                print_linha("Nome do ficheiro :");
                print_linha_comandos();
                String nome_ficheiro = this.input.lerString();
                if (this.hiper.gravar_hipermercado(this.hiper, nome_ficheiro) == true) {
                    print_linha("Sucesso!");
                } else {
                    print_linha("Insucesso !");
                }
                menu_principal();
                break;

            case 3:
                print_linha("Nome do ficheiro :");
                print_linha_comandos();
                String nome_ficheiro2 = this.input.lerString();
                this.hiper = this.hiper.load_hipermercado(nome_ficheiro2);
                apresenta_estatisticas_de_leitura_1_1();
                menu_after_leitura();
                break;

            case 0:
                break;
        }

    }

    private void menu_after_leitura() throws IOException {

        limparEcra(4);
        System.out.println("\t\t\t\t--------------------------------");
        System.out.println("\t\t\t\t1 - Consulta de estatisticas");
        System.out.println("\t\t\t\t--------------------------------");
        System.out.println("\t\t\t\t2 - Consultas interactivas");
        System.out.println("\t\t\t\t--------------------------------");
        System.out.println("\t\t\t\t3 - Dados de leitura");
        System.out.println("\t\t\t\t--------------------------------");
        System.out.println("\t\t\t\t0 - Voltar");
        System.out.println("\t\t\t\t--------------------------------");
        print_linha_comandos();
        int opcao;
        opcao = input.lerInteiro();
        switch (opcao) {
            case 1:
                apresenta_estatisticas_de_leitura_1_2();
                break;
            case 2:
                consultas_interactivas();
                break;
            case 3:
                apresenta_estatisticas_de_leitura_1_1();
                menu_after_leitura();
                break;

            case 0:
                menu_principal();
                break;

        }

    }

    private void apresenta_estatisticas_de_leitura_1_1() {
        limparEcra(4);
        print_linha("88888888ba,                       88                                   88               88            88                                          \n"
                + "88      `\"8b                      88                                   88               88            \"\"   ,d                                     \n"
                + "88        `8b                     88                                   88               88                 88                                     \n"
                + "88         88 ,adPPYYba,  ,adPPYb,88  ,adPPYba,  ,adPPYba,     ,adPPYb,88  ,adPPYba,    88  ,adPPYba, 88 MM88MMM 88       88 8b,dPPYba, ,adPPYYba,\n"
                + "88         88 \"\"     `Y8 a8\"    `Y88 a8\"     \"8a I8[    \"\"    a8\"    `Y88 a8P_____88    88 a8P_____88 88   88    88       88 88P'   \"Y8 \"\"     `Y8\n"
                + "88         8P ,adPPPPP88 8b       88 8b       d8  `\"Y8ba,     8b       88 8PP\"\"\"\"\"\"\"    88 8PP\"\"\"\"\"\"\" 88   88    88       88 88         ,adPPPPP88\n"
                + "88      .a8P  88,    ,88 \"8a,   ,d88 \"8a,   ,a8\" aa    ]8I    \"8a,   ,d88 \"8b,   ,aa    88 \"8b,   ,aa 88   88,   \"8a,   ,a88 88         88,    ,88\n"
                + "88888888Y\"'   `\"8bbdP\"Y8  `\"8bbdP\"Y8  `\"YbbdP\"'  `\"YbbdP\"'     `\"8bbdP\"Y8  `\"Ybbd8\"'    88  `\"Ybbd8\"' 88   \"Y888  `\"YbbdP'Y8 88         `\"8bbdP\"Y8\n"
                + "                                                                                                                                                  ");
        System.out.println("  \t\t\t\t o Ficheiro de Produtos :" + this.hiper.getDados_leitura().getFicheiro_produtos());
        System.out.println("  \t\t\t\t o Ficheiro de Clientes : " + this.hiper.getDados_leitura().getFicheiro_clientes());
        System.out.println("  \t\t\t\t o Ficheiro de Compras : " + this.hiper.getDados_leitura().getFicheiro_compras());
        System.out.println("  \t\t\t\t o Total de Produtos : " + this.hiper.getDados_leitura().getTotal_produtos());
        System.out.println("  \t\t\t\t o Total de clientes : " + this.hiper.getDados_leitura().getTotal_clientes());
        System.out.println("  \t\t\t\t o Total de compras : " + this.hiper.getDados_leitura().getTotal_compras());
        System.out.println("  \t\t\t\t o Total de produtos comprados : " + this.hiper.getDados_leitura().getTotal_produtos_diferentes_comprados());
        System.out.println("  \t\t\t\t o Total de produtos não comprados :" + this.hiper.getDados_leitura().getTotal_produtos_nao_comprados());
        System.out.println("  \t\t\t\t o Total de clientes que compraram :" + this.hiper.getDados_leitura().getTotal_clientes_que_compraram());
        System.out.println("  \t\t\t\t o Total de clientes que não compraram :" + this.hiper.getDados_leitura().getTotal_clientes_que_nao_compraram());
        System.out.println("  \t\t\t\t o Total de compras com valor zero :" + this.hiper.getDados_leitura().getTotal_compras_valor_zero());
        System.out.println("  \t\t\t\t o Facturação total :" + this.hiper.getDados_leitura().getFacturacao_total());
        try {
            Thread.sleep(2000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void apresenta_estatisticas_de_leitura_1_2() throws IOException {
        int opcao;
        limparEcra(3);
        print_linha(" _____                       _ _                        _        _       _   _               \n"
                + "/  __ \\                     | | |                      | |      | |     | | (_)              \n"
                + "| /  \\/ ___  _ __  ___ _   _| | |_ __ _ ___    ___  ___| |_ __ _| |_ ___| |_ _  ___ __ _ ___ \n"
                + "| |    / _ \\| '_ \\/ __| | | | | __/ _` / __|  / _ \\/ __| __/ _` | __/ __| __| |/ __/ _` / __|\n"
                + "| \\__/\\ (_) | | | \\__ \\ |_| | | || (_| \\__ \\ |  __/\\__ \\ || (_| | |_\\__ \\ |_| | (_| (_| \\__ \\\n"
                + " \\____/\\___/|_| |_|___/\\__,_|_|\\__\\__,_|___/  \\___||___/\\__\\__,_|\\__|___/\\__|_|\\___\\__,_|___/\n"
                + "                                                                                             \n\n");
        print_linha("----------------------------------------------------------------");
        print_linha("1 - Número total de compras por mês");
        print_linha("----------------------------------------------------------------");
        print_linha("2 - Facturação total por mês (valor total das compras/vendas) e total global");
        print_linha("----------------------------------------------------------------");
        print_linha("3 - Número de distintos clientes que compraram em cada mês ");
        print_linha("----------------------------------------------------------------");
        print_linha("4 - Total de registos de compras inválidos");
        print_linha("----------------------------------------------------------------");
        print_linha("0 - Voltar");
        print_linha("----------------------------------------------------------------");

        print_linha_comandos();
        opcao = this.input.lerInteiro();

        switch (opcao) {
            case 1:
                limparEcra(2);
                this.tempos.start();
                ArrayList<String> res = this.hiper.devolve_compras_mes_a_mes();
                this.tempos.stop();
                print_linha("Demorou : " + this.tempos.print());
                print_linha("MÊS  -  VALOR");
                print_linha("-------------------------------");
                printLista_Strings(res, 0);
                print_linha("-------------------------------");

                print_linha("Prime qualquer tecla");
                input.lerString();
                apresenta_estatisticas_de_leitura_1_2();
                break;

            case 2:
                limparEcra(2);
                this.tempos.start();
                ArrayList<String> res2 = this.hiper.devolve_facturacao_total_por_mes();
                this.tempos.stop();
                print_linha("Demorou : " + this.tempos.print());
                print_linha("-------------------------------");
                print_linha("MÊS - VALOR");
                printLista_Strings(res2, 0);
                print_linha("-------------------------------");

                print_linha("Prime qualquer tecla");
                input.lerString();
                apresenta_estatisticas_de_leitura_1_2();
                break;

            case 3:
                limparEcra(2);
                this.tempos.start();
                ArrayList<String> res3 = this.hiper.devolve_clientes_que_compraram_mes();
                this.tempos.stop();
                print_linha("Demorou : " + this.tempos.print());
                print_linha("MÊS - VALOR");
                print_linha("-------------------------------");
                printLista_Strings(res3, 0);
                print_linha("-------------------------------");
                print_linha("Facturação total : "+this.hiper.getDados_leitura().getFacturacao_total());
                print_linha("Prime qualquer tecla");
                input.lerString();
                apresenta_estatisticas_de_leitura_1_2();

                break;

            case 4:
                limparEcra(2);
                this.tempos.start();
                print_linha("O numero de compras inválidas é de : " + this.hiper.getDados_leitura().getTotal_compras_invalidas());
                this.tempos.stop();
                print_linha("Demorou : " + this.tempos.print());
                print_linha("Deseja imprimir as linhas inválidas para um ficheiro ? (S|N)");
                print_linha_comandos();
                String linhas = this.input.lerString();
                if (linhas.equals("S")) {
                    print_linha_comandos();
                    String f = this.input.lerString();

                    this.hiper.guardar_linhas_invalidas(f);
                    print_linha("Ficheiro criado!");
                }

                print_linha("Prime qualquer tecla");
                input.lerString();
                apresenta_estatisticas_de_leitura_1_2();

                break;

            case 0:
                menu_after_leitura();
                break;

            default:
                print_linha("Opção inexistente");

        }

    }

    private void consultas_interactivas() throws IOException {
        limparEcra(3);
        print_linha("  _____                      _ _              _____       _                      _   _                \n"
                + " / ____|                    | | |            |_   _|     | |                    | | (_)               \n"
                + "| |     ___  _ __  ___ _   _| | |_ __ _ ___    | |  _ __ | |_ ___ _ __ __ _  ___| |_ ___   ____ _ ___ \n"
                + "| |    / _ \\| '_ \\/ __| | | | | __/ _` / __|   | | | '_ \\| __/ _ \\ '__/ _` |/ __| __| \\ \\ / / _` / __|\n"
                + "| |___| (_) | | | \\__ \\ |_| | | || (_| \\__ \\  _| |_| | | | ||  __/ | | (_| | (__| |_| |\\ V / (_| \\__ \\\n"
                + " \\_____\\___/|_| |_|___/\\__,_|_|\\__\\__,_|___/ |_____|_| |_|\\__\\___|_|  \\__,_|\\___|\\__|_| \\_/ \\__,_|___/\n\n");
        print_linha("----------------------------------------------------------------------------------------------------------------");
        print_linha("1 - Lista ordenada com os códigos dos produtos nunca comprados e respectivo total");
        print_linha("----------------------------------------------------------------------------------------------------------------");
        print_linha("2 - Lista ordenada com os códigos dos clientes que nunca compraram e seu total");
        print_linha("----------------------------------------------------------------------------------------------------------------");
        print_linha("3 - Dado um mês válido, determinar o número total de compras e o total de clientes distintos que as realizaram");
        print_linha("----------------------------------------------------------------------------------------------------------------");
        print_linha("4 - Dado 1 cliente para cada mês,saber num de compras ,num produtos q comprou,quanto gastou e o total anual facturado ao cliente");
        print_linha("----------------------------------------------------------------------------------------------------------------");
        print_linha("5 - Dado 1 cód de produto,determinar, mês a mês, num vezes q foi comprado, por quantos clientes diferentes e o total facturado");
        print_linha("----------------------------------------------------------------------------------------------------------------");
        print_linha("6 - Dado 1 cód de produto,saber,mês a mês, quantas vezes foi comprado em N e em P e respectivas facturações");
        print_linha("----------------------------------------------------------------------------------------------------------------");
        print_linha("7 - Dado 1 coódigo cliente determinar a lista de códigos de produtos que mais comprou ,ordenada por ordem decrescente de quantidade e, para quantidades iguais, por ordem alfabética dos códigos;");
        print_linha("----------------------------------------------------------------------------------------------------------------");
        print_linha("8 - Lista de X produtos mais vendidos");
        print_linha("----------------------------------------------------------------------------------------------------------------");
        print_linha("9 - Determinar os X clientes que compraram um maior nuúmero de diferentes produtos, indicando quantos, sendo o critério de ordenação igual a 7;");
        print_linha("----------------------------------------------------------------------------------------------------------------");
        print_linha("10 - Dado o código de um produto, determinar o conjunto dos X clientes que mais o compraram e qual o valor gasto (ordenação cf. 7)");
        print_linha("----------------------------------------------------------------------------------------------------------------");
        print_linha("0 - Voltar");
        print_linha("----------------------------------------------------------------------------------------------------------------");
        limparEcra(3);
        print_linha_comandos();
        int opcao = this.input.lerInteiro();

        switch (opcao) {
            case 1:
                limparEcra(3);
                this.tempos.start();
                print_linha("Total de produtos não comprados : " + this.hiper.getDados_leitura().getTotal_produtos_nao_comprados());
                TreeSet<String> res = this.hiper.devolve_lista_de_produtos_nao_comprados();
                this.tempos.stop();
                printTreeSet_Strings(res, 1);
                print_linha("Demorou : " + this.tempos.print());
                print_linha("Prime qualquer tecla");
                input.lerString();
                consultas_interactivas();
                break;

            case 2:
                limparEcra(3);
                this.tempos.start();
                print_linha("Total de clientes que não compraram : " + this.hiper.getDados_leitura().getTotal_clientes_que_nao_compraram());
                TreeSet<String> res2 = this.hiper.devolve_lista_de_clientes_que_nao_compraram();
                this.tempos.stop();
                printTreeSet_Strings(res2, 1);
                print_linha("Demorou : " + this.tempos.print());
                print_linha("Prime qualquer tecla");
                input.lerString();
                consultas_interactivas();
                break;

            case 3:
                limparEcra(3);
                print_linha("Insira o mês");
                print_linha_comandos();
                int mes = input.lerInteiro();
                this.tempos.start();
                print_linha("Total de compras no mês  " + printMeses(mes) + " " + this.hiper.devolve_numero_de_compras_em_certo_mes(mes));
                TreeSet<String> res3 = this.hiper.devolve_lista_de_clientes_que_compraram_em_certo_mes(mes);
                this.tempos.stop();
                printTreeSet_Strings(res3, 1);
                print_linha("Demorou : " + this.tempos.print());
                print_linha("Prime qualquer tecla");
                input.lerString();
                consultas_interactivas();

            case 4:
                limparEcra(3);
                print_linha("Insira o cliente");
                String cliente = input.lerString();
                this.tempos.start();
                for (int i = 1; i <= 12; i++) {
                    print_linha("\t  Mês " + printMeses(i - 1));
                    print_linha("-----------------------------------");
                    print_linha("o Numero de produtos comprados : " + this.hiper.devolve_numero_produtos_comprados_by_cliente_mes(cliente, i));
                    print_linha("o Numero de compras efectudas : " + this.hiper.devolve_numero_compras_mes_by_cliente(cliente, i));
                    print_linha("o Total gasto " + this.hiper.devolve_quantia_gasta_por_mes_by_cliente(cliente, i));
                    print_linha("-----------------------------------");
                    limparEcra(1);
                }
                print_linha("Gasto anual : " + this.hiper.devolve_quantia_total_gasta_por_cliente(cliente));
                this.tempos.stop();
                print_linha("Demorou : " + this.tempos.print());
                print_linha("Prime qualquer tecla");

                input.lerString();
                limparEcra(3);

                consultas_interactivas();
                break;

            case 5:
                limparEcra(3);
                print_linha("Insira o produto");
                String produto = input.lerString();
                this.tempos.start();
                for (int i = 1; i <= 12; i++) {
                    print_linha("\t  Mês " + printMeses(i - 1));
                    print_linha("-----------------------------------");
                    print_linha("Número de vezes comprado : " + this.hiper.devolve_numero_de_vezes_que_produto_foi_comprado_by_mes(produto, i));
                    print_linha("Número de clientes que compraram : " + this.hiper.devolve_numero_de_clientes_que_compraram_produto_em_mes(produto, i));
                    print_linha("Total facturado : " + this.hiper.devolve_total_facturado_por_produto_em_mes(i, produto));
                    print_linha("-----------------------------------");
                    limparEcra(2);
                }
                this.tempos.stop();
                print_linha("Demorou : " + this.tempos.print());
                print_linha("Prime qualquer tecla");
                input.lerString();
                limparEcra(3);
                consultas_interactivas();
                break;

            case 6:
                limparEcra(3);
                print_linha("Insira o produto");
                String produto2 = input.lerString();
                this.tempos.start();
                for (int i = 1; i <= 12; i++) {
                    print_linha("\t  Mês " + printMeses(i - 1));
                    print_linha("------------------------------------------");
                    print_linha("Número de vezes comprado modo N: " + this.hiper.devolve_total_vezes_que_produto_foi_comprado_modo_N_em_mes(produto2, i));
                    print_linha("Facturação modo N: " + this.hiper.devolve_total_facturado_por_produto_em_mes_modo_N(produto2, i));
                    limparEcra(1);
                    print_linha("Número de vezes comprado modo P: " + this.hiper.devolve_total_vezes_que_produto_foi_comprado_modo_P_em_mes(produto2, i));
                    print_linha("Facturação modo P:  " + this.hiper.devolve_total_facturado_por_produto_em_mes_modo_P(produto2, i));

                    print_linha("------------------------------------------");
                    limparEcra(2);
                }
                this.tempos.stop();
                print_linha("Demorou : " + this.tempos.print());
                print_linha("Prime qualquer tecla");
                input.lerString();
                limparEcra(3);
                consultas_interactivas();
                break;

            case 7:
                limparEcra(3);
                print_linha("Insira o cliente");
                String cliente2 = input.lerString();
                this.tempos.start();
                TreeSet<ParProdutoNumVezesClienteComprou> res7 = this.hiper.devolve_lista_de_produtos_mais_comprados_por_cliente(cliente2);
                this.tempos.stop();
                for (ParProdutoNumVezesClienteComprou next : res7) {
                    print_linha("------------------------------------------");
                    print_linha("o " + next.getProduto().getCodigo_produto() + " --> " + next.getVezes_que_cliente_comprou());
                    print_linha("------------------------------------------");
                }
                print_linha("Demorou : " + this.tempos.print());
                print_linha("Prime qualquer tecla");
                input.lerString();
                limparEcra(3);
                consultas_interactivas();

                break;

            case 8:
                limparEcra(3);
                print_linha("Introduza o N");
                int N = this.input.lerInteiro();
                this.tempos.start();
                TreeSet<TriploProdutoUnidadesVendidas_e_clientes_que_compraram> res8 = this.hiper.devolve_lista_de_X_produtos_mais_vendidos(N);
                this.tempos.stop();
                print_linha("Demorou : " + this.tempos.print());
                printDadosQuerie8(res8, N);
                print_linha("Prime qualquer tecla");
                input.lerString();
                limparEcra(3);
                consultas_interactivas();

            case 9:
                limparEcra(3);
                print_linha("Introduza o N ");
                int N2 = this.input.lerInteiro();
                this.tempos.start();
                TreeSet<ParClienteNumProdutosComprados> res9 = this.hiper.devolve_lista_de_X_clientes_que_compraram_mais();
                this.tempos.stop();
                print_linha("Demorou : " + this.tempos.print());
                printDadosQuerie9(res9, N2);
                print_linha("Prime qualquer tecla");
                input.lerString();
                limparEcra(3);
                consultas_interactivas();

            case 10:
                limparEcra(3);
                print_linha("Introduza o produto ");
                String p2 = this.input.lerString();
                print_linha("Introduza o N ");
                int N3 = this.input.lerInteiro();
                this.tempos.start();
                TreeSet<TriploClienteVezesQuefoiCompradoTotalGasto> res10 = this.hiper.devolve_lista_de_clientes_que_mais_compraram_produto(p2);
                this.tempos.stop();
                print_linha("Demorou : " + this.tempos.print());
                printDadosQuerie10(res10, N3);

                print_linha("Prime qualquer tecla");
                input.lerString();
                limparEcra(3);
                consultas_interactivas();

            case 0:
                menu_after_leitura();
                break;

        }

    }

    private void print_linha(String linha) {
        System.out.println(linha);

    }

    private void print_linha_comandos() {
        System.out.print("GESTHIPER-OO/");
    }

    private void printLista_Strings(ArrayList<String> l, int interval) {
        for (String l1 : l) {
            print_linha(l1);
        }

    }

    private void printTreeSet_Strings(TreeSet<String> t, int interval) throws IOException {
        int pagina = 0;
        if (interval == 0) {
            for (String f : t) {
                print_linha(" o " + f);
            }
        } else {
            String opcao = " ";
            int contador = 0;
            print_linha("-------------------------------");
            for (String f : t) {
                print_linha(" o " + f);
                contador++;

                if (contador == 10) {
                    limparEcra(2);
                    print_linha("-------------Página " + pagina + " ----------------");
                    print_linha("Deseja continuar ? (S ou N)");
                    print_linha_comandos();

                    opcao = this.input.lerString();
                    if (opcao.equals("N")) {
                        break;
                    } else {
                        limparEcra(3);
                        contador = 0;
                    }
                    pagina++;
                }

                print_linha("-------------------------------");

            }

        }

    }

    private void printDadosQuerie8(TreeSet<TriploProdutoUnidadesVendidas_e_clientes_que_compraram> res, int N) {
        int i = 0;
        int contador = 0;
        int pagina = 0;
        String opcao;
        for (TriploProdutoUnidadesVendidas_e_clientes_que_compraram res2 : res) {

            print_linha(i + 1 + " º " + " - " + res2.toString());
            print_linha("------------------------------------------------------------------------------------------");
            i++;
            if (i == N) {
                return;
            }
            contador++;
            if (contador == 10) {
                limparEcra(2);
                print_linha("-------------Página " + pagina + " ----------------");
                print_linha("Deseja continuar ? (S ou N)");
                print_linha_comandos();

                opcao = this.input.lerString();
                if (opcao.equals("N")) {
                    break;
                } else {
                    limparEcra(3);
                    contador = 0;
                    pagina++;
                }

            }

        }

    }

    private void printDadosQuerie9(TreeSet<ParClienteNumProdutosComprados> res, int N) {
        int i = 0;
        int contador = 0;
        int pagina = 0;
        String opcao;
        for (ParClienteNumProdutosComprados res2 : res) {

            print_linha(i + 1 + " º " + " - " + res2.getCliente().getCodigo_cliente() + " - UN " + res2.getProdutos_comprados());
            print_linha("------------------------------------------------------------------------------------------");
            i++;
            if (i == N) {
                return;
            }
            contador++;
            if (contador == 10) {
                limparEcra(2);
                print_linha("-------------Página " + pagina + " ----------------");
                print_linha("Deseja continuar ? (S ou N)");
                print_linha_comandos();

                opcao = this.input.lerString();
                if (opcao.equals("N")) {
                    break;
                } else {
                    limparEcra(3);
                    contador = 0;
                    pagina++;
                }

            }

        }

    }

    private void printDadosQuerie10(TreeSet<TriploClienteVezesQuefoiCompradoTotalGasto> res, int N) {
        int i = 0;
        int contador = 0;
        int pagina = 0;
        String opcao;
        for (TriploClienteVezesQuefoiCompradoTotalGasto res2 : res) {

            print_linha(res2.toString());
            print_linha("------------------------------------------------------------------------------------------");
            i++;
            if (i == N) {
                return;
            }
            contador++;
            if (contador == 10) {
                limparEcra(2);
                print_linha("-------------Página " + pagina + " ----------------");
                print_linha("Deseja continuar ? (S ou N)");
                print_linha_comandos();

                opcao = this.input.lerString();
                if (opcao.equals("N")) {
                    break;
                } else {
                    limparEcra(3);
                    contador = 0;
                    pagina++;
                }

            }

        }

    }

    private String printMeses(int x) {
        String c = "";
        switch (x) {
            case 0:
                c = "Janeiro";
                break;
            case 1:
                c = "Fevereiro";
                break;
            case 2:
                c = "Março";
                break;
            case 3:
                c = "Abril";
                break;
            case 4:
                c = "Maio";
                break;
            case 5:
                c = "Junho";
                break;
            case 6:
                c = "Julho";
                break;
            case 7:
                c = "Agosto";
                break;
            case 8:
                c = "Setembro";
                break;
            case 9:
                c = "Outubro";
                break;
            case 10:
                c = "Novembro";
                break;
            case 11:
                c = "Dezembro";
                break;

        }
        return c;
    }

    private void limparEcra(int x) {
        for (int i = 0; i < x; i++) {
            System.out.println("");
        }
    }

}
