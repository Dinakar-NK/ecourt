package DropDown_Values;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ComboModel {


    //Creates a Drop Down model for the Cities
    public DefaultComboBoxModel city_model(){
        ArrayList<String> city;
        city=new DropValues().cities();
        DefaultComboBoxModel city_mode=new DefaultComboBoxModel();
        Iterator it_city=city.iterator();
        while(it_city.hasNext()){
            city_mode.addElement(it_city.next());
        }
        return city_mode;
    }

    //Creates a Drop Down model for the Types of Lawyers
    public DefaultComboBoxModel type_model(){
        ArrayList<String> type;
        type=new DropValues().types();
        DefaultComboBoxModel type_mode=new DefaultComboBoxModel();
        Iterator it_type=type.iterator();
        while (it_type.hasNext()){
            type_mode.addElement(it_type.next());
        }
        return type_mode;
    }

    public DefaultComboBoxModel case_type_model(){
        ArrayList<String> type;
        type=new DropValues().case_types();
        DefaultComboBoxModel type_mode=new DefaultComboBoxModel();
        Iterator it_type=type.iterator();
        while (it_type.hasNext()){
            type_mode.addElement(it_type.next());
        }
        return type_mode;
    }


}
