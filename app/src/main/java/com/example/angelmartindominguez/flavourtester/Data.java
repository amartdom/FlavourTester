package com.example.angelmartindominguez.flavourtester;

import android.os.Environment;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.MalformedURLException;
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

    public String getProductImage(){
        String imageURL = "";
        String image = "";
        this.getProductData();
        try {
            if(null != this.data){
                String status = this.data.get("status").toString();
                if(status.equals("1")){
                    JSONObject jo = (JSONObject) this.data.get("product");
                    imageURL = jo.get("image_front_url").toString();
                    System.err.println("\n\n\n\n" +imageURL);
                    String[] ss = imageURL.split("\\.");
                    String type = null;
                    if(1 <= ss.length){
                        type = ss[ss.length -1];
                    }
                    else{
                        System.err.println("No existe la URL");
                        type="jpg";
                    }
                    String sFolder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/flavourtester/downloadfiles";
                    System.err.println("\n\n\n\n" + sFolder);
                    image = sFolder + "/"  + getProductName().replace(" ","_") + "." + type;
                    System.err.println("\n\n\n\n" +image);
                    File dirs = new File(sFolder);
                    if(!dirs.mkdirs()){
                        System.err.println("No se crean las putas carpetas");
                    }
                    dirs.mkdir();
                    File img = new File(image);

                    try {
                        img.createNewFile();
                    } catch (IOException e) {
                        Log.e("saveImageIOExceptionCr",e.getMessage());
                    }

                    try {

                        URL imageUrl = null;
                        imageUrl = new URL(imageURL);

                        URLConnection urlConnection = imageUrl.openConnection();
                        urlConnection.connect();
                        int file_size = urlConnection.getContentLength()/1000;

                        InputStream in =new BufferedInputStream(imageUrl.openStream(), file_size);
                        int total = 0;
                        OutputStream out = new BufferedOutputStream(new FileOutputStream(img));
                        byte data[] = new byte[1024];
                        for (int b; (b = in.read(data)) != -1;) {
                            total += b/1000;
                            out.write(data, 0, b);

                        }

                        out.close();
                        in.close();
                    } catch (MalformedURLException e) {
                        img = null;
                        Log.e("saveImageMalformed",e.getMessage());
                    } catch (IOException e) {
                        img = null;
                        Log.e("saveImageIOExceptionWr",e.getMessage());
                    }

                }
                else if(status.equals("0")){
                    image="IMAGE NOT FOUND";
                }
                else{
                    image = "NOT FOUND DATA EMPTY JSON";
                }
            }

        } catch (JSONException e) {
            System.err.println(e);
            e.printStackTrace();
            image = "NOT FOUND DATA JSON PARSING";
        }
        return image;
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
