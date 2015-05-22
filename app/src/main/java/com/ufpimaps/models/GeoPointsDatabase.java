package com.ufpimaps.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.ufpimaps.controllers.GeoPointsContract.GeoPointsEntry.COLUMN_NODE_DESCRIPTION;
import static com.ufpimaps.controllers.GeoPointsContract.GeoPointsEntry.COLUMN_NODE_ID;
import static com.ufpimaps.controllers.GeoPointsContract.GeoPointsEntry.COLUMN_NODE_LATITUDE;
import static com.ufpimaps.controllers.GeoPointsContract.GeoPointsEntry.COLUMN_NODE_LONGITUDE;
import static com.ufpimaps.controllers.GeoPointsContract.GeoPointsEntry.TABLE_NODE;
import static com.ufpimaps.controllers.GeoPointsContract.GeoPointsEntry._ID;

/**
 * Created by HugoPiauilino on 21/05/15.
 * Classe que armazena os principais geopoints da ufpi para agilizar a busca no aplicativo
 */
public class GeoPointsDatabase extends SQLiteOpenHelper {

    /**
     * Se o Schema do Banco de Dados for modificado, incrementar DATABASE_VERSION
     */

    private static final int DATABASE_VERSION = 1;

    /**
     * Nome do banco de dados que armazena os GeoPoints
     */

    private static final String DATABASE_NAME = "GeoPointsDatabase.db";

    /**
     *
     */

    private static final String TEXT_TYPE = " TEXT";
    private static final String DOUBLE_TYPE = " REAL";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    /**
     *
     */

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NODE + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NODE_ID + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_NODE_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NODE_LATITUDE + DOUBLE_TYPE + COMMA_SEP +
                    COLUMN_NODE_LONGITUDE + DOUBLE_TYPE +
                    " )";

    /**
     *
     */
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NODE;


