Ext.define('Admin.dv.view.DvViewTab', {
    extend: 'Admin.system.base.BaseViewTab',
    xtype: 'dvViewTab',
    viewName: null,
    fields: null,
    entity: null,
    initComponent: function () {
        var me = this,
                width = this.up().getSize().width;

        var displayFields = Ext.Array.filter(this.fields, function (field) {
            return !field.readonly;
        })

        var trs = Util.getTrFields(width, displayFields);
        var rows = [];
        Ext.each(trs, function (tr) {
            var row = [];
            Ext.each(tr, function (field) {
                row.push({
                    fieldLabel: field.name,
                    name: field.idx,
                    xtype: 'base' + field.type + 'Field',
                    type: field.type
                });
            });
            rows.push({items: row});
        });


        me.items = [{
                xtype: 'dvDetails',
                fields: rows
            }, {
                xtype: 'dvEditor',
                items: rows
            }];
        this.callParent(arguments);
    }
});

