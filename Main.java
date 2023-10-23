import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        // Создаем несколько ноутбуков
        Notebook notebook1 = new Notebook("Ноутбук 1", 8, 512, "Windows", "Черный");
        Notebook notebook2 = new Notebook("Ноутбук 2", 16, 1024, "macOS", "Серебристый");
        Notebook notebook3 = new Notebook("Ноутбук 3", 8, 256, "Linux", "Черный");

        // Создаем множество ноутбуков
        Set<Notebook> notebooks = Set.of(notebook1, notebook2, notebook3);

        // Создаем объект Scanner для чтения пользовательского ввода
        Scanner scanner = new Scanner(System.in);

        // Создаем карту для хранения критериев фильтрации
        Map<String, Object> filters = new HashMap<>();

        // Запрашиваем у пользователя критерии фильтрации
        System.out.println("Введите цифру, соответствующую критерию фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("Для завершения фильтра введите '0'.");
        int option;
        do {
            System.out.print("Выберите критерий: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Пропускаем символ новой строки после чтения цифры
            if (option == 0) {
                break;
            }
            String property;
            switch (option) {
                case 1:
                    property = "ОЗУ";
                    System.out.print("Введите минимальный объем ОЗУ: ");
                    int ram = scanner.nextInt();
                    filters.put(property, ram);
                    break;
                case 2:
                    property = "Объем ЖД";
                    System.out.print("Введите минимальный объем жесткого диска: ");
                    int storage = scanner.nextInt();
                    filters.put(property, storage);
                    break;
                case 3:
                    property = "Операционная система";
                    System.out.print("Введите операционную систему: ");
                    String os = scanner.nextLine();
                    filters.put(property, os);
                    break;
                case 4:
                    property = "Цвет";
                    System.out.print("Введите цвет: ");
                    String color = scanner.nextLine();
                    filters.put(property, color);
                    break;
                default:
                    System.out.println("Некорректный выбор критерия. Попробуйте еще раз.");
            }
        } while (option != 0);

        // Фильтруем и выводим ноутбуки
        filterNotebooks(notebooks, filters);
    }
    
    private static void filterNotebooks(Set<Notebook> notebooks, Map<String, Object> filters) {
        // Проход по каждому ноутбуку
        for (Notebook notebook : notebooks) {
            // Флаг для проверки, соответствует ли ноутбук всем критериям фильтрации
            boolean matchesFilter = true;
    
            // Проверка критериев фильтрации
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String property = entry.getKey();
                Object value = entry.getValue();
    
                // Проверка свойства ноутбука и его соответствия критерию фильтрации
                if (property.equals("Ram")) {
                    int minRam = (int) value;
                    if (notebook.getRam() < minRam) {
                        matchesFilter = false;
                        break;
                    }
                } else if (property.equals("Объем ЖД")) {
                    int minStorage = (int) value;
                    if (notebook.getStorage() < minStorage) {
                        matchesFilter = false;
                        break;
                    }
                } else if (property.equals("Операционная система")) {
                    String os = (String) value;
                    if (!notebook.getOperatingSystem().equalsIgnoreCase(os)) {
                        matchesFilter = false;
                        break;
                    }
                } else if (property.equals("Цвет")) {
                    String color = (String) value;
                    if (!notebook.getColor().equalsIgnoreCase(color)) {
                        matchesFilter = false;
                        break;
                    }
                }
            }
    
            // Если ноутбук соответствует всем критериям фильтрации, выводим его
            if (matchesFilter) {
                System.out.println(notebook.getName());
            }
        }
    }
}
