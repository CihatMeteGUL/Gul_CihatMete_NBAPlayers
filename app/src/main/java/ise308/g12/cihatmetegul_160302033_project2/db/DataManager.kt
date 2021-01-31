package ise308.g12.cihatmetegul_160302033_project2.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import ise308.g12.cihatmetegul_160302033_project2.Players
import ise308.g12.cihatmetegul_160302033_project2.PlayersAdapter
import kotlin.coroutines.coroutineContext

private const val TAG = "DataManager"
public var countOfPlayers: Int = 0

class DataManager(context: Context) {

    private val db: SQLiteDatabase

    init {

        db = CustomSQLiteOpenHelper(context).writableDatabase
    }

    fun insert(
        lastName: String,
        firstName: String,
        position: String,
        age: String,
        injured: Boolean
    ) {

        val insertQuery = "INSERT INTO " + TABLE_PLAYER + " ( " +
                LASTNAME + ", " + FIRSTNAME + ", " + POSITION + ", " + AGE + ", " + INJURED +
                ") VALUES ('" + lastName + "', '" + firstName + "', '" + position + "', '" + age + "', '" + injured + "');"

        Log.i(TAG, "-----> insert: $insertQuery")

        db?.execSQL(insertQuery)


    }


    //can be problem
    fun remove(lName: String) {

        val removeQuery = "DELETE FROM $TABLE_PLAYER WHERE $LASTNAME = '$lName';"

        Log.i(TAG, "-----> remove: $removeQuery")

        db?.execSQL(removeQuery)

    }

    fun searchAll(): ArrayList<Players> {

        val resultsQuery = "SELECT * FROM " + TABLE_PLAYER
        var results: String = ""
        var dbPlayersList: ArrayList<Players> = ArrayList<Players>()
        val resultsCursor = db.rawQuery(resultsQuery, null)

        while (resultsCursor.moveToNext()) {


            dbPlayersList.add(
                Players(
                    resultsCursor.getString(1),
                    resultsCursor.getString(2),
                    resultsCursor.getInt(4),
                    resultsCursor.getString(3),
                    resultsCursor.getString(5).toBoolean()

                )
            )
            savePlayerCount(resultsCursor.getInt(0))
        }
        return dbPlayersList

    }

    fun savePlayerCount(idCount: Int) {
        countOfPlayers = idCount
    }

    fun getCount(): Int {
        return countOfPlayers
    }

    private inner class CustomSQLiteOpenHelper(context: Context) :
        SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

        override fun onCreate(db: SQLiteDatabase?) {

            val newTableQuery = "CREATE TABLE " + TABLE_PLAYER + " ( " +
                    _ID + " integer primary key autoincrement not null , " +
                    FIRSTNAME + " text not null , " +
                    LASTNAME + " text not null , " +
                    POSITION + " text not null , " +
                    AGE + " text not null , " +
                    INJURED + " text not null)"

            Log.i(TAG, "-----> onCreate: $newTableQuery")

            db?.execSQL(newTableQuery)

        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }

    }

    companion object {

        private const val DB_NAME = "players_database"
        private const val DB_VERSION = 1
        private const val TABLE_PLAYER = "player_table"
        private const val _ID = "plater_id"
        private const val FIRSTNAME = "player_firstname"
        private const val LASTNAME = "player_lastname"
        private const val POSITION = "player_position"
        private const val AGE = "player_age"
        private const val INJURED = "player_injured"
    }
}