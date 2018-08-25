Ext.define('Admin.system.base.BaseDetails', {
    extend: 'Ext.panel.Panel',
    xtype: 'baseDetails', 
    layout: 'hbox', 
    height: 300,
    width:"100%", 
    cls: 'base-details',
    border:'1px solid #d0d0d0',
    style: { 
        'padding': '10px' 
    },
    setData: function(data){
        this.items && this.items.items[0].setData(data); 
    } 
});
