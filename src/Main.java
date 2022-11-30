import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static Diary diary1 = new Diary();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            deleteTaskById(scanner);
                            break;
                        case 3:
                            getTaskForDay(scanner);
                            break;
                        case 4:
                            getAllTasks(scanner);
                            break;
                        case 5:
                            getNextDayOfTask(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner) {
        System.out.print("\nВведите название задачи: ");
        String taskName = scanner.next();
        System.out.print("\nВведите описание задачи: ");
        String taskDescription = scanner.next();
        System.out.print("\nВведите тип задачи (1 - рабочая, 0 - личная): ");
        int type = scanner.nextInt();
        Task.Type taskType;
        if (type == 0){
            taskType = Task.Type.PERSONAL;
        } else{
            if (type == 1){
                taskType = Task.Type.WORKING;
            }else throw new IllegalArgumentException("\nНеверно введен тип задачи!");
        }
        System.out.print("\nВведите дату планируемой задачи (дд.мм.гггг): ");
        LocalDate taskDate = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        System.out.print("\nВведите время планируемой задачи (чч:мм): ");
        LocalTime taskTime = LocalTime.parse(scanner.next(), DateTimeFormatter.ofPattern("HH:mm"));
        printMenuRepeatability();
        System.out.print("\nВведите тип повторяемости задачи из списка: ");
        if (scanner.hasNextInt()) {
            int menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    diary1.addTaskToDiary(new Task(taskName, taskDescription, taskType, taskDate, taskTime));
                    break;
                case 2:
                    diary1.addTaskToDiary(new DailyTask(taskName, taskDescription, taskType, taskDate, taskTime));
                    break;
                case 3:
                    diary1.addTaskToDiary(new WeeklyTask(taskName, taskDescription, taskType, taskDate, taskTime));
                    break;
                case 4:
                    diary1.addTaskToDiary(new MonthlyTask(taskName, taskDescription, taskType, taskDate, taskTime));
                    break;
                case 5:
                    diary1.addTaskToDiary(new YearlyTask(taskName, taskDescription, taskType, taskDate, taskTime));
                    break;
            }
        } else {
            scanner.next();
            System.out.println("Введите пункт типа повторяемости задачи из списка!");
        }
        System.out.println("\nЗадача добавлена в ежедневник!\n\nНажмите 0 для выхода в меню!");
        scanner.next();
    }

    private static void getTaskForDay(Scanner scanner){
        System.out.print("\nВведите дату (дд.мм.гггг), чтобы узнать планы на день: ");
        LocalDate checkingDate = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        System.out.println("\nПланы на " + checkingDate.format(formatter) + ":");
        System.out.println(diary1.getTaskForDay(checkingDate));
        System.out.println("\nНажмите 0 для выхода в меню!");
        scanner.next();
    }

    private static void deleteTaskById(Scanner scanner){
        System.out.print("\nВведите id задания, которое вы хотите удалить: ");
        Integer id = scanner.nextInt();
        diary1.deleteTaskById(id);
        System.out.println("\nЗадание (id " + id  + ")удалено!");
        System.out.println("\nНажмите 0 для выхода в меню!");
        scanner.next();
    }

    private static void getAllTasks(Scanner scanner){
        System.out.println(diary1);
        System.out.println("\nНажмите 0 для выхода в меню!");
        scanner.next();
    }

    private static void getNextDayOfTask(Scanner scanner){
        System.out.println("\nВведите id задачи, для которой вы хотите получить дату следующего выполнения: ");
        Integer id = scanner.nextInt();
        Task helpingTask = diary1.diary.get(id);
        if (helpingTask.getNextDayOfTask() != null){
            System.out.println("Cледующая дата выполнения задания (id " + helpingTask.getId() + "): "
                    + helpingTask.getNextDayOfTask().format(formatter) + " в " + helpingTask.getTimeOfTask());
        }
        System.out.println("\nНажмите 0 для выхода в меню!");
        scanner.next();
    }

    private static void printMenuRepeatability(){
        System.out.println(
                """
                        1. Однократная
                        2. Ежедневная
                        3. Еженедельная
                        4. Ежемесечная
                        5. Ежегодная
                        """
        );
    }

    private static void printMenu() {
        System.out.println(
                """
                        1. Добавить задачу
                        2. Удалить задачу
                        3. Получить задачу на указанный день
                        4. Получить все задачи из ежедневника
                        5. Узнать следующую дату выполнения задания по id
                        0. Выход
                        """
        );
    }
}