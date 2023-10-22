package com.example.examenfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import DatosExamen.EstructuraDatos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrimerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrimerFragment extends Fragment {

    TextView preguntas;
    Button regresa, siguiente, califica;
    RadioButton r1, r2, r3;
    RadioGroup grupo;
    int contador = 0, aciertos = 0, puntos = 0, errores = 10;
    ArrayList<EstructuraDatos> listadatos = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PrimerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrimerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrimerFragment newInstance(String param1, String param2) {
        PrimerFragment fragment = new PrimerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_primer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preguntas = getView().findViewById(R.id.txtpreguntas);
        regresa = getView().findViewById(R.id.btnatras);
        siguiente = getView().findViewById(R.id.btnsiguiente);

        r1 = getView().findViewById(R.id.r1);
        r2 = getView().findViewById(R.id.r2);
        r3 = getView().findViewById(R.id.r3);
        grupo = getView().findViewById(R.id.grupo);

        preguntas();
        siguiente.setEnabled(false);
        califica.setEnabled(false);
        regresa.setEnabled(false);

        siguiente.setOnClickListener(v -> {
            String seleccion = seleccionr();
            if (seleccion != null){
                listadatos.get(contador).setSeleccion(seleccion);
            }
            contador++;
            if (contador < listadatos.size()){
                EstructuraDatos siguientep = listadatos.get(contador);
                preguntas.setText(siguientep.getPregunta());
                r1.setText(siguientep.getR1());
                r2.setText(siguientep.getR2());
                r3. setText(siguientep.getR3());

                grupo.clearCheck();
            }
        });
        regresa.setOnClickListener(v -> {
            contador--;

            if(contador >= 0 && contador < listadatos.size()){
                EstructuraDatos prev = listadatos.get(contador);
                preguntas.setText(prev.getPregunta());
                r1.setText(prev.getR1());
                r2.setText(prev.getR2());
                r3.setText(prev.getR3());

                grupo.clearCheck();
            }
        });

        grupo.setOnCheckedChangeListener((radioGroup, i) -> {
            if (r1.isChecked() || r2.isChecked() || r3.isChecked()){
                if (contador==9){
                    siguiente.setEnabled(false);
                    califica.setEnabled(true);
                }else{
                    siguiente.setEnabled(true);
                }

                regresa.setEnabled(contador != 0);
            }else{
                siguiente.setEnabled(false);
            }
        });
        califica.setOnClickListener(v -> {
            if (contador >= 0 && contador < listadatos.size()){
                EstructuraDatos actual = listadatos.get(contador);
                String seleccion = seleccionr();

                if (seleccion != null){
                    actual.setSeleccion(seleccion);
                }
            }
            puntos = 0;
            aciertos = 0;
            errores = 10;

            for (EstructuraDatos pregunta : listadatos){
                String respSeleccionada = pregunta.getSeleccion();

                if (respSeleccionada != null){
                    if (respSeleccionada.equals(pregunta.getRc())){
                        puntos++;
                        aciertos++;
                        errores--;
                    }
                }
            }
           // Intent lanza = new Intent(PrimerFragment.this, SegundoFragment.class);
            //lanza.putExtra("aciertos", aciertos);
            //lanza.putExtra("errores", errores);
            //lanza.putExtra("puntos", puntos);
            //startActivity(lanza);


        });



    }

    private void preguntas() {
        EstructuraDatos ed;

        ed = new EstructuraDatos();
        ed.setPregunta("1.-¿Quien descubrio america?");
        ed.setR1("a) Cristobal Colon");
        ed.setR2("b) Nose");
        ed.setR3("c) Benito Juarez");
        ed.setRc("a");
        listadatos.add(ed);

        ed = new EstructuraDatos();
        ed.setPregunta("2.-¿Cuántos huesos hay en el cuerpo humano?");
        ed.setR1("a) 206");
        ed.setR2("b) 900");
        ed.setR3("c) 100");
        ed.setRc("a");
        listadatos.add(ed);

        ed = new EstructuraDatos();
        ed.setPregunta("3.-¿Quién pintó “la última cena?");
        ed.setR1("a) Leonardo DaVinci");
        ed.setR2("b) El pepe");
        ed.setR3("c) Nose");
        ed.setRc("a");
        listadatos.add(ed);

        ed = new EstructuraDatos();
        ed.setPregunta("4.-¿Cuál es el lugar más frío de la tierra?");
        ed.setR1("a) La antartida");
        ed.setR2("b) El corazon de tu ex jajaja");
        ed.setR3("c) Noshe");
        ed.setRc("a");
        listadatos.add(ed);

        ed = new EstructuraDatos();
        ed.setPregunta("5.-¿Cuál es el río más largo del mundo?");
        ed.setR1("a) Nose");
        ed.setR2("b) El canal pa ");
        ed.setR3("c) El nilo");
        ed.setRc("c");
        listadatos.add(ed);

        ed = new EstructuraDatos();
        ed.setPregunta("6.-¿Cómo se denomina el resultado de la multiplicación?");
        ed.setR1("a) Quien sabe");
        ed.setR2("b) Residuo");
        ed.setR3("c) Producto");
        ed.setRc("b");
        listadatos.add(ed);

        ed = new EstructuraDatos();
        ed.setPregunta("7.-¿Cuál es la vida promedio de un humano?");
        ed.setR1("a) 50 años");
        ed.setR2("b) 75 años");
        ed.setR3("c) 40 años");
        ed.setRc("b");
        listadatos.add(ed);

        ed = new EstructuraDatos();
        ed.setPregunta("8.-¿Cuál es el océano más grande?");
        ed.setR1("a) El elba");
        ed.setR2("b) Acapulquito pa ");
        ed.setR3("c) Pacifico");
        ed.setRc("c");
        listadatos.add(ed);

        ed = new EstructuraDatos();
        ed.setPregunta("9.-¿Qué significa FIFA?");
        ed.setR1("a) Nose");
        ed.setR2("b) Fédération Internationale de Football Association");
        ed.setR3("c) Federacion internacional de borrachos jajaja");
        ed.setRc("b");
        listadatos.add(ed);

        ed = new EstructuraDatos();
        ed.setPregunta("10.-¿En qué se especializa la cartografía?");
        ed.setR1("a) Ciencia que estudia mapas");
        ed.setR2("b) Mapitas chidos");
        ed.setR3("c) Nose");
        ed.setRc("a");
        listadatos.add(ed);

    }
    private String seleccionr(){
        if (r1.isChecked()){
            return "a";
        } else if (r2.isChecked()) {
            return "b";
        } else if (r3.isChecked()) {
            return "c";
        }else {
            return null;
        }
    }
}