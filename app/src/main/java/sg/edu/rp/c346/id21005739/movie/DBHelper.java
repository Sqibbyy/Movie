package sg.edu.rp.c346.id21005739.movie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MOVIE = "movie";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String  COLUMN_YEAR = "year";
    private static final String  COLUMN_RATING = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createMovieTableSql = "CREATE TABLE " + TABLE_MOVIE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT , "
                + COLUMN_GENRE + " TEXT , "
                + COLUMN_YEAR + " INTEGER, "
                + COLUMN_RATING + " TEXT  )";
        db.execSQL(createMovieTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("ALTER TABLE " + TABLE_SONG + " ADD COLUMN module_name TEXT ");
//        db.execSQL("DROP TABLE movie");
        onCreate(db);
    }

    public long insertMovie(String title, String genre, int year, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATING, rating);
        long result = db.insert(TABLE_MOVIE, null, values);
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, should not be -1
        return result;
    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movies = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        Cursor cursor = db.query(TABLE_MOVIE, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movie note = new Movie(id,title,genre,year,rating);
                movies.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }

    public ArrayList<Movie> getAllPG13Movies() {
        ArrayList<Movie> pgMovies = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] col= {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        String condition = COLUMN_RATING + "= ?";
        String[] args = {"PG13"};
        Cursor cursor = db.query(TABLE_MOVIE, col, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movie note = new Movie(id,title,genre,year,rating);
                pgMovies.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return pgMovies;
    }

    public int updateMovie(Movie data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_GENRE, data.getGenre());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_RATING, data.getRating());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.get_id())};
        int result = db.update(TABLE_MOVIE, values, condition, args);
        db.close();
        return result;
    }

    public int deleteMovie(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_MOVIE, condition, args);
        db.close();
        return result;
    }
}