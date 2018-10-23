package com.example.android.bookstore.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class BookContract {

    // Empty constructor to prevent someone from accidentally instantiating the contract class.
    private BookContract() {
    }

    public static final String CONTENT_AUTHORITY = "com.example.android.bookstore";

    // Create a base URI which app will use to contact the content provider
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_BOOKS = "bookstore";

    // Inner class that defines constant values for books database table.
    public static final class BookEntry implements BaseColumns {

        /**
         * The content URI to access the pet data in the provider
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOOKS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of books.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single book.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        // Name of the database table for books
        public static final String TABLE_NAME = "books";

        // Unique ID number for the product of type INTEGER.
        public static final String _ID = BaseColumns._ID;

        // Name of the book of type TEXT
        public static final String COLUMN_BOOK_NAME = "product_name";

        // Price of the book of type INTEGER
        public static final String COLUMN_BOOK_PRICE = "price";

        // Books quantity of type INTEGER
        public static final String COLUMN_BOOK_QUANTITY = "quantity";

        // Type of book
        public static final String COLUMN_BOOK_TYPE = "type";

        // Name of the book supplier of type TEXT
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";

        // Supplier phone number of type INTEGER
        public static final String COLUMN_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";

        // Possible values for the type of book.
        public static final int BOOK_TYPE_UNKNOWN = 0;
        public static final int BOOK_TYPE_FICTION = 1;
        public static final int BOOK_TYPE_NON_FICTION = 2;

        public static boolean isValidType(int type) {
            if (type == BOOK_TYPE_UNKNOWN || type == BOOK_TYPE_FICTION || type == BOOK_TYPE_NON_FICTION);
            return true;
        }

    }
}
