import java.util.Map;
import java.util.Set;

public class Notebook {
    private String name;
    private int ram;
    private int storage;
    private String operatingSystem;
    private String color;

    public Notebook(String name, int ram, int storage, String operatingSystem, String color) {
        this.name = name;
        this.ram = ram;
        this.storage = storage;
        this.operatingSystem = operatingSystem;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Метод для получения информации о ноутбуке
    public String getInfo() {
        return "Ноутбук: " + name + ", ОЗУ: " + ram + " Гб, Объем жесткого диска: " + storage +
                " Гб, Операционная система: " + operatingSystem +
                ", Цвет: " + color;
    }

    // Метод для фильтрации ноутбуков по критериям
    public static void filterNotebooks(Set<Notebook> notebooks, Map<String, Object> filters) {
        for (Notebook notebook : notebooks) {
            boolean satisfiesFilters = true;
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String property = entry.getKey();
                Object value = entry.getValue();
                if (!notebook.matchesFilter(property, value)) {
                    satisfiesFilters = false;
                    break;
                }
            }
            if (satisfiesFilters) {
                System.out.println(notebook.getInfo());
            }
        }
    }

    // Метод для проверки, соответствует ли ноутбук фильтру по определенному критерию
    private boolean matchesFilter(String property, Object value) {
        switch (property) {
            case "ОЗУ":
                return ram >= (int) value;
            case "Объем ЖД":
                return storage >= (int) value;
            case "Операционная система":
                return operatingSystem.equals(value);
            case "Цвет":
                return color.equals(value);
            default:
                return true;
        }
    }
}