package dev.mobile.digibanq.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by banktech on 7/29/2016.
 */
public class ExpandableListDataGoalMiscelliany {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

       /*  List<String> cricket = new ArrayList<String>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("England");
        cricket.add("South Africa"); */

        List<String> other = new ArrayList<String>();
        other.add("Rent");
        other.add("Electricity");

        List<String> CarInitialExpenses = new ArrayList<String>();
        CarInitialExpenses.add("Estimated duty");
        CarInitialExpenses.add("Estimated haulage");
        CarInitialExpenses.add("Tax and road permits");
        CarInitialExpenses.add("Others");

        //expandableListDetail.put("CRICKET TEAMS", cricket);
        expandableListDetail.put("OTHER GOALS", other);
        expandableListDetail.put("CAR INITIAL EXPENSES", CarInitialExpenses);
        return expandableListDetail;
    }
}
