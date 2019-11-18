package com.androidtutorialshub.letzgoapp.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androidtutorialshub.letzgoapp.model.Travel_Details;
import com.androidtutorialshub.letzgoapp.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    // User table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_DETAILS="details";
    private static final String TABLE_SEASONS="seasons_list";
    private static final String TABLE_TYPE="type";
    private static final String TABLE_TAGS="tags";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    private static final String COLUMN_DETAILS_ID1 = "user_id";
    private static final String COLUMN_DETAILS_MONTH = "month";
    private static final String COLUMN_DETAILS_LOCATION = "location";
    private static final String COLUMN_DETAILS_TYPE = "type";

    private static final String COLUMN_SEASONS_SEASON="season";
    private static final String COLUMN_SEASONS_SLIST="Items";

    private static final String COLUMN_TYPES_TYPE="trip_type";
    private static final String COLUMN_TYPES_TLIST="items";

    private static final String COLUMN_TAGS_TAG="tag";
    private static final String COLUMN_TAGS_ITEM="item";
    private static final String COLUMN_TAGS_TYPE="type";



    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    private String CREATE_DETAILS_TABLE = "CREATE TABLE " + TABLE_DETAILS + "("
            + COLUMN_DETAILS_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DETAILS_MONTH + " TEXT,"
            + COLUMN_DETAILS_LOCATION + " TEXT," + COLUMN_DETAILS_TYPE + " TEXT" + ")";

    private String CREATE_SEASONS_TABLE = "CREATE TABLE " + TABLE_SEASONS + "("
            + COLUMN_SEASONS_SEASON + " TEXT," + COLUMN_SEASONS_SLIST + " TEXT" +  ")";

    private String CREATE_TYPE_TABLE = "CREATE TABLE " + TABLE_TYPE + "("
            + COLUMN_TYPES_TYPE + " TEXT," + COLUMN_TYPES_TLIST + " TEXT" +  ")";

    private String CREATE_TAGS_TABLE = "CREATE TABLE " + TABLE_TAGS + "("
            + COLUMN_TAGS_TAG + " TEXT," + COLUMN_TAGS_ITEM+ " TEXT," + COLUMN_TAGS_TYPE + " TEXT" + ")";


    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_DETAILS_TABLE= "DROP TABLE IF EXISTS " + TABLE_DETAILS;
    private String DROP_SEASONS_TABLE= "DROP TABLE IF EXISTS " + TABLE_SEASONS;
    private String DROP_TYPE_TABLE= "DROP TABLE IF EXISTS " + TABLE_TYPE;
    private String DROP_TAGS_TABLE= "DROP TABLE IF EXISTS " + TABLE_TAGS;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_DETAILS_TABLE);
        db.execSQL(CREATE_SEASONS_TABLE);
        db.execSQL(CREATE_TYPE_TABLE);
        db.execSQL(CREATE_TAGS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_DETAILS_TABLE);
        db.execSQL(DROP_SEASONS_TABLE);
        db.execSQL(DROP_TYPE_TABLE);
        db.execSQL(DROP_TAGS_TABLE);

        // Create tables again
        onCreate(db);

    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void addDetails(Travel_Details td) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DETAILS_LOCATION, td.getLoc());
        values.put(COLUMN_DETAILS_MONTH, td.getStart_date());
        values.put(COLUMN_DETAILS_TYPE, td.getTrip_type());

        // Inserting Row
        db.insert(TABLE_DETAILS, null, values);
        db.close();
    }

    public void addSeasons(){
        String []column1=new String[29];
        String []column2={"Sunscreen","Tank top","Shorts", "Cap", "Sunglasses", "Swim wear", "Glucose", "Gloves", "Muffler",
                "Handkerchief", "Cap", "Dryness lotion", "Windcheater", "Thermal", "Coat", "Hoodie", "Kettle", "Socks", "Boots", "Chapstick", "Umbrella",
                "Raincoat", "Waterproof boots", "Towel", "Waterproof bag", "Mosquito repellent", "Clothesline", "Monsoon", "Waterproof shoes"};
        for(int i=0;i<7;i++)
            column1[i]="Summer";
        for(int i=7;i<20;i++)
            column1[i]="Winter";
        for(int i=20;i<29;i++)
            column1[i]="Monsoon";
        for(int i=0;i<29;i++) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_SEASONS_SEASON,column1[i]);
            values.put(COLUMN_SEASONS_SLIST,column2[i]);

            db.insert(TABLE_SEASONS, null, values);
            db.close();
        }
    }
    public void addType(){
        String []column1=new String[23];
        String []column2={"Suitcase", "Laptop", "Laptop charger", "Business cards", "Formal wear", "Pen", "Diary",
                "Swimwear", "Camera", "Slippers", "Headphones", "Reading glasses", "Books","Camera", "Running shoes",
                "Hiking shoes", "Tent", "Matchstick", "Flashlight", "Bug spray", "First aid kit", "Sleeping bag", "Power bank"};
        for(int i=0;i<7;i++)
            column1[i]="Business";
        for(int i=7;i<13;i++)
            column1[i]="Leisure";
        for(int i=13;i<23;i++)
            column1[i]="Adventure";
        for(int i=0;i<23;i++) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_TYPES_TYPE,column1[i]);
            values.put(COLUMN_TYPES_TLIST,column2[i]);

            db.insert(TABLE_TYPE, null, values);
            db.close();
        }
    }

    public void addTag(){
        String []column1={"Sunscreen","Tank top", "Shorts", "Cap", "Sunglasses", "Swim wear", "Glucose", "Gloves", "Muffler", "Handkerchief", "Dryness lotion",
                "Windcheater", "Thermal", "Coat", "Hoodie", "Kettle", "Socks", "Boots", "Chapstick", "Umbrella", "Raincoat", "Waterproof boots",
                "Towel", "Waterproof bag", "Mosquito repellent", "Clothesline", "Waterproof shoes", "Suitcase", "Laptop", "Laptop charger", "Business cards",
                "Formal wear", "Pen", "Diary" , "Camera", "Slippers", "Headphones", "Reading glasses", "Books", "Running shoes", "Hiking shoes", "Tent",
                "Matchstick", "Flashlight", "Bug spray", "First aid kit", "Sleeping bag", "Power bank", "Hand Sanitizer", "Medicines", "Makeup", "Travel Documents",
                "Wallet", "House Keys", "Perfume", "Toothbrush", "Toothpaste", "Shaving Gel","Shaver",
                "Contacts solution", "Belt", "Lingerie","Cellphone", "Cellphone charger", "Watch", "Bottle", "Disposable Utensils"};
        String []column2={"Toiletry", "Clothing", "Clothing", "Clothing", "Misc.", "Clothing", "Misc.", "Clothing", "Clothing", "Clothing", "Toiletry",
                "Clothing", "Clothing", "Clothing", "Clothing", "Misc.", "Clothing", "Footwear", "Essential", "Misc.", "Clothing", "Footwear", "Toiletry",
                "Misc.", "Misc.", "Misc.", "Footwear", "Misc.", "Electronics", "Electronics", "Misc.", "Clothing", "Misc.", "Misc." , "Electronics", "Footwear",
                "Electronics", "Misc", "Misc." , "Footwear", "Footwear", "Misc." , "Misc." , "Misc." , "Misc." , "Essential", "Misc." , "Electronics", "Essential",
                "Essential", "Toiletries", "Essential", "Essential", "Essential", "Toiletries", "Toiletries", "Toiletries", "Toiletries", "Toiletries", "Toiletries", "Misc." ,
                "Clothing", "Electronics", "Electronics", "Misc." , "Misc." , "Misc."};
        String []column3=new String[67];
        for(int i=0;i<48;i++)
            column3[i]="Specific";
        for(int i=48;i<67;i++)
            column3[i]="General";
        for(int i=0;i<67;i++) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(COLUMN_TAGS_TAG,column2[i]);
            values.put(COLUMN_TAGS_ITEM,column1[i]);
            values.put(COLUMN_TAGS_TYPE,column3[i]);

            db.insert(TABLE_TAGS, null, values);
            db.close();
        }
    }
    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<String> getItems(String tag1, String season1, String type1 ) {

        String[] column1= { COLUMN_SEASONS_SLIST};
        String column2= COLUMN_SEASONS_SEASON+" = ?";
        String[] selectionarg1={season1};
        String[] column3={ COLUMN_TYPES_TLIST};
        String column4= COLUMN_TYPES_TYPE + " = ?";
        String[] selectionarg2={type1};
        String[] column5={COLUMN_TAGS_ITEM};
        String column6=COLUMN_TAGS_TAG+ " = ?"+ "AND " + COLUMN_TAGS_TYPE+" = ?";
        String selectionarg3[]={tag1, "Specific"};
        String selectionarg4[]={tag1, "General"};
        Set<String> items1= new HashSet<>();
        Set<String> items2= new HashSet<>();

        SQLiteDatabase db= this.getReadableDatabase();

        Cursor cursor1= db.query(TABLE_SEASONS, column1,column2,selectionarg1,null,null,null);
        Cursor cursor2= db.query(TABLE_TYPE, column3, column4,selectionarg2, null, null,null);
        Cursor cursor3= db.query(TABLE_TAGS, column5,column6,selectionarg3, null, null,null);
        Cursor cursor4= db.query(TABLE_TAGS, column5,column6,selectionarg4, null, null,null);

        if(cursor1.moveToFirst()){
            do{

                items1.add(cursor1.getString(cursor1.getColumnIndex(COLUMN_SEASONS_SLIST)));
            }while(cursor1.moveToNext());
        }
        cursor1.close();
        if(cursor2.moveToFirst()){
            do{

                items1.add(cursor2.getString(cursor2.getColumnIndex(COLUMN_TYPES_TLIST)));
            }while(cursor2.moveToNext());
        }
        cursor2.close();
        if(cursor3.moveToFirst()){
            do{

                items2.add(cursor3.getString(cursor3.getColumnIndex(COLUMN_TAGS_ITEM)));
            }while(cursor3.moveToNext());
        }
        cursor3.close();
        Set<String> items3= new HashSet<>(items1);
        items3.retainAll(items2);

        if(cursor4.moveToFirst()){
            do{

                items3.add(cursor4.getString(cursor4.getColumnIndex(COLUMN_TAGS_ITEM)));
            }while(cursor4.moveToNext());
        }
        cursor4.close();
        db.close();
        List<String> list = new ArrayList<String>(items3);
        return list;
    }
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**rrrrrrrrrrrr
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
