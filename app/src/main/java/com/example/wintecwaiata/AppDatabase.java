package com.example.wintecwaiata;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(
        entities = {
                Carving.class,
                CarvingDescription.class,
                Multimedia.class,
                VideoContent.class,
                VideoContentDetails.class,
                ExternalHTTP.class
        },
        views = {
                CarvingListView.class,
                CarvingDescriptionView.class,
                VideoListView.class,
                VideoDetailsView.class
        },
        version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static  final String DATABASE_NAME = "app_database";
    private static AppDatabase instance;

    // Tables Dao
    // ---- Carvings
    public abstract CarvingDao carvingDao();
    public abstract CarvingDescriptionDao carvingDescriptionDao();
    // ---- Videos
    public abstract VideoContentDao videoContentDao();
    public abstract VideoContentDetailsDao videoContentDetailsDao();
    // ---- Multimedia (video and pictures filenames)
    public abstract MultimediaDao multimediaDao();
    // ---- HTTP addresses
    public abstract ExternalHTTPDao externalHTTPDao();

    // Views Dao
    // ---- Carvings
    public abstract CarvingListViewDao carvingListViewDao();
    public abstract CarvingDescriptionViewDao carvingDescriptionViewDao();
    // ---- Videos
    public abstract VideoListViewDao videoListViewDao();
    public abstract VideoDetailsViewDao videoDetailsViewDao();


    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().addCallback(roomCallback).build();
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
        private VideoContentDao videoContentDao;
        private VideoContentDetailsDao videoContentDetailsDao;
        private ExternalHTTPDao externalHTTPDao;

        public PopulateDbAsyncTask(AppDatabase appDatabase) {
            this.carvingDao = appDatabase.carvingDao();
            this.carvingDescriptionDao = appDatabase.carvingDescriptionDao();
            this.multimediaDao = appDatabase.multimediaDao();
            this.videoContentDao = appDatabase.videoContentDao();
            this.videoContentDetailsDao = appDatabase.videoContentDetailsDao();
            this.externalHTTPDao = appDatabase.externalHTTPDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Prepopulate tables in background
            // Drawable pictures for carvings
            multimediaDao.insert(new Multimedia("gateway_entrance_01.jpg"));
            multimediaDao.insert(new Multimedia("gateway_entrance_02.jpg"));
            multimediaDao.insert(new Multimedia("internal_wharenui_post_01.jpg"));
            multimediaDao.insert(new Multimedia("internal_wharenui_post_02.png"));
            multimediaDao.insert(new Multimedia("memorial_pillar_01.jpg"));
            multimediaDao.insert(new Multimedia("pillars_01.jpg"));
            multimediaDao.insert(new Multimedia("doorway_lintels_01.png"));
            multimediaDao.insert(new Multimedia("doorway_lintels_02.jpg"));
            multimediaDao.insert(new Multimedia("window_lintel_01.jpg"));
            multimediaDao.insert(new Multimedia("window_lintel_02.jpg"));
            multimediaDao.insert(new Multimedia("memorial_pillar_02.jpg"));
            multimediaDao.insert(new Multimedia("pillars_02.jpg"));
            // Raw video for songs
            multimediaDao.insert(new Multimedia("ekorekoe_1.mp4"));
            multimediaDao.insert(new Multimedia("ekorekoe_2.mp4"));
            multimediaDao.insert(new Multimedia("ekorekoe_3.mp4"));
            multimediaDao.insert(new Multimedia("hemaimaiaroha_1.mp4"));
            multimediaDao.insert(new Multimedia("hemaimaiaroha_2.mp4"));
            multimediaDao.insert(new Multimedia("hemaimaiaroha_3.mp4"));
            multimediaDao.insert(new Multimedia("waikatoteawa_1.mp4"));
            multimediaDao.insert(new Multimedia("waikatoteawa_2.mp4"));
            multimediaDao.insert(new Multimedia("waikatoteawa_3.mp4"));
            multimediaDao.insert(new Multimedia("tutiramainga_1.mp4"));
            multimediaDao.insert(new Multimedia("tutiramainga_2.mp4"));
            multimediaDao.insert(new Multimedia("tutiramainga_3.mp4"));
            multimediaDao.insert(new Multimedia("pupuketehihiri_1.mp4"));
            multimediaDao.insert(new Multimedia("pupuketehihiri_2.mp4"));
            multimediaDao.insert(new Multimedia("pupuketehihiri_3.mp4"));
            multimediaDao.insert(new Multimedia("itewhare_1.mp4"));
            multimediaDao.insert(new Multimedia("itewhare_2.mp4"));
            multimediaDao.insert(new Multimedia("itewhare_3.mp4"));
            multimediaDao.insert(new Multimedia("puatekowhai_1.mp4"));
            multimediaDao.insert(new Multimedia("puatekowhai_2.mp4"));
            multimediaDao.insert(new Multimedia("puatekowhai_3.mp4"));

            // Carvings
            carvingDao.insert(new Carving("Waka Maumahara (Memorial Pillar)", 5, 1));
            carvingDao.insert(new Carving("Pou Whakarae (Pillars)", 6, 2));
            carvingDao.insert(new Carving("Pou-tūā-rangi (Internal Wharenui Post)", 4, 3));
            carvingDao.insert(new Carving("Pou-tūā-rongo - Tawhaki - Internal Wharenui Post", 3, 4));
            carvingDao.insert(new Carving("Tomokanga (Gateway Entrance)", 1, 5));
            carvingDao.insert(new Carving("Pare and Whakawae (Doorway Lintels)", 7, 6));
            carvingDao.insert(new Carving("Kōrupe (Window Lintel)", 9, 7));

            // Carvings Description
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

            // Videos
            videoContentDao.insert(new VideoContent("E Kore Koe E Ngaro", 13, 1));
            videoContentDao.insert(new VideoContent("He Maimai Aroha nā Tāwhiao", 16, 2));
            videoContentDao.insert(new VideoContent("Waikato Te Awa", 19, 3));
            videoContentDao.insert(new VideoContent("Tutira Mai Nga Iwi", 22, 4));
            videoContentDao.insert(new VideoContent("Pupuke Te Hihiri", 25, 5));
            videoContentDao.insert(new VideoContent("I Te Whare Whakapiri", 28, 6));
            videoContentDao.insert(new VideoContent("Pua Te Kōwhai", 31, 7));

            // Lyrics
            videoContentDetailsDao.insert(new VideoContentDetails(
                    13,
                    14,
                    15,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>" +
                            "E Ko Te Kurantini o Waikato e<br><br>" +
                            "E kore koe e ngaro he kākano i ruia\n" +
                            "<br>" +
                            "Kākano a te Kaahu e tū nei e\n" +
                            "<br>" +
                            "Te Kōpū Mānia, te ngāwhā whakatupu\n" +
                            "<br>" +
                            "Ka tupu he tangata, rere ki te ao\n" +
                            "<br><br>" +
                            "Horahia Matariki, kūmea ngā waka\n" +
                            "<br>" +
                            "Herea ki te pou o te aroha e\n" +
                            "<br>" +
                            "Te Atairangikaahu, tāiri kei runga\n" +
                            "<br>" +
                            "Ko Kīngi Tūheitia ki te whenua e\n" +
                            "<br><br>" +
                            "Piki ake Tāwhaki, tāhūhū matapū\n" +
                            "<br>" +
                            "Ngā kete wānanga e toru e\n" +
                            "<br>" +
                            "Whītikitia rā, ka turuturu iho\n" +
                            "<br>" +
                            "E ko Te Kuratini o Waikato e.</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>To Waikato Institute of Technology<br><br>\n" +
                            "You are not lost, seed of heaven<br>\n" +
                            "Kākano a te Kaahu stand tall<br>\n" +
                            "Te Kōpū Mānia, cultivate new growth<br>\n" +
                            "Foster this person of the world<br><br>\n" +
                            "Matariki on display, draw in all canoes<br>\n" +
                            "Bind them to the posts of support and care<br>\n" +
                            "Te Atairangikaahu, fly above<br>\n" +
                            "Kīngi Tūheitia, hold steadfast below<br><br>\n" +
                            "Tāwhaki ascend, prepare the house<br>\n" +
                            "For the three baskets of knowledge<br>\n" +
                            "Bind them, fasten them<br>\n" +
                            "To Waikato Institute of Technology\n" +
                            "</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    1));
            videoContentDetailsDao.insert(new VideoContentDetails(
                    16,
                    17,
                    18,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>Ka mātakitaki iho au ki te riu o Waikato\n" +
                            "<br>" +
                            "Anō nei hei kapo kau ake māku\n" +
                            "<br>" +
                            "Ki te kapu o taku ringa,\n" +
                            "<br>" +
                            "Ka whakamiri noa I tōna aratau,\n" +
                            "<br>" +
                            "E tia nei he tupu pua hou.\n" +
                            "<br><br>" +
                            "\n" +
                            "Kia hiwa ake au I te tihi o Pirongia,\n" +
                            "<br>" +
                            "Inā hei toronga whakaruruhau mōna\n" +
                            "<br>" +
                            "Ki tōku tauawhirotanga.\n" +
                            "<br><br>" +
                            "\n" +
                            "Anā! Te ngoto o tōna ngāwhā\n" +
                            "<br>" +
                            "I ōna uma kīhai I ārikarika\n" +
                            "<br>" +
                            "A Maungatautari, a Maungakawa,\n" +
                            "<br>" +
                            "Ōku puke maunga, ngā taonga tuku iho:\n" +
                            "<br>" +
                            "Hoki ake nei au ki tōku awa koiora\n" +
                            "<br>" +
                            "Me ōna pikonga\n" +
                            "<br>" +
                            "He kura tangihia o te mātāmuri.\n" +
                            "<br><br>" +
                            "\n" +
                            "E whakawhiti atu ai I te kōpū mānia\n" +
                            "<br>" +
                            "O Kirikiriroa\n" +
                            "<br>" +
                            "Me ōna māra kai, te ngāwhā whakatupu\n" +
                            "<br>" +
                            "Ake o te whenua mōmona,\n" +
                            "<br>" +
                            "Hei kawe ki Ngāruawāhia,\n" +
                            "<br>" +
                            "Te huinga o te tangata.\n" +
                            "<br><br>" +
                            "\n" +
                            "Arā, te pae haumako, hei okiokinga mō\n" +
                            "<br>" +
                            "Taku upoko,\n" +
                            "<br>" +
                            "Hei tirohanga atu mā raro I ngā hūhā\n" +
                            "<br>" +
                            "O Taupiri.\n" +
                            "<br><br>" +
                            "\n" +
                            "Kei reira rā, kei te oroko hanganga o te tangata\n" +
                            "<br>" +
                            "Wāhia te tungaroa o te whare,\n" +
                            "<br>" +
                            "Te whakaputanga mō te Kīngi.</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>I look down on the valley of Waikato,<br> \n" +
                            "As though to hold it in the hollow of my hand<br> \n" +
                            "And caress its beauty<br> \n" +
                            "Like some tender verdant thing.<br><br> \n" +
                            "I reach out from the top of Pirongia<br> \n" +
                            "As though to cover and protect its <br>\n" +
                            "Substance with my own.<br><br> \n" +
                            "See how it bursts through the full bosoms <br>\n" +
                            "Of Maungatautari and Maungakawa, <br>\n" +
                            "Hills of my inheritance <br>\n" +
                            "The river of life, each curve more beautiful <br>\n" +
                            "Than the last.<br><br>\n" +
                            "Crossing the smooth belly of Kirikiriroa,<br> \n" +
                            "Its gardens bursting with fullness of the rich <br>\n" +
                            "Earth towards the meeting place at <br>\n" +
                            "Ngaruawahia.<br><br>\n" +
                            "There on the fertile mound I would rest <br>\n" +
                            "My head <br>\n" +
                            "And look through the thighs of<br> \n" +
                            "Taupiri<br><br>\n" +
                            "There at the place of all creation<br>\n" +
                            "Let the King come forth.\n" +
                            "</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    2));
            videoContentDetailsDao.insert(new VideoContentDetails(
                    19,
                    20,
                    21,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>Waikato te awa\n" +
                            "<br>" +
                            "Katohia, katohia he wai māu\n" +
                            "<br>" +
                            "Katohia he wai māu\n" +
                            "<br>" +
                            "Ka eke ki Te Pūaha o Waikato\n" +
                            "<br>" +
                            "Te awa; he piko, he taniwha\n" +
                            "<br><br>" +
                            "He piko, he taniwha\n" +
                            "<br>" +
                            "Kia tūpato rā kei tahuri koe\n" +
                            "<br>" +
                            "I ngā au kaha o Waikato\n" +
                            "<br>" +
                            "Whakamau tō titiro ki tawhiti rā\n" +
                            "<br>" +
                            "Ko Taupiri te maunga\n" +
                            "<br>" +
                            "Pōtatau te tangata\n" +
                            "<br><br>" +
                            "Te mauri o te motu e\n" +
                            "<br>" +
                            "E hoe tō waka ki Ngāruawāhia\n" +
                            "<br>" +
                            "Tūrangawaewae mō te ao katoa\n" +
                            "<br>" +
                            "Te tongi whakamutunga a Matutaera\n" +
                            "<br>" +
                            "Auē hoki auē</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>Waikato to awa<br> \n" +
                            "Dip in the water<br> \n" +
                            "As it surges at the mouth<br> \n" +
                            "Waikato is the river<br> \n" +
                            "At every bend there lives A chief.<br><br> \n" +
                            "Be careful lest you capsize<br> \n" +
                            "For the currents are strong<br> \n" +
                            "In the Waikato<br> \n" +
                            "Fix your gaze on the distance<br> \n" +
                            "Where Taupiri is the mountain<br> \n" +
                            "And Pōtatau the man.<br><br> \n" +
                            "Paddle your canoe to Ngāruawāhia<br> \n" +
                            "To Tūrangawaewae<br> \n" +
                            "The heart of the kingdom<br> \n" +
                            "Where Matutaera finished his lament<br> \n" +
                            "Alas, let me grieve also \n" +
                            "</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    3));
            videoContentDetailsDao.insert(new VideoContentDetails(
                    22,
                    23,
                    24,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>Tūtira mai ngā iwi,\n" +
                            "<br>" +
                            "tātou tātou e\n" +
                            "<br>" +
                            "Tūtira mai ngā iwi,\n" +
                            "<br>" +
                            "tātou tātou e\n" +
                            "<br>" +
                            "Whai-a te marama-tanga,\n" +
                            "<br>" +
                            "me te aroha - e ngā iwi!\n" +
                            "<br>" +
                            "Ki-a ko tapa tahi,\n" +
                            "<br>" +
                            "Ki-a ko-tahi rā\n" +
                            "<br>" +
                            "Tātou tātou e\n" +
                            "<br><br>" +
                            "\n" +
                            "Tā-tou tā-tou e E!!\n" +
                            "<br>" +
                            "Hi aue hei !!!</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>Line up together people\n" +
                            "<br>" +
                            "All of us, all of us\n" +
                            "<br>" +
                            "Stand in rows people\n" +
                            "<br>" +
                            "All of us, all of us\n" +
                            "<br>" +
                            "Seek after knowledge\n" +
                            "<br>" +
                            "and love of others - everyone\n" +
                            "<br>" +
                            "Think as one\n" +
                            "<br>" +
                            "Act as one\n" +
                            "<br>" +
                            "All of us, all of us\n" +
                            "<br><br>" +
                            "\n" +
                            "All of us, All of us!!\n" +
                            "<br>" +
                            "Hi aue hei !!!</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    4));
            videoContentDetailsDao.insert(new VideoContentDetails(
                    25,
                    26,
                    27,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>Piki ake e tama e Hine\n" +
                            "<br>" +
                            "I te ahurei o te Kuratini o Waikato\n" +
                            "<br>" +
                            "He mātāpono whakaū kounga\n" +
                            "<br>" +
                            "Ka whakaata i te angitū\n" +
                            "<br>" +
                            "Piki ake e tama e Hine\n" +
                            "<br>" +
                            "I te ahurei o te Kuratini o Waikato\n" +
                            "<br>" +
                            "He mātāpono whakaū kounga\n" +
                            "<br>" +
                            "Ka whakaata i te angitū\n" +
                            "<br><br>" +
                            "\n" +
                            "Pupuke te hihiri\n" +
                            "<br>" +
                            "Pupuke te mahara\n" +
                            "<br>" +
                            "Pupuke te wānanga a te Kore\n" +
                            "<br><br>" +
                            "\n" +
                            "Nō ngā kete i pikihia e Tāwhaki\n" +
                            "<br>" +
                            "He wairua, he mauri, he mana, he reo\n" +
                            "<br>" +
                            "He māramatanga, he mātauranga\n" +
                            "<br>" +
                            "He iho o te ahurea"+
                            "<br><br>" +
                            "Pupuke te hihiri\n" +
                            "<br>" +
                            "Pupuke te mahara\n" +
                            "<br>" +
                            "Pupuke te wānanga a te Kore\n" +
                            "</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    " <html>" +
                            "<body>" +
                            "<p><span>Ascend Son, Daughter<br>" +
                            "To the pinnacle of Wintec<br>" +
                            "Hold fast to quality principles<br>" +
                            "That reflect your successes<br>" +
                            "Ascend Son, Daughter<br>" +
                            "To the pinnacle of Wintec<br>" +
                            "Hold fast to quality principles<br>" +
                            "That reflect your successes.<br><br>" +
                            "From conception the increase<br>" +
                            "From increase the thought<br>" +
                            "From thought the knowledge out of Chaos<br>" +
                            "From conception the increase<br>" +
                            "From increase the<br>" +
                            "thought From thought the knowledge out of Chaos.<br><br>" +
                            "From the baskets of knowledge Tawhaki retrieved<br>" +
                            "Comes spirit, life-force, power, language,<br>" +
                            "Understanding and knowledge<br>" +
                            "The isthmus of culture.<br><br>" +
                            "From conception the increase<br>" +
                            "From increase the thought<br>" +
                            "From thought the knowledge out of Chaos<br>" +
                            "From conception the increase<br>" +
                            "From increase the thought<br>" +
                            "From thought the knowledge out of Chaos" +
                            "</span></p>" +
                            "</body>" +
                            "</html> ",
                    5));
            videoContentDetailsDao.insert(new VideoContentDetails(
                    28,
                    29,
                    30,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>He Māori tū tangata" +
                            "<br><br>" +
                            "I te whare whakapiri a Tāne\n" +
                            "<br>" +
                            "Roto te Kuratini o Waikato\n" +
                            "<br>" +
                            "Ka puta ngā mamaetanga\n" +
                            "<br>" +
                            "Rere iho ko ngā roimata\n" +
                            "<br>" +
                            "Auē, te arohanui\n" +
                            "<br><br>" +
                            "\n" +
                            "Homai, homai taku Māoritanga\n" +
                            "<br>" +
                            "Whāngaihia mai tōku reo tuku iho\n" +
                            "<br>" +
                            "He kaiwhakaoho i tōku wairua\n" +
                            "<br><br>" +
                            "\n" +
                            "Kua ngaro te korekore\n" +
                            "<br>" +
                            "Puāwai ana ko ahau\n" +
                            "<br>" +
                            "He Māori tū tangata</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>I am Māori<br><br> \n" +
                            "In this cultural house<br> \n" +
                            "That is within Waikato Institute of Technology<br> \n" +
                            "We feel the sorrow<br> \n" +
                            "The tears, The flow<br> \n" +
                            "And the love.<br><br> \n" +
                            "Give back our cultural inheritance<br> \n" +
                            "Nurture the development of my language<br> \n" +
                            "That awakens my spirit.<br><br> \n" +
                            "The nothingness has disappeared<br> \n" +
                            "I begin to bloom<br> \n" +
                            "I am Māori<br> \n" +
                            "I am Māori  \n" +
                            "</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    6));
            videoContentDetailsDao.insert(new VideoContentDetails(
                    31,
                    32,
                    33,
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>Pua te kōwhai\n" +
                            "<br>" +
                            "Ngawhā te kōrari\n" +
                            "<br>" +
                            "He tohu kōanga\n" +
                            "<br>" +
                            "Tau mai e Tui ki tō kāpunipuni\n" +
                            "<br>" +
                            "Honihoni, kohikohi\n" +
                            "<br>" +
                            "Hei oranga, hei rongoā\n" +
                            "<br>" +
                            "Pania te kiri ki te kōwhai kura\n" +
                            "<br>" +
                            "E Tui haurangi i te tākoha o te Atua\n" +
                            "<br>" +
                            "Rere atu, hoki mai\n" +
                            "<br>" +
                            "Parea te ua kōwhai\n" +
                            "<br>" +
                            "Ka whiti mai te rā e</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    "<html>\n" +
                            "<body>\n" +
                            "<p><span>The kowhai blooms<br> \n" +
                            "Bursts open in readiness for plucking<br> \n" +
                            "A sign of Spring<br> \n" +
                            "The Tui begin to assemble<br> \n" +
                            "To nibble to gather<br> \n" +
                            "For life and well-being<br> \n" +
                            "The stain of the kawhai touches<br> \n" +
                            "While Tui get drunk on the nectar of God<br> \n" +
                            "Flittering away, returning<br> \n" +
                            "A display of yellow<br> \n" +
                            "As the sun shines   \n" +
                            "</span></p>\n" +
                            "</body>\n" +
                            "</html>",
                    7));

            // External HTTP addresses
            externalHTTPDao.insert(new ExternalHTTP("CarvingListFragment", 0, "https://www.wintec.ac.nz/about-wintec/m%C4%81ori-and-pasifika/wintec-marae/marae-carvings"));
            externalHTTPDao.insert(new ExternalHTTP("CarvingDescriptionActivity", 1, "https://www.wintec.ac.nz/about-wintec/m%C4%81ori-and-pasifika/wintec-marae/marae-carvings/#waka-maumahara"));
            externalHTTPDao.insert(new ExternalHTTP("CarvingDescriptionActivity", 2, "https://www.wintec.ac.nz/about-wintec/m%C4%81ori-and-pasifika/wintec-marae/marae-carvings/#pou-whakarae"));
            externalHTTPDao.insert(new ExternalHTTP("CarvingDescriptionActivity", 3, "https://www.wintec.ac.nz/about-wintec/m%C4%81ori-and-pasifika/wintec-marae/marae-carvings/#pou-tūā-rangi"));
            externalHTTPDao.insert(new ExternalHTTP("CarvingDescriptionActivity", 4, "https://www.wintec.ac.nz/about-wintec/m%C4%81ori-and-pasifika/wintec-marae/marae-carvings/#pou-tūā-rongo"));
            externalHTTPDao.insert(new ExternalHTTP("CarvingDescriptionActivity", 5, "https://www.wintec.ac.nz/about-wintec/m%C4%81ori-and-pasifika/wintec-marae/marae-carvings/#tomokanga"));
            externalHTTPDao.insert(new ExternalHTTP("CarvingDescriptionActivity", 6, "https://www.wintec.ac.nz/about-wintec/m%C4%81ori-and-pasifika/wintec-marae/marae-carvings/#pare-and-whakawae"));
            externalHTTPDao.insert(new ExternalHTTP("CarvingDescriptionActivity", 7, "https://www.wintec.ac.nz/about-wintec/m%C4%81ori-and-pasifika/wintec-marae/marae-carvings/#kōrupe"));


            return null;
        }
    }
}
