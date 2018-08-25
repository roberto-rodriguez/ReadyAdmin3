Ext.define('Admin.system.base.BaseContainer', {
    extend: 'Ext.tab.Panel',
    xtype: 'baseContainer', 
    controller: 'baseController',
    cls: 'base-container', 
    bodyStyle: {
        'background-color': 'white' 
    },
    layout: {
        type: 'vbox',
        align: 'stretch'
    } 
});
