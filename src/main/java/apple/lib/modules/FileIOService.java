package apple.lib.modules;

import apple.utilities.threading.service.base.create.AsyncTaskQueueStart;
import apple.utilities.threading.service.queue.AsyncTaskQueue;
import apple.utilities.threading.service.queue.TaskHandlerQueue;

public class FileIOService {

    private static final TaskHandlerQueue instance = new TaskHandlerQueue(10, 0, 0);

    public static TaskHandlerQueue get() {
        return instance;
    }

    public static AsyncTaskQueueStart<AsyncTaskQueue> taskCreator() {
        return instance.taskCreator();
    }
}
