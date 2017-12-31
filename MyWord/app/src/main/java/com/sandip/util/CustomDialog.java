package com.sandip.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.sandip.model.ModelWord;
import com.sandip.myword.R;

import java.util.ArrayList;

/**
 * Created by Admin on 14-02-2017.
 */

public class CustomDialog  extends Dialog {

    TextView txtWord, txtEnglishMeaning, txtMarathiMeaning;
    int positionOfarrayelement;
    ArrayList<ModelWord> listOfParseWords=null;
    String wordString;

    public CustomDialog(Context context, myOnClickListener myclick, String wordString, ArrayList<ModelWord> listOfParseWords, int positionOfarrayelement) {
        super(context);
        this.myListener = myclick;
        this.wordString=wordString;
        this.listOfParseWords=listOfParseWords;
        this.positionOfarrayelement=positionOfarrayelement;
    }


    public myOnClickListener myListener;

      // This is my interface //
    public interface myOnClickListener {
        void onButtonClick();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_custom_dialog_box);

        setUI();



        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                myListener.onButtonClick();

                // I am giving the click to the
                // interface function which we need
                // to implements where we call this
                // class
                dismiss();

            }
        });
    }

    public void setUI()
    {
        txtWord = (TextView) findViewById(R.id.txt_word);
        txtEnglishMeaning = (TextView) findViewById(R.id.txt_word_english_meaning);
        txtMarathiMeaning = (TextView) findViewById(R.id.txt_word_marathi_meaning);
        txtWord.setText(wordString);
        txtEnglishMeaning.setText(listOfParseWords.get(positionOfarrayelement).getWordMeaningEnglish());
        txtMarathiMeaning.setText(listOfParseWords.get(positionOfarrayelement).getGetWordMeaningMarathi());

    }
}
