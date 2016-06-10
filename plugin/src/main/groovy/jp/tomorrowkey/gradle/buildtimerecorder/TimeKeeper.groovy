package jp.tomorrowkey.gradle.buildtimerecorder

import jp.tomorrowkey.gradle.buildtimerecorder.model.GradleTaskLog
import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState
import org.gradle.util.Clock

class TimeKeeper implements TaskExecutionListener, BuildListener {

    def callback

    def args

    def logs = new ArrayList<>()

    def clock = new Clock()

    TimeKeeper(callback) {
        this.callback = callback
    }

    @Override
    void beforeExecute(Task task) {
        clock.reset()
    }

    @Override
    void afterExecute(Task task, TaskState state) {
        logs << new GradleTaskLog(task, clock.getTimeInMs())
    }

    @Override
    void projectsEvaluated(Gradle gradle) {
        def requests = gradle.startParameter.taskRequests
        if (!requests.empty) {
           args = requests.first().args
        }
    }

    @Override
    void buildFinished(BuildResult buildResult) {
        callback.call(args, logs)
    }

    @Override
    void projectsLoaded(Gradle gradle) {
    }

    @Override
    void buildStarted(Gradle gradle) {
    }

    @Override
    void settingsEvaluated(Settings settings) {
    }

}
