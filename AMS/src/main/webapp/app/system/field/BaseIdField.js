Ext.define('Admin.system.field.BaseIdField', {
    extend: 'Ext.form.field.Hidden',
    xtype: 'idField',
    prefix: '(I)',
    name: 'id',
    getKeyValue: function () {
        return {
            name: this.name,
            value: this.getValue()
        };
    }
});