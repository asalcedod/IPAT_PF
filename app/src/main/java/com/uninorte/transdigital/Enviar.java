package com.uninorte.transdigital;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfWriter;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import harmony.java.awt.Color;

public class Enviar extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{
    private static final String TAG = "LogsAndroid";
    // Progress Dialog
    private ProgressDialog pDialog;
    String id_cl="",text="",nombrec="",codb="";
    ;
    boolean sw=false;
    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    //testing on Emulator:
    private static final String CAMPO1_URL = "https://transitodigital-asalcedod.c9users.io/accidente.php";
    private static final String CAR_LUG_URL = "https://transitodigital-asalcedod.c9users.io/car_lug.php";
    private static final String DATOS_PERSONALES_URL = "https://transitodigital-asalcedod.c9users.io/datospersonales.php";
    private static final String DETALLES_CONDUCTOR_URL = "https://transitodigital-asalcedod.c9users.io/detallesconductor.php";
    private static final String DATOS_VEHICULO_URL = "https://transitodigital-asalcedod.c9users.io/datosvehiculo.php";
    private static final String DETALLES_VEHICULO_URL = "https://transitodigital-asalcedod.c9users.io/detallesvehiculo.php";
    private static final String PROPIETARIO_URL = "https://transitodigital-asalcedod.c9users.io/propietario.php";
    private static final String VICTIMAS_URL = "https://transitodigital-asalcedod.c9users.io/victimas.php";
    private final static String NOMBRE_DIRECTORIO = "IPAT_Digital";
    private final static String GENERADOR = "MisArchivos";
    private final static String D_FOTO = "Evidencias";
    private final static String NOMBRE_DOCUMENTO = "Copia_IPAT.pdf";
    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar);
    }

    public void onClick_Informe(View view) {
        sw=true;
        Document documento = new Document(PageSize.LETTER);
        File f =  new File(Environment.getExternalStorageDirectory().toString() + File.separator + NOMBRE_DIRECTORIO);
        if(!f.exists()){
            f.mkdir();
        }
        File ficheroPdf = new File(f.getPath() + File.separator + GENERADOR);
        if(!ficheroPdf.exists()){
            ficheroPdf.mkdir();
        }

        nombrec=Environment.getExternalStorageDirectory().toString() + File.separator + NOMBRE_DIRECTORIO + File.separator + GENERADOR + File.separator + NOMBRE_DOCUMENTO;
        File outPutFile = new File(nombrec);
        if(outPutFile.exists()){
            outPutFile.delete();
        }
        String organismo = "",gravedad = "",direccion_a="",latitud = "",longitud="",clase_a="",choque_con="",objeto_fijo="",id_c_l="",fecha_a="",hora_a="",fecha_i="",hora_i="";
        List<DBAccidente> c = new Select().from(DBAccidente.class).queryList();
        for (DBAccidente ca : c) {
            organismo = ca.ot;
            gravedad = ca.gravedad;
            direccion_a = ca.ubicacion;
            latitud = ca.latitud;
            longitud = ca.longitud;
            clase_a = ca.accidente;
            choque_con = ca.choque;
            objeto_fijo = ca.objetof;
            id_c_l = ca.caracteristicasl;
            id_cl=id_c_l;
            fecha_a = ca.a_fecha;
            hora_a = ca.a_hora;
            fecha_i = ca.r_fecha;
            hora_i = ca.r_hora;
        }
        String[] cof=fecha_i.split("/");
        String[] coh=hora_i.split(":");
        for(int i=0;i<cof.length;i++){
            codb=codb+cof[i]+coh[i];
        }

        String id_carac_l= "", area= "", sector= "", zona= "", diseño= "", condicionesc= "";
        new Enviar.Addform1().execute(organismo,gravedad,direccion_a,latitud,longitud,clase_a,choque_con,objeto_fijo,id_c_l,fecha_a,hora_a,fecha_i,hora_i,codb);
        List<DBCaracteristicasl> cl = new Select().from(DBCaracteristicasl.class).queryList();
        for (DBCaracteristicasl ca : cl) {
            id_carac_l = id_cl;
            area = ca.area;
            sector = ca.sector;
            zona = ca.zona;
            diseño = ca.diseño;
            condicionesc = ca.condicionc;
        }
        new Caract_lugar().execute(id_carac_l, area, sector, zona, diseño, condicionesc);
        String nombre="",tdoc="",ndoc="",nacionalidad="",fecha_n="",sexo="",direc="",ciudad="",tel="";
        List<DBDatosP> dp = new Select().from(DBDatosP.class).queryList();
        for (DBDatosP ca : dp) {
            nombre=ca.nombre;
            tdoc=ca.tdoc;
            ndoc=ca.ndoc;
            nacionalidad=ca.nacionalidad;
            fecha_n=ca.fecha_n;
            sexo=ca.sexo;
            direc=ca.direc;
            ciudad=ca.ciudad;
            tel=ca.tel;
        }
        new DatosPersonales().execute(nombre,tdoc,ndoc,nacionalidad,fecha_n,sexo,direc,ciudad,tel,id_cl);
        String gravedad2="",examen="",aut="",ebriagez="",gradoE="",sustancias="",portalicencia="",idlicencia="",restriccion="",categoria="",estado="",fecha="",cod_of="",chaleco="",casco="",cinturon="",hospital="",descip="";
        List<DBDetallesCond> dec = new Select().from(DBDetallesCond.class).queryList();
        for (DBDetallesCond ca : dec) {
            //gravedad2,examen,aut,ebriagez,gradoE,sustancias,portalicencia,idlicencia,categoria,estado,fecha,cod_of,chaleco,casco,cinturon,hospital,descip
            gravedad2=ca.gravedad;
            examen=ca.examen;
            aut=ca.aut;
            ebriagez=ca.ebriagez;
            gradoE=ca.gradoE;
            sustancias=ca.sustancias;
            portalicencia=ca.portalicencia;
            idlicencia=ca.idlicencia;
            categoria=ca.categoria;
            restriccion=ca.restriccion;
            estado=ca.estado;
            fecha=ca.fecha;
            cod_of=ca.cod_of;
            chaleco=ca.chaleco;
            casco=ca.casco;
            cinturon=ca.cinturon;
            hospital=ca.hospital;
            descip=ca.descip;
        }
        new DetallesConductor().execute(gravedad2,examen,aut,ebriagez,gradoE,sustancias,portalicencia,idlicencia,categoria,restriccion,estado,fecha,cod_of,chaleco,casco,cinturon,hospital,descip,id_cl);
        String placa="",remorque="",nacionalidad2="",marca="",linea="",color="",modelo="",carroceria="",toneladas="",n_personas="",id_licencia="";
        List<DBDatosV> dv = new Select().from(DBDatosV.class).queryList();
        for (DBDatosV ca : dv) {
            //placa,remorque,nacionalidad,marca,linea,color,modelo,carroceria,toneladas,n_personas,id_licencia;
            placa=ca.placa;
            remorque=ca.remorque;
            nacionalidad2=ca.nacionalidad;
            marca=ca.marca;
            linea=ca.linea;
            color=ca.color;
            modelo=ca.modelo;
            carroceria=ca.carroceria;
            toneladas=ca.toneladas;
            n_personas=ca.n_personas;
            id_licencia=ca.id_licencia;
        }
        new DatosVehiculos().execute(placa,remorque,nacionalidad2,marca,linea,color,modelo,carroceria,toneladas,n_personas,id_licencia,id_cl);
        String empresa="",nit="",matriculado="",inmovilizado="",dispocicion="",t_registro="",rev_tecnica="",nrevic="",n_acompañantes="",SOAT="",aseguradora="",poliza="",fecha_v_soat="",porta_seguro="",id_seguro="",asignatura="",fecha_vsre="",porta_seguro2="",fecha_vsce="";
        //empresa,nit,matriculado,inmovilizado,dispocicion,t_registro,rev_tecnica,n_acompañantes,SOAT,aseguradora,poliza,fecha_v_soat,porta_seguro,id_seguro,asignatura,fecha_vsre,porta_seguro2,fecha_vsce;
        List<DBDetallesV> dev = new Select().from(DBDetallesV.class).queryList();
        for (DBDetallesV a : dev) {
            empresa=a.empresa;
            nit=a.nit;
            matriculado=a.matriculado;
            inmovilizado=a.inmovilizado;
            dispocicion=a.dispocicion;
            t_registro=a.t_registro;
            rev_tecnica=a.rev_tecnica;
            nrevic=a.nrevt;
            n_acompañantes=a.n_acompañantes;
            SOAT=a.SOAT;
            aseguradora=a.aseguradora;
            poliza=a.poliza;
            fecha_v_soat=a.fecha_v_soat;
            porta_seguro=a.porta_seguro;
            asignatura=a.asignatura;
            fecha_vsre=a.fecha_vsre;
            porta_seguro2=a.porta_seguro2;
            fecha_vsce=a.fecha_vsce;
        }
        new DetallesVehiculos().execute(empresa,nit,matriculado,inmovilizado,dispocicion,t_registro,rev_tecnica,nrevic,n_acompañantes,SOAT,aseguradora,poliza,fecha_v_soat,porta_seguro,id_seguro,asignatura,fecha_vsre,porta_seguro2,fecha_vsce,id_cl);
        String mismo_cond="",nombre2="",t_doc="",n_doc="",clasev="",clases="",modalidad_t="",radioa="",fallas="",descrip_daño="",lugar_impacto="";
        List<DBPropietario> pr = new Select().from(DBPropietario.class).queryList();
        for (DBPropietario a : pr) {
            //mismo_cond,nombre2,t_doc,n_doc,clasev,clases,modalidad_t,radioa,fallas,descrip_daño,lugar_impacto
            mismo_cond=a.mismo_cond;
            nombre2=a.nombre;
            t_doc=a.t_doc;
            n_doc=a.n_doc;
            clasev=a.clasev;
            clases=a.clases;
            modalidad_t=a.modalidad_t;
            radioa=a.radioa;
            fallas=a.fallas;
            descrip_daño=a.descrip_daño;
            lugar_impacto=a.lugar_impacto;
        }
        new Propietario().execute(mismo_cond,nombre2,t_doc,n_doc,clasev,clases,modalidad_t,radioa,fallas,descrip_daño,lugar_impacto,id_cl);
        String detalle_victima="",nombrev="",tdocv="",ndocv="",nacionalidadv="",fecha_nv="",x="",direcv="",ciudadv="",telv="",gravedadv="",exam="",autv="",ebriagezv="",gradoEv="",sustancia="",chalecov="",cascov="",cinturonv="",hospitalv="";
        List<DBVictima> v = new Select().from(DBVictima.class).queryList();
        for (DBVictima ca : v) {
            detalle_victima=ca.detalle_victima;
            nombrev=ca.nombre;
            tdocv=ca.tdoc;
            ndocv=ca.ndoc;
            nacionalidadv=ca.nacionalidad;
            fecha_nv=ca.fecha_n;
            x=ca.sexo;
            direcv=ca.direc;
            ciudadv=ca.ciudad;
            telv=ca.tel;
            gravedadv=ca.gravedad;
            exam=ca.examen;
            autv=ca.aut;
            ebriagezv=ca.ebriagez;
            gradoEv=ca.gradoE;
            sustancia=ca.sustancias;
            chalecov=ca.chaleco;
            cascov=ca.casco;
            cinturonv=ca.cinturon;
            hospitalv=ca.hospital;
            new Victimas().execute(detalle_victima,nombrev,tdocv,ndocv,nacionalidadv,fecha_nv,x,direcv,ciudadv,telv,gravedadv,exam,autv,ebriagezv,gradoEv,sustancia,cinturonv,cascov,chalecov,hospitalv,id_cl);
        }
        try {
            String foto = Environment.getExternalStorageDirectory() + File.separator + NOMBRE_DIRECTORIO + File.separator + D_FOTO;
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(nombrec));
            documento.open();
            documento.add(new Paragraph("Ref:"+codb));

            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.encabezado);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image imagen = Image.getInstance(stream.toByteArray());
            documento.add(imagen);
            Font marcaa = FontFactory.getFont(FontFactory.TIMES_ITALIC, 55, Font.BOLD,
                    Color.LIGHT_GRAY);
            ColumnText.showTextAligned(writer.getDirectContentUnder(),
                    Element.ALIGN_CENTER, new Paragraph(
                            "Secretaría de Tránsito y Movilidad", marcaa), 297.5f, 421,
                    writer.getPageNumber() % 2 == 1 ? 50 : -50);
            Font font = FontFactory.getFont(FontFactory.defaultEncoding,25,
                    Font.BOLD, Color.BLACK);
            documento.add(new Paragraph("                        COPIA HOJA IPAT", font));
            documento.add(new Paragraph("                        "));
            font = FontFactory.getFont(FontFactory.defaultEncoding,20,
                    Font.BOLD, Color.BLACK);
            documento.add(new Paragraph("Información del Conductor", font));
            documento.add(new Paragraph("                        "));
            documento.add(new Paragraph("Nombre: "+nombre));
            documento.add(new Paragraph("Tipo identificación: "+tdoc));
            documento.add(new Paragraph("N° identificacion: "+ndoc));
            documento.add(new Paragraph("Nacionalidad: "+nacionalidad));
            documento.add(new Paragraph("Fecha de Nacimiento: "+fecha_n));
            documento.add(new Paragraph("Sexo: "+sexo));
            documento.add(new Paragraph("Direccion Residencia: "+direc));
            documento.add(new Paragraph("Ciudad Residencia: "+ciudad));
            documento.add(new Paragraph("Telefono: "+tel));
            documento.add(new Paragraph("                        "));
            documento.add(new Paragraph("Información del accidente", font));
            documento.add(new Paragraph("                        "));
            documento.add(new Paragraph("Código Oficina Dane: "+organismo));
            documento.add(new Paragraph("Gravedad del Accidente: "+gravedad));
            documento.add(new Paragraph("Dirección del accidente: "+direccion_a));
            documento.add(new Paragraph("Clase de Accidente: "+clase_a));
            documento.add(new Paragraph("Choque con: "+choque_con));
            documento.add(new Paragraph("Objeto fijo: "+objeto_fijo));
            documento.add(new Paragraph("Fecha del accidente: "+fecha_a));
            documento.add(new Paragraph("Hora del accidente: "+hora_a));
            documento.add(new Paragraph("Fecha del informe: "+fecha_i));
            documento.add(new Paragraph("Hora del informe: "+hora_i));
            //Siguiente pag
            documento.newPage();
            bitmap = BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.encabezado);
            stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            imagen = Image.getInstance(stream.toByteArray());
            documento.add(imagen);
            marcaa = FontFactory.getFont(FontFactory.TIMES_ITALIC, 55, Font.BOLD,
                    Color.LIGHT_GRAY);
            ColumnText.showTextAligned(writer.getDirectContentUnder(),
                    Element.ALIGN_CENTER, new Paragraph(
                            "Secretaría de Tránsito y Movilidad", marcaa), 297.5f, 421,
                    writer.getPageNumber() % 2 == 1 ? 50 : -50);
            font = FontFactory.getFont(FontFactory.defaultEncoding,20,
                    Font.BOLD, Color.BLACK);
            documento.add(new Paragraph("Detalles del Vehículo", font));
            documento.add(new Paragraph("                        "));
            documento.add(new Paragraph("Empresa(Vehículos de transporte de servicio publico): "+empresa));
            documento.add(new Paragraph("NIT: "+nit));
            documento.add(new Paragraph("Matriculado en: "+matriculado));
            documento.add(new Paragraph("Inmovilizado en: "+inmovilizado));
            documento.add(new Paragraph("A Disposición de: "+dispocicion));
            documento.add(new Paragraph("N° Tarjeta de Registro: "+t_registro));
            documento.add(new Paragraph("Rev. Tecnico mecanica y de gases: "+rev_tecnica));
            documento.add(new Paragraph("N° de la Revision: "+nrevic));
            documento.add(new Paragraph("Número de acompañantes: "+n_acompañantes));
            documento.add(new Paragraph("Porta SOAT"+SOAT));
            documento.add(new Paragraph("Aseguradora: "+aseguradora));
            documento.add(new Paragraph("N° Poliza: "+poliza));
            documento.add(new Paragraph("Vencimiento del SOAT: "+fecha_v_soat));
            documento.add(new Paragraph("Porta Seguro de Seguridad Civil Contractual: "+porta_seguro));
            documento.add(new Paragraph("Aseguradora: "+asignatura));
            documento.add(new Paragraph("Fecha de vencimiento: "+fecha_vsre));
            documento.add(new Paragraph("Porta Seguro de Seguridad Civil Extractual: "+porta_seguro2));
            documento.add(new Paragraph("Fecha de vencimiento: "+fecha_vsce));
            //EVIDENCIAS
            documento.newPage();
            Bitmap bmp = BitmapFactory.decodeFile(foto+File.separator+"foto"+1+".jpg");
            int i=1;
            while(bmp!=null && i<10) {
                int width = bmp.getWidth();
                int height = bmp.getHeight();
                float scaleWidth = ((float) 300) / width;
                float scaleHeight = ((float) 600) / height;
                Matrix matrix = new Matrix();
                matrix.postScale(scaleWidth, scaleHeight);
                bmp = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, false);
                ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 50, stream2);
                Image imagen2 = Image.getInstance(stream2.toByteArray());
                documento.newPage();
                ColumnText.showTextAligned(writer.getDirectContentUnder(),
                        Element.ALIGN_CENTER, new Paragraph(
                                "Secretaría de Tránsito y Movilidad", marcaa), 297.5f, 421,
                        writer.getPageNumber() % 2 == 1 ? 50 : -50);
                documento.add(imagen2);
                i++;
                bmp = BitmapFactory.decodeFile(foto+File.separator+"foto"+i+".jpg");
            }
            documento.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Desea enviar copia del Informe?")
                .setTitle("Advertencia")
                .setCancelable(false)
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                copiacorreo();
                                Intent i=new Intent();
                                setResult(Activity.RESULT_OK,i);
                                finish();
                                finish();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void copiacorreo(){
        Uri uri = Uri.fromFile(new File(nombrec));
        Intent itSend = new Intent(android.content.Intent.ACTION_SEND);
        itSend.setType("application/pdf");
        itSend.putExtra(android.content.Intent.EXTRA_EMAIL, "antony9409@gmail.com");
        itSend.putExtra(android.content.Intent.EXTRA_SUBJECT, "Copia IPAT_"+codb);
        itSend.putExtra(android.content.Intent.EXTRA_TEXT, text);
        itSend.putExtra(Intent.EXTRA_STREAM, uri);
        startActivityForResult(itSend, 1);
    }

    public void copiaInforme(View view) {
        if(sw==true) {
            copiacorreo();
        }else{
            Toast.makeText(this, "No has confirmado el envío.", Toast.LENGTH_SHORT).show();
        }
    }

    class Addform1 extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog(Enviar.this);
            pDialog.setMessage("Saving...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String organismo = args[0];
            String gravedad = args[1];
            String direccion_a = args[2];
            String latitud = args[3];
            String longitud = args[4];
            String clase_a = args[5];
            String choque_con = args[6];
            String objeto_fijo = args[7];
            String id_c_l = args[8];
            String fecha_a = args[9];
            String hora_a = args[10];
            String fecha_i = args[11];
            String hora_i = args[12];
            String id_formulario = args[13];

            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("id_formulario",id_formulario));
                params.add(new BasicNameValuePair("organismo", organismo));
                params.add(new BasicNameValuePair("gravedad", gravedad));
                params.add(new BasicNameValuePair("direccion_a", direccion_a));
                params.add(new BasicNameValuePair("latitud", latitud));
                params.add(new BasicNameValuePair("longitud", longitud));
                params.add(new BasicNameValuePair("clase_a", clase_a));
                params.add(new BasicNameValuePair("choque_con", choque_con));
                params.add(new BasicNameValuePair("objeto_fijo", objeto_fijo));
                params.add(new BasicNameValuePair("id_caracteristicas_l", id_c_l));
                params.add(new BasicNameValuePair("fecha_a", fecha_a));
                params.add(new BasicNameValuePair("hora_a", hora_a));
                params.add(new BasicNameValuePair("fecha_i", fecha_i));
                params.add(new BasicNameValuePair("hora_i", hora_i));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        CAMPO1_URL, "POST", params);

                if(json != null) {
                    // check your log for json response
                    Log.d("Registering attempt", json.toString());

                    // json success tag
                    success = json.getInt(TAG_SUCCESS);
                }else{
                    return "Falla en el servidor";
                }
                if (success == 1) {
                    Log.d("Formulario enviado!", json.toString());
                    List<DBAccidente> a = new Delete().from(DBAccidente.class).queryList();
                    //finish();
                    //startActivity(it);
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            //pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(Enviar.this, file_url, Toast.LENGTH_SHORT).show();
            }
        }
    }

    class Caract_lugar extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog(Enviar.this);
            pDialog.setMessage("Saving...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String id_carac_l = args[0];
            String area = args[1];
            String sector = args[2];
            String zona = args[3];
            String diseño = args[4];
            String condicionesc = args[5];


            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("anexo1", id_carac_l));
                params.add(new BasicNameValuePair("area", area));
                params.add(new BasicNameValuePair("sector", sector));
                params.add(new BasicNameValuePair("zona", zona));
                params.add(new BasicNameValuePair("diseno", diseño));
                params.add(new BasicNameValuePair("condicionesc", condicionesc));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        CAR_LUG_URL, "POST", params);

                if(json != null) {
                    // check your log for json response
                    Log.d("Registering attempt", json.toString());

                    // json success tag
                    success = json.getInt(TAG_SUCCESS);
                }else{
                    return "Falla en el servidor";
                }
                if (success == 1) {
                    Log.d("Formulario enviado!", json.toString());
                    List<DBCaracteristicasl> b = new Delete().from(DBCaracteristicasl.class).queryList();
                    //startActivity(it);
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            //pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(Enviar.this, file_url, Toast.LENGTH_SHORT).show();
            }
        }
    }

    class DatosPersonales extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog(Enviar.this);
            pDialog.setMessage("Saving...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String nombre=args[0];
            String tdoc=args[1];
            String ndoc=args[2];
            String nacionalidad=args[3];
            String fecha_n=args[4];
            String sexo=args[5];
            String direc=args[6];
            String ciudad=args[7];
            String tel=args[8];
            String anexo1=args[9];


            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("nombre", nombre));
                params.add(new BasicNameValuePair("tipo_doc", tdoc));
                params.add(new BasicNameValuePair("doc_id", ndoc));
                params.add(new BasicNameValuePair("nacionalidad", nacionalidad));
                params.add(new BasicNameValuePair("fecha_n", fecha_n));
                params.add(new BasicNameValuePair("sexo", sexo));
                params.add(new BasicNameValuePair("domicilio", direc));
                params.add(new BasicNameValuePair("ciudad", ciudad));
                params.add(new BasicNameValuePair("telefono", tel));
                params.add(new BasicNameValuePair("id_Anexo_1", anexo1));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        DATOS_PERSONALES_URL, "POST", params);

                if(json != null) {
                    // check your log for json response
                    Log.d("Registering attempt", json.toString());

                    // json success tag
                    success = json.getInt(TAG_SUCCESS);
                }else{
                    return "Falla en el servidor";
                }
                if (success == 1) {
                    Log.d("Formulario enviado!", json.toString());
                    List<DBDatosP> cc = new Delete().from(DBDatosP.class).queryList();
                    //startActivity(it);
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            //pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(Enviar.this, file_url, Toast.LENGTH_SHORT).show();
            }
        }
    }

    class DetallesConductor extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog(Enviar.this);
            pDialog.setMessage("Saving...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            //cod_of,chaleco,casco,cinturon,hospital,descip
            String gravedad=args[0];
            String examen=args[1];
            String aut=args[2];
            String ebriagez=args[3];
            String gradoE=args[4];
            String sustancias=args[5];
            String portalicencia=args[6];
            String idlicencia=args[7];
            String categoria=args[8];
            String restriccion=args[9];
            String estado=args[10];
            String fecha=args[11];
            String cod_of=args[12];
            String chaleco=args[13];
            String casco=args[14];
            String cinturon=args[15];
            String hospital=args[16];
            String descip=args[17];
            String anexo1=args[18];


            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("gravedad", gravedad));
                params.add(new BasicNameValuePair("examen", examen));
                params.add(new BasicNameValuePair("aut", aut));
                params.add(new BasicNameValuePair("embriagez", ebriagez));
                params.add(new BasicNameValuePair("grade_emb", gradoE));
                params.add(new BasicNameValuePair("sustancias_psico", sustancias));
                params.add(new BasicNameValuePair("porta_licencia", portalicencia));
                params.add(new BasicNameValuePair("id_licencia", idlicencia));
                params.add(new BasicNameValuePair("categoria", categoria));
                params.add(new BasicNameValuePair("restriccion", restriccion));
                params.add(new BasicNameValuePair("estado_licencia", estado));
                params.add(new BasicNameValuePair("fecha_lic", fecha));
                params.add(new BasicNameValuePair("oficina_transito", cod_of));
                params.add(new BasicNameValuePair("chaleco", chaleco));
                params.add(new BasicNameValuePair("casco", casco));
                params.add(new BasicNameValuePair("cinturon", cinturon));
                params.add(new BasicNameValuePair("centro_hospital", hospital));
                params.add(new BasicNameValuePair("descripcion", descip));
                params.add(new BasicNameValuePair("id_anexo1", anexo1));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        DETALLES_CONDUCTOR_URL, "POST", params);

                if(json != null) {
                    // check your log for json response
                    Log.d("Registering attempt", json.toString());

                    // json success tag
                    success = json.getInt(TAG_SUCCESS);
                }else{
                    return "Falla en el servidor";
                }
                if (success == 1) {
                    Log.d("Formulario enviado!", json.toString());
                    List<DBDetallesCond> d = new Delete().from(DBDetallesCond.class).queryList();
                    //startActivity(it);
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            //pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(Enviar.this, file_url, Toast.LENGTH_SHORT).show();
            }
        }
    }

    class DatosVehiculos extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog(Enviar.this);
            pDialog.setMessage("Saving...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            //placa,remorque,nacionalidad,marca,linea,color,modelo,carroceria,toneladas,n_personas,id_licencia;
            String placa=args[0];
            String remorque=args[1];
            String nacionalidad=args[2];
            String marca=args[3];
            String linea=args[4];
            String color=args[5];
            String modelo=args[6];
            String carroceria=args[7];
            String toneladas=args[8];
            String n_personas=args[9];
            String id_licencia=args[10];
            String id_anexo1=args[11];

            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("placa", placa));
                params.add(new BasicNameValuePair("placa_remolque", remorque));
                params.add(new BasicNameValuePair("nacionalidad", nacionalidad));
                params.add(new BasicNameValuePair("marca", marca));
                params.add(new BasicNameValuePair("linea", linea));
                params.add(new BasicNameValuePair("color", color));
                params.add(new BasicNameValuePair("modelo", modelo));
                params.add(new BasicNameValuePair("carroceria", carroceria));
                params.add(new BasicNameValuePair("toneladas", toneladas));
                params.add(new BasicNameValuePair("n_pasajeros", n_personas));
                params.add(new BasicNameValuePair("licencia_transp", id_licencia));
                params.add(new BasicNameValuePair("id_anexo1", id_anexo1));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        DATOS_VEHICULO_URL, "POST", params);

                if(json != null) {
                    // check your log for json response
                    Log.d("Registering attempt", json.toString());

                    // json success tag
                    success = json.getInt(TAG_SUCCESS);
                }else{
                    return "Falla en el servidor";
                }
                if (success == 1) {
                    Log.d("Formulario enviado!", json.toString());
                    List<DBDatosV> e = new Delete().from(DBDatosV.class).queryList();
                    //startActivity(it);
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            //pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(Enviar.this, file_url, Toast.LENGTH_SHORT).show();
            }
        }
    }

    class DetallesVehiculos extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog(Enviar.this);
            pDialog.setMessage("Saving...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            //cod_of,chaleco,casco,cinturon,hospital,descip
            String empresa=args[0];
            String nit=args[1];
            String matriculado=args[2];
            String inmovilizado=args[3];
            String disposicion=args[4];
            String tarjeta_registro=args[5];
            String rev_tec=args[6];
            String n_rev=args[7];
            String n_acom=args[8];
            String porta_soat=args[9];
            String aseguradora=args[10];
            String poliza=args[11];
            String fecha_venci_soat=args[12];
            String porta_seg_contra=args[13];
            String n_seg=args[14];
            String name_aseg=args[15];
            String fecha_seg_contra=args[16];
            String porta_seg_extra=args[17];
            String fecha_seg_extra=args[18];
            String anexo1=args[19];


            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("empresa", empresa));
                params.add(new BasicNameValuePair("nit", nit));
                params.add(new BasicNameValuePair("matriculado", matriculado));
                params.add(new BasicNameValuePair("inmovilizado", inmovilizado));
                params.add(new BasicNameValuePair("disposicion", disposicion));
                params.add(new BasicNameValuePair("tarjeta_registro", tarjeta_registro));
                params.add(new BasicNameValuePair("rev_tec", rev_tec));
                params.add(new BasicNameValuePair("n_rev", n_rev));
                params.add(new BasicNameValuePair("n_acom", n_acom));
                params.add(new BasicNameValuePair("porta_soat", porta_soat));
                params.add(new BasicNameValuePair("aseguradora", aseguradora));
                params.add(new BasicNameValuePair("poliza", poliza));
                params.add(new BasicNameValuePair("fecha_venci_soat", fecha_venci_soat));
                params.add(new BasicNameValuePair("porta_seg_contra", porta_seg_contra));
                params.add(new BasicNameValuePair("n_seg", n_seg));
                params.add(new BasicNameValuePair("name_aseg", name_aseg));
                params.add(new BasicNameValuePair("fecha_seg_contra", fecha_seg_contra));
                params.add(new BasicNameValuePair("porta_seg_extra", porta_seg_extra));
                params.add(new BasicNameValuePair("fecha_seg_extra", fecha_seg_extra));
                params.add(new BasicNameValuePair("id_anexo1", anexo1));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        DETALLES_VEHICULO_URL, "POST", params);

                if(json != null) {
                    // check your log for json response
                    Log.d("Registering attempt", json.toString());

                    // json success tag
                    success = json.getInt(TAG_SUCCESS);
                }else{
                    return "Falla en el servidor";
                }
                if (success == 1) {
                    Log.d("Formulario enviado!", json.toString());
                    List<DBDetallesV> fe = new Delete().from(DBDetallesV.class).queryList();
                    //startActivity(it);
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            //pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(Enviar.this, file_url, Toast.LENGTH_SHORT).show();
            }
        }
    }

    class Propietario extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog(Enviar.this);
            pDialog.setMessage("Saving...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String mismo_cond=args[0];
            String nombre_cond=args[1];
            String typo_doc=args[2];
            String id_doc=args[3];
            String clase_v=args[4];
            String clase_s=args[5];
            String modalidad_transp=args[6];
            String radio_accion=args[7];
            String fallas_en=args[8];
            String descripcion_daños=args[9];
            String lugar_impacto=args[10];
            String anexo1=args[11];


            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("mismo_cond", mismo_cond));
                params.add(new BasicNameValuePair("nombre_cond", nombre_cond));
                params.add(new BasicNameValuePair("typo_doc", typo_doc));
                params.add(new BasicNameValuePair("id_doc", id_doc));
                params.add(new BasicNameValuePair("clase_v", clase_v));
                params.add(new BasicNameValuePair("clase_s", clase_s));
                params.add(new BasicNameValuePair("modalidad_transp", modalidad_transp));
                params.add(new BasicNameValuePair("radio_accion", radio_accion));
                params.add(new BasicNameValuePair("fallas_en", fallas_en));
                params.add(new BasicNameValuePair("descripcion_danos", descripcion_daños));
                params.add(new BasicNameValuePair("lugar_impacto", lugar_impacto));
                params.add(new BasicNameValuePair("id_anexo1", anexo1));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        PROPIETARIO_URL, "POST", params);

                if(json != null) {
                    // check your log for json response
                    Log.d("Registering attempt", json.toString());

                    // json success tag
                    success = json.getInt(TAG_SUCCESS);
                }else{
                    return "Falla en el servidor";
                }
                if (success == 1) {
                    Log.d("Formulario enviado!", json.toString());
                    List<DBPropietario> g = new Delete().from(DBPropietario.class).queryList();
                    //startActivity(it);
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            //pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(Enviar.this, file_url, Toast.LENGTH_SHORT).show();
            }
        }
    }

    class Victimas extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Enviar.this);
            pDialog.setMessage("Saving...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String detalles=args[0];
            String nombre=args[1];
            String tdoc=args[2];
            String ndoc=args[3];
            String nacionalidad=args[4];
            String fecha_n=args[5];
            String sexo=args[6];
            String direc=args[7];
            String ciudad=args[8];
            String tel=args[9];
            String gravedad=args[10];
            String examen=args[11];
            String aut=args[12];
            String ebriagez=args[13];
            String gradoE=args[14];
            String sustancias=args[15];
            String cinturon=args[16];
            String casco=args[17];
            String chaleco=args[18];
            String hospital=args[19];
            String anexo1=args[20];


            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("detalles_victima", detalles));
                params.add(new BasicNameValuePair("nombre", nombre));
                params.add(new BasicNameValuePair("tipo_doc", tdoc));
                params.add(new BasicNameValuePair("doc_id", ndoc));
                params.add(new BasicNameValuePair("nacionalidad", nacionalidad));
                params.add(new BasicNameValuePair("fecha_n", fecha_n));
                params.add(new BasicNameValuePair("sexo", sexo));
                params.add(new BasicNameValuePair("domicilio", direc));
                params.add(new BasicNameValuePair("ciudad", ciudad));
                params.add(new BasicNameValuePair("telefono", tel));
                params.add(new BasicNameValuePair("centro_medico", hospital));
                params.add(new BasicNameValuePair("examen", examen));
                params.add(new BasicNameValuePair("autorizo", aut));
                params.add(new BasicNameValuePair("embriaguez", ebriagez));
                params.add(new BasicNameValuePair("grado_emb", gradoE));
                params.add(new BasicNameValuePair("sustancias_psico", sustancias));
                params.add(new BasicNameValuePair("cinturon", cinturon));
                params.add(new BasicNameValuePair("casco", casco));
                params.add(new BasicNameValuePair("chaleco", chaleco));
                params.add(new BasicNameValuePair("gravedad", gravedad));
                params.add(new BasicNameValuePair("id_Anexo_1", anexo1));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        VICTIMAS_URL, "POST", params);

                if(json != null) {
                    // check your log for json response
                    Log.d("Registering attempt", json.toString());

                    // json success tag
                    success = json.getInt(TAG_SUCCESS);
                }else{
                    return "Falla en el servidor";
                }
                if (success == 1) {
                    Log.d("Formulario enviado!", json.toString());
                    List<DBVictima> vic = new Delete().from(DBVictima.class).queryList();
                    //startActivity(it);
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(Enviar.this, file_url, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
