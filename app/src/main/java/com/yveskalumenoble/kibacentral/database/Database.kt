package com.yveskalumenoble.kibacentral.database

/*
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yveskalumenoble.kibacentral.model.Event


@Database(entities = [Event::class],version = 1, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {
    abstract val eventDao : EventDao

    companion object {

        private lateinit var INSTANCE : EventDatabase

        fun getDataBase(context: Context) : EventDatabase {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        EventDatabase::class.java, "events"
                    )
                        .build()
                }
            }
            return INSTANCE
        }
    }
}*/
