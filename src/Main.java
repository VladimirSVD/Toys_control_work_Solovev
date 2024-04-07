
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Functions k = new Functions();
        Scanner sc = new Scanner(System.in);
        System.out.println("Здравствуйте! Вы попали на розыгрыш призов магазина Игрушки.");
        while (true) {
            System.out.println("Для продолжения выберите: ");
            System.out.println("1 - Для начала работы с программой добавте новые игрушки в призовой фонд.");
            System.out.println("2 - Изменение вероятности попадания (веса) игрушки в выигрыш приза.");
            System.out.println("3 - Розыгрыш призов и сохранение результатов.");
            System.out.println("4 - Тестовый розыгрыш призов (10 раз подряд).");
            System.out.println("0 - Выход из программы.");
            System.out.print("Выберите вариант: ");
            var selection = sc.next();
            switch (selection) {
                case "1":
                    k.addPrize();
                    break;
                case "2":
                    k.editFrequency();
                    break;
                case "3":
                    k.prizeRaffle();
                    break;
                case "4":
                    k.testRaffle();
                    break;
                case "0":
                {
                    System.out.println("До новых встреч!");
                    System.exit(0);
                }
                default: System.out.println("Некорректный ввод данных. Наберите снова");
            }
        }
    }
}
