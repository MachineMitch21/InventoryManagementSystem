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
public class ProductSearchMonitor extends SearchMonitorThread {
    
    public ProductSearchMonitor()
    {
        super._searchType = SearchType.PRODUCT;
    }
}
