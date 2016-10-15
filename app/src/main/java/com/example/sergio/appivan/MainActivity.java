package com.example.sergio.appivan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.sergio.appivan.adapters.ExpandableListAdapter;
import com.roughike.swipeselector.OnSwipeItemSelectedListener;
import com.roughike.swipeselector.SwipeItem;
import com.roughike.swipeselector.SwipeSelector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int SIMPLE = 1;
    private static final int COMPUESTA = 2;
    private static final int INFINITIVO = 3;


    private RecyclerView recyclerview;
    private SwipeSelector swipeSelector;


    /**/
    String json = "[{\"Verbo\":\"abreuver\",\"Tiempo\":1,\"Sing_1\":\"abreuve\",\"Sing_2\":\"abreuves\",\"Sing_3\":\"abreuve\",\"Plural_1\":\"abreuvons\",\"Plural_2\":\"abreuvez\",\"Plural_3\":\"abreuvent\"},{\"Verbo\":\"abreuver\",\"Tiempo\":2,\"Sing_1\":\"abreuvais\",\"Sing_2\":\"abreuvais\",\"Sing_3\":\"abreuvait\",\"Plural_1\":\"abreuvions\",\"Plural_2\":\"abreuviez\",\"Plural_3\":\"abreuvaient\"},{\"Verbo\":\"abreuver\",\"Tiempo\":3,\"Sing_1\":\"abreuvai\",\"Sing_2\":\"abreuvas\",\"Sing_3\":\"abreuva\",\"Plural_1\":\"abreuvâmes\",\"Plural_2\":\"abreuvâtes\",\"Plural_3\":\"abreuvèrent\"},{\"Verbo\":\"abreuver\",\"Tiempo\":4,\"Sing_1\":\"abreuverai\",\"Sing_2\":\"abreuveras\",\"Sing_3\":\"abreuvera\",\"Plural_1\":\"abreuverons\",\"Plural_2\":\"abreuverez\",\"Plural_3\":\"abreuveront\"},{\"Verbo\":\"abreuver\",\"Tiempo\":5,\"Sing_1\":\"abreuve\",\"Sing_2\":\"abreuves\",\"Sing_3\":\"abreuve\",\"Plural_1\":\"abreuvions\",\"Plural_2\":\"abreuviez\",\"Plural_3\":\"abreuvent\"},{\"Verbo\":\"abreuver\",\"Tiempo\":6,\"Sing_1\":\"abreuvasse\",\"Sing_2\":\"abreuvasses\",\"Sing_3\":\"abreuvât\",\"Plural_1\":\"abreuvassions\",\"Plural_2\":\"abreuvassiez\",\"Plural_3\":\"abreuvassent\"},{\"Verbo\":\"abreuver\",\"Tiempo\":7,\"Sing_1\":\"abreuverais\",\"Sing_2\":\"abreuverais\",\"Sing_3\":\"abreuverait\",\"Plural_1\":\"abreuverions\",\"Plural_2\":\"abreuveriez\",\"Plural_3\":\"abreuveraient\"},{\"Verbo\":\"abreuver\",\"Tiempo\":8,\"Sing_1\":\"\",\"Sing_2\":\"abreuve\",\"Sing_3\":\"\",\"Plural_1\":\"abreuvons\",\"Plural_2\":\"abreuvez\",\"Plural_3\":\"\"},{\"Verbo\":\"abreuver\",\"Tiempo\":9,\"Sing_1\":\"\",\"Sing_2\":\"\",\"Sing_3\":\"\",\"Plural_1\":\"\",\"Plural_2\":\"\",\"Plural_3\":\"\",\"FormaSimple\":\"abreuver\"},{\"Verbo\":\"abreuver\",\"Tiempo\":10,\"Sing_1\":\"\",\"Sing_2\":\"\",\"Sing_3\":\"\",\"Plural_1\":\"\",\"Plural_2\":\"\",\"Plural_3\":\"\",\"FormaSimple\":\"abreuvant\"},{\"Verbo\":\"abreuver\",\"Tiempo\":11,\"Sing_1\":\"\",\"Sing_2\":\"\",\"Sing_3\":\"\",\"Plural_1\":\"\",\"Plural_2\":\"\",\"Plural_3\":\"\",\"FormaSimple\":\"abreuvé\"}]";
    private String[] tiempos_verbales_simples = {"Indicatif present", "Indicatif imparfait", "Indicatif passé simple"};
    private String[] tiempos_verbales_compuestos = {"compues present", "compues imparfait", "compues passé simple"};

    private String[] listaPersonas = {"Je", "Tu", "Il,Elle", "Nous", "Vous", "Ils/Elles"};
