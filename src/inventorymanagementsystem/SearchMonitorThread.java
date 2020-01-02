/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author mitch
 */
public class SearchMonitorThread extends Thread {
    
    private Set<SearchListener> _searchListeners = new HashSet<SearchListener>();
    
    private double _timeAtLastKey = 0.0;
    private double _delayTime     = 650;

    Control _searchSource = null;
    
    protected SearchType  _searchType;
    private boolean     _isSearching = false;

    private void fireSearchEvent() {
        for (SearchListener listener : _searchListeners)
        {
            SearchEvent event = new SearchEvent(_searchType);
            listener.searchInitiated(event);
            _isSearching = false;
        }
    }

    @Override
    public synchronized void run() {
        
        while (true)
        {
            double timeSinceLastKey = System.currentTimeMillis() - _timeAtLastKey;
            
            if ((timeSinceLastKey > _delayTime) && _isSearching)
            {
                fireSearchEvent();
            }
        }    
    }
    
    public void addListener(SearchListener listener)
    {
        _searchListeners.add(listener);
    }
    
    public void removeListener(SearchListener listener)
    {
        _searchListeners.remove(listener);
    }

    public void setSearchSource(Control source) {
        if (_searchSource != null) {
            _searchSource.setOnKeyReleased(null);
        }
        _searchSource = source;
        _searchSource.setOnKeyReleased((KeyEvent e) -> {
            SearchMonitorThread smt = InventoryManagementSystem.getPartSearchMonitor();
            _isSearching = true;
            _timeAtLastKey = System.currentTimeMillis();
        });
    }
}
