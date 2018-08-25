Ext.define('Admin.system.filter.FilterString', {
    extend: 'Ext.form.Text',
    xtype: 'filterString',
    width: '100%',
    hidden: true,
    prefix: '(S)',
    style: {
        'padding': '0px',
        'margin': '0px',
        'text-align': 'center'
    },
    expression: null, //Initial value of the filter 
    constructor: function(){
        var me = this,
                expression = arguments && arguments[0] && arguments[0].expression;

        if (expression) {
            expression = expression.split('(S)')[1];

            if (expression) {
                me.value = expression;
            }
        }

        me.callParent(arguments);
    },
    listeners: {
        specialkey: function (cmp, e) {
            if (e.getKey() == e.ENTER) {
                cmp.up().up().up().getStore().loadPage(1);
            }
        }
    }
});