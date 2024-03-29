import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserData {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные пользователя через пробел :");
        System.out.println("Фамилия Имя Отчество дата_рождения(формат dd.mm.yyyy) номер_телефона пол (символ латиницей f или m):");
        String input = scanner.nextLine();

        String[] userData = input.split(" ");
        if (userData.length != 6) {
            System.err.println("Ошибка: введено неверное количество данных");
            return;
        }

        String lastName = userData[0];
        String firstName = userData[1];
        String middleName = userData[2];
        String dateOfBirth = userData[3];
        String phoneNumber = userData[4];
        String gender = userData[5];

        try {
            validateData(lastName, firstName, middleName, dateOfBirth, phoneNumber, gender);

            File file = new File(lastName + ".txt");
            FileWriter writer = new FileWriter(file, true);
            writer.write(lastName + " " + firstName + " " + middleName + " " + dateOfBirth + " " + phoneNumber + " " + gender + "\n");
            writer.close();
            System.out.println("Данные успешно записаны в файл " + lastName + ".txt");
        } catch (InvalidDataException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при записи данных в файл: " + e.getMessage());
        }
    }

    public static void validateData(String lastName, String firstName, String middleName, String dateOfBirth, String phoneNumber, String gender) throws InvalidDataException {
        // Проверяем формат даты рождения
        if (!dateOfBirth.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new InvalidDataException("Ошибка: Неверный формат даты рождения");
        }

        // Проверяем формат номера телефона
        try {
            Long.parseLong(phoneNumber);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Ошибка: Номер телефона должен быть целым числом");
        }

        // Проверяем пол
        if (!gender.equals("f") && !gender.equals("m")) {
            throw new InvalidDataException("Ошибка: Неверно указан пол, используйте f или m");
        }
    }

    static class InvalidDataException extends Exception {
        public InvalidDataException(String message) {
            super(message);
        }
    }
}

