package com.comp306.kubdb.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.comp306.kubdb.dao.UserDao
import com.comp306.kubdb.data.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(User::class), version = 2, exportSchema = false)
abstract class LibraryDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: LibraryDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): LibraryDatabase { // todo: remove scope later if not needed

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibraryDatabase::class.java,
                    "library_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(LibraryDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class LibraryDatabaseCallback(private val scope: CoroutineScope) : //TODO: remove later
        RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateTable(database.userDao())
                }
            }
        }

        suspend fun populateTable(userDao: UserDao) {
            val user1 = User(
                123,
                "xyz",
                "wali",
                "3bi",
                22,
                "Esenyurt",
                "Istanbul",
                "Turkey"
            )
            val user2 = User(
                4324,
                "awsd",
                "saria",
                "al-said hasan",
                22,
                "Esenyurt",
                "Istanbul",
                "Turkey"
            )

            userDao.addUser(user1)
            userDao.addUser(user2)
        }
    }
}

