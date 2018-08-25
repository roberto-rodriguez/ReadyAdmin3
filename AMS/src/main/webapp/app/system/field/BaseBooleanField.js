Ext.define('Admin.system.field.BaseBooleanField', {
    extend: 'Ext.form.field.Checkbox',
    xtype: 'baseBooleanField',
    width: "100%",
    prefix: '(B)',
    getVal: function () {
        return (this.getValue() === true) + "";
    },
    getKeyValue: function () {
        return {
            name: this.name,
            value: this.getVal()
        };
    } 
});