package com.kc.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kc.newsapp.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun getArticleDao() : ArticleDao

    companion object {
        // Other threads can easily see when a thread changes this instance
        @Volatile
        private var INSTANCE: ArticleDatabase? = null
        // Makes sure that only one instance is created
        private val Lock = Any()

        // When ArticleDatabase() constructor is called it will call below method
        operator fun invoke(context: Context) {
            getDatabase(context)
        }

        fun getDatabase(context: Context): ArticleDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    "article_db.db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }
}