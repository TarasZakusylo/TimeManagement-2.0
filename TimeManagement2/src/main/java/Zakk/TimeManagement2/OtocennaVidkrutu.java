package Zakk.TimeManagement2;
import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class OtocennaVidkrutu implements ListModel<String> {

	private ArrayList<String> data;

	public OtocennaVidkrutu() {
		data =  new ArrayList<String>() ;
	}

	@Override
	public void addListDataListener(ListDataListener arg0) {

	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {

	}

	@Override
	public String getElementAt(int index) {
		return data.get(index);
	}

	@Override
	public int getSize() {
		return data.size();
	}
	
	void addElements(String s){
		data.add(s);
	}
	void remuveElements(int index){
		data.remove(index);
	}
	void remuveElements(String obj){
		data.remove(obj);
	}

}
