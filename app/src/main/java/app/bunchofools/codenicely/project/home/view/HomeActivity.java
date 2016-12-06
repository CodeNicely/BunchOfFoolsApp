package app.bunchofools.codenicely.project.home.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import app.bunchofools.codenicely.project.R;
import app.bunchofools.codenicely.project.about_us.view.AboutUsFragment;
import app.bunchofools.codenicely.project.contact_us.view.ContactUsFragment;
import app.bunchofools.codenicely.project.developers.view.DeveloperFragment;
import app.bunchofools.codenicely.project.gallery.view.GalleryFragment;
import app.bunchofools.codenicely.project.helper.Keys;
import app.bunchofools.codenicely.project.home.model.data.FbData;
import app.bunchofools.codenicely.project.image_viewer.ImageViewerActivity;
import app.bunchofools.codenicely.project.join_us.view.JoinUsFragment;
import app.bunchofools.codenicely.project.spot_upload.view.UploadSpotFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.cordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setFragment(new HomeFragment(),"Home");

         }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(getSupportFragmentManager().getBackStackEntryCount() > 1) {

            super.onBackPressed();
        }else{
                // super.onBackPressed();

                final AlertDialog ad = new AlertDialog.Builder(this)
                        .create();
                ad.setCancelable(false);
                ad.setTitle("Exit ?");
                ad.setMessage("Do you really want to exit ?");
                ad.setButton(DialogInterface.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ad.cancel();
                        finish();
                    }
                });
                ad.setButton(DialogInterface.BUTTON_NEGATIVE, "no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ad.cancel();

                    }
                });
                ad.show();
            }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        String title="BunOfFools";
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            fragment=new HomeFragment();
            title="Home";
            getSupportFragmentManager().popBackStack();
        } else if (id == R.id.nav_spot) {
            fragment=new UploadSpotFragment();
            title="Upload Spot";
            getSupportFragmentManager().popBackStack();
        } else if (id == R.id.nav_gallery) {
            fragment=new GalleryFragment();
            title="Gallery";
        } else if (id == R.id.nav_join) {
            fragment=new JoinUsFragment();
            title="Join Us";
        } else if (id == R.id.nav_about) {
            fragment=new AboutUsFragment();
            title="About Us";
            getSupportFragmentManager().popBackStack();
        } else if (id == R.id.nav_developers) {
            fragment=new DeveloperFragment();
            title="Developers";
        }else if (id == R.id.nav_contact) {
            fragment=new ContactUsFragment();
            title="Contact Us";
        }

        if(id==R.id.nav_developers){
            toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.codeNicelyColor));
        }else{
            toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));

        }

        setFragment(fragment,title);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

public void setFragment(Fragment fragment,String title){
    if (fragment != null) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.commit();
        getSupportActionBar().setTitle(title);
    }
}
    public void openImageViewerFacebook(List<FbData> largeImageUrl, int position){

        ArrayList<String> imageUrlList=new ArrayList<>();
        for ( int i=0;i<largeImageUrl.size();i++){
            imageUrlList.add(largeImageUrl.get(i).getMedia().getImage().getSrc());
        }
        Intent intent = new Intent(this, ImageViewerActivity.class);
        intent.putStringArrayListExtra(Keys.KEY_IMAGE_LIST, imageUrlList);
        intent.putExtra(Keys.KEY_POSITION_IMAGE, position);
        startActivity(intent);
    }

    public void openImageViewer(ArrayList<String> largeImageUrl, int position){

        Intent intent = new Intent(this, ImageViewerActivity.class);
        intent.putStringArrayListExtra(Keys.KEY_IMAGE_LIST, largeImageUrl);
        intent.putExtra(Keys.KEY_POSITION_IMAGE, position);
        startActivity(intent);
    }


}
