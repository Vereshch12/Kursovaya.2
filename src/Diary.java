import java.time.LocalDate;
import java.util.*;

public class Diary {

    public Map<Integer, Task> diary = new HashMap<>();

    public Diary() {
    }

    public void addTaskToDiary(Task task){
        diary.put(task.getId(), task);
    }

    public List<Task> getTaskForDay(LocalDate taskdate){
        List<Task> tasksForDay = new ArrayList<>();
        for(Map.Entry<Integer, Task> entryTask : diary.entrySet() ){
            if (entryTask.getValue().checkDate(taskdate)) {
                tasksForDay.add(entryTask.getValue());
            }
        }
        return tasksForDay;
    }

    public void deleteTaskById(Integer id){
        diary.remove(id);
    }

    @Override
    public String toString() {
        for (Map.Entry<Integer, Task> entryTask : diary.entrySet() ){
            System.out.println(entryTask.getValue());
        }
        return null;
    }
}
