package br.com.nfsconsultoria.escolasabatina;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Declaração das URLs
    final private String url1 = "http://evangelismo.adventistas.org.pt/licao/2016/4T/1";
    final private String url2 = "http://evangelismo.adventistas.org.pt/licao/2016/4T/2";
    final private String url3 = "http://evangelismo.adventistas.org.pt/licao/2016/4T/3";
    final private String url4 = "http://evangelismo.adventistas.org.pt/licao/2016/4T/4";
    final private String url5 = "http://evangelismo.adventistas.org.pt/licao/2016/4T/5";
    final private String url6 = "http://evangelismo.adventistas.org.pt/licao/2016/4T/6";
    final private String url7 = "http://evangelismo.adventistas.org.pt/licao/2016/4T/7";
    final private String url8 = "http://evangelismo.adventistas.org.pt/licao/2016/4T/8";
    final private String url9 = "http://evangelismo.adventistas.org.pt/licao/2016/4T/9";
    final private String url10 = "http://evangelismo.adventistas.org.pt/licao/2016/4T/10";
    final private String url11 = "http://evangelismo.adventistas.org.pt/licao/2016/4T/11";
    final private String url12 = "http://evangelismo.adventistas.org.pt/licao/2016/4T/12";
    final private String url13 = "http://evangelismo.adventistas.org.pt/licao/2016/4T/13";
    final private String url14 = "http://evangelismo.adventistas.org.pt/licao/2016/4T/14";

    private String url = null;
    private String licao = null;
    private String estudo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Exibe();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /* Salvar url da instancia atual */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("url", url);
        outState.putSerializable("estudo", estudo);
    }

    /* Reculperar a url da instancia salva */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        url = (String) savedInstanceState.getSerializable("url");
        estudo = (String) savedInstanceState.getSerializable("estudo");
        if (url == null) {
            Dialogo();
        } else {
            Exibe();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.notas) {
            if (estudo != null) {
                Anota("Anotações da " + estudo, "");
                return true;
            } else {
                toast("Anotação indisponivel no momento");
            }
        } else if (id == R.id.excluir) {
            File arq = new File(Environment.getExternalStorageDirectory() +
                    "/Android/data" +
                    "/br.com.nfsconsultoria.escolasabatina/files/Download/" + licao);
            if (arq.exists()) {
                arq.delete();
                toast("Lição " + licao + " excluida com sucesso");
            } else {
                toast("Nenhuma lição encontrada");
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /* Metodo de menssagem toast */
    public void toast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    /* Metodo para baixar a lição em HTML */
    public void Download() {

        String serviceString = Context.DOWNLOAD_SERVICE;
        DownloadManager downloadManager;
        downloadManager = (DownloadManager) getSystemService(serviceString);
        File file = new File(Environment.getExternalStorageDirectory() + "/Android/data" +
                "/br.com.nfsconsultoria.escolasabatina/files/Download/" + licao);

        try {
            Uri uri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setAllowedOverRoaming(true);
            request.setDestinationInExternalFilesDir(MainActivity.this,
                    Environment.DIRECTORY_DOWNLOADS, licao);
            if (file.exists()) {
                toast("Lição disponivel offline");
            } else {
                Long reference = downloadManager.enqueue(request);
                toast("Download da lição efetuado com sucesso");
            }
        } catch (RuntimeException erro) {
            toast("Arquivo inexistente");
        }
    }

    /* Dialogo de item não disponivel */
    public void Dialogo() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        alerta.setTitle(getString(R.string.app_name));
        alerta.setMessage("Lição ainda não disponivel no momento. Aguarde...");

        AlertDialog dialog = alerta.create();
        dialog.show();

        url = null;
    }

    /* Metodo para anotações da lição */
    public void Anota(String titulo, String texto) {
        final AlertDialog.Builder mensagem = new AlertDialog.Builder(MainActivity.this);
        final ScrollView scrollView = new ScrollView(MainActivity.this);
        mensagem.setTitle(titulo);
        mensagem.setMessage(texto);
        final EditText input = new EditText(this);
        input.setHint("Digite as suas anotações");
        input.setMaxHeight(500);
        scrollView.addView(input);
        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
        mensagem.setView(scrollView);

        final SharedPreferences notas = getSharedPreferences(estudo, 0);
        input.setText(notas.getString(estudo, " "));

        mensagem.setNeutralButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                SharedPreferences.Editor editor = notas.edit();
                editor.putString(estudo, input.getText().toString());
                editor.apply();
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
                toast("Anotações Salva");

            }
        });
        mensagem.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mensagem.setCancelable(true);
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);

            }
        });
        mensagem.show();
        // Força o teclado aparecer ao abrir o Alert
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /* Exibir o conteudo da lição na tela */
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
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setSupportMultipleWindows(true);
            webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            webView.getSettings().setDefaultTextEncodingName("UTF-8");
            webView.getSettings().setSaveFormData(true);
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
                public void onReceivedError(WebView view, int errorCode, String description,
                                            String failingUrl) {
                    File file = new File(Environment.getExternalStorageDirectory()
                            + "/Android/data" +
                            "/br.com.nfsconsultoria.escolasabatina/files/Download/" + licao);

                    if (file.exists()) {
                        webView.loadUrl("file:///" + file.getAbsolutePath());
                    } else {
                        webView.loadDataWithBaseURL("", "<font color= 'blue'>" +
                                "FALHA DE CONEXÃO<font>", "text/html", "UTF-8", "");
                        toast("FALHA NA CONEXÃO COM A INTERNET");
                    }
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
            licao = "licao1_3_2016.html";
            if (compara > 20160923) {
                estudo = getString(R.string.licao1);
                Download();
                Exibe();
            } else {
                estudo = null;
                Dialogo();
            }
        } else if (id == R.id.licao2) {
            url = url2;
            licao = "licao2_3_2016.html";
            if (compara > 20160930) {
                estudo = getString(R.string.licao2);
                Download();
                Exibe();
            } else {
                estudo = null;
                Dialogo();
            }
        } else if (id == R.id.licao3) {
            url = url3;
            licao = "licao3_3_2016.html";
            if (compara > 20161007) {
                estudo = getString(R.string.licao3);
                Download();
                Exibe();
            } else {
                estudo = null;
                Dialogo();
            }
        } else if (id == R.id.licao4) {
            url = url4;
            licao = "licao4_3_2016.html";
            if (compara > 20161014) {
                estudo = getString(R.string.licao4);
                Download();
                Exibe();
            } else {
                estudo = null;
                Dialogo();
            }
        } else if (id == R.id.licao5) {
            url = url5;
            licao = "licao5_3_2016.html";
            if (compara > 20161021) {
                estudo = getString(R.string.licao5);
                Download();
                Exibe();
            } else {
                estudo = null;
                Dialogo();
            }
        } else if (id == R.id.licao6) {
            url = url6;
            licao = "licao6_3_2016.html";
            if (compara > 20161028) {
                estudo = getString(R.string.licao6);
                Download();
                Exibe();
            } else {
                estudo = null;
                Dialogo();
            }
        } else if (id == R.id.licao7) {
            url = url7;
            licao = "licao7_3_2016.html";
            if (compara > 20161104) {
                estudo = getString(R.string.licao7);
                Download();
                Exibe();
            } else {
                estudo = null;
                Dialogo();
            }
        } else if (id == R.id.licao8) {
            url = url8;
            licao = "licao8_3_2016.html";
            if (compara > 20161111) {
                estudo = getString(R.string.licao8);
                Download();
                Exibe();
            } else {
                estudo = null;
                Dialogo();
            }
        } else if (id == R.id.licao9) {
            url = url9;
            licao = "licao9_3_2016.html";
            if (compara > 20161118) {
                estudo = getString(R.string.licao9);
                Download();
                Exibe();
            } else {
                estudo = null;
                Dialogo();
            }
        } else if (id == R.id.licao10) {
            url = url10;
            licao = "licao10_3_2016.html";
            if (compara > 20161125) {
                estudo = getString(R.string.licao10);
                Download();
                Exibe();
            } else {
                estudo = null;
                Dialogo();
            }
        } else if (id == R.id.licao11) {
            url = url11;
            licao = "licao11_3_2016.html";
            if (compara > 20161202) {
                estudo = getString(R.string.licao11);
                Download();
                Exibe();
            } else {
                estudo = null;
                Dialogo();
            }
        } else if (id == R.id.licao12) {
            url = url12;
            licao = "licao12_3_2016.html";
            if (compara > 20161209) {
                estudo = getString(R.string.licao12);
                Download();
                Exibe();
            } else {
                estudo = null;
                Dialogo();
            }
        } else if (id == R.id.licao13) {
            url = url13;
            licao = "licao13_3_2016.html";
            if (compara > 20161216) {
                estudo = getString(R.string.licao13);
                Download();
                Exibe();
            } else {
                estudo = null;
                Dialogo();
            }
        } else if (id == R.id.licao14) {
            url = url14;
            licao = "licao14_3_2016.html";
            if (compara > 20161223) {
                estudo = getString(R.string.licao14);
                Download();
                Exibe();
            } else {
                estudo = null;
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
