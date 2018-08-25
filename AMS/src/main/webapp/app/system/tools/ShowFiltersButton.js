Ext.define('Admin.system.tools.ShowFiltersButton', {
    extend: 'Ext.button.Button',
    xtype: 'showFilters',
    iconCls: 'x-fa fa-filter',
    tooltip: 'Filter',
    listeners: {
        click: function (element) {
            var grid = element.up().up().items.items[0];

            grid.columns.forEach(function (column) {
                var filterComponent = column.items.items[0];
                if (filterComponent.isVisible()) {
                    filterComponent.hide();
                } else {
                    filterComponent.show();
                }
            });
        }
    }
});