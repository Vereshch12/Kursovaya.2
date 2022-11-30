import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task implements NextDayOfTask{
    private String name;
    private String description;
    private Type type;
    private LocalDate dateOfTask;
    private LocalTime timeOfTask;
    private static Integer generalId = 1;
    private Integer id;

    public Task(String name, String description, Type type, LocalDate dateOfTask, LocalTime timeOfTask) {
        if (checkNaming(name)) throw new RuntimeException("Поле названия задачи должно быть заполнено!");
        if (checkNaming(description)) description = "Без описания";
        this.name = name;
        this.description = description;
        this.type = type;
        this.dateOfTask = dateOfTask;
        this.timeOfTask = timeOfTask;
        this.id = generalId;
        generalId++;
    }

    public boolean checkDate (LocalDate checkingDate){
        return dateOfTask.equals(checkingDate);
    }


    public boolean checkNaming (String line){
        return (line == null || line.isEmpty() || line.isBlank());
    }

    @Override
    public LocalDate getNextDayOfTask() {
        LocalTime nowTime = LocalTime.now();
        LocalDate resultDate = LocalDate.now();
        if (getDateOfTask().equals(resultDate)){
            if (!getTimeOfTask().isAfter(nowTime)){
                System.out.println("\nВы должны были уже выполнить это задание сегодня в " + getTimeOfTask());
                return null;
            }
            return resultDate;
        }else {
            resultDate = getDateOfTask();
            if(resultDate.isBefore(LocalDate.now())){
                System.out.println("\nВы должны были уже выполнить это задание " + getDateOfTask() + " в " + getTimeOfTask());
                return null;
            }
        }
        return resultDate;
    }

    public enum Type{
        WORKING ("Рабочая задача"),
        PERSONAL ("Личная задача");

        private String type;

        Type(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        private void printType(){
            System.out.println("\n     Тип задачи: " + type);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    public LocalDate getDateOfTask() {
        return dateOfTask;
    }

    public LocalTime getTimeOfTask() {
        return timeOfTask;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        System.out.println("\n" +  id + ") " + name +
                           "\n     Описание: " + description +
                           "\n     Дата: " + dateOfTask.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                           "\n     Время: " + timeOfTask +
                           "\n     Тип: " + this.getType().type +
                           "\n     Частота выполнения: Однократно");
        return null;
    }
}
