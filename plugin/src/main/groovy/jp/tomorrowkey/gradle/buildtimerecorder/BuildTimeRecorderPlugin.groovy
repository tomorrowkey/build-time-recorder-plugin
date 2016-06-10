package jp.tomorrowkey.gradle.buildtimerecorder

import jp.tomorrowkey.gradle.buildtimerecorder.database.DatabaseManager
import jp.tomorrowkey.gradle.buildtimerecorder.database.model.ElapsedTimeLogs
import jp.tomorrowkey.gradle.buildtimerecorder.database.model.ExecutionLogs
import jp.tomorrowkey.gradle.buildtimerecorder.task.BuildTimeAnalyticsTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class BuildTimeRecorderPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.getExtensions().add("build-time-recorder", new BuildTimeRecorderExtension())

        TimeKeeper timeKeeper = new TimeKeeper({args, logs ->
            try{
                DatabaseManager.run({db ->
                    def key = db.insertAndGetKey(new ExecutionLogs(args.join(' '), System.currentTimeMillis()))
                    db.insertAll(logs.collect {new ElapsedTimeLogs(key, it.taskName, it.elapsedTime)})

                    println 'Build time saved'
                })
            }catch(Exception e) {
                e.printStackTrace()
            }
        })
        project.getGradle().addBuildListener(timeKeeper)

        project.task('buildTimeAnalytics', type: BuildTimeAnalyticsTask)
    }
}
