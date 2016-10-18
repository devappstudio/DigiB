package dev.mobile.digibanq.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

       /*  List<String> cricket = new ArrayList<String>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("England");
        cricket.add("South Africa"); */

        List<String> household = new ArrayList<String>();
        household.add("Rent");
        household.add("Electricity");
        household.add("Water");
        household.add("Gas");
        household.add("Internet");

        List<String> PersonalGrooming = new ArrayList<String>();
        PersonalGrooming.add("Hair & Nails");
        PersonalGrooming.add("Clothing");
        PersonalGrooming.add("Body Care");
        PersonalGrooming.add("Massage");
        PersonalGrooming.add("Italy");

        //expandableListDetail.put("CRICKET TEAMS", cricket);
        expandableListDetail.put("HOUSEHOLD", household);
        expandableListDetail.put("PERSONAL GROOMING", PersonalGrooming);
        return expandableListDetail;
    }
}
