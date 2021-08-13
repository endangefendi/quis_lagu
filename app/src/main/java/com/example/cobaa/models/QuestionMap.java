package com.example.cobaa.models;

public class QuestionMap {
    public static final String SUMATERA = "SUMATERA";
    public static final String JAWA = "JAWA";
    public static final String KALIMANTAN = "KALIMANTAN";
    public static final String SULAWESI = "SULAWESI";
    public static final String MALUKU_IRIANJAYA = "MALUKU_IRIANJAYA";
    public String mQuestions[];
    public String mChoices[][];
    public String mCorrectAnswer [];
    public String mFile[];

    public QuestionMap(String daerah) {
        if (daerah.equalsIgnoreCase(SUMATERA)) {
            mQuestions = mQuestionSumatera;
            mChoices = mChoicesSumatera;
            mCorrectAnswer = mAnswerSumatera;
            mFile = mFileSumatera;
        } else if (daerah.equalsIgnoreCase(JAWA)) {
            mQuestions = mQuestionJawa;
            mChoices = mChoicesJawa;
            mCorrectAnswer = mAnswerJawa;
            mFile = mFileJawa;
        } else if (daerah.equalsIgnoreCase(KALIMANTAN)) {
            mQuestions = mQuestionKalimantan;
            mChoices = mChoicesKalimantan;
            mCorrectAnswer = mAnswerKalimantan;
            mFile = mFileKalimantan;
        } else if (daerah.equalsIgnoreCase(SULAWESI)) {
            mQuestions = mQuestionSulawesi;
            mChoices = mChoicesSulawesi;
            mCorrectAnswer = mAnswerSulawesi;
            mFile = mFileSulawesi;
        } else if (daerah.equalsIgnoreCase(MALUKU_IRIANJAYA)) {
            mQuestions = mQuestionMalukuIrianjaya;
            mChoices = mChoicesMalukuIrianjaya;
            mCorrectAnswer = mAnswerMalukuIrianjaya;
            mFile = mFileMalukuIrianjaya;
        }
    }

    //Question
    public String mQuestionSumatera[] = {
            "Lagu Butet berasal dari ?",
            "Apa nama judul lagu tersebut ?",
            "Salah satu lagu daerah dari Sumatera Barat yang diputar  adalah ?",
            "Apa nama judul lagu tersebut ?",
            "Sansaro adalah lagu daerah dari ?",
            "Apa nama judul lagu dan berasal dari mana lagunya ?",
            "Ayam Den Lapeh merupakan lagu asal daerah Sumatera bagian mana ?",
            "Judul lagu ini berasal dari ?",
            "Lagu daerah popular yang judulnya Bungong Jeumpa berasal dari ?",
            "Salah satu lagu daerah dari Jambi yang diputar adalah ?",
    };

    public String mQuestionJawa[] = {
            "Lagu Jali-Jali berasal dari ?",
            "Rek Ayo Rek berasal dari pulau jawa bagian mana ?",
            "Keroncong Kemayoran adalah lagu daerah dari ?",
            "Apa nama judul lagu tersebut ?",
            "Apa nama judul lagu yang diputar ?",
            "Lagu Bermain layang-layang merupakan asal lagu dari ?",
            "Apa nama judul lagu dan berasal dari mana lagunya ?",
            "Lagu Suwe Ora Jamu merupakan lagu berasal dari ?",
            "Salah satu lagu daerah dari Jawa Barat yang diputar adalah ?",
            "Apa nama judul lagu dan berasal dari mana lagunya ?",
    };

    public String mQuestionKalimantan[] = {
            "Berasal darimana kah lagu daerah yang dinyanyikan tersebut ?",
            "Lagu Buah Bolok berasal dari daerah apa ?",
            "Apa nama judul lagu yang diputar ?",
            "Masjid Kami merupakan lagu asal daerah Kalimantan yang letaknya di ?",
            "Berasal darimana kah lagu daerah yang dinyanyikan tersebut ?",
            "Apa nama judul lagu yang diputar ?",
            "Apa nama judul tersebut ?",
            "Cik cik periuk merupakan lagu daerah Kalimantan mana?",
            "Berasal darimana kah lagu daerah yang dinyanyikan tersebut ?",
            "Apa nama judul tersebut ?",
    };

