package jp.tomorrowkey.gradle.buildtimerecorder.database.model;

import com.iciql.Iciql;
import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQIndex;
import com.iciql.Iciql.IQIndexes;
import com.iciql.Iciql.IQSchema;
import com.iciql.Iciql.IQTable;
import com.iciql.Iciql.IndexType;
import java.io.Serializable;

@IQTable(name="elapsed_time_logs")
public class ElapsedTimeLogs implements Serializable {

	private static final long serialVersionUID = 1L;

	@IQColumn(primaryKey=true)
	public Integer id;

	@IQColumn
	public Long execution_log_id;

	@IQColumn(length=2000000000)
	public String task_name;

	@IQColumn
	public Long elapsed_time;

	public ElapsedTimeLogs() {
	}

	public ElapsedTimeLogs(Long execution_log_id, String task_name, Long elapsed_time) {
		this.execution_log_id = execution_log_id;
		this.task_name = task_name;
		this.elapsed_time = elapsed_time;
	}
}
