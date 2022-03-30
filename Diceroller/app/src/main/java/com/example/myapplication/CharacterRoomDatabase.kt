package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


// Annotates class to be a Room Database with a table (entity) of the Character class
@Database(entities = [Character::class], version = 1, exportSchema = false)
public abstract class CharacterRoomDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CharacterRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope
        )
                : CharacterRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterRoomDatabase::class.java,
                    "character_database"
                ).addCallback(CharacterDatabaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class CharacterDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.characterDao())
                    }
                }
            }

            suspend fun populateDatabase(characterDao: CharacterDao) {
                // Delete all content here.
                characterDao.deleteAll()

                // Add sample words.
                var character = Character("Jyrki")
                characterDao.insert(character)
                character = Character("Pekka")
                characterDao.insert(character)

                // TODO: Add your own words!
            }
        }


    }
}



