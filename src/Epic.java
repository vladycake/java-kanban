import java.util.ArrayList;


public class Epic extends Task {

    private final int id;
    private ArrayList<Subtask> subtaskList;

    @Override
    public int getId() {
        return id;
    }


    public ArrayList<Subtask> getSubtaskList() {
        return subtaskList;
    }

    public void addSubtask(Subtask subtask) {
        subtaskList.add(subtask);
    }

    public Epic(String title, String description) {
        super(title, description);
        this.id = TaskManager.generateTaskID();
        this.subtaskList = new ArrayList<>();

    }


    public boolean isCompleted() {
        for (Subtask subtask : subtaskList) {
            if (subtask.getStatus() != Status.DONE) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setStatus(Status status) {
        super.setStatus(status);
        if (status == Status.DONE || status == Status.IN_PROGRESS) {
            if (isCompleted()) {
                super.setStatus(Status.DONE);
            } else {
                super.setStatus(Status.IN_PROGRESS);
            }
        }
    }
}
