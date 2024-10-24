import lombok.Getter;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Lombard {
    private List<Contract> contracts = new ArrayList<>();

    // Добавление контракта
    public void addContract(Client client, Item item, double amount, LocalDate redemptionWithoutInterest, LocalDate nonRedemptionPeriod) {
        contracts.add(new Contract(client, item, amount, redemptionWithoutInterest, nonRedemptionPeriod));
    }

    // Метод для загрузки контрактов из текстового файла
    public void loadContractsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String clientName = data[0].trim();
                    String itemDescription = data[1].trim();
                    double amount = Double.parseDouble(data[2].trim());
                    LocalDate redemptionWithoutInterest = LocalDate.parse(data[3].trim());
                    LocalDate nonRedemptionPeriod = LocalDate.parse(data[4].trim());

                    Client client = new Client(clientName);
                    Item item = new Item(itemDescription);
                    addContract(client, item, amount, redemptionWithoutInterest, nonRedemptionPeriod);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ошибка при обработке данных: " + e.getMessage());
        }
    }

    // Отрисовка таблицы данных
    public void printTable() {
        System.out.printf("%-20s %-20s %-10s %-30s %-30s%n", "Клиент", "Вещь", "Сумма", "Выкуп без процентов", "Период невыкупа");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        for (Contract contract : contracts) {
            System.out.printf("%-20s %-20s %-10.2f %-30s %-30s%n",
                    contract.getClient(),
                    contract.getItem(),
                    contract.getAmount(),
                    contract.getRedemptionWithoutInterest(),
                    contract.getNonRedemptionPeriod());
        }
    }

    // Поиск клиентов, выкупивших вещи с процентами
    public void findClientsWhoPaidWithInterest() {
        LocalDate today = LocalDate.now();
        System.out.println("Клиенты, выкупившие вещи с процентами:");
        for (Contract contract : contracts) {
            if (contract.getRedemptionWithoutInterest().isBefore(today)) {
                System.out.println(contract.getClient());
            }
        }
    }

    // Поиск невыкупленных вещей
    public void findUnredeemedItems() {
        LocalDate today = LocalDate.now();
        System.out.println("Невыкупленные вещи:");
        for (Contract contract : contracts) {
            if (contract.getNonRedemptionPeriod().isBefore(today)) {
                System.out.println(contract.getItem());
            }
        }
    }
}

