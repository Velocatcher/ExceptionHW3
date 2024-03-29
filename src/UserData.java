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
            ValidateData.validateData(lastName, firstName, middleName, dateOfBirth, phoneNumber, gender);

            File file = new File(lastName + ".txt");
            FileWriter writer = new FileWriter(file, true);
            writer.write(String.format("<%s> <%s> <%s> <%s> <%s> <%s>%n", lastName, firstName, middleName, dateOfBirth, phoneNumber, gender));
            writer.close();
            System.out.println("Данные успешно записаны в файл " + lastName + ".txt");
        } catch (InvalidDataException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при записи данных в файл: " + e.getMessage());
        }
    }

}

