package com.example.forin.datamodel;

import com.example.forin.R;

import java.util.ArrayList;

public class FoodData {
    private static String[] titleFood = {
            "Sate Madura",
            "Rendang Minang",
            "Gado-Gado",
            "Pempek Palembang",
            "Nasi Goreng",
            "Bakso",
            "Es Buah",
            "Jus Mangga",
            "Jus Sirsak",
            "Jus Belimbing",
            "Jus Alpukat",
            "Es Teh Maniez"
    };

    private static String[] descFood = {
            "Masakan khas Indonesia dengan daging ayam premium serta rempah-rempah pilihan",
            "Masakan khas Indonesia dengan sapi berkualitas serta rempah-rempah pilihan",
            "Masakan khas Indonesia dengan sayur serta sambal kacang dan teman-temannya",
            "Masakan khas Indonesia dengan daging ikan belida serta rempah-rempah pilihan",
            "Masakan khas Indonesia dengan bumbu pilihan khas Indonesia",
            "Gumpalan daging yang kenyal dengan cita rasa yang menakjubkan bersama dengan kuah gurih",
            "Minuman dengan buah-buahan segar yang dicampur dengan susu serta sirup",
            "Minuman dengan 100% mangga yang berkualitas tinggi dan menyegarkan",
            "Minuman dengan 100% sirsak yang berkualitas tinggi dan menyegarkan",
            "Minuman dengan 100% Belimbing yang berkualitas tinggi dan menyegarkan",
            "Minuman dengan 100% alpukat yang berkualitas tinggi dan menyegarkan",
            "Minuman berbahan dasar pucuk daun teh dengan rasa yang elegan"
    };

    private static String[] priceFood = {
            "24000",
            "24000",
            "17000",
            "15000",
            "24000",
            "24000",
            "20000",
            "15000",
            "15000",
            "15000",
            "15000",
            "8000"
    };

    private static int[] foodCount = {
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0
    };

    private static int[] imgFood = {
        R.drawable.sate_madura,
            R.drawable.rendang_minang,
            R.drawable.gado_gado,
            R.drawable.pempek_palembang,
            R.drawable.nasi_goreng,
            R.drawable.bakso,
            R.drawable.es_buah,
            R.drawable.mangga,
            R.drawable.sirsak,
            R.drawable.belimbing,
            R.drawable.alpukat,
            R.drawable.es_teh
    };

    public static ArrayList<Food> getListData(){
        ArrayList<Food> list = new ArrayList<>();
        for (int position = 0; position < titleFood.length; position++) {
            Food food = new Food();
            food.setTitleFood(titleFood[position]);
            food.setDescFood(descFood[position]);
            food.setPriceFood(priceFood[position]);
            food.setImgFood(imgFood[position]);
            list.add(food);
        }
        return list;
    };
}
