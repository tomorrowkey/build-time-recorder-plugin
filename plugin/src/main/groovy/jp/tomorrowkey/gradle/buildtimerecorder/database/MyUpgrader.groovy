package jp.tomorrowkey.gradle.buildtimerecorder.database

import com.iciql.Db
import com.iciql.DbUpgrader
import com.iciql.Iciql

@Iciql.IQVersion(1)
class MyUpgrader implements DbUpgrader {
    @Override
    boolean upgradeDatabase(Db db, int fromVersion, int toVersion) {
        println "Database migrating from $fromVersion to $toVersion"

        if(fromVersion == 0 && toVersion == 1) {
            def dao = db.open(V1Upgrader.class)
            dao.createExecutionLogs()
            dao.createElapsedTimeLogs()
            return true
        }
        return false
    }

    @Override
    boolean upgradeTable(Db db, String schema, String table, int fromVersion, int toVersion) {
        println "Table migrating from $fromVersion to $toVersion"

        if(fromVersion == 0 && toVersion == 1) {
            return true
        }
        return false
    }
}
