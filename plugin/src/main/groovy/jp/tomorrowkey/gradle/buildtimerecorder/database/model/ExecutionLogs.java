package jp.tomorrowkey.gradle.buildtimerecorder.database.model;

import com.iciql.Iciql;
import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQIndex;
import com.iciql.Iciql.IQIndexes;
import com.iciql.Iciql.IQSchema;
import com.iciql.Iciql.IQTable;
import com.iciql.Iciql.IndexType;
import java.io.Serializable;

@IQTable(name="execution_logs")
public class ExecutionLogs implements Serializable {

	private static final long serialVersionUID = 1L;

	@IQColumn(primaryKey=true)
	public Long id;

	@IQColumn(length=2000000000)
	public String args;

	@IQColumn
	public Long timestamp;

	public ExecutionLogs() {
	}

	public ExecutionLogs(String args, Long timestamp) {
		this.args = args;
		this.timestamp = timestamp;
	}
}
