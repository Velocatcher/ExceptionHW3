public class ValidateData {
    public static void validateData(String lastName, String firstName, String middleName, String dateOfBirth, String phoneNumber, String gender) throws InvalidDataException {
        // Проверяем формат даты фамилии, имени и отчества
        if (!lastName.matches("^[a-zA-Z]*$")&&!lastName.matches("[А-яЁё][-А-яЁё]+")) {
            throw new InvalidDataException("Ошибка: Неверный формат фамилии");
        }
        if (!firstName.matches("^[a-zA-Z]*$")&&!firstName.matches("[А-яЁё][-А-яЁё]+")) {
            throw new InvalidDataException("Ошибка: Неверный формат имени");
        }
        if (!middleName.matches("^[a-zA-Z]*$")&&!middleName.matches("[А-яЁё][-А-яЁё]+")) {
            throw new InvalidDataException("Ошибка: Неверный формат отчества");
        }

        // Проверяем формат даты рождения
        if (!dateOfBirth.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new InvalidDataException("Ошибка: Неверный формат даты рождения (dd.mm.yyyy)");
        }

        // Проверяем формат номера телефона
        try {
            Long.parseLong(phoneNumber);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Ошибка: Номер телефона должен быть целым числом");
        }

        // Проверяем пол
        if (!gender.equals("f") && !gender.equals("m")) {
            throw new InvalidDataException("Ошибка: Неверно указан пол, используйте f (female) или m (male)");
        }
    }
}
