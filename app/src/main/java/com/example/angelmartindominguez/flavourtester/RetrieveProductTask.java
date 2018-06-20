package com.example.angelmartindominguez.flavourtester;

import android.os.AsyncTask;

public class RetrieveProductTask extends AsyncTask {
    MenuLateral caller;

    RetrieveProductTask(MenuLateral caller){
        this.caller = caller;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        if(null != objects){
            Data data = new Data(objects[0].toString());
            return data.getProductName();
        }
        return "NOT FOUND DO IN BACKGROUND";
    }

    @Override
    protected void onPostExecute(Object o) {
        if(null != o) {
            caller.onBackgroundTaskCompleted(o);
        }
        else{
            caller.onBackgroundTaskCompleted("NOT FOUND ON POST EXECUTE");
        }
    }
}
