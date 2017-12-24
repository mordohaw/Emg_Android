package williammordohay.localisationapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import williammordohay.localisationapp.R;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        getSupportActionBar().hide();

        //On récupère l'image
        final ImageView ivEmg = (ImageView) findViewById(R.id.logoView);

        //On affiche une petite fenetre de dialogue pour souhaiter la bienvenue à l'utilisateur
        Toast.makeText(this, R.string.welcome_message, Toast.LENGTH_SHORT).show();

        //On crée les animations que nous allons utiliser
        final Animation animApparition = AnimationUtils.loadAnimation(getBaseContext(),R.anim.appearance_effect);

        genereAnim(animApparition,ivEmg);

        quitteAnim(animApparition, ivEmg);

    }

    public void genereAnim(Animation animation, final ImageView image)
    {
        //On applique les animations aux images
        image.startAnimation(animation);
    }

    public void quitteAnim(final Animation animFin, final ImageView imgFin)
    {
        final Animation animQuitter = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);
        animFin.setAnimationListener(new Animation.AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            // à la fin de l'animation, on  lance l'animation "animQuitter" et on va à l'activité suivante
            public void onAnimationEnd(Animation animation)
            {
                imgFin.startAnimation(animQuitter);
                finish();

                demarrageApplication();
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
    }

    public void demarrageApplication(){
        Intent demarrage;

        demarrage = new Intent(LaunchActivity.this, MainActivity.class);

        startActivity(demarrage);
    }
}