    public String mQuestionSulawesi[] = {
            "Berasal darimana kah lagu Esa Mokan yang dinyanyikan tersebut ?",
            "Apa nama judul lagu yang diputar ?",
            "Tana Wolio merupakan lagu daerah Sulawesi mana?",
            "Lagu Tondok Kadadiangku berasal dari ?",
            "Apa nama judul tersebut ?",
            "Peia Tawa Tawa adalah lagu daerah dari ?",
            "Apa nama judul lagu dan berasal dari mana lagunya ?",
            "Anging Mamiri berasal dari pulau Sulawesi bagian mana ?",
            "Salah satu lagu daerah dari Sulawesi Utara yang diputar adalah ?",
            "Si Patokaan merupakan contoh lagu daerah provinsi ?",
    };

    public String mQuestionMalukuIrianjaya[] = {
            "Salah satu lagu daerah dari Maluku yang diputar adalah ?",
            "Lagu E Mambo Simbo berasal dari ?",
            "Berasal darimana kah lagu Una Kapita yang dinyanyikan tersebut ?",
            "Lagu daerah popular yang judulnya Rasa Sayange berasal dari ?",
            "Salah satu lagu daerah dari Maluku yang diputar adalah ?",
            "Apa nama judul lagu yang diputar ?",
            "Ayo Mama adalah lagu daerah dari ?",
            "Judul lagu ini berasal dari ?",
            "Apa nama judul lagu dan berasal dari mana lagunya ?",
            "Lagu Apuse berasal dari daerah ?",
    };


    //Choices
    public String mChoicesSumatera[][] = {
            {"Sumatera Barat", "Kepulauan Riau", "Riau", "Sumatera Utara"},
            {"Soleram", "Injit Injit Semut", "Kampuang Nan Jauh Dimato", "Sensaro"},
            {"Sansaro", "Kampuang Nan Jauh Dimato", "Segantang Lada", "Soleram"},
            {"Segantang Lada", "Soleram", "Bungong Jeumpa", "Ayam Den Lapeh"},
            {"Medan", "PekanBaru", "Padang", "Jambi"},
            {"Pepaya Pisang , Aceh", "Pepaya Pisang , Batam", "Pepaya Pisang , Padang", "Pepaya Pisang , Jambi"},
            {"Sumatera Utara", "Sumatera Barat", "Sumatera Selatan", "Kepulauan Riau"},
            {"Sumatera Utara, Anju Ahu", "Sumatera Utara , Butet", "Sumatera Barat , Butet", "Sumatera Barat, Anju Ahu"},
            {"Tanjung Pinang", "Palembang", "Medan", "Aceh"},
            {"Pepaya Pisang", "Segantang Lada", "Injit - Injit Semut", "Injit - Injit Gajah"},
    };

    public String mChoicesJawa[][] = {
            {"Jawa Timur", "Jakarta", "Bandung", "Jawa Tengah"},
            {"Jawa Barat", "Jawa Tengah", "Jawa Timur", "Banten"},
            {"Jakarta", "DI Jogjakarta", "Depok", "Banten"},
            {"Tokecang", "Jali-Jali", "Cublak cublak suweng", "Manuk Dadali"},
            {"Bermain Layang-layang", "Cublak cublak suweng", "Gundul Gundul Pacul", "Jali Jali"},
            {"Jawa Timur", "Jakarta", "Jawa Barat", "Jawa tengah"},
            {"Tokecang , Jakarta", "Tokecang , Jawa", "Keroncong Kemayoran , Jakarta", "Kerongcong Kemayoran , Jawa"},
            {"Banten", "Jakarta", "Jogjakarta", "Jawa"},
            {"Manuk Dadali", "Cing Cangkeling", "Gundul Gundul pacul", "Tokecang"},
            {"Cublak - Cublak Suweng , Jawa Tengah", "Cublak - Cublak Suweng , Jawa Timur", "Cublak - Cublak Suweng , Jawa Barat", "Cublak - Cublak Suweng , Jakarta"},
    };

    public String mChoicesKalimantan[][] = {
            {"Kalimantan Barat", "Kalimantan Utara", "Kalimantan Selatan", "Pontianak"},
            {"Kalimantan Timur", "Kalimantan Utara", "Kalimantan Selatan", "Kalimantan Barat"},
            {"Bebilin", "Ampar-Ampar Pisang", "Indung-Indung", "Masjid Kami"},
            {"Balikpapan", "Palangkaraya", "Serawak", "Pontianak"},
            {"Kalimantan Utara", "Kalimantan Barat", "Kalimantan Selatan", "Kalimantan Utara"},
            {"SapuTangan Bapuncu Ampat", "Indung Indung", "Buah Bolok", "Mamangun Mahaga"},
            {"SapuTangan Bapuncu Ampat", "Indung Indung", "Buah Bolok", "Mamangun Mahaga"},
            {"Kalimantan Barat", "Kalimantan Utara", "Serawak", "Pontianak"},
            {"Kalimantan Utara", "Balikpapan", "Kalimantan Selatan", "Pangkal Pinang"},
            {"Naik- naik puncang gunung", "Sungai Kapuas", "Ampar-Ampar Pisang", "Paris Berantai"},
    };

    public String mChoicesSulawesi[][] = {
            {"Sulawesi Tenggara", "Sulawesi Utara", "Sulawesi Selatan", "Maluku"},
            {"Ammaciang", "Ampar-Ampar Pisang", "Pakarena", "Esa Mokan"},
            {"Sulawesi Selatan", "Sulawesi Utara", "Sulawesi Tenggara", "Makassar"},
            {"Sulawesi Timur", "Kendari", "Gorontalo", "Sulawesi Selatan"},
            {"Pakarena", "Sungai Kapuas", "O Ina Ni Keke", "Ana'Kukang"},
            {"Makassar", "Sulawesi Selatan", "Sulawesi Tenggara", "Sulawesi Utara"},
            {"Pakarena , Sulawesi Selatan", "Pakarena , Sulawesi Tenggara", "Pakarena , Sulawesi Utara", "Kerongcong Kemayoran , Sulawesi Utara"},
            {"Sulawesi Barat", "Sulawesi Tengah", "Sulawesi Tenggara", "Sulawesi Selatan"},
            {"Esa Mokan", "O Ina Ni Keke", "Si Patokaan", "Tokecang"},
            {"Sulawesi Utara", "Sulawesi Selatan", "Maluku", "Sulawesi Tenggara"},
    };

    public String mChoicesMalukuIrianjaya[][] = {
            {"Una Kapita", "Ambon Manise", "Rasa Sayange", "Ayo Mama"},
            {"Papua Barat", "Maluku Utara", "Maluku", "Papua"},
            {"Maluku", "Papua Barat", "Irian Jaya", "Maluku Utara"},
            {"Raja Ampat", "Jayapura", "Maluku", "Sorong"},
            {"Ambon Manise", "Nona Manis", "Ayo Mama", "Apuse"},
            {"Naik Kepuncak Gunung", "Ayo Mama", "Gundul Gundul Pacul", "Nona Manis"},
            {"Makassar", "Papua", "Ambon", "Maluku"},
            {"Papua , Sajojo", "Maluku , Sajojo", "Maluku Utara , Sajojo", "Papua Barat , Sajojo"},
            {"Yamko Rambe Yamko , papua", "Yamko Rambe Yamko , Maluku", "Yamko Rambe Yamko , papua Barat", "Yamko Rambe Yamko , Maluku Utara"},
            {"Papua Barat", "Papua", "Gorontalo", "Maluku Utara"},
    };

    //Correct Answer
    public String mAnswerSumatera[] = {
            "Sumatera Utara",
            "Soleram",
            "Kampuang Nan Jauh Dimato",
            "Segantang Lada",
            "Padang",
            "Pepaya Pisang , Jambi",
            "Sumatera Barat",
            "Sumatera Utara, Anju Ahu",
            "Aceh",
            "Injit - Injit Semut",
    };

    public String mAnswerJawa[] = {
            "Jakarta",
            "Jawa Timur",
            "Jakarta",
            "Manuk Dadali",
            "Gundul Gundul Pacul",
            "Jawa tengah",
            "Tokecang , Jawa",
            "Jawa",
            "Cing Cangkeling",
            "Cublak - Cublak Suweng , Jawa Tengah",
    };

    public String mAnswerKalimantan[] = {
            "Kalimantan Utara",
            "Kalimantan Timur",
            "Indung-Indung",
            "Pontianak",
            "Kalimantan Barat",
            "SapuTangan Bapuncu Ampat",
            "Mamangun Mahaga",
            "Kalimantan Barat",
            "Kalimantan Selatan",
            "Ampar-Ampar Pisang",
    };

    public String mAnswerSulawesi[] = {
            "Sulawesi Utara",
            "Ammaciang",
            "Sulawesi Tenggara",
            "Sulawesi Selatan",
            "Ana'Kukang",
            "Sulawesi Tenggara",
            "Pakarena , Sulawesi Selatan",
            "Sulawesi Selatan",
            "O Ina Ni Keke",
            "Sulawesi Utara",
    };

    public String mAnswerMalukuIrianjaya[] = {
            "Ambon Manise",
            "Papua Barat",
            "Maluku Utara",
            "Maluku",
            "Nona Manis",
            "Naik Kepuncak Gunung",
            "Maluku",
            "Papua , Sajojo",
            "Yamko Rambe Yamko , papua Barat",
            "Papua Barat",
    };

    //Files
    public String mFileSumatera[] = {
            "butet",
            "soleram",
            "kampungnanjauh",
            "segantanglada",
            "sansaro",
            "pepayapisang",
            "ayamdenlapeh",
            "anjuahu",
            "bungongjeumpa",
            "injitsemut",
    };

    public String mFileJawa[] = {
            "jalijali",
            "rekayorek",
            "keroncongkemayoran",
            "manukdadali",
            "gundulpacul",
            "bermainlayang",
            "tokecang",
            "suweorajamu",
            "cingcangkeling",
            "cublaksuweng",
    };

    public String mFileKalimantan[] = {
            "amparpisang",
            "buahbolok",
            "indung",
            "masjidjami",
            "sungaikapuas",
            "saputangan",
            "mamangun",
            "cikcikperiuk",
            "parisbarantai",
            "amparpisang",
    };

    public String mFileSulawesi[] = {
            "esamokan",
            "ammaciang",
            "tanawolio",
            "tondok",
            "anakukang",
            "peiyatawa",
            "pakarena",
            "anginmamiri",
            "oinanikeke",
            "sipatokan",
    };

    public String mFileMalukuIrianjaya[] = {
            "ambonmanise",
            "emambo",
            "unakapita",
            "rasasayange",
            "nonamanis",
            "naikgunung",
            "ayomama",
            "sajojo",
            "yamko",
            "apuse",
    };

    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }

    public String getchoice1(int a) {
        String choice = mChoices[a][0];
        return choice;
    }

    public String getchoice2(int a) {
        String choice = mChoices[a][1];
        return choice;
    }

    public String getchoice3(int a) {
        String choice = mChoices[a][2];
        return choice;
    }

    public String getchoice4(int a) {
        String choice = mChoices[a][3];
        return choice;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswer[a];
        return answer;
    }

    public String getFileName(int a) {
        String answer = mFile[a];
        return answer;
    }

}
