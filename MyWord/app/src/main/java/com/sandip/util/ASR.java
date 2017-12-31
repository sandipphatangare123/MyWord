package com.sandip.util;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;


public class ASR implements View.OnClickListener {

    String TAG = "Speech";
    static Intent intent;
    Context context;
    private TextToSpeech talker;
    static TextView txtHeader, txtSubHeader;
    public static SpeechRecognizer recognizer;
    Dialog dialog = null;
    String mStatus;
    String mError;
    ArrayList<String> mResults = new ArrayList<String>();
    String partialString = "";
    String result = "";
    SpeechListner mListener;
    private static ASR asr;
    static Handler mainHandler;

    private ASR() {

    }

    public static ASR getInstance(Context context) {
        if (asr != null) {
            return asr;
        } else {
            asr = new ASR();
            intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
            // intent.putExtra(RecognizerIntent.EXTRA_PROMPT, context.getApplicationContext().getString(R.string.speech_prompt));
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something&#8230;");

            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.getApplicationContext().getPackageName());
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
            Log.e("Speech", "Speech recognition started!");

            return asr;
        }
    }


    public String promptSpeechInput(Context context1) {
        this.context = context1;

        if (recognizer != null) {
            recognizer = null;
            mListener = null;
        }
        mainHandler = new Handler(context.getMainLooper());
        Log.e("Speech", "setRecognitionListener");
        mListener = new SpeechListner();


        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "In run");
                recognizer = SpeechRecognizer.createSpeechRecognizer(context.getApplicationContext());
                recognizer.setRecognitionListener(mListener);
                recognizer.startListening(intent);
            } // This is your code
        };
        mainHandler.post(myRunnable);
        Log.e("Result", result);
        return result;
    }


    class SpeechListner implements RecognitionListener {
        @Override
        public void onBeginningOfSpeech() {
            Log.i(TAG, "onBeginningOfSpeech");
            mStatus = "Beginning speech";
            result = "";
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
            Log.i(TAG, "onBufferReceived************");


        }

        @Override
        public void onEndOfSpeech() {
            Log.i(TAG, "onEndOfSpeech");
            mStatus = "Speech ended";
        }


        @Override
        public void onEvent(int eventType, Bundle params) {
            Log.i(TAG, "onEvent " + eventType);

        }

        @Override
        public void onPartialResults(final Bundle partialResults) {
            Log.i(TAG, "onPartialResults");
            mStatus = "Partial results";
            Log.e(TAG, " on partial results " + partialResults);

            mResults = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            if (mResults.size() > 0) {
                Log.e(TAG, "mResults " + mResults);
                ArrayList<String> listOfResults = new ArrayList<String>();
                //txtHeader.setText(mResults.get(0)+"");
                if (!mResults.get(0).equalsIgnoreCase("")) {

                    result = mResults.get(0) + "";
                    String resultString = "fromPartial--" + result;
                    listOfResults.add(resultString);
                    partialString = new String();
                    partialString = result;
                    if (!result.equalsIgnoreCase("")) {
                        result = " \" " + result + " ....\"";
                    }
                    txtHeader.setText(result + "");
                    txtSubHeader.setVisibility(View.GONE);
                    Log.i(TAG, "before timer start....");

                }
            }

        }

        @Override
        public void onReadyForSpeech(Bundle params) {
            Log.i(TAG, "onReadyForSpeech");
            mStatus = "Speech engine ready";
        }

        @Override
        public void onRmsChanged(float rmsdB) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onError(int error) {
            // TODO Auto-generated method stub
            mError = "";
            mStatus = "Error detected";
            Log.e(TAG, "on Error " + error);
            switch (error) {
                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                    mError = " network timeout";
                    //	startListening();
                    break;
                case SpeechRecognizer.ERROR_NETWORK:
                    mError = " network";
                    Log.e(TAG, "on Error " + SpeechRecognizer.ERROR_NETWORK);

                    //toast("Please check data bundle or network settings");
                    break;
                case SpeechRecognizer.ERROR_AUDIO:
                    mError = " audio";
                    break;
                case SpeechRecognizer.ERROR_SERVER:
                    mError = " server";
                    //	startListening();
                    break;
                case SpeechRecognizer.ERROR_CLIENT:
                    mError = " client";
                    break;
                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                    mError = " speech time out";
                    break;

                case SpeechRecognizer.ERROR_NO_MATCH:
                    mError = " no match";

                    break;
                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                    cancelListening();
                    startListening(context);


                    break;
                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                    mError = " insufficient permissions";
                    break;

            }
            //			doSoundNormal();
            if (partialString.length() > 0) {
                ArrayList<String> listOfResults = new ArrayList<String>();

                if (!partialString.equalsIgnoreCase("")) {
                    result = " \" " + partialString + " ....\"";
                }
                txtHeader.setText(partialString + "");
                txtSubHeader.setVisibility(View.GONE);
                dissmissDialogue();
                Log.e(TAG, "In Error message  " + partialString);
                //	partialString="";
                Bundle bundle = new Bundle();
                listOfResults.add(partialString);
                bundle.putStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION, listOfResults);
                onResults(bundle);


            } else {
                dissmissDialogue();
                cancelListening();
                Log.i(TAG, "Error: " + error + " - " + mError);

            }

        }


        @Override
        public void onResults(Bundle results) {
            Log.i(TAG, "On Results ;)");
            System.out.println("On Results ;)");
            mStatus = "Got some results";
            Log.e(TAG, "onResults " + results);
            result = "";
            mResults = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            if (mResults.size() > 0) {

                result = mResults.get(0) + "";
                txtHeader.setText(result + "");
                txtSubHeader.setVisibility(View.GONE);
                returnSpeechResults(result);

            }

        }


        public void cancelListening() {
            if (recognizer != null) {
                recognizer.destroy();
                mResults.clear();
            }
        }


        private void dissmissDialogue() {
            dialog.dismiss();
        }

        public void returnSpeechResults(String voiceInput) {
            try {
                //  showProgressDialogue(ActivitySplashScreen.this);
                // text.setText(voiceInput + "");
                Log.e(TAG, voiceInput);
                final JSONObject object = new JSONObject();
                object.put("action", "process-asr-request");
                object.put("userInput", voiceInput.trim() + "");
                Log.e("Input request", object + "");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void startListening(Context context) {

        if (SpeechRecognizer.isRecognitionAvailable(context)) {
            if (recognizer != null) {
                promptSpeechInput(context);
                mResults = new ArrayList<String>();
            } else
                promptSpeechInput(context);
        }
    }


    public void requestRecordAudioPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
            String INTERNET = Manifest.permission.INTERNET;
            String ACCESS_NETWORK_STATE = Manifest.permission.ACCESS_NETWORK_STATE;
            String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
            String READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;

            // If the user previously denied this permission then show a message explaining why
            // this permission is needed
            ((Activity) context).requestPermissions(new String[]{RECORD_AUDIO, INTERNET, ACCESS_NETWORK_STATE, WRITE_EXTERNAL_STORAGE, READ_PHONE_STATE}, 101);

        }
    }


    @Override
    public void onClick(View view) {


    }

    public void textToSpeech(Context context, final String speechText) {
        try {
            talker = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int arg0) {
                    if (arg0 == TextToSpeech.SUCCESS) {
                        talker.setLanguage(Locale.US);
                        talker.setSpeechRate(0);
                        talker.speak(speechText, TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        // }
    }

}
