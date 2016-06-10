package jp.tomorrowkey.gradle.buildtimerecorder.database

import com.iciql.Dao
import com.iciql.Iciql;

interface V1Upgrader extends Dao {
    @Dao.SqlStatement('CREATE TABLE execution_logs(id INTEGER PRIMARY KEY AUTOINCREMENT, timestamp INTEGER, args TEXT)')
    void createExecutionLogs()

    @Dao.SqlStatement('CREATE TABLE elapsed_time_logs(id INTEGER PRIMARY KEY AUTOINCREMENT, execution_log_id INTEGER, task_name TEXT, elapsed_time INTEGER)')
    void createElapsedTimeLogs()
}
