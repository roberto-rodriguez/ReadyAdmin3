Ext.define('Admin.admin.entity.CreateEntitySubPanel', {
    extend: 'Admin.system.base.BaseSubPanel',
    xtype: 'createEntitySubPanel',
    config: {
        subPanels: [
            {
                xtype: 'createEntityFieldGridTab'
            }
        ]
    }
});