/**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = (RecyclerView) this.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

//      Inicializo el Selector
        initSwipeSelector();
//      Añado los escuchadores al selector
        addListener();
//        Imprimo los datos iniciales
        displayVerbosSimples();

    }

    /**
     * Metodo que inicializa el SwipeSelector con la cantidad de tiempos verbales deseada.
     */
    public void initSwipeSelector() {
        swipeSelector = (SwipeSelector) findViewById(R.id.swipeSelector);

        swipeSelector.setItems(
                // The first argument is the value for that item, and should in most cases be unique for the
                // current SwipeSelector, just as you would assign values to radio buttons.
                // You can use the value later on to check what the selected item was.
                // The value can be any Object, here we're using ints.
                new SwipeItem(SIMPLE, "Verbos Simples", ""),
                new SwipeItem(COMPUESTA, "Verbos Compuestos", ""),
                new SwipeItem(INFINITIVO, "Infinitivo", "")
        );
    }

    /**
     * This method add listeners to the components.
     */
    private void addListener() {
        swipeSelector.setOnItemSelectedListener(new OnSwipeItemSelectedListener() {
            @Override
            public void onItemSelected(SwipeItem item) {
//                Eliminar esta linea de codigo
                Toast.makeText(getApplicationContext(), item.value.toString(), Toast.LENGTH_SHORT).show();

                displayData(Integer.parseInt(item.value.toString()));
            }
        });
    }

    private void displayVerbosSimples() {
//        Hago la instancia de mi clase Adapter
        List<ExpandableListAdapter.Item> tiemposVerbales = new ArrayList<>();
        ExpandableListAdapter.Item tiempo;
        for (int i = 0; i < tiempos_verbales_simples.length; i++) {

            tiempo = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, tiempos_verbales_simples[i]);
            tiempo.invisibleChildren = new ArrayList<>();
            tiempo.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, listaPersonas[0] + " hola"));
            tiempo.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, listaPersonas[1]));
            tiempo.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, listaPersonas[2]));
            tiempo.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, listaPersonas[3]));
            tiempo.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, listaPersonas[4]));
            tiemposVerbales.add(tiempo);

        }
        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(tiemposVerbales);
        recyclerview.setAdapter(expandableListAdapter);

    }


    private void displayData(int value ) {
//        https://www.mkyong.com/java/json-simple-example-read-and-write-json/
//        Hago la instancia de mi clase Adapter
        List<ExpandableListAdapter.Item> tiemposVerbales = new ArrayList<>();
        ExpandableListAdapter.Item tiempo;

        switch (value) {
            case SIMPLE:
                displayVerbosSimples();
                break;
            case COMPUESTA:
                for (int i = 0; i < tiempos_verbales_simples.length; i++) {

                    tiempo = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, tiempos_verbales_compuestos[i]);
                    tiempo.invisibleChildren = new ArrayList<>();
                    tiempo.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, listaPersonas[0] + ""));
                    tiempo.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, listaPersonas[1]));
                    tiempo.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, listaPersonas[2]));
                    tiempo.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, listaPersonas[3]));
                    tiempo.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, listaPersonas[4]));

                    tiemposVerbales.add(tiempo);

                    break;
//            case "Infinivo":
//
//                break;
                }


                ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(tiemposVerbales);
                recyclerview.setAdapter(expandableListAdapter);

        }
    }
}