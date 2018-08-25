Ext.define('Admin.system.views.ViewsContainer', {
    extend: 'Admin.system.base.BaseContainer',
    xtype: 'views',
    data: null,
    viewName: null, //Will be set by the MainController
    viewIcon: null,
    controller: 'dvController',
    listeners: {
        render: function () {
            var data = this.config.data,
                    fields = this.config.fields; 
            this.fireEvent('openView', data, fields);
        }
    },
    items: []
});


