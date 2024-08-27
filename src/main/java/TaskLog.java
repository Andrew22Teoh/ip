public class TaskLog {
    private Task[] log;
    private int numTasks;

    TaskLog(Task[] newLog) {
        this.log = newLog;
        this.numTasks = 0;
    }

    TaskLog() {
        this(new Task[100]);
    }

    public void addTask(Task newTask) {
        this.log[this.numTasks] = newTask;
        this.numTasks++;
        if (this.numTasks == this.log.length) {
            this.doubleLogSize();
        }
    }

    public void addTask(String taskDesc) {
        this.addTask(new Task(taskDesc));
    }

    private void doubleLogSize() {
        int newLogSize = 2 * this.log.length;
        Task[] newLog = new Task[newLogSize];
        System.arraycopy(this.log, 0, newLog, 0, this.log.length);
        this.log = newLog;
    }

    protected Task getTask(int pos) throws RizzlerException {
        if (pos > this.numTasks) {
            throw new RizzlerException("there ain't no task " + (pos + 1) + " darlin'");
        } else if (pos <= 0) {
            throw new RizzlerException("pumpkin, why you tryna give me problems?");
        }
        return this.log[pos];
    }

    public Task lastTask() throws RizzlerException {
        return this.getTask(this.numTasks - 1);
    }

    protected Task[] getLog() {
        Task[] truncLog = new Task[this.numTasks];
        for (int i = 0; i < this.numTasks; i++) {
            truncLog[i] = this.getTask(i);
        }
        return truncLog;
    }

    protected Task doTask(int task_num) throws RizzlerException {
        Task doneTask = this.getTask(task_num - 1);
        doneTask.done();
        return doneTask;
    }

    protected Task undoTask(int task_num) throws RizzlerException {
        Task undoneTask = this.getTask(task_num - 1);
        undoneTask.undone();
        return undoneTask;
    }

    public int getNumTasks() {
        return this.numTasks;
    }
}
