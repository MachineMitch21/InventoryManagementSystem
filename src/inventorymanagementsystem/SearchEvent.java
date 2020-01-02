/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

/**
 *
 * @author mitch
 */
public class SearchEvent {
    private SearchType _searchType;
    
    public SearchEvent(SearchType type)
    {
        _searchType = type;
    }
    
    public SearchType getSearchType()
    {
        return _searchType;
    }
}
