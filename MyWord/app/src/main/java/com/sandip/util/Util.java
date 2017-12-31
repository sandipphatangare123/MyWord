package com.sandip.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.sandip.myword.ActivityWelcomeScreen;
import com.sandip.myword.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;

public class Util {

    // public  static String Server_URL="http://192.168.1.134:5000/".trim();
    public static String Server_URL = "http://api.apla-manus.com/subscriber.php".trim();

    public static int visibleStoriedThreshold = 5;
    public static int visibleThreshold = 5;
    public static int firstVisibleItem = 1, visibleItemCount = 5, totalItemCount = 0;

    public static String TAG = "Apla manus=====>", flagFromSocial = "";
    public static HashMap<String, String> map = new HashMap<>();
    public static ExpandableListAdapter adapter;

    public static Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable) {
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    public static void retrieveContactName(Context cntx) //This Context parameter is nothing but your Activity class's Context
    {
        Cursor cursor = cntx.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        Integer contactsCount = cursor.getCount(); // get how many contacts you have in your contacts list
        if (contactsCount > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (contactName.toString().toLowerCase().contains("jadhav".toLowerCase())) {
                    if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                        //the below cursor will give you details for multiple contacts
                        Cursor pCursor = cntx.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id}, null);
                        // continue till this cursor reaches to all phone numbers which are associated with a contact in the contact list
                        while (pCursor.moveToNext()) {
                            int phoneType = pCursor.getInt(pCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                            //String isStarred 		= pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.STARRED));
                            String phoneNo = pCursor.getString(pCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            //you will get all phone numbers according to it's type as below switch case.
                            //Logs.e will print the phone number along with the name in DDMS. you can use these details where ever you want.
                            switch (phoneType) {
                                case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                    Log.e(contactName + ": TYPE_MOBILE", " " + phoneNo);
                                    break;
                                case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                    Log.e(contactName + ": TYPE_HOME", " " + phoneNo);
                                    break;
                                case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                                    Log.e(contactName + ": TYPE_WORK", " " + phoneNo);
                                    break;
                                case ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE:
                                    Log.e(contactName + ": TYPE_WORK_MOBILE", " " + phoneNo);
                                    break;
                                case ContactsContract.CommonDataKinds.Phone.TYPE_OTHER:
                                    Log.e(contactName + ": TYPE_OTHER", " " + phoneNo);
                                    break;
                                default:
                                    break;
                            }
                        }
                        pCursor.close();
                    }
                }
            }
            cursor.close();
        }
    }

    public static int getViewOfChar(String ch, Context context) {

        if (ch.equalsIgnoreCase("a")) {
            return R.drawable.a1;
        } else if (ch.equalsIgnoreCase("b")) {
            return R.drawable.b1;
        } else if (ch.equalsIgnoreCase("c")) {
            return R.drawable.c1;
        } else if (ch.equalsIgnoreCase("d")) {
            return R.drawable.d1;
        } else if (ch.equalsIgnoreCase("e")) {
            return R.drawable.e1;
        } else if (ch.equalsIgnoreCase("f")) {
            return R.drawable.f1;
        } else if (ch.equalsIgnoreCase("g")) {
            return R.drawable.g1;
        } else if (ch.equalsIgnoreCase("h")) {
            return R.drawable.h1;
        } else if (ch.equalsIgnoreCase("i")) {
            return R.drawable.i1;
        } else if (ch.equalsIgnoreCase("j")) {
            return R.drawable.j1;
        } else if (ch.equalsIgnoreCase("k")) {
            return R.drawable.k1;
        } else if (ch.equalsIgnoreCase("l")) {
            return R.drawable.l1;
        } else if (ch.equalsIgnoreCase("m")) {
            return R.drawable.m1;
        } else if (ch.equalsIgnoreCase("n")) {
            return R.drawable.n1;
        } else if (ch.equalsIgnoreCase("o")) {
            return R.drawable.o1;
        } else if (ch.equalsIgnoreCase("p")) {
            return R.drawable.p1;
        } else if (ch.equalsIgnoreCase("q")) {
            return R.drawable.q1;
        } else if (ch.equalsIgnoreCase("r")) {
            return R.drawable.r1;
        } else if (ch.equalsIgnoreCase("s")) {
            return R.drawable.s1;
        } else if (ch.equalsIgnoreCase("t")) {
            return R.drawable.t1;
        } else if (ch.equalsIgnoreCase("u")) {
            return R.drawable.u1;
        } else if (ch.equalsIgnoreCase("v")) {
            return R.drawable.v1;
        } else if (ch.equalsIgnoreCase("w")) {
            return R.drawable.w1;
        } else if (ch.equalsIgnoreCase("x")) {
            return R.drawable.x1;
        } else if (ch.equalsIgnoreCase("y")) {
            return R.drawable.y1;
        } else if (ch.equalsIgnoreCase("z")) {
            return R.drawable.z1;
        }

        return 0;
    }

    public static File compressImage(String imageUri, Context context) {
        String fileName = getFilename();
        File file = null;
        String filePath = getRealPathFromURI(imageUri, context);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

        //          by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
        //          you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

        Log.e("Actual height", "h:-" + actualHeight + " w:-" + actualWidth);
        //          max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 640.0f;
        float maxWidth = 640.0f;
        float imgRatio = (float) actualWidth / actualHeight;
        float maxRatio = (float) maxWidth / maxHeight;

        //          width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

        //          setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

        //          inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

        //          this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
            //              load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

        //          check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (scaledBitmap != null) {
            try {

                Log.e("scaled height", "h:-" + scaledBitmap.getHeight() + " w:-" + scaledBitmap.getWidth());

                file = new File(fileName);
                FileOutputStream out = new FileOutputStream(file);
                scaledBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;

    }

    public static String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "MyFolder/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriSting;

    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }

    private static String getRealPathFromURI(String contentURI, Context context) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = context.getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    public static void setLocale(String lang, Context context) {

        Locale myLocale = new Locale(lang);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(context, ActivityWelcomeScreen.class);
        refresh.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(refresh);
        ((Activity) context).finish();
/*
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        startActivity(intent);*/
    }

    public static  int getTime() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        return timeOfDay;
    }

    public static void setToolTips(Context context, String toolTipsText, View view) {

        new SimpleTooltip.Builder(context)
                .anchorView(view)
                .text(toolTipsText)
                .textColor(Color.BLACK)
                .anchorView(view)
                .maxWidth(R.dimen.s250dp)
                .backgroundColor(Color.rgb(235, 235, 241))
                .gravity(Gravity.TOP)
                .animated(true)
                .transparentOverlay(true)
                .build()
                .show();
    }
}
