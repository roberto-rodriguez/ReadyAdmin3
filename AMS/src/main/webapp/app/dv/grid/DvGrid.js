Ext.define('Admin.dv.grid.DvGrid', {
    extend: 'Admin.system.base.BasePaginatedGrid',
    xtype: 'dvGrid',
    forceFit: true, 
    initComponent: function () {
        var me = this,
                items = [{
                        xtype: 'idGridColumn'
                    }];

        Ext.each(me.fields, function (config) {
            var field = {
                text: config.name,
                dataIndex: config.idx,
                filterType: 'filter' + config.type,
                flex: 1
            }

            switch (config.type) {
                case 'Boolean':
                case 'Double':
                case 'Date':
                    field.xtype = config.type.toLowerCase() + 'GridColumn';
                    break;
            }

            items.push(field);
        });

        this.columns.items = items;
        this.callParent(arguments);
    },
    listeners: {
        render: function (cmp, b, c) {
            if(cmp.up('tableView')){
               cmp.getStore().loadPage(1); 
            } 
        }
    },
    columns: {
        defaults: {
            align: 'center',
            flex: 1
        },
        items: []
    }
});
