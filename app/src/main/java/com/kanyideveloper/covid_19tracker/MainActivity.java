package com.kanyideveloper.covid_19tracker;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FancyWalkthroughActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);


        FancyWalkthroughCard card1 = new FancyWalkthroughCard("DO THE FOLLOWING","Help stop coronavirus",R.drawable.covid19);
        FancyWalkthroughCard card2 = new FancyWalkthroughCard("Wash your hands frequently",
                "Regularly and thoroughly clean your hands with an alcohol-based hand rub or wash them with soap and water.",R.drawable.handwash);
        FancyWalkthroughCard card3 = new FancyWalkthroughCard("Maintain social distancing",
                "Maintain at least 1 metre (3 feet) distance between yourself and anyone who is coughing or sneezing.",R.drawable.meterapart);
        FancyWalkthroughCard card4 = new FancyWalkthroughCard("Avoid touching eyes, nose and mouth",
                "Hands touch many surfaces and can pick up viruses. Once contaminated, hands can transfer the virus to your eyes, " +
                        "nose or mouth.",R.drawable.donttouch);
        FancyWalkthroughCard card5 = new FancyWalkthroughCard("Practice respiratory hygiene",
                "Follow good respiratory hygiene. This means covering your mouth and nose with your bent elbow or tissue when you cough or sneeze."
                        + " Then dispose of the used tissue immediately.",R.drawable.elbowcough);
        FancyWalkthroughCard card6 = new FancyWalkthroughCard("Stay at Home",
                "Stay at home to protect others, and use these strategies to keep life as normal as possible.",R.drawable.stayathome);
        FancyWalkthroughCard card7 = new FancyWalkthroughCard("Avoid hand shake",
                "Greet people with a wave, a node or a bow",R.drawable.han);


        card1.setBackgroundColor(R.color.CardColor);
        card1.setIconLayoutParams(300,300,0,0,0,0);

        card2.setBackgroundColor(R.color.CardColor);
        card2.setIconLayoutParams(300,300,0,0,0,0);

        card3.setBackgroundColor(R.color.CardColor);
        card3.setIconLayoutParams(300,300,0,0,0,0);

        card4.setBackgroundColor(R.color.CardColor);
        card4.setIconLayoutParams(300,300,0,0,0,0);

        card5.setBackgroundColor(R.color.CardColor);
        card5.setIconLayoutParams(300,300,0,0,0,0);

        card6.setBackgroundColor(R.color.CardColor);
        card6.setIconLayoutParams(300,300,0,0,0,0);

        card7.setBackgroundColor(R.color.CardColor);
        card7.setIconLayoutParams(300,300,0,0,0,0);

        List<FancyWalkthroughCard> pages = new ArrayList<>();


        pages.add(card1);
        pages.add(card2);
        pages.add(card3);
        pages.add(card4);
        pages.add(card5);
        pages.add(card6);
        pages.add(card7);


        for(FancyWalkthroughCard page : pages){
            page.setTitleColor(R.color.black);
            page.setDescriptionColor(R.color.black);
            page.setDescriptionTextSize(14);
        }

        setFinishButtonTitle("Check Covid-19 statistics");
        setColorBackground(R.color.backgroundColor);
        showNavigationControls(true);
        setActiveIndicatorColor(R.color.color_green);
        setInactiveIndicatorColor(R.color.grey_200);
        setOnboardPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {
        Intent i = new Intent(MainActivity.this,ContentActivity.class);
        startActivity(i);
    }
}
