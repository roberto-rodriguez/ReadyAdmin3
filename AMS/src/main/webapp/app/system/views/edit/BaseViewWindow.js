Ext.define('Admin.system.views.edit.BaseViewWindow', {
    extend: 'Ext.window.Window',
    xtype: 'baseViewWindow',
    width: 400,
    padding: 10,
    type: null,
    closable: true,
    floating: true,
    data: null,
    bodyItems: [], 
    layout: {
        type: 'vbox',
        align: 'left'
    },
    items: [{
            xtype: 'baseStringField',
            fieldLabel: 'View Name',
            width: 300
        }],
    initComponent: function () {
        var me = this,
                data = me.config.data;

        this.title = (data && data.id ? 'Editing ' : 'Adding ') + me.type + ' View';

        Ext.Array.each(me.bodyItems, function (item) {
            me.items.push(item);
        });

        this.bbar = [
        {
            xtype: 'checkbox',
            fieldLabel: 'Show in Menu',
            value: data.menu
        },
        '->',
        {
            text: 'Cancel',
            ui: 'soft-green',
            handler: function (cmp, b, c) {
                debugger;
            }
        },
        {
            text: 'Accept',
            ui: 'green',
            handler: this.onSave
        }
    ];

        this.callParent(arguments);
    }, 
    getData: function () {
        var data = this.config.data || {};
         
        var viewData = Ext.apply( data, {
            name: this.items.items[0].value,
            menu: this.down('toolbar').down('checkbox').value
        });
        
        delete viewData.fields

        return Ext.apply(viewData, this.getValues());
    },
    getValues: function () {
        return {}; //Override this to get the values of specific fields
    }
});
