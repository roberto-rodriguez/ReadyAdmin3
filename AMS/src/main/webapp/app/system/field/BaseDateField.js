Ext.define('Admin.system.field.BaseDateField', {
    extend: 'Ext.form.DateField',
    xtype: 'baseDateField',
    dateFormat: 'm/d/Y',
    prefix: '(D)',
    init: function (data) {
        var me = this;

        if (data && data[me.name]) {
            var date = new Date(parseInt(data[me.name]));
            me.setValue(date);
        }
    },
    getVal: function () {
        return this.getValue() && this.getValue().getTime();
    },
    getKeyValue: function () {
        return {
            name: this.name,
            value: this.getVal()
        };
    }
});
 