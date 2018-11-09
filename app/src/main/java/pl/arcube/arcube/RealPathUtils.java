package pl.arcube.arcube;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

/**
 * Created by Maciej Szalek on 2018-09-12.
 */

public class RealPathUtils {

    public RealPathUtils(){}

    public String getRealPathFromURI(Context context, Uri uri){
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);
        String id = wholeID.split(":")[1];

        String[] column = {MediaStore.Images.Media.DATA};
        String selection = MediaStore.Images.Media._ID + "=?";
        String[] selectionArgs = {id};

        Cursor cursor = context.getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        column, selection, selectionArgs, null);
        int columnIndex = cursor.getColumnIndex(column[0]);

        if(cursor.moveToFirst()){
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }
}
