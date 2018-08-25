Ext.define('Admin.system.base.BaseFieldContainer', {
    extend: 'Ext.form.FieldContainer',
    xtype: 'baseFieldContainer',
    defaultType: 'baseStringField',
    labelWidth: 100,
    marginRight: '30px',
    layout: 'hbox',
    width: '100%',
    style: {
        margin: '0px 0px 20px 0px'
    },
    initComponent: function () {
        var me = this;
        Ext.apply(this, {
            defaults: {
                xtype: me.defaultType,
                labelWidth: me.labelWidth, 
                style: {
                    'margin-right': me.marginRight
                } 
            }
        });
        me.callParent(arguments);
    }
});