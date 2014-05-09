package com.sampas.socbs.core.network.impl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SmpSwingUtil {
  
  public static ListModel toListModel(final List elements) {
    return(
      new AbstractListModel() {
        public int getSize() {
          return(elements.size());
        }

        public Object getElementAt(int index) {
          return(elements.get(index));
        }
      }
    );
  }
  
  public static ListModel toListModel(Collection elements) {
    return(toListModel(new ArrayList(elements)));  
  }
  
  public static List toList(ListModel model) {
    ArrayList list = new ArrayList(model.getSize());
    for (int i = 0; i < model.getSize(); i++) {
      list.add(model.getElementAt(i));  
    }
    
    return(list);
  }
  
  public static void setSelection(JList list, Object element) {
    for (int i = 0; i < list.getModel().getSize(); i++) {
      Object value = (Object)list.getModel().getElementAt(i);
      if (value == element) {
        list.setSelectedIndex(i);
        list.scrollRectToVisible(
          list.getCellBounds(i,i)  
        ); 
        return;
      }
    }
  }
  
  public static void addDoubleClickEvent(JList list) {
    list.addMouseListener(
      new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          JList source = (JList)e.getSource();
          if (e.getClickCount() == 2) {
            ListSelectionListener[] listeners = source.getListSelectionListeners();
            for (int i = 0; i < listeners.length; i++) {
              listeners[i].valueChanged(
                new ListSelectionEvent(
                  source, source.getSelectedIndex(),
                  source.getSelectedIndex(), false
                )
              );
            }   
          }
        }
      }  
    );
  }
}