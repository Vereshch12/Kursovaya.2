import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class YearlyTask extends Task implements NextDayOfTask{
    public YearlyTask(String name, String description, Type type, LocalDate dateOfTask, LocalTime timeOfTask) {
        super(name, description, type, dateOfTask, timeOfTask);
    }

    public boolean checkDate (LocalDate checkingDate){
        LocalDate helpingDate = this.getDateOfTask();
        while(!helpingDate.isAfter(checkingDate)){
            if (checkingDate.equals(helpingDate)){
                return true;
            }
            helpingDate = helpingDate.plusYears(1);
        }
        return false;
    }

    public String getTaskType(){
        return "Ежегодная";
    }

    @Override
    public LocalDate getNextDayOfTask() {
        LocalTime nowTime = LocalTime.now();
        LocalDate resultDate = LocalDate.now();
        if (getDateOfTask().equals(resultDate)){
            if (!getTimeOfTask().isAfter(nowTime)){
                resultDate = resultDate.plusYears(1);
            }
            return resultDate;
        }else {
            resultDate = getDateOfTask();
            while(resultDate.isBefore(LocalDate.now())){
                resultDate = resultDate.plusYears(1);
            }
        }
        return resultDate;
    }
}
