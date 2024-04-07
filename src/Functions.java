import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Functions {
    ArrayList<Object> toys = new ArrayList<>();
    ArrayList<Integer> frequency = new ArrayList<>();
    int id = 0;
    int test = 0;
    public void addPrize()                  //метод создания новой игрушки
    {
        if(toys.isEmpty()) {
            toys.add(new Toy(0, "Sam0", 2));
            toys.add(new Toy(1, "Sam1", 1));
            toys.add(new Toy(2, "Sam2", 3));
            toys.add(new Toy(3, "Sam3", 3));
        }
        System.out.println("\nСписок призового фонда по умолчанию: " + toys);
        Scanner scan = new Scanner(System.in);
        String title;
        int frequency;
        while (true) {
            System.out.print("Введите название игрушки: ");
            title = scan.nextLine();
            if (title.isEmpty()) {
                System.out.println("Не корректный ввод. Повторите опять.");
                break;
            }
            System.out.print("Введите вероятность (вес) игрушки стать призом (от 1 до 9): ");
            String value = scan.nextLine();
            if (isDigit(value)) {
                frequency = Integer.parseInt(value);
                if (frequency <= 0 || frequency > 9) {
                    System.out.println("Не корректный ввод. Повторите ввод.");
                } else {
                    Toy toy = new Toy(toys.size(), title, frequency);
                    if (!toys.contains(toy) || toys.isEmpty()) {
                        toys.add(toy);
                        System.out.println("Игрушка в призовой фонд добавлена.");
                        System.out.println("\nСписок призового фонда: " + toys);
                    } else {
                        System.out.println("Такая игрушка уже есть.");
                    }
                }
            } else {
                System.out.println("Некорректный ввод. Повторите набор.");
            }
            break;
        }
    }

    public void editFrequency() {                      //метод изменения frequency
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите ID игрушки: ");
        String value = scan.nextLine();
        if (isDigit(value)) {
            int selectedId = Integer.parseInt(value);
            if (selectedId >= 0 && selectedId < toys.size()) {
                System.out.println("Игрушка " + ((Toy) toys.get(selectedId)).getToyTitle() +
                        " имеет частоту выборки " + ((Toy) toys.get(selectedId)).getToyVictoryFrequency());
                System.out.print("Введите новый параметр выборки (от 1 до 9): ");
                value = scan.nextLine();
                if (isDigit(value)) {
                    int newFrequency = Integer.parseInt(value);
                    if (newFrequency >= 0 && newFrequency < 10){
                        ((Toy) toys.get(selectedId)).setToyVictoryFrequency(newFrequency);
                        System.out.println("Частота выборки изменена: " + (Toy) toys.get(selectedId));
                    }
                     else {
                    System.out.println("Некорректный ввод. Повторите набор.");
                    }
                } else {
                        System.out.println("Некорректный ввод. Повторите набор.");
                    }
            } else {
                System.out.println("Такой игрушки в призовом фонде нет.");
            }
        } else {
            System.out.println("Некорректный ввод. Повторите набор.");
        }
    }

    public void prizeRaffle() {                 //метод розыгрыша лотереи
        if (toys.size() > 3) {
            for(int i = 0; i < toys.size(); i++){
                int n = 0;
                while(n != ((Toy) toys.get(i)).getToyVictoryFrequency()){
                    frequency.add(i);
                    n++;
                }
            }
        } else {
            System.out.println("В призовом фонде должно быть минимум четыре игрушки.");
            return;
        }
        Random rnd = new Random();
        int id_prize = rnd.nextInt(frequency.size());
        id = frequency.get(id_prize);
        if (test != 1){
            System.out.println("Ваш приз игрушка " + ((Toy) toys.get(id)).getToyTitle() + ", ID: " + ((Toy) toys.get(id)).getToyId());
            saveResult(String.format("ID: %d, Title: %s", ((Toy) toys.get(id)).getToyId(), ((Toy) toys.get(id)).getToyTitle()));
            toys.remove(id);
        }
    }

    public void testRaffle() {
        test = 1;
        ArrayList<Integer> testArray = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            prizeRaffle();
            testArray.add(id);
        }
        System.out.println("ID призовых игрушек пройденного теста: " + testArray);
    }

    private void saveResult(String text) {       //метод записи результата лотереи в файл Result.txt
        File file = new File("Result.txt");
        try {
            file.createNewFile();
        } catch (Exception ignored) {
            throw new RuntimeException();
        }
        try (FileWriter fw = new FileWriter("Result.txt", true)) {
            fw.write(text + "\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static boolean isDigit(String s) throws NumberFormatException {  //метод проверяет наличия цифр в заданной строке
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
