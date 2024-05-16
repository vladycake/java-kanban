public class Subtask extends Task {
    private final int id;
    private Epic epic;


    public Subtask(String title, String description, Epic epic) {
        super(title, description);
        this.id = TaskManager.generateTaskID();
        this.epic = epic;

    }

    public Epic getEpic() {
        return epic;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }
}
