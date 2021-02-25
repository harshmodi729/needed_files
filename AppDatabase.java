import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {MODEL_CLASS_NAME.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `table_name` (`pk_id` INTEGER NOT NULL, `item_id` INTEGER NOT NULL, `item_name` TEXT, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, PRIMARY KEY(`pk_id`))");
            database.execSQL("ALTER TABLE `another_table_name` ADD COLUMN `another_id` INTEGER DEFAULT 0 NOT NULL");
            database.execSQL("ALTER TABLE `another_table_name` ADD COLUMN `another_name` TEXT");
        }
    };

    private static AppDatabase SINGLETON_INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (SINGLETON_INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (SINGLETON_INSTANCE == null) {
                    SINGLETON_INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "database_name")
                            .addMigrations(ADD_COMMA_SEPARATED_MIGRATION)
                            .build();
                }
            }
        }
        return SINGLETON_INSTANCE;
    }

    public abstract MODEL_CLASS_NAMEDao getMODEL_CLASS_NAMEDao();
}
