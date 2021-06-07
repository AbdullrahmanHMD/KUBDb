package com.comp306.kubdb.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.comp306.kubdb.dao.AuthorDao
import com.comp306.kubdb.dao.BookDao
import com.comp306.kubdb.dao.UserDao
import com.comp306.kubdb.data.entities.*
import com.comp306.kubdb.data.views.MostRatingUser

@Database(
    entities = [User::class, Book::class, Author::class, Rating::class, Writes::class],
    views = [MostRatingUser::class],
    version = 4,
    exportSchema = false
)
abstract class LibraryDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun bookDao(): BookDao
    abstract fun AuthorDao(): AuthorDao

    companion object {

        @Volatile
        private var INSTANCE: LibraryDatabase? = null

        fun getDatabase(
            context: Context,
            callback: () -> Unit,
        ): LibraryDatabase { // todo: remove scope later if not needed
            println("BUILDING")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibraryDatabase::class.java,
                    "library_database"
                ).createFromAsset("library_db4.db").fallbackToDestructiveMigration()
                    .addCallback(LibraryDatabaseCallback(callback))
                    .build()
                INSTANCE = instance
                instance
            }
        }


    }

    private class LibraryDatabaseCallback(
        private val callback: () -> Unit
    ) : //TODO: remove later
        RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            println("CREATED")
            callback()
        }

    }
}

