import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class WeeklyTask extends Task implements NextDayOfTask{
    public WeeklyTask(String name, String description, Type type, LocalDate dateOfTask, LocalTime timeOfTask) {
        super(name, description, type, dateOfTask, timeOfTask);
    }

    public boolean checkDate (LocalDate checkingDate){
        LocalDate helpingDate = this.getDateOfTask();
        while(!helpingDate.isAfter(checkingDate)){
            if (checkingDate.equals(helpingDate)){
                return true;
            }
            helpingDate = helpingDate.plusWeeks(1);
        }
        return false;
    }

    @Override
    public String getTaskType() {
        return "Еженедельная";
    }

    @Override
    public LocalDate getNextDayOfTask() {
        LocalTime nowTime = LocalTime.now();
        LocalDate resultDate = LocalDate.now();
        if (getDateOfTask().equals(resultDate)){
            if (!getTimeOfTask().isAfter(nowTime)){
                resultDate = resultDate.plusWeeks(1);
            }
            return resultDate;
        }else {
            resultDate = getDateOfTask();
            while(resultDate.isBefore(LocalDate.now())){
                resultDate = resultDate.plusWeeks(1);
            }
        }
        return resultDate;
    }
}
