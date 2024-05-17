public class Main {

    public static void main(String[] args) {
        TaskManager tm = new TaskManager();
        Task task1 = new Task("Сходить в гости", "Посетить друзей в соседнем городе");
        Task task2 = new Task("полить цветы", "полить все цветы дома");

        tm.addTask(task1);
        tm.addTask(task2);

        Epic epic1 = new Epic("Учеба", "Выучить программирование");
        Subtask subtask1 = new Subtask("выучить java", "изучить ООП", epic1);
        Subtask subtask2 = new Subtask("выучить питон", "изучить ООП", epic1);

        subtask1.setStatus(Status.IN_PROGRESS);
        subtask2.setStatus(Status.IN_PROGRESS);

        tm.addSubtask(subtask1);
        tm.addSubtask(subtask2);

        tm.addEpic(epic1);


        Epic epic2 = new Epic("Работа", "получить хорошую оценку на перфоманс ревью");
        Subtask subtask3 = new Subtask("проекты", "Закрыть текущие проекты", epic2);

        tm.addEpic(epic2);
        tm.addSubtask(subtask3);




        System.out.println(tm.getSubtasksList(epic1));
        System.out.println(epic1);


    }
}
