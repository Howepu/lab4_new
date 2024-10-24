import java.time.LocalDate;

public class LombardApp {
    public static void main(String[] args) {
        Lombard lombard = new Lombard();

        // Загрузка контрактов из файла
        lombard.loadContractsFromFile("C:\\Users\\gstit\\IdeaProjects\\lab4_new\\src\\contracts.txt");

        // Отрисовка таблицы с контрактами
        System.out.println("Таблица контрактов:");
        lombard.printTable();

        // Выполнение поиска
        System.out.println();
        lombard.findClientsWhoPaidWithInterest();
        System.out.println();
        lombard.findUnredeemedItems();
    }
}
