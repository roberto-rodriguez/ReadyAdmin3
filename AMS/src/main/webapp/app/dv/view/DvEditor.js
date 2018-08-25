Ext.define('Admin.dv.view.DvEditor', {
    extend: 'Admin.system.base.BaseEditor',
    alias: 'widget.dvEditor',
    initComponent: function () {
        var me = this;
        me.items = me.items.concat( {items: [{xtype: 'idField'}]} );
        this.callParent(arguments);
    }
});


