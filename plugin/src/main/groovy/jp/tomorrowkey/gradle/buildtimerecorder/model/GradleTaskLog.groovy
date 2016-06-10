package jp.tomorrowkey.gradle.buildtimerecorder.model

import org.gradle.api.Task
import org.gradle.api.tasks.TaskState;

class GradleTaskLog {

    def taskName
    def elapsedTime

    public GradleTaskLog(Task task, long elapsedTime) {
        this.taskName = task.path
        this.elapsedTime = elapsedTime
    }

}
