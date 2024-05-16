import java.util.ArrayList;


public class Epic extends Task {


    private ArrayList<Subtask> subtaskList;

    public Epic(String title, String description) {
        super(title, description);
        this.subtaskList = new ArrayList<>();

    }

    public ArrayList<Subtask> getSubtaskList() {
        return subtaskList;
    }

    public void addSubtask(Subtask subtask) {
        subtaskList.add(subtask);
        subtask.setEpic(this);
    }


    @Override
    public int getId() {
        return id;
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