    /**
     * @param context
     */
    public GeoPointsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(SQL_CREATE_ENTRIES);
    }


    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * @param no
     */
    public void addNode(Node no) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NODE_ID, no.getIdNode());
        values.put(COLUMN_NODE_DESCRIPTION, no.getDescription());
        values.put(COLUMN_NODE_LATITUDE, no.getLocalization().getLatitude());
        values.put(COLUMN_NODE_LONGITUDE, no.getLocalization().getLongitude());

        db.insert(TABLE_NODE, null, values);
        db.close();
    }

    public List<Node> getAllNodes() {
        List<Node> nodeList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NODE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Node node = new Node();
                node.setIdNode(Integer.parseInt(cursor.getString(0)));
                node.setDescription(cursor.getString(2));
                nodeList.add(node);

            } while (cursor.moveToNext());
        }

        // return node list
        return nodeList;
    }

    public void populateDatabase() {
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NODE_ID, 1);
        values.put(COLUMN_NODE_DESCRIPTION, "Manutenção - NTI");
        values.put(COLUMN_NODE_LATITUDE, -5.055527);
        values.put(COLUMN_NODE_LONGITUDE, -42.788745);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 2);
        values.put(COLUMN_NODE_DESCRIPTION, "Redes - NTI");
        values.put(COLUMN_NODE_LATITUDE, -5.055527);
        values.put(COLUMN_NODE_LONGITUDE, -42.788745);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 3);
        values.put(COLUMN_NODE_DESCRIPTION, "Coordenação - NTI");
        values.put(COLUMN_NODE_LATITUDE, -5.055927);
        values.put(COLUMN_NODE_LONGITUDE, -42.788682);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 4);
        values.put(COLUMN_NODE_DESCRIPTION, "Leg 1 - DC");
        values.put(COLUMN_NODE_LATITUDE, -5.056282);
        values.put(COLUMN_NODE_LONGITUDE, -42.78844);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 5);
        values.put(COLUMN_NODE_DESCRIPTION, "Leg2 - DC");
        values.put(COLUMN_NODE_LATITUDE, -5.05642);
        values.put(COLUMN_NODE_LONGITUDE, -42.788332);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 6);
        values.put(COLUMN_NODE_DESCRIPTION, "Banheiros da Computação - DC ");
        values.put(COLUMN_NODE_LATITUDE, -5.056622);
        values.put(COLUMN_NODE_LONGITUDE, -42.788533);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 54);
        values.put(COLUMN_NODE_DESCRIPTION, "BALAO_ENTRADA1_NORTE");
        values.put(COLUMN_NODE_LATITUDE, -5.055527);
        values.put(COLUMN_NODE_LONGITUDE, -42.788745);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 55);
        values.put(COLUMN_NODE_DESCRIPTION, "ENTRADA_RU1");
        values.put(COLUMN_NODE_LATITUDE, -5.055927);
        values.put(COLUMN_NODE_LONGITUDE, -42.788682);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 56);
        values.put(COLUMN_NODE_DESCRIPTION, "ENTRADA_RI1ADM_PRAEC");
        values.put(COLUMN_NODE_LATITUDE, -5.056282);
        values.put(COLUMN_NODE_LONGITUDE, -42.78844);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 57);
        values.put(COLUMN_NODE_DESCRIPTION, "CRUZ_RU_DCE");
        values.put(COLUMN_NODE_LATITUDE, -5.05642);
        values.put(COLUMN_NODE_LONGITUDE, -42.788332);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 58);
        values.put(COLUMN_NODE_DESCRIPTION, "RUA_DCE");
        values.put(COLUMN_NODE_LATITUDE, -5.056622);
        values.put(COLUMN_NODE_LONGITUDE, -42.788533);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 59);
        values.put(COLUMN_NODE_DESCRIPTION, "DCE");
        values.put(COLUMN_NODE_LATITUDE, -5.056862);
        values.put(COLUMN_NODE_LONGITUDE, -42.788797);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 60);
        values.put(COLUMN_NODE_DESCRIPTION, "CRUZ_DCE_ANEXOODONTO");
        values.put(COLUMN_NODE_LATITUDE, -5.056984);
        values.put(COLUMN_NODE_LONGITUDE, -42.788932);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 61);
        values.put(COLUMN_NODE_DESCRIPTION, "ANEXO_ODONTO_MATEMATICA");
        values.put(COLUMN_NODE_LATITUDE, -5.057008);
        values.put(COLUMN_NODE_LONGITUDE, -42.789308);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 62);
        values.put(COLUMN_NODE_DESCRIPTION, "ODONTO");
        values.put(COLUMN_NODE_LATITUDE, -5.055954);
        values.put(COLUMN_NODE_LONGITUDE, -42.789227);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 63);
        values.put(COLUMN_NODE_DESCRIPTION, "COMPUTACAO_ESPINTEGR2");
        values.put(COLUMN_NODE_LATITUDE, -5.056317);
        values.put(COLUMN_NODE_LONGITUDE, -42.78963);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 64);
        values.put(COLUMN_NODE_DESCRIPTION, "ESPINTEGR2_1");
        values.put(COLUMN_NODE_LATITUDE, -5.056315);
        values.put(COLUMN_NODE_LONGITUDE, -42.789377);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 65);
        values.put(COLUMN_NODE_DESCRIPTION, "ESPINTEGR2_2");
        values.put(COLUMN_NODE_LATITUDE, -5.056312);
        values.put(COLUMN_NODE_LONGITUDE, -42.789107);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 66);
        values.put(COLUMN_NODE_DESCRIPTION, "ESPINTEGR2");
        values.put(COLUMN_NODE_LATITUDE, -5.056499);
        values.put(COLUMN_NODE_LONGITUDE, -42.789103);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 67);
        values.put(COLUMN_NODE_DESCRIPTION, "PRACA_ALIM_COMP");
        values.put(COLUMN_NODE_LATITUDE, -5.056583);
        values.put(COLUMN_NODE_LONGITUDE, -42.789922);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 68);
        values.put(COLUMN_NODE_DESCRIPTION, "NTI_ANEXOODONTO");
        values.put(COLUMN_NODE_LATITUDE, -5.05673);
        values.put(COLUMN_NODE_LONGITUDE, -42.790087);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 69);
        values.put(COLUMN_NODE_DESCRIPTION, "PREFEITURA");
        values.put(COLUMN_NODE_LATITUDE, -5.056943);
        values.put(COLUMN_NODE_LONGITUDE, -42.790324);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 70);
        values.put(COLUMN_NODE_DESCRIPTION, "PREFEITURA_MATEMATICA");
        values.put(COLUMN_NODE_LATITUDE, -5.057094);
        values.put(COLUMN_NODE_LONGITUDE, -42.790478);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 71);
        values.put(COLUMN_NODE_DESCRIPTION, "DAA_QUIMICA");
        values.put(COLUMN_NODE_LATITUDE, -5.057508);
        values.put(COLUMN_NODE_LONGITUDE, -42.790921);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 72);
        values.put(COLUMN_NODE_DESCRIPTION, "REITORIA_PRACAREITORIA_FISICA");
        values.put(COLUMN_NODE_LATITUDE, -5.057885);
        values.put(COLUMN_NODE_LONGITUDE, -42.791439);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 73);
        values.put(COLUMN_NODE_DESCRIPTION, "BIOLOGIA");
        values.put(COLUMN_NODE_LATITUDE, -5.058342);
        values.put(COLUMN_NODE_LONGITUDE, -42.791836);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 74);
        values.put(COLUMN_NODE_DESCRIPTION, "BALAO_CCN_LESTE");
        values.put(COLUMN_NODE_LATITUDE, -5.05851);
        values.put(COLUMN_NODE_LONGITUDE, -42.792282);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 75);
        values.put(COLUMN_NODE_DESCRIPTION, "BALAO_CCN_OESTE");
        values.put(COLUMN_NODE_LATITUDE, -5.058504);
        values.put(COLUMN_NODE_LONGITUDE, -42.792278);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 76);
        values.put(COLUMN_NODE_DESCRIPTION, "BALAO_CCN_SUL");
        values.put(COLUMN_NODE_LATITUDE, -5.058649);
        values.put(COLUMN_NODE_LONGITUDE, -42.792288);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 77);
        values.put(COLUMN_NODE_DESCRIPTION, "ACESSO_CCN_1");
        values.put(COLUMN_NODE_LATITUDE, -5.058362);
        values.put(COLUMN_NODE_LONGITUDE, -42.792644);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 78);
        values.put(COLUMN_NODE_DESCRIPTION, "CRUZ_ACESSOCCN_ADM_EMAS");
        values.put(COLUMN_NODE_LATITUDE, -5.058158);
        values.put(COLUMN_NODE_LONGITUDE, -42.793026);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 79);
        values.put(COLUMN_NODE_DESCRIPTION, "TRIDENTE_CCN_EMAS");
        values.put(COLUMN_NODE_LATITUDE, -5.058158);
        values.put(COLUMN_NODE_LONGITUDE, -42.793026);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 80);
        values.put(COLUMN_NODE_DESCRIPTION, "PRACA_REITORIA");
        values.put(COLUMN_NODE_LATITUDE, -5.057777);
        values.put(COLUMN_NODE_LONGITUDE, -42.792575);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 81);
        values.put(COLUMN_NODE_DESCRIPTION, "REITORIA");
        values.put(COLUMN_NODE_LATITUDE, -5.057571);
        values.put(COLUMN_NODE_LONGITUDE, -42.792345);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 82);
        values.put(COLUMN_NODE_DESCRIPTION, "PROPESQ");
        values.put(COLUMN_NODE_LATITUDE, -5.057195);
        values.put(COLUMN_NODE_LONGITUDE, -42.791924);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 83);
        values.put(COLUMN_NODE_DESCRIPTION, "PRAD_PROTOCOLO");
        values.put(COLUMN_NODE_LATITUDE, -5.057006);
        values.put(COLUMN_NODE_LONGITUDE, -42.791723);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 84);
        values.put(COLUMN_NODE_DESCRIPTION, "CRUZ_ALMOX");
        values.put(COLUMN_NODE_LATITUDE, -5.057006);
        values.put(COLUMN_NODE_LONGITUDE, -42.791723);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 85);
        values.put(COLUMN_NODE_DESCRIPTION, "ACESSO_ALMOX");
        values.put(COLUMN_NODE_LATITUDE, -5.056494);
        values.put(COLUMN_NODE_LONGITUDE, -42.791984);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 86);
        values.put(COLUMN_NODE_DESCRIPTION, "ALMOX1");
        values.put(COLUMN_NODE_LATITUDE, -5.056063);
        values.put(COLUMN_NODE_LONGITUDE, -42.792442);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 87);
        values.put(COLUMN_NODE_DESCRIPTION, "BIOQUIMICA");
        values.put(COLUMN_NODE_LATITUDE, -5.056571);
        values.put(COLUMN_NODE_LONGITUDE, -42.791265);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 88);
        values.put(COLUMN_NODE_DESCRIPTION, "COPESE");
        values.put(COLUMN_NODE_LATITUDE, -5.056162);
        values.put(COLUMN_NODE_LONGITUDE, -42.790817);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 89);
        values.put(COLUMN_NODE_DESCRIPTION, "ODONTO_FINAL");
        values.put(COLUMN_NODE_LATITUDE, -5.055702);
        values.put(COLUMN_NODE_LONGITUDE, -42.790317);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 90);
        values.put(COLUMN_NODE_DESCRIPTION, "FARMACIA");
        values.put(COLUMN_NODE_LATITUDE, -5.055404);
        values.put(COLUMN_NODE_LONGITUDE, -42.789975);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 91);
        values.put(COLUMN_NODE_DESCRIPTION, "ANEXO_FARMACIA_MORFOLOGIA");
        values.put(COLUMN_NODE_LATITUDE, -5.055183);
        values.put(COLUMN_NODE_LONGITUDE, -42.789717);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 92);
        values.put(COLUMN_NODE_DESCRIPTION, "CRUZ_FARMACIA_MORF_ENFERMAGEM");
        values.put(COLUMN_NODE_LATITUDE, -5.054995);
        values.put(COLUMN_NODE_LONGITUDE, -42.789498);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 93);
        values.put(COLUMN_NODE_DESCRIPTION, "BALAO_ENTRADA1_OESTE");
        values.put(COLUMN_NODE_LATITUDE, -5.055536);
        values.put(COLUMN_NODE_LONGITUDE, -42.789035);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 94);
        values.put(COLUMN_NODE_DESCRIPTION, "BALAO_ENTRADA1_LESTE");
        values.put(COLUMN_NODE_LATITUDE, -5.055815);
        values.put(COLUMN_NODE_LONGITUDE, -42.788797);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 95);
        values.put(COLUMN_NODE_DESCRIPTION, "BALAO_ENTRADA1_SUL");
        values.put(COLUMN_NODE_LATITUDE, -5.055815);
        values.put(COLUMN_NODE_LONGITUDE, -42.788797);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 96);
        values.put(COLUMN_NODE_DESCRIPTION, "NUTRICAO1");
        values.put(COLUMN_NODE_LATITUDE, -5.055331);
        values.put(COLUMN_NODE_LONGITUDE, -42.78923);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 97);
        values.put(COLUMN_NODE_DESCRIPTION, "ENFERMAGEM");
        values.put(COLUMN_NODE_LATITUDE, -5.05461);
        values.put(COLUMN_NODE_LONGITUDE, -42.789834);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 98);
        values.put(COLUMN_NODE_DESCRIPTION, "CRUZ_ENFERMAGEM_JUIZADO");
        values.put(COLUMN_NODE_LATITUDE, -5.054204);
        values.put(COLUMN_NODE_LONGITUDE, -42.79019);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 99);
        values.put(COLUMN_NODE_DESCRIPTION, "JUIZADO_ENFERMAGEM");
        values.put(COLUMN_NODE_LATITUDE, -5.054039);
        values.put(COLUMN_NODE_LONGITUDE, -42.79033);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 100);
        values.put(COLUMN_NODE_DESCRIPTION, "UNKNOWN");
        values.put(COLUMN_NODE_LATITUDE, -5.053744);
        values.put(COLUMN_NODE_LONGITUDE, -42.790579);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 101);
        values.put(COLUMN_NODE_DESCRIPTION, "NTF");
        values.put(COLUMN_NODE_LATITUDE, -5.054122);
        values.put(COLUMN_NODE_LONGITUDE, -42.791231);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 102);
        values.put(COLUMN_NODE_DESCRIPTION, "CRUZ_UNKNOWN_ACESSOALMOX");
        values.put(COLUMN_NODE_LATITUDE, -5.053633);
        values.put(COLUMN_NODE_LONGITUDE, -42.790682);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 103);
        values.put(COLUMN_NODE_DESCRIPTION, "CRUZ_ACESSOALMOX_OUTUFPI");
        values.put(COLUMN_NODE_LATITUDE, -5.054565);
        values.put(COLUMN_NODE_LONGITUDE, -42.79175);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 104);
        values.put(COLUMN_NODE_DESCRIPTION, "ACESSO_ALMOX");
        values.put(COLUMN_NODE_LATITUDE, -5.054998);
        values.put(COLUMN_NODE_LONGITUDE, -42.79226);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 105);
        values.put(COLUMN_NODE_DESCRIPTION, "ALMOX2");
        values.put(COLUMN_NODE_LATITUDE, -5.055717);
        values.put(COLUMN_NODE_LONGITUDE, -42.793068);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 106);
        values.put(COLUMN_NODE_DESCRIPTION, "Departamento de Computação - CCN");
        values.put(COLUMN_NODE_LATITUDE, -5.056208);
        values.put(COLUMN_NODE_LONGITUDE, -42.789851);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 107);
        values.put(COLUMN_NODE_DESCRIPTION, "Copese - CCN");
        values.put(COLUMN_NODE_LATITUDE, -5.056189);
        values.put(COLUMN_NODE_LONGITUDE, -42.79062);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 108);
        values.put(COLUMN_NODE_DESCRIPTION, "Núcleo de Tecnologia da Informação");
        values.put(COLUMN_NODE_LATITUDE, -5.056622);
        values.put(COLUMN_NODE_LONGITUDE, 42.79036);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 109);
        values.put(COLUMN_NODE_DESCRIPTION, "Departamento de Física - CCN");
        values.put(COLUMN_NODE_LATITUDE, -5.057612);
        values.put(COLUMN_NODE_LONGITUDE, -42.790637);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 110);
        values.put(COLUMN_NODE_DESCRIPTION, "Departamento de Matemática - CCN");
        values.put(COLUMN_NODE_LATITUDE, -5.057208);
        values.put(COLUMN_NODE_LONGITUDE, -42.790142);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 111);
        values.put(COLUMN_NODE_DESCRIPTION, "Departamento de Odontologia - CCS");
        values.put(COLUMN_NODE_LATITUDE, -5.055761);
        values.put(COLUMN_NODE_LONGITUDE, -42.789411);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 112);
        values.put(COLUMN_NODE_DESCRIPTION, "Departamento de Farmácia - CCS");
        values.put(COLUMN_NODE_LATITUDE, -5.055235);
        values.put(COLUMN_NODE_LONGITUDE, -42.790143);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 113);
        values.put(COLUMN_NODE_DESCRIPTION, "Departamento de Nutrição - CCS");
        values.put(COLUMN_NODE_LATITUDE, -5.054868);
        values.put(COLUMN_NODE_LONGITUDE, -42.789381);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 114);
        values.put(COLUMN_NODE_DESCRIPTION, "Departamento de Enfermagem - CCS");
        values.put(COLUMN_NODE_LATITUDE, -5.05409);
        values.put(COLUMN_NODE_LONGITUDE, -42.790065);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 115);
        values.put(COLUMN_NODE_DESCRIPTION, "Departamento de Biologia - CCN");
        values.put(COLUMN_NODE_LATITUDE, -5.05847);
        values.put(COLUMN_NODE_LONGITUDE, -42.791618);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 116);
        values.put(COLUMN_NODE_DESCRIPTION, "Departamento de Química - CCN");
        values.put(COLUMN_NODE_LATITUDE, -5.058065);
        values.put(COLUMN_NODE_LONGITUDE, -42.791159);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 117);
        values.put(COLUMN_NODE_DESCRIPTION, "Restaurante Universitário I");
        values.put(COLUMN_NODE_LATITUDE, -5.055964);
        values.put(COLUMN_NODE_LONGITUDE, -42.788375);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 118);
        values.put(COLUMN_NODE_DESCRIPTION, "Espaço Integrado II");
        values.put(COLUMN_NODE_LATITUDE, -5.05654);
        values.put(COLUMN_NODE_LONGITUDE, -42.788935);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 119);
        values.put(COLUMN_NODE_DESCRIPTION, "Prefeitura Universitária");
        values.put(COLUMN_NODE_LATITUDE, -5.057038);
        values.put(COLUMN_NODE_LONGITUDE, -42.790762);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 120);
        values.put(COLUMN_NODE_DESCRIPTION, "Entrada-Saída I");
        values.put(COLUMN_NODE_LATITUDE, -5.061454);
        values.put(COLUMN_NODE_LONGITUDE, -42.794787);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 121);
        values.put(COLUMN_NODE_DESCRIPTION, "Entrada-Saída II");
        values.put(COLUMN_NODE_LATITUDE, -5.05543);
        values.put(COLUMN_NODE_LONGITUDE, -42.788594);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 122);
        values.put(COLUMN_NODE_DESCRIPTION, "Entrada-Saída III");
        values.put(COLUMN_NODE_LATITUDE, -5.053806);
        values.put(COLUMN_NODE_LONGITUDE, -42.800342);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 123);
        values.put(COLUMN_NODE_DESCRIPTION, "Entrada-Saída IV");
        values.put(COLUMN_NODE_LATITUDE, -5.061778);
        values.put(COLUMN_NODE_LONGITUDE, -42.804461);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 124);
        values.put(COLUMN_NODE_DESCRIPTION, "Biblioteca Central");
        values.put(COLUMN_NODE_LATITUDE, -5.060364);
        values.put(COLUMN_NODE_LONGITUDE, -42.796352);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 125);
        values.put(COLUMN_NODE_DESCRIPTION, "Espaço Noé Mendes");
        values.put(COLUMN_NODE_LATITUDE, -5.062082);
        values.put(COLUMN_NODE_LONGITUDE, -42.79727);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 126);
        values.put(COLUMN_NODE_DESCRIPTION, "Departamento de Moda - CCE ");
        values.put(COLUMN_NODE_LATITUDE, -5.060783);
        values.put(COLUMN_NODE_LONGITUDE, -42.797495);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 127);
        values.put(COLUMN_NODE_DESCRIPTION, "Hospital Universitário");
        values.put(COLUMN_NODE_LATITUDE, -5.060172);
        values.put(COLUMN_NODE_LONGITUDE, -42.794047);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 128);
        values.put(COLUMN_NODE_DESCRIPTION, "Protocolo Geral");
        values.put(COLUMN_NODE_LATITUDE, -5.056827);
        values.put(COLUMN_NODE_LONGITUDE, -42.7908);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 129);
        values.put(COLUMN_NODE_DESCRIPTION, "Almoxarifado");
        values.put(COLUMN_NODE_LATITUDE, -5.056115);
        values.put(COLUMN_NODE_LONGITUDE, -42.792627);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 130);
        values.put(COLUMN_NODE_DESCRIPTION, "Reitoria");
        values.put(COLUMN_NODE_LATITUDE, -5.057446);
        values.put(COLUMN_NODE_LONGITUDE, -42.791865);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 131);
        values.put(COLUMN_NODE_DESCRIPTION, "Espaço Integrado I");
        values.put(COLUMN_NODE_LATITUDE, -5.059477);
        values.put(COLUMN_NODE_LONGITUDE, -42.796676);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 132);
        values.put(COLUMN_NODE_DESCRIPTION, "Diretório Central dos Estudantes - DCE");
        values.put(COLUMN_NODE_LATITUDE, -5.0568);
        values.put(COLUMN_NODE_LONGITUDE, -42.788974);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 133);
        values.put(COLUMN_NODE_DESCRIPTION, "Pró-Reitoria de Extensão - PREX");
        values.put(COLUMN_NODE_LATITUDE, -5.060008);
        values.put(COLUMN_NODE_LONGITUDE, -42.79286);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 134);
        values.put(COLUMN_NODE_DESCRIPTION, "Pró-Reitoria de Assuntos Estudantis e Comunitários - PRAEC");
        values.put(COLUMN_NODE_LATITUDE, -5.056356);
        values.put(COLUMN_NODE_LONGITUDE, -42.788658);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 135);
        values.put(COLUMN_NODE_DESCRIPTION, "Pró-Reitoria de Administração - PRAD");
        values.put(COLUMN_NODE_LATITUDE, -5.057034);
        values.put(COLUMN_NODE_LONGITUDE, -42.791554);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 136);
        values.put(COLUMN_NODE_DESCRIPTION, "Pró-Reitoria de Ensino de Graduação - PREG");
        values.put(COLUMN_NODE_LATITUDE, -5.057401);
        values.put(COLUMN_NODE_LONGITUDE, -42.791505);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 137);
        values.put(COLUMN_NODE_DESCRIPTION, "Pró-Reitoria de Pós-Graduação - PRPG");
        values.put(COLUMN_NODE_LATITUDE, -5.057337);
        values.put(COLUMN_NODE_LONGITUDE, -42.791575);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 138);
        values.put(COLUMN_NODE_DESCRIPTION, "Pró-Reitoria de Planejamento e Orçamento - PROPLAN");
        values.put(COLUMN_NODE_LATITUDE, -5.056827);
        values.put(COLUMN_NODE_LONGITUDE, -42.7908);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 147);
        values.put(COLUMN_NODE_DESCRIPTION, "Coordenacao Graduacao Politica - CCHL");
        values.put(COLUMN_NODE_LATITUDE, -5.057132);
        values.put(COLUMN_NODE_LONGITUDE, -42.796898);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 148);
        values.put(COLUMN_NODE_DESCRIPTION, "Coordenaçao de Administracao - CCHL");
        values.put(COLUMN_NODE_LATITUDE, -5.057395);
        values.put(COLUMN_NODE_LONGITUDE, -42.796935);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 149);
        values.put(COLUMN_NODE_DESCRIPTION, "Coordenaçao de Ciências Contabeis - CCHL");
        values.put(COLUMN_NODE_LATITUDE, -5.0575);
        values.put(COLUMN_NODE_LONGITUDE, -42.796946);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 150);
        values.put(COLUMN_NODE_DESCRIPTION, "Departamento de Letras - CCHL");
        values.put(COLUMN_NODE_LATITUDE, -5.057565);
        values.put(COLUMN_NODE_LONGITUDE, -42.796952);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 151);
        values.put(COLUMN_NODE_DESCRIPTION, "Departamento de Economia - CCHL");
        values.put(COLUMN_NODE_LATITUDE, -5.057628);
        values.put(COLUMN_NODE_LONGITUDE, -42.79696);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 152);
        values.put(COLUMN_NODE_DESCRIPTION, "Departamento de Filosofia - CCHL");
        values.put(COLUMN_NODE_LATITUDE, -5.057682);
        values.put(COLUMN_NODE_LONGITUDE, -42.796974);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 153);
        values.put(COLUMN_NODE_DESCRIPTION, "Mestrado em Etica e Epistemologia - CCHL");
        values.put(COLUMN_NODE_LATITUDE, -5.057938);
        values.put(COLUMN_NODE_LONGITUDE, -42.797398);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 154);
        values.put(COLUMN_NODE_DESCRIPTION, "Mestrado em Geografia - CCHL");
        values.put(COLUMN_NODE_LATITUDE, -5.057532);
        values.put(COLUMN_NODE_LONGITUDE, -42.797657);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 155);
        values.put(COLUMN_NODE_DESCRIPTION, "Mestrado em Sociologia - CCHL");
        values.put(COLUMN_NODE_LATITUDE, -5.05761);
        values.put(COLUMN_NODE_LONGITUDE, -42.797668);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 156);
        values.put(COLUMN_NODE_DESCRIPTION, "Coordenacao Artes Visuais - CCE");
        values.put(COLUMN_NODE_LATITUDE, -5.058168);
        values.put(COLUMN_NODE_LONGITUDE, -42.796377);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 157);
        values.put(COLUMN_NODE_DESCRIPTION, "Coordenação de Historia - CCHL");
        values.put(COLUMN_NODE_LATITUDE, -5.057615);
        values.put(COLUMN_NODE_LONGITUDE, -42.79726);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 158);
        values.put(COLUMN_NODE_DESCRIPTION, "Coordenação de Geografia - CCHL");
        values.put(COLUMN_NODE_LATITUDE, -5.057593);
        values.put(COLUMN_NODE_LONGITUDE, -42.797084);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 159);
        values.put(COLUMN_NODE_DESCRIPTION, "Coordenação de Direito - CCHL");
        values.put(COLUMN_NODE_LATITUDE, -5.057615);
        values.put(COLUMN_NODE_LONGITUDE, -42.79726);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 160);
        values.put(COLUMN_NODE_DESCRIPTION, "Coordenação de Ciências Jurídicas - CCHL");
        values.put(COLUMN_NODE_LATITUDE, -5.057614);
        values.put(COLUMN_NODE_LONGITUDE, -42.79721);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 161);
        values.put(COLUMN_NODE_DESCRIPTION, "Coordenação de Ciências Sociais - CCHL");
        values.put(COLUMN_NODE_LATITUDE, -5.057605);
        values.put(COLUMN_NODE_LONGITUDE, -42.797176);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 162);
        values.put(COLUMN_NODE_DESCRIPTION, "Coordenação de Serviço Social - CCE");
        values.put(COLUMN_NODE_LATITUDE, -5.057609);
        values.put(COLUMN_NODE_LONGITUDE, -42.79712);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 163);
        values.put(COLUMN_NODE_DESCRIPTION, "Coordenação de Pedagogia - CCE");
        values.put(COLUMN_NODE_LATITUDE, -5.057873);
        values.put(COLUMN_NODE_LONGITUDE, -42.796717);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 165);
        values.put(COLUMN_NODE_DESCRIPTION, "Praça de Alimentação - CCHL");
        values.put(COLUMN_NODE_LATITUDE, -5.057998);
        values.put(COLUMN_NODE_LONGITUDE, -42.79675);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 166);
        values.put(COLUMN_NODE_DESCRIPTION, "Restaurante Universitario III");
        values.put(COLUMN_NODE_LATITUDE, -5.047707);
        values.put(COLUMN_NODE_LONGITUDE, -42.784237);
        db.insert(TABLE_NODE, null, values);
        values.put(COLUMN_NODE_ID, 167);
        values.put(COLUMN_NODE_DESCRIPTION, "Hospital Veterinário");
        values.put(COLUMN_NODE_LATITUDE, -5.047046);
        values.put(COLUMN_NODE_LONGITUDE, -42.78406);
        db.insert(TABLE_NODE, null, values);

        db.close();
    }

}

