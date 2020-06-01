package com.example.wintecwaiata;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.io.FileNotFoundException;

@Database(entities = {Carving.class, CarvingDescription.class, Multimedia.class}, views = {CarvingListView.class, CarvingDescriptionView.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static  final String DATABASE_NAME = "app_database";
    private static AppDatabase instance;
    private static Context appContext;

    public abstract CarvingDao carvingDao();
    public abstract CarvingDescriptionDao carvingDescriptionDao();
    public abstract MultimediaDao multimediaDao();
    public abstract CarvingDescriptionViewDao carvingDescriptionViewDao();
    public abstract CarvingListViewDao carvingListViewDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().addCallback(roomCallback).build();
            appContext = context;
        }
        return instance;

    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CarvingDao carvingDao;
        private CarvingDescriptionDao carvingDescriptionDao;
        private MultimediaDao multimediaDao;

        public PopulateDbAsyncTask(AppDatabase appDatabase) {
            this.carvingDao = appDatabase.carvingDao();
            this.carvingDescriptionDao = appDatabase.carvingDescriptionDao();
            this.multimediaDao = appDatabase.multimediaDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                multimediaDao.insert(new Multimedia("gateway_entrance_01.jpg", appContext));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                multimediaDao.insert(new Multimedia("gateway_entrance_02.jpg", appContext));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                multimediaDao.insert(new Multimedia("internal_wharenui_post_01.jpg", appContext));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                multimediaDao.insert(new Multimedia("internal_wharenui_post_02.png", appContext));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                multimediaDao.insert(new Multimedia("memorial_pillar_01.jpg", appContext));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                multimediaDao.insert(new Multimedia("pillars_01.jpg", appContext));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                multimediaDao.insert(new Multimedia("doorway_lintels_01.png", appContext));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                multimediaDao.insert(new Multimedia("doorway_lintels_02.jpg", appContext));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                multimediaDao.insert(new Multimedia("window_lintel_01.jpg", appContext));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                multimediaDao.insert(new Multimedia("window_lintel_02.jpg", appContext));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                multimediaDao.insert(new Multimedia("memorial_pillar_02.jpg", appContext));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                multimediaDao.insert(new Multimedia("pillars_02.jpg", appContext));
            } catch (Exception e) {
                e.printStackTrace();
            }

            carvingDao.insert(new Carving("Waka Maumahara (Memorial Pillar)", 5, 1));
            carvingDao.insert(new Carving("Pou Whakarae (Pillars)", 6, 2));
            carvingDao.insert(new Carving("Pou-tūā-rangi (Internal Wharenui Post)", 4, 3));
            carvingDao.insert(new Carving("Pou-tūā-rongo - Tawhaki - Internal Wharenui Post", 3, 4));
            carvingDao.insert(new Carving("Tomokanga (Gateway Entrance)", 1, 5));
            carvingDao.insert(new Carving("Pare and Whakawae (Doorway Lintels)", 7, 6));
            carvingDao.insert(new Carving("Kōrupe (Window Lintel)", 9, 7));

            carvingDescriptionDao.insert(new CarvingDescription(
                    1,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><b>About</b></p>\n" +
                            "<p>On the marae &#257;tea\n" +
                            "(courtyard) is 7.5 metre pou (pillar) in the form of a waka (canoe).</p>\n" +
                            "<p>This is the waka maumahara\n" +
                            "(memorial pillar) which faces Taupiri mountain. At the top of the waka\n" +
                            "maumahara stands the native kaahu (hawk) representing the M&#257;ori Queen Dame\n" +
                            "Te Atarirangikaahu.</p>\n" +
                            "</body>\n" +
                            "</html>",
                    5));
            carvingDescriptionDao.insert(new CarvingDescription(
                    1,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>Below this are five niho taniwha, a pattern representing the\n" +
                            "five M&#257;ori kings.</span></p>\n" +
                            "<ul type=disc>\n" +
                            " <li class=MsoNormal style='color:#373A3C;text-align:justify;line-height:normal;\n" +
                            "     background:white'><span style='font-size:12.0pt;font-family:\"Source Sans Pro\",sans-serif'>P&#333;tatau</span></li>\n" +
                            " <li class=MsoNormal style='color:#373A3C;text-align:justify;line-height:normal;\n" +
                            "     background:white'><span style='font-size:12.0pt;font-family:\"Source Sans Pro\",sans-serif'>T&#257;whiao</span></li>\n" +
                            " <li class=MsoNormal style='color:#373A3C;text-align:justify;line-height:normal;\n" +
                            "     background:white'><span style='font-size:12.0pt;font-family:\"Source Sans Pro\",sans-serif'>Mahuta</span></li>\n" +
                            " <li class=MsoNormal style='color:#373A3C;text-align:justify;line-height:normal;\n" +
                            "     background:white'><span style='font-size:12.0pt;font-family:\"Source Sans Pro\",sans-serif'>Te\n" +
                            "     Rata</span></li>\n" +
                            " <li class=MsoNormal style='color:#373A3C;text-align:justify;line-height:normal;\n" +
                            "     background:white'><span style='font-size:12.0pt;font-family:\"Source Sans Pro\",sans-serif'>Korok&#299;.</span></li>\n" +
                            "</ul>\n" +
                            "<p><span>Learning and knowledge are symbolised with manaia and\n" +
                            "matakupenga patterns through the centre, with the p&#363;horo design\n" +
                            "representing the Waikato River.&nbsp;</span></p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "</body>\n" +
                            "</html>",
                    11));
            carvingDescriptionDao.insert(new CarvingDescription(
                    2,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>Near the waka maumahara (memorial pillar ) are\n" +
                            "seven smaller pou whakarae(pillars) which symbolise the star cluster Matariki\n" +
                            "(Pleides).</span></p>\n" +
                            "</body>\n" +
                            "</html>\n",
                    6));
            carvingDescriptionDao.insert(new CarvingDescription(
                    2,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>This star cluster is significant to M&#257;ori\n" +
                            "and Tainui culture.</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    12));
            carvingDescriptionDao.insert(new CarvingDescription(
                    3,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>The pou-t&#363;&#257;-rangi (internal wharenui post) near the\n" +
                            "entrance represents Wintec values.</span></p>\n" +
                            "<ul type=disc>\n" +
                            " <li class=MsoNormal style='color:#373A3C;text-align:justify;line-height:normal;\n" +
                            "     background:white'><span>Mahi\n" +
                            "     tahi - Working together</span></li>\n" +
                            " <li class=MsoNormal style='color:#373A3C;text-align:justify;line-height:normal;\n" +
                            "     background:white'><span>Whakaaro\n" +
                            "     whanui - Challenge and innovation</span></li>\n" +
                            " <li class=MsoNormal style='color:#373A3C;text-align:justify;line-height:normal;\n" +
                            "     background:white'><span>Manaaki\n" +
                            "     tangata - Customer focus</span></li>\n" +
                            " <li class=MsoNormal style='color:#373A3C;text-align:justify;line-height:normal;\n" +
                            "     background:white'><span>Kia\n" +
                            "     Tika - Taking ownership</span></li>\n" +
                            " <li class=MsoNormal style='color:#373A3C;text-align:justify;line-height:normal;\n" +
                            "     background:white'><span>Kia\n" +
                            "     tupu, kia hua - Improvement and opportunity</span></li>\n" +
                            " <li class=MsoNormal style='color:#373A3C;text-align:justify;line-height:normal;\n" +
                            "     background:white'><span>Whakamana\n" +
                            "     i te tangata - Valuing people</span></li>\n" +
                            "</ul>\n" +
                            "<p><span>It has been carved in totara from a tree which used to stand on\n" +
                            "Wintec city campus. Behind the pou-tua-rangi is a tukutuku panel from the\n" +
                            "original Wintec wharenui, Te K&#257;kano a te Kaahu.</span></p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "</body>\n" +
                            "</html>",
                    4));
            carvingDescriptionDao.insert(new CarvingDescription(
                    4,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>Located on the\n" +
                            "back wall of the wharenui the pou-t&#363;&#257;-rongo is a carving of Tawhaki\n" +
                            "who received the baskets of knowledge (nga kete wananga).</span></p>\n" +
                            "<p><span>Tawhaki\n" +
                            "is featured looking towards the doorway and the world of light. This was also\n" +
                            "carved in totara from a tree which use to stand on Wintec’s city campus.</span></p>\n" +
                            "<p>&nbsp;</p>\n" +
                            "</body>\n" +
                            "</html>\n",
                    3));
            carvingDescriptionDao.insert(new CarvingDescription(
                    5,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>The entrance\n" +
                            "designs welcome people from around the world of Wintec.</span></p>\n" +
                            "<p><span>The\n" +
                            "sub-tribes (hap&#363;) of this area are shown welcoming visitors onto the marae\n" +
                            "in a central carving at the entrance.</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    1));
            carvingDescriptionDao.insert(new CarvingDescription(
                    5,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>M&#257;tauranga M&#257;ori and part of the\n" +
                            "creation story of Tainui are also represented in the carvings.</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    2));
            carvingDescriptionDao.insert(new CarvingDescription(
                    6,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>Above the wharenui pare (doorway) are three\n" +
                            "figures nga kete w&#257;nanga representing the three baskets of knowledge. Part\n" +
                            "of the creation story of Tainui is also told in the designs.</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    7));
            carvingDescriptionDao.insert(new CarvingDescription(
                    6,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>Manaia and takarangi patterns symbolise growth\n" +
                            "and development. The textured ngao matariki pattern on the sides of the doorway\n" +
                            "(whakawae) represents the welcoming of the cultures and peoples of the world.</span></p>\n" +
                            "</body>\n" +
                            "</html>\n",
                    8));
            carvingDescriptionDao.insert(new CarvingDescription(
                    7,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>The taniwha Tuheitia is carved on the window\n" +
                            "lintel (k&#333;rupe) above the front window of Te K&#257;kano a te Kaahu, along\n" +
                            "with ngao matariki and manaia designs.</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    9));
            carvingDescriptionDao.insert(new CarvingDescription(
                    7,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>Tuheitia holds guardianship of the Waipa river\n" +
                            "and was regarded with awe and respect.</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    10));
            return null;
        }
    }


}
