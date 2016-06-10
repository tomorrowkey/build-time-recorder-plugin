package jp.tomorrowkey.gradle.buildtimerecorder.database

import com.iciql.Db
import jp.tomorrowkey.gradle.buildtimerecorder.database.model.ElapsedTimeLogs
import jp.tomorrowkey.gradle.buildtimerecorder.database.model.ExecutionLogs
import jp.tomorrowkey.gradle.buildtimerecorder.util.AutoCloser

class DatabaseManager {

    private static final String JDBC_DRIVER_CLASS = 'org.sqlite.JDBC'

    private static final String DATABASE_FILE = 'build_time_recorder.sqlite'

    static void run(closure) {
        def file = new File(DATABASE_FILE)
        if (!file.exists()) {
            println 'Create new sqlite file'
            file.createNewFile()
        }
        def databaseUrl = 'jdbc:sqlite://' + file.absolutePath

        Class.forName(JDBC_DRIVER_CLASS)
        def db = Db.open(databaseUrl)
        db.setDbUpgrader(new MyUpgrader())
        AutoCloser.run(db, closure)
    }
}
