package com.example.vladok.testtask.util;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * This class executes Logcat save to file  in background thread.
 */
public class AsyncLogWriter extends AsyncTask<Void, String, Void> {

    public static final String LOG_TAG = "AsyncLogWriter";
    public static final String DIR_SD = "MyFiles";
    public static final String FILENAME_SD = "Logcat.txt";

    private StringBuilder mLog;
    private Process mLogProcess;
    private BufferedReader mBufferedReader;
    private File mFileSdPath;


    @Override
    protected Void doInBackground(Void... params) {
        //Проверяем доступ к SD карте
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return null;
        }
        try {
            //Формируем наш лог
            mLogProcess = Runtime.getRuntime().exec("logcat -d");
            mBufferedReader = new BufferedReader(
                    new InputStreamReader(mLogProcess.getInputStream()));
            mLog = new StringBuilder();
            String line;
            while ((line = mBufferedReader.readLine()) != null) {
                mLog.append(line + "\n");
            }
            //Получаем путь
            mFileSdPath = Environment.getExternalStorageDirectory();
            mFileSdPath = new File(mFileSdPath.getAbsolutePath() + "/" + DIR_SD);
            mFileSdPath.mkdirs();
            //Создаем по пути файл
            File sdFile = new File(mFileSdPath, FILENAME_SD);
            //Записываем наш файл
            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
            bw.write(mLog.toString());
            bw.close();
            Log.d(LOG_TAG, "Log был записан в файл на sd карту. ");
            mLogProcess.destroy();

        } catch (IOException e) {
        }
        return null;

    }
}