package com.example.felix_000.naievents_beta;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseCrashReporting;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class EventCreationActivity extends ActionBarActivity implements View.OnClickListener {
    private ImageView saveButton;
    private EditText editText;
    private DatePickerDialog datePickerDialog;
    private EditText fromDate;
    private EditText toDate;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private Button button;
    private Button btnUpload;
    ParseObject event = new ParseObject("EventsData");
    private SimpleDateFormat simpleDateFormat;
    byte[] imageBytes;
    private Object category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_creation);
      final Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.events_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),1 );
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                category = parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Enable Crash Reporting
        ParseCrashReporting.enable(this);
         //Enable Local Datastores
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "yKTdQtjm9QjPKlym7zVFjJrXjbmAX5EncrgUZCbV", "pMSZxLIDpkcgqxIiTYAM1Ybh58uGesRKt5N1nXHf");
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        findViewsById();
        setDateTimeField();
        saveButton = (ImageView) findViewById(R.id.add);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Saving...", Toast.LENGTH_SHORT).show();
                event.put("Technology", category.toString());
                event.put("fromDate", fromDate.getText().toString());
                event.put("toDate", toDate.getText().toString());
                final EditText title = (EditText) findViewById(R.id.title);
                event.put("Title", title.getText().toString());
                final EditText location = (EditText) findViewById(R.id.location);
                event.put("Location", location.getText().toString());
                ParseFile image = new ParseFile("Logo", imageBytes);
                image.saveInBackground();
                event.put("Logo", image);
                event.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            Toast.makeText(getApplicationContext(), "Event added successfully", Toast.LENGTH_LONG).show();
                            title.setText("");
                            location.setText("");
                            toDate.setText("");
                            fromDate.setText("");
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Error, check your internet connection.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
    //Set Date Pickers

    private void findViewsById() {
        fromDate = (EditText) findViewById(R.id.fromdate);
        fromDate.setInputType(InputType.TYPE_NULL);
        fromDate.requestFocus();

        toDate = (EditText) findViewById(R.id.todate);
        toDate.setInputType(InputType.TYPE_NULL);
        fromDate.requestFocus();
    }

    private void setDateTimeField() {
        fromDate.setOnClickListener(this);
        toDate.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDate.setText(simpleDateFormat.format(newDate.getTime()));


            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDate.setText(simpleDateFormat.format(newDate.getTime()));


            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
   public void addEvent(View view){
      // setDateTimeField();


   }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_creation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view == fromDate) {
            fromDatePickerDialog.show();
        } else if(view == toDate) {
            toDatePickerDialog.show();
        }
    }
    // To handle when an image is selected from the browser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                // currImageURI is the global variable I’m using to hold the content:
               Uri currImageURI = data.getData();
                System.out.println("Current image Path is ----->" + getRealPathFromURI(currImageURI));
                TextView tv_path = (TextView) findViewById(R.id.path);
                tv_path.setText(getRealPathFromURI(currImageURI));
                try {
                    InputStream imageStream = getContentResolver().openInputStream(currImageURI);
                  imageBytes = getBytes(imageStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }
    //Convert the image URI to the direct file system path of the image file
    public String getRealPathFromURI(Uri contentUri) {
        String [] proj={MediaStore.Images.Media.DATA};
        android.database.Cursor cursor = managedQuery( contentUri,
                proj,     // Which columns to return
                null,     // WHERE clause; which rows to return (all rows)
                null,     // WHERE clause selection arguments (none)
                null);     // Order-by clause (ascending by name)
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
