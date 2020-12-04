package com.example.tylermitchell.connect3app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public String turn = "red";
    public int numEmptys = 9;

    //Keeping track of counter positions
    String[] counterPositions = {"empty","empty","empty","empty","empty","empty","empty","empty","empty"};

    //ArrayList<String>[][] counterPositions = new ArrayList[2][2]; - 2d array list useful for learning

    public boolean hasWon(String colour){
        //Checks if a player has won
        if(verticalWin(colour) || horizontalWin(colour)|| diagonialWin(colour)){
            return true;
        }
        return false;
    }

    public boolean verticalWin(String colour){
        //Checks for a vertical win
        for(int i=1;i<=2;i++){
            if((counterPositions[i] == counterPositions[i+3]) && (counterPositions[i] == counterPositions[i+6]) && (counterPositions[i] == colour)){
                return true;
            }
        }
        return false;
    }

    public boolean horizontalWin(String colour){
        //Checks for a horizontal win
        for(int i=0;i<=6;i+=3){
            if((counterPositions[i] == counterPositions[i+1]) && (counterPositions[i] == counterPositions[i+2]) && (counterPositions[i] == colour)){
                return true;
            }
        }
        return false;
    }

    public boolean diagonialWin(String colour){
        //Checks for a diagonal win
        if((counterPositions[0] == counterPositions[4]) && (counterPositions[0] == counterPositions[8]) && (counterPositions[0] == colour)){
            return true;
        }else if((counterPositions[2] == counterPositions[4]) && (counterPositions[2] == counterPositions[6]) && (counterPositions[2] == colour)){
            return true;
        }
        return false;
    }

    public void dropIn(View view){
        //Log.i("Info","clicked");

        //checking if position already has a counter

        ImageView counter = (ImageView) view;

        if( counterPositions[Integer.parseInt(counter.getTag().toString()) -1] == "empty"){
            counter.setTranslationY(-1500);

            if(turn == "red"){
                counter.setImageResource(R.drawable.red);
                counterPositions[Integer.parseInt(counter.getTag().toString()) -1] = "red";
                numEmptys -= 1;
                //turn = "yellow";
            }else{
                counter.setImageResource(R.drawable.yellow);
                counterPositions[Integer.parseInt(counter.getTag().toString()) -1] = "yellow";
                numEmptys -= 1;
                //turn = "red";
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(1000);
//            Log.i("counter Location" ,String.valueOf(Integer.parseInt(counter.getTag().toString()) -1)   );
//            Log.i("Counter colour",counterPositions[Integer.parseInt(counter.getTag().toString()) -1]);

            //Checking if there has been a win
            if(hasWon(turn)){
                Toast.makeText(this,  turn+" player has won! Congratulations!", Toast.LENGTH_LONG).show();

                //Button resetButton = (Button) findViewById(R.id.resetButton);
                TextView winnerText = (TextView) findViewById(R.id.winnerText);
                winnerText.setText(turn + " player has won!");
                winnerText.setVisibility(View.VISIBLE);
                //resetButton.setVisibility(View.VISIBLE);
            }else if(numEmptys == 0){
                Toast.makeText(this,"Its a Tie!", Toast.LENGTH_LONG).show();
                TextView winnerText = (TextView) findViewById(R.id.winnerText);
                winnerText.setText("Its a draw!");
                winnerText.setVisibility(View.VISIBLE);
            }



            // Updating turn
            if(turn =="red"){
                turn = "yellow";
            }else{
                turn = "red";
            }

        }

    }

    public void reset(View view){

        //Making textView invisable again
        TextView winnerText = (TextView) findViewById(R.id.winnerText);
        winnerText.setVisibility(View.INVISIBLE);

        //resetting counter Positions
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){

            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null); // removes the imageview
            counterPositions[i] = "empty";
        }
        numEmptys = 9;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
