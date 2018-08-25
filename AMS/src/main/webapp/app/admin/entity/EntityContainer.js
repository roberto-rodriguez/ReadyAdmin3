Ext.define('Admin.admin.entity.EntityContainer', {
    extend: 'Admin.system.base.BaseContainer', 
    xtype: 'entity',  
    controller: 'entityController',
    items: [
        {
          xtype:'entityGridTab' 
        } 
    ]
});



