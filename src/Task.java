import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class Task implements NextDayOfTask{
    private String name;
    private String description;
    private Type type;
    private LocalDate dateOfTask;
    private LocalTime timeOfTask;
    private static Integer generalId = 1;
    private Integer id;
    private boolean deleted;

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
        this.deleted = false;
    }

    public boolean checkDate (LocalDate checkingDate){
        return dateOfTask.equals(checkingDate);
    }


    public boolean checkNaming (String line){
        return (line == null || line.isEmpty() || line.isBlank());
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted() {
        this.deleted = true;
    }

    public abstract String getTaskType();

    public String toString() {
        return  "\n" +  getId() + ") " + getName() +
                "\n     Описание: " + getDescription() +
                "\n     Дата: " + getDateOfTask().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                "\n     Время: " + getTimeOfTask() +
                "\n     Тип: " + this.getType().getType() +
                "\n     Частота выполнения: " + getTaskType();
    }
}
