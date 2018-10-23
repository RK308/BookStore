package com.example.android.bookstore;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookstore.data.BookContract.BookEntry;

public class BookCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link BookCursorAdapter}.
     *
     * @param context The context
     * @param cursor       The cursor from which to get the data.
     */
    public BookCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the book data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current book can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find individual views that we want to modify in the List item layout.
        TextView bookName = (TextView)view.findViewById(R.id.book_name_textView);
        TextView bookPrice = (TextView)view.findViewById(R.id.book_price_textView);
        TextView bookQty = (TextView)view.findViewById(R.id.book_qty_textView);
        Button sale = (Button)view.findViewById(R.id.sale_button);

        final int columnIndex = cursor.getInt(cursor.getColumnIndex(BookEntry._ID));
        final int qty = cursor.getInt(cursor.getColumnIndex(BookEntry.COLUMN_BOOK_QUANTITY));

        // Find the columns of book attributes that we're interested in
        // And read the book attributes from the cursor for the current book.
        // Update the TextViews with the attributes for the current book.
        bookName.setText(cursor.getString(cursor.getColumnIndex(BookEntry.COLUMN_BOOK_NAME)));
        bookPrice.setText(context.getString(R.string.details_price) + String.valueOf(cursor.getInt(cursor.getColumnIndex(BookEntry.COLUMN_BOOK_PRICE))));
        bookQty.setText(context.getString(R.string.details_qty) + String.valueOf(qty));

        sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = ContentUris.withAppendedId(BookEntry.CONTENT_URI, columnIndex);

                if (qty == 0) {
                    Toast.makeText(context, R.string.adapter_empty_stock, Toast.LENGTH_SHORT).show();
                } else {
                    int newQty = qty - 1;

                    ContentValues values = new ContentValues();
                    values.put(BookEntry.COLUMN_BOOK_QUANTITY, newQty);
                    context.getContentResolver().update(uri, values, null, null);
                }
            }
        });
    }
}
