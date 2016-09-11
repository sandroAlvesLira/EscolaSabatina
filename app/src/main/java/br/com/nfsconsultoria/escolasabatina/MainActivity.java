package br.com.nfsconsultoria.escolasabatina;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Declaração das URLs
    final String url1 = "http://evangelismo.adventistas.org.pt/licao/2016/3T/1";
    final String url2 = "http://evangelismo.adventistas.org.pt/licao/2016/3T/2";
    final String url3 = "http://evangelismo.adventistas.org.pt/licao/2016/3T/3";
    final String url4 = "http://evangelismo.adventistas.org.pt/licao/2016/3T/4";
    final String url5 = "http://evangelismo.adventistas.org.pt/licao/2016/3T/5";
    final String url6 = "http://evangelismo.adventistas.org.pt/licao/2016/3T/6";
    final String url7 = "http://evangelismo.adventistas.org.pt/licao/2016/3T/7";
    final String url8 = "http://evangelismo.adventistas.org.pt/licao/2016/3T/8";
    final String url9 = "http://evangelismo.adventistas.org.pt/licao/2016/3T/9";
    final String url10 = "http://evangelismo.adventistas.org.pt/licao/2016/3T/10";
    final String url11 = "http://evangelismo.adventistas.org.pt/licao/2016/3T/11";
    final String url12 = "http://evangelismo.adventistas.org.pt/licao/2016/3T/12";
    final String url13 = "http://evangelismo.adventistas.org.pt/licao/2016/3T/13";
    String url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Exibe();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // TODO Dialogo de item não disponivel
    public void Dialogo() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        alerta.setTitle(getString(R.string.app_name));
        alerta.setMessage("Lição ainda não disponivel no momento. Aguarde...");

        AlertDialog dialog = alerta.create();
        dialog.show();
    }

    // TODO Exibir o conteudo da lição na tela
    public void Exibe() {

        final WebView webView = (WebView) this.findViewById(R.id.wbView);
        final View progress = this.findViewById(R.id.progress);
        final ImageView telaInicio = (ImageView) this.findViewById(R.id.telaInicio);

        progress.setVisibility(View.INVISIBLE);

        // Exibe tela de apresentação
        if (url == null) {
            telaInicio.setVisibility(View.VISIBLE);
        } else {
            telaInicio.setVisibility(View.INVISIBLE);
           webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.setPadding(0, 0, 0, 0);
            webView.getSettings().setLoadWithOverviewMode(false);
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

        Date hoje = new Date();
        String formato = "yyyyMMdd";
        SimpleDateFormat dataFormatada = new SimpleDateFormat(formato);
        Long compara = Long.parseLong(dataFormatada.format(hoje));

        int id = item.getItemId();

        if (id == R.id.licao1) {
            url = url1;
            if (compara > 20160624) {
                Exibe();
            } else {
                Dialogo();
            }
        } else if (id == R.id.licao2) {
            url = url2;
            if (compara > 20160701) {
                Exibe();
            } else {
                Dialogo();
            }
        } else if (id == R.id.licao3) {
            url = url3;
            if (compara > 20160709) {
                Exibe();
            } else {
                Dialogo();
            }
        } else if (id == R.id.licao4) {
            url = url4;
            if (compara > 20160715) {
                Exibe();
            } else {
                Dialogo();
            }
        } else if (id == R.id.licao5) {
            url = url5;
            if (compara > 20160722) {
                Exibe();
            } else {
                Dialogo();
            }
        } else if (id == R.id.licao6) {
            url = url6;
            if (compara > 20160729) {
                Exibe();
            } else {
                Dialogo();
            }
        } else if (id == R.id.licao7) {
            url = url7;
            if (compara > 20160806) {
                Exibe();
            } else {
                Dialogo();
            }
        } else if (id == R.id.licao8) {
            url = url8;
            if (compara > 20160812) {
                Exibe();
            } else {
                Dialogo();
            }
        } else if (id == R.id.licao9) {
            url = url9;
            if (compara > 20160819) {
                Exibe();
            } else {
                Dialogo();
            }
        } else if (id == R.id.licao10) {
            url = url10;
            if (compara > 20160826) {
                Exibe();
            } else {
                Dialogo();
            }
        } else if (id == R.id.licao11) {
            url = url11;
            if (compara > 20160902) {
                Exibe();
            } else {
                Dialogo();
            }
        } else if (id == R.id.licao12) {
            url = url12;
            if (compara > 20160909) {
                Exibe();
            } else {
                Dialogo();
            }
        } else if (id == R.id.licao13) {
            url = url13;
            if (compara > 20160916) {
                Exibe();
            } else {
                Dialogo();
            }
        } else if (id == R.id.sair) {
            MainActivity.this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
