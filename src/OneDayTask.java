import java.time.LocalDate;
import java.time.LocalTime;

public class OneDayTask extends Task implements NextDayOfTask{
    public OneDayTask(String name, String description, Type type, LocalDate dateOfTask, LocalTime timeOfTask) {
        super(name, description, type, dateOfTask, timeOfTask);
    }

    public String getTaskType(){
        return "Однократная";
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

}
