Ext.define('Admin.admin.entity.view.EntityViewTab', {
    extend: 'Admin.system.base.BaseViewTab',
    xtype: 'entityViewTab',
    viewName: 'Entity',
    entity: 'entity',
    getAdditionalFilters: function () {
        return "app.id" + C.IS + '(I)' + Session.CurrentApp.id;
    },
    items: [
        {
            xtype: 'entityDetails'
        },
        {
            xtype: 'entityEditor'
        },
        {
            xtype: 'createEntitySubPanel',
            hidden: false
        }
    ]
});

