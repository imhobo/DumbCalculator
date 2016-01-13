package com.imhobo.dac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.imhobo.dac.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Socket socket;

    private static final int SERVERPORT = 5000;
    private static final String SERVER_IP = "10.42.0.45";

    static String query = "0";
    static String completeQuery = "";

    boolean operClick = false;

    EditText display;

    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;
    Button buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Declaring the edittext
        display = (EditText) findViewById(R.id.editText);
        display.setText(query);

        //Declaring buttons 0-9
        Button button0 = (Button)findViewById(R.id.button15);
        button0.setOnClickListener(this);
        Button button1 = (Button)findViewById(R.id.button11);
        button1.setOnClickListener(this);
        Button button2= (Button)findViewById(R.id.button12);
        button2.setOnClickListener(this);
        Button button3 = (Button)findViewById(R.id.button13);
        button3.setOnClickListener(this);
        Button button4 = (Button)findViewById(R.id.button3);
        button4.setOnClickListener(this);
        Button button5= (Button)findViewById(R.id.button4);
        button5.setOnClickListener(this);
        Button button6 = (Button)findViewById(R.id.button9);
        button6.setOnClickListener(this);
        Button button7 = (Button)findViewById(R.id.button5);
        button7.setOnClickListener(this);
        Button button8= (Button)findViewById(R.id.button6);
        button8.setOnClickListener(this);
        Button button9 = (Button)findViewById(R.id.button7);
        button9.setOnClickListener(this);

        //Declaring operators
        buttonA = (Button)findViewById(R.id.button18);
        buttonA.setOnClickListener(this);
        buttonB = (Button)findViewById(R.id.button14);
        buttonB.setOnClickListener(this);
        buttonC= (Button)findViewById(R.id.button10);
        buttonC.setOnClickListener(this);
        buttonD = (Button)findViewById(R.id.button8);
        buttonD.setOnClickListener(this);

        //Declaring other buttons
        buttonDot = (Button)findViewById(R.id.button16);
        buttonDot.setOnClickListener(this);
        Button buttonEq = (Button)findViewById(R.id.button17);
        buttonEq.setOnClickListener(this);
        Button buttonBack= (Button)findViewById(R.id.button1);
        buttonBack.setOnClickListener(this);
        Button buttonAC = (Button)findViewById(R.id.button2);
        buttonAC.setOnClickListener(this);

    }

    public void disableOper()
    {
        buttonA.getBackground().setAlpha(128);
        buttonA.setEnabled(false);
        buttonB.getBackground().setAlpha(128);
        buttonB.setEnabled(false);
        buttonC.getBackground().setAlpha(128);
        buttonC.setEnabled(false);
        buttonD.getBackground().setAlpha(128);
        buttonD.setEnabled(false);
    }

    public void enableOper()
    {
        buttonA.getBackground().setAlpha(255);
        buttonA.setEnabled(true);
        buttonB.getBackground().setAlpha(255);
        buttonB.setEnabled(true);
        buttonC.getBackground().setAlpha(255);
        buttonC.setEnabled(true);
        buttonD.getBackground().setAlpha(255);
        buttonD.setEnabled(true);
    }

    public void disbaleDot()
    {
        buttonDot.getBackground().setAlpha(128);
        buttonDot.setEnabled(false);
    }

    public void enableDot()
    {
        buttonDot.getBackground().setAlpha(255);
        buttonDot.setEnabled(true);
    }

    public String cleanDot(String str)
    {
        int len = str.length();
        if(str.charAt(len-1) == '.')
            str = str.substring(0,len-1);
        return str;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            //Handling All Clear
            case R.id.button2:
                completeQuery = "";
                query = "0";
                display.setText(query);
                enableDot();
                enableOper();
                operClick = false;
                break;

            //Handling dot button
            case R.id.button16:
                query+=".";
                display.setText(query);
                disbaleDot();
                break;


            //Handling equal to button
            case R.id.button17:
                if(!operClick)
                {

                }
                else
                {
                    operClick = false;
                    query = cleanDot(query);
                    completeQuery+=query;
                    ClientThread obj = new ClientThread();
                    obj.setQuery(completeQuery);
                    try
                    {
                        Thread t = new Thread(obj);
                        t.start();
                        t.join();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    query= obj.getResult();
                    display.setText(query);
                    Log.d("AfterSetting", query);
                    if (query == "NO INTERNET")query = "0";
                    completeQuery = "";
                    enableOper();
                    enableDot();
                }
                break;

            //Handling buttons 0-9
            case R.id.button15:
                if(query!="0")
                {
                    query += "0";
                    display.setText(query);
                }
                else display.setText(query);
                break;
            case R.id.button11:
                if(query!="0") query+="1";
                else query="1";

                display.setText(query);
                break;
            case R.id.button12:
                if(query!="0") query+="2";
                else query="2";

                display.setText(query);
                break;
            case R.id.button13:
                if(query!="0") query+="3";
                else query="3";

                display.setText(query);
                break;
            case R.id.button3:
                if(query!="0") query+="4";
                else query="4";

                display.setText(query);
                break;
            case R.id.button4:
                if(query!="0") query+="5";
                else query="5";

                display.setText(query);
                break;
            case R.id.button9:
                if(query!="0") query+="6";
                else query="6";

                display.setText(query);
                break;
            case R.id.button5:
                if(query!="0") query+="7";
                else query="7";

                display.setText(query);
                break;
            case R.id.button6:
                if(query!="0") query+="8";
                else query="8";

                display.setText(query);
                break;
            case R.id.button7:
                if(query!="0") query+="9";
                else query="9";

                display.setText(query);
                break;

            //Handling operators +,-,*,/

            case R.id.button18:

                if(!operClick)
                {
                    query = cleanDot(query);
                    completeQuery += query;
                    completeQuery += " + ";
                    query = "0";
                    display.setText(query);
                    disableOper();
                    operClick = true;
                    enableDot();
                }

                break;

            case R.id.button14:
                if(!operClick)
                {
                    query = cleanDot(query);
                    completeQuery += query;
                    completeQuery += " - ";
                    query = "0";
                    display.setText(query);
                    disableOper();
                    operClick = true;
                    enableDot();
                }
                break;
            case R.id.button10:
                if(!operClick)
                {
                    query = cleanDot(query);
                    completeQuery += query;
                    completeQuery += " * ";
                    query = "0";
                    display.setText(query);
                    disableOper();
                    operClick = true;
                    enableDot();
                }
                break;
            case R.id.button8:
                if(!operClick)
                {
                    query = cleanDot(query);
                    completeQuery += query;
                    completeQuery += " / ";
                    query = "0";
                    display.setText(query);
                    disableOper();
                    operClick = true;
                    enableDot();
                }
                break;
        }

    }

    class ClientThread implements Runnable
    {
        String query = "";
        String res;

        public void setQuery(String str)
        {
            query = str;
        }

        public String getResult()
        {
            return res;
        }

        @Override
        public void run()
        {
            try
            {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                Log.d("DEBUG", "Trying to connect..");
                socket = new Socket(serverAddr, SERVERPORT);
                Log.d("DEBUG", "Connected");
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())),
                        true);
                out.println(query);


                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                res = reader.readLine();
                return;

            } catch (UnknownHostException e1)
            {
                res = "NO INTERNET";
                e1.printStackTrace();
            } catch (IOException e1)
            {   res = "NO INTERNET";
                e1.printStackTrace();
            }


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
