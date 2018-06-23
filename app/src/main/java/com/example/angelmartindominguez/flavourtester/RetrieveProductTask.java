package com.example.angelmartindominguez.flavourtester;

import android.os.AsyncTask;

public class RetrieveProductTask extends AsyncTask {
    private MenuLateral caller;

    RetrieveProductTask(MenuLateral caller){
        this.caller = caller;
    }
    @Override
    protected Object[] doInBackground(Object[] objects) {
        Object[] objs = new Object[2];
        if(null != objects){
            Data data = new Data(objects[0].toString());
            objs[0] = data.getProductName();
            objs[1] = data.getProductImage();
            return objs;
        }
        objs[0] = "NOT FOUND doInBackground";
        objs[1] = "IMAGE NOT FOUND doInBackground";
        return objs;
    }

    @Override
    protected void onPostExecute(Object o) {
        Object[] objs = (Object[]) o;
        if(null != o) {
            caller.onBackgroundTaskCompleted(objs);
        }
        else{
            objs[0] = "PRODUCT NOT FOUND onPostExecute";
            objs[1] = "IMAGE NOT FOUND onPostExecute";
            caller.onBackgroundTaskCompleted(objs);
        }
    }
}
