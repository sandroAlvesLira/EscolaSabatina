package br.com.nfsconsultoria.escolasabatina;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final String url1 = "http://evangelismo.adventistas.org.pt/licao/2016/2T/1";
    final String url2 = "http://evangelismo.adventistas.org.pt/licao/2016/2T/2";
    final String url3 = "http://evangelismo.adventistas.org.pt/licao/2016/2T/3";
    final String url4 = "http://evangelismo.adventistas.org.pt/licao/2016/2T/4";
    final String url5 = "http://evangelismo.adventistas.org.pt/licao/2016/2T/5";
    final String url6 = "http://evangelismo.adventistas.org.pt/licao/2016/2T/6";
    final String url7 = "http://evangelismo.adventistas.org.pt/licao/2016/2T/7";
    final String url8 = "http://evangelismo.adventistas.org.pt/licao/2016/2T/8";
    final String url9 = "http://evangelismo.adventistas.org.pt/licao/2016/2T/9";
    final String url10 = "http://evangelismo.adventistas.org.pt/licao/2016/2T/10";
    final String url11 = "http://evangelismo.adventistas.org.pt/licao/2016/2T/11";
    final String url12 = "http://evangelismo.adventistas.org.pt/licao/2016/2T/12";
    final String url13 = "http://evangelismo.adventistas.org.pt/licao/2016/2T/13";
    String url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Exibe();
        Menu();
 /**
 FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
 fab.setOnClickListener(new View.OnClickListener() {
@Override public void onClick(View view) {
Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
.setAction("Action", null).show();
}
});
 */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void Menu() {

        /**
        final MenuItem item1 = (MenuItem) this.findViewById(R.id.licao1);
        final MenuItem item2 = (MenuItem) this.findViewById(R.id.licao2);
        final MenuItem item3 = (MenuItem) this.findViewById(R.id.licao3);
        final MenuItem item4 = (MenuItem) this.findViewById(R.id.licao4);
        final MenuItem item5 = (MenuItem) this.findViewById(R.id.licao5);
        final MenuItem item6 = (MenuItem) this.findViewById(R.id.licao6);
        final MenuItem item7 = (MenuItem) this.findViewById(R.id.licao7);
        final MenuItem item8 = (MenuItem) this.findViewById(R.id.licao8);
        final MenuItem item9 = (MenuItem) this.findViewById(R.id.licao9);
        final MenuItem item10 = (MenuItem) this.findViewById(R.id.licao10);
        final MenuItem item11 = (MenuItem) this.findViewById(R.id.licao11);
        final MenuItem item12 = (MenuItem) this.findViewById(R.id.licao12);
        final MenuItem item13 = (MenuItem) this.findViewById(R.id.licao13);
*/

        Drawable icoDone = getResources().getDrawable(R.drawable.ic_book_24dp);
        Drawable icoAtual = getResources().getDrawable(R.drawable.ic_school_24dp);

        Date hoje = new Date();
        String formato = "yyyyMMdd";
        SimpleDateFormat dataFormatada = new SimpleDateFormat(formato);
        Long compara = Long.parseLong(dataFormatada.format(hoje));

/**
        if ((compara >= 20151226) && (compara <= 20160101)) {
            menu.getItem(0).setIcon(icoAtual);
        } else if (compara > 20160101) {
            menu.getItem(0).setIcon(icoDone);
        }

        if ((compara >= 20160102) && (compara <= 20160108)) {
            menu.getItem(1).setIcon(icoAtual);
        } else if (compara > 20160108) {
            menu.getItem(1).setIcon(icoDone);
        }

        if ((compara >= 20160109) && (compara <= 20160115)) {
            menu.getItem(2).setIcon(icoAtual);
        } else if (compara > 20160115) {
            menu.getItem(2).setIcon(icoDone);
        }

        if ((compara >= 20160116) && (compara <= 20160122)) {
            menu.getItem(3).setIcon(icoAtual);
        } else if (compara > 20160122) {
            menu.getItem(3).setIcon(icoDone);
        }

        if ((compara >= 20160123) && (compara <= 20160129)) {
            menu.getItem(4).setIcon(icoAtual);
        } else if (compara > 20160129) {
            menu.getItem(4).setIcon(icoDone);
        }

        if ((compara >= 20160130) && (compara <= 20160205)) {
            menu.getItem(5).setIcon(icoAtual);
        } else if (compara > 20160205) {
            menu.getItem(5).setIcon(icoDone);
        }

        if ((compara >= 20160206) && (compara <= 20160212)) {
            menu.getItem(6).setIcon(icoAtual);
        } else if (compara > 20160212) {
            menu.getItem(6).setIcon(icoDone);
        }

        if ((compara >= 20160213) && (compara <= 20160219)) {
            menu.getItem(7).setIcon(icoAtual);
        } else if (compara > 20160219) {
            menu.getItem(7).setIcon(icoDone);
        }

        if ((compara >= 20160220) && (compara <= 20160226)) {
            menu.getItem(8).setIcon(icoAtual);
        } else if (compara > 20160226) {
            menu.getItem(8).setIcon(icoDone);
        }

        if ((compara >= 20160227) && (compara <= 20160304)) {
            menu.getItem(9).setIcon(icoAtual);
        } else if (compara > 20160304) {
            menu.getItem(9).setIcon(icoDone);
        }

        if ((compara >= 20160305) && (compara <= 20160311)) {
            menu.getItem(10).setIcon(icoAtual);
        } else if (compara > 20160311) {
            menu.getItem(10).setIcon(icoDone);
        }

        if ((compara >= 20160312) && (compara <= 20160318)) {
            menu.getItem(11).setIcon(icoAtual);
        } else if (compara > 20160318) {
            menu.getItem(11).setIcon(icoDone);
        }

        if ((compara >= 20160318) && (compara <= 20160225)) {
            menu.getItem(12).setIcon(icoAtual);
        } else if (compara > 20160325) {
            menu.getItem(12).setIcon(icoDone);
        }
 */
    }

    public void Exibe() {

        final WebView webView = (WebView) this.findViewById(R.id.wbView);
        final View progress = this.findViewById(R.id.progress);

        progress.setVisibility(View.INVISIBLE);

        if (url == null) {
            webView.loadDataWithBaseURL("", "<h4><p><font color= 'blue' size='05'>BEM VINDO AO ESTUDO DA " +
                    "LIÇÃO DA ESCOLA SABATINA 2º TRIMESTRE DE 2016</font></p></h4>" +
                    "<p><font color='red' size='03'>Aplicativo desenvolvido por Luis Carlos Santos - NFS" +
                    "</font></p>", "text/html", "UTF-8", "");
        } else {
           webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            //webView.getSettings().setBuiltInZoomControls(true);
            webView.setPadding(0, 0, 0, 0);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            webView.loadUrl(url);

            webView.setWebViewClient(new WebViewClient() {
                private WebView view;
                private int errorCode;
                private String description;
                private String failingUrl;

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    progress.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    progress.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    webView.loadDataWithBaseURL("", "<font color= 'blue'>FALHA DE CONEXÃO<font>", "text/html", "UTF-8", "");
                    Toast.makeText(MainActivity.this, "FALHA NA CONEXÃO COM A INTERNET", Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            MainActivity.this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        // final TextView texto = (TextView) this.findViewById(R.id.texto);

        int id = item.getItemId();

        if (id == R.id.licao1) {
            url = url1;
            Exibe();
        } else if (id == R.id.licao2) {
            url = url2;
            Exibe();
        } else if (id == R.id.licao3) {
            url = url3;
            Exibe();
        } else if (id == R.id.licao4) {
            url = url4;
            Exibe();
        } else if (id == R.id.licao5) {
            url = url5;
            Exibe();
        } else if (id == R.id.licao6) {
            url = url6;
            Exibe();
        } else if (id == R.id.licao7) {
            url = url7;
            Exibe();
        } else if (id == R.id.licao8) {
            url = url8;
            Exibe();
        } else if (id == R.id.licao9) {
            url = url9;
            Exibe();
        } else if (id == R.id.licao10) {
            url = url10;
            Exibe();
        } else if (id == R.id.licao11) {
            url = url11;
            Exibe();
        } else if (id == R.id.licao12) {
            url = url12;
            Exibe();
        } else if (id == R.id.licao13) {
            url = url13;
            Exibe();
        } else if (id == R.id.sair) {
            MainActivity.this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
