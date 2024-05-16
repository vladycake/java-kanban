import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    private static int taskCounter = 1;

    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();


    public static int generateTaskID() {
        return taskCounter++;
    }


    public List<Task> getTasksList() {
        List<Task> list = new ArrayList<>();
        for (Task task : tasks.values()) {
            list.add(task);
        }
        return list;

    }

    public List<Subtask> getSubtasksList() {
        List<Subtask> list = new ArrayList<>(subtasks.values());
        return list;

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

        tasks.put(task.getId(), task);
    }

    public void addSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);

        Epic epic = subtask.getEpic();
        if (epic != null) {
            if (!epics.containsKey(epic.getId())) {
                addEpic(epic);
            }
            epic.addSubtask(subtask);
            updateEpicStatus(epic);
        }
    }

    public void addEpic(Epic epic) {

        epics.put(epic.getId(), epic);

    }


    public void updateTask(Task task) {
        tasks.remove(task.getId());
        tasks.put(task.getId(), task);
    }

    public void updateSubtask(Subtask task) {
        tasks.remove(task.getId());
        tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic task) {
        epics.remove(task.getId());
        epics.put(task.getId(), task);


    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
        subtasks.remove(id);
        epics.remove(id);
    }


    public ArrayList<Subtask> getSubtasksList(Epic epic) {
        return epic.getSubtaskList();

    }

    private void updateEpicStatus(Epic epic) {
        boolean allIsDone = true;
        boolean allIsInProgress = true;
        boolean hasNewOrInProgress = false; // Добавлено для правильной проверки

        ArrayList<Subtask> list = epic.getSubtaskList();

        for (Subtask subtask : list) {
            if (subtask.getStatus() != Status.DONE) {
                allIsDone = false;
            }
            if (subtask.getStatus() != Status.IN_PROGRESS) {
                allIsInProgress = false;
            }
            if (subtask.getStatus() == Status.NEW || subtask.getStatus() == Status.IN_PROGRESS) {
                hasNewOrInProgress = true;
            }
        }

        if (allIsDone) {
            epic.setStatus(Status.DONE);
        } else if (allIsInProgress || hasNewOrInProgress) {
            epic.setStatus(Status.IN_PROGRESS);
        } else {
            epic.setStatus(Status.NEW);
        }
    }

}