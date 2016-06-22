package io.hamtech.doto.db;

/**
 * Created by hmiles on 6/21/16.
 */

import android.provider.BaseColumns;

public class TaskContract {
    public static final String DB_NAME = "io.hamtech.doto.db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks";

        public static final String COL_TASK_TITLE = "title";
    }
}