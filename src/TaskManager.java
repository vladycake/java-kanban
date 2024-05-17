import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    private int taskCounter = 1;

    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();


    public int generateTaskID() {
        return taskCounter++;
    }


    public List<Task> getTasksList() {
        return new ArrayList<>(tasks.values());

    }

    public List<Subtask> getSubtasksList() {
        return new ArrayList<>(subtasks.values());

    }

    public List<Epic> getEpicList() {
        return new ArrayList<>(epics.values());

    }

    public void removeAllTasks() {
        tasks.clear();
        subtasks.clear();
        epics.clear();
    }


    public Task getTaskByID(int id) {
        return tasks.get(id);
    }

    public Task getSubTaskByID(int id) {
        return subtasks.get(id);
    }

    public Task getEpicByID(int id) {
        return epics.get(id);
    }

    public void addTask(Task task) {
        task.setId(generateTaskID());
        tasks.put(task.getId(), task);

    }

    public void addSubtask(Subtask subtask) {
        subtask.setId(generateTaskID());
        subtasks.put(subtask.getId(), subtask);

        Epic epic = subtask.getEpic();
        if (epic != null) {
            epic.addSubtask(subtask);
            updateEpicStatus(epic);
        }
    }

    public void addEpic(Epic epic) {
        epic.setId(generateTaskID());
        epics.put(epic.getId(), epic);


    }


    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateSubtask(Subtask task) {
        tasks.put(task.getId(), task);
        updateEpicStatus(task.getEpic());
    }

    public void updateEpic(Epic task) {
        epics.put(task.getId(), task);


    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public void deleteSubtaskById(int id) {
        subtasks.remove(id);
        updateEpicStatus(subtasks.get(id).getEpic());
    }

    public void deleteEpicyId(int id) {
        epics.remove(id);
    }


    public List<Subtask> getSubtasksList(Epic epic) {
        return epic.getSubtaskList();

    }

    private void updateEpicStatus(Epic epic) {
        int allIsDoneCount = 0;
        int allIsInNewCount = 0;


        ArrayList<Subtask> list = epic.getSubtaskList();

        for (Subtask subtask : list) {
            if (subtask.getStatus() == Status.DONE) {
                allIsDoneCount++;
            }
            if (subtask.getStatus() == Status.NEW) {
                allIsInNewCount++;
            }
        }

        if (allIsDoneCount == list.size()) {
            epic.setStatus(Status.DONE);
        } else if (allIsInNewCount == list.size()) {
            epic.setStatus(Status.NEW);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }


    }

}