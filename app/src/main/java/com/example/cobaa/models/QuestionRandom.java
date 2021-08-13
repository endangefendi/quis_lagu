package com.example.cobaa.models;

public class QuestionRandom {

    public String mQuestions[] = {
            "Lagu Ampar-Ampar Pisang ini berasal dari ?",
            "Lagu Ismail Marzuki tersebut berjudul ?",
            "Lagu Kambanglah Bungo berasal dari ?",
            "Lagu Bungong Jeumpa berasal dari ?",
            "Lagu Soleram berasal dari ?",
            "Lagu Ambon Manise berasal dari ?",
            "Lagu Anju ahu berasal dari ?",
            "Lagu Ayam den lapeh berasal dari ?",
            "Lagu Anak kambing saya berasal dari ?",
            "Lagu Cublak-cublak suweng berasal dari ?",
            "Lagu Injit-injit semut berasal dari ?",
            "Lagu Naik-naik ke puncak gunung berasal dari ?",
            "Lagu Gundul pacul berasal dari ?",
            "Lagu Si Patokaan berasal dari ?",
            "Lagu Tokecang berasal dari ?",
            "Lagu Yamko Rame Yamko berasal dari ?",
            "Lagu Jali-jali berasal dari ?",
            "Lagu Manuk Dadali berasal dari ?",
            "Lagu Angin Mamiri berasal dari ?",
            "Lagu Cing Cangkeling berasal ?",
    };

    private String mChoices[][] = {
            {"Kalimantan Selatan", "Jakarta", "Sumatera Barat", "Aceh", "Kepulauan Riau"},
            {"Soleram", "Halo Bandoeng", "Kembanglah Bungo", "Apuse", "Naik-Naik ke Puncak Gunung"},
            {"Jawa Timur", "Sumatera Utara", "Sumatera Barat", "Riau", "Kepulauan Riau"},
            {"Jakarta", "Kalimantan Selatan", "Medan", "Aceh", "Papua"},
            {"Papua", "Jawa barat", "Riau", "Balikpapan", "Bandung"},
            {"Papua", "Maluku", "Riau", "Balikpapan", "Bandung"},
            {"Jawa Timur", "Sumatera Utara", "Sumatera Barat", "Riau", "Kepulauan Riau"},
            {"Jawa tengah", "Sumatera Utara", "Sumatera Barat", "Riau", "Kepulauan Riau"},
            {"Jakarta", "Kalimantan Selatan", "Medan", "NTT", "Papua"},
            {"Papua", "Jawa Timur", "Riau", "Jawa Barat", "Jawa Tengah"},
            {"Jambi", "Maluku", "Riau", "Kalimantan selatan", "Bandung"},
            {"Jambi", "Maluku", "Riau", "Pontianak", "Bandung"},
            {"Pontianak", "Jawa Tengah", "Riau", "Balikpapan", "Yogyakarta"},
            {"Jawa Tengah", "Maluku", "Sulawesi Utara", "Balikpapan", "Batam"},
            {"Sulawesi Utara", "Balikpapan", "Jawa Barat", "Medan", "Bandung"},
            {"Papua", "Maluku", "Riau", "Batam", "Jakarta"},
            {"Bali", "Pontianak", "Padang", "Jakarta", "Papua"},
            {"Tanjung Pinang", "Pontianak", "Jakarta", "Jawa Barat", "Makassar"},
            {"Batam", "Tanjung Pinang", "Padang", "Makassar", "Jakarta"},
            {"Bali", "Jawa Barat", "Padang", "Medan", "Makassar"},
    };

    private String mCorrectAnswer[] = {
            "Kalimantan Selatan",
            "Halo Bandoeng",
            "Sumatera Barat",
            "Aceh",
            "Riau",
            "Maluku",
            "Sumatera Utara",
            "Sumatera Barat",
            "NTT",
            "Jawa Timur",
            "Jambi",
            "Maluku",
            "Jawa Tengah",
            "Sulawesi Utara",
            "Jawa Barat",
            "Papua",
            "Jakarta",
            "Jawa Barat",
            "Makassar",
            "Jawa Barat"
    };

    private String mFile[] = {
            "amparpisang",
            "halobandung",
            "kambanglahbungo",
            "bungongjeumpa",
            "soleram",
            "ambonmanise",
            "anjuahu",
            "ayamdenlapeh",
            "anakkambing",
            "cublaksuweng",
            "injitsemut",
            "naikgunung",
            "gundulpacul",
            "sipatokaan",
            "tokecang",
            "yamkorame",
            "jalijali",
            "manukdadali",
            "anginmamiri",
            "cingcangkeling"
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
