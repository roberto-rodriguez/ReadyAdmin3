Ext.define('Admin.dv.DvContainer', {
    extend: 'Admin.system.base.BaseContainer',
    xtype: 'dv',
    viewName: null, //Will be set by the MainController
    viewIcon: null,
    controller: 'dvController',
    initComponent: function () {
        this.items = [{
                xtype: 'dvGridTab',
                title: this.viewName,
                entity: this.entity,
                idDv: this.idDv,
                fields: this.fields,
                iconCls: this.viewIcon
            }];

        this.callParent(arguments);
    },
    items: []
});


