Ext.define('Admin.system.filter.FilterBoolean', {
    extend: 'Ext.panel.Panel',
    xtype: 'filterBoolean',
    width: '100%',
    hidden: true,
    prefix: '(B)',
    value: null,
    style: {
        'padding': '0px',
        'margin': '0px',
        'text-align': 'center'
    },
    constructor: function () {
        var me = this,
                expression = arguments && arguments[0] && arguments[0].expression;

        if (expression) {
            expression = expression.split('(B)')[1];

            if (expression) {
                me.value = expression;
            }
        }

        me.callParent(arguments);
    },
    items: [
        {
            text: 'All',
            value: 0
        },
        {
            text: 'Yes',
            value: 1,
            cls: 'disabled-button'
        },
        {
            text: 'No',
            value: 2,
            cls: 'disabled-button'
        },
    ],
    defaults: {
        xtype: 'button',
        width: '33%',
        listeners: {
            render: function () {
                var me = this,
                        parent = me.up(),
                        parentValue = parent.value;

                if (parentValue
                        && ((parentValue == 'true' && me.value == 1) || (parentValue == 'false' && me.value == 2))) {
                    parent.refreshValues(this);
                }
            },
            click: function (cmp, b, c) {
                cmp.up().refreshValues(cmp);
            }
        }
    },
    getValue: function () {
        return this.value && (this.value == 1) + '';
    },
    refreshValues: function (cmp) {
        Ext.each(cmp.up().items.items, function (button) {
            button.addCls('disabled-button');
        });
        cmp.removeCls('disabled-button');
        cmp.up().value = cmp.value;
        cmp.up('dvGrid').getStore().loadPage(1)
    }
});