package com.example.angelmartindominguez.flavourtester;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class Data {
    private String product = "";
    private JSONObject data;

    public Data(String product){
        this.product = product;
        this.data = null;
    }

    private void getProductData() {
        InputStream is = null;
        try {
            URL url = new URL("https://world.openfoodfacts.org/api/v0/product/" + this.product +".json");
            URLConnection con = url.openConnection();
            is = con.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            if(null != jsonText && !jsonText.equals(""))
            {
                data = new JSONObject(jsonText);
            }
            else{
                data = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getProductName(){
        String name = "";
        this.getProductData();
        try {
            if(null != this.data){
                String status = this.data.get("status").toString();
                if(status.equals("1")){
                    JSONObject jo = (JSONObject) this.data.get("product");
                    name = jo.get("product_name").toString();
                }
                else if(status.equals("0")){
                    name="PRODUCT NOT FOUND";
                }
            }
            else{
                name = "NOT FOUND DATA EMPTY JSON";
            }
        } catch (JSONException e) {
            System.err.println(e);
            e.printStackTrace();
            name = "NOT FOUND DATA JSON PARSING";
        }
        return name;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
