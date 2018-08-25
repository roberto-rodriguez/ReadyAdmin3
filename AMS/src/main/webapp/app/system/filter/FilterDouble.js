Ext.define('Admin.system.filter.FilterDouble', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.filterDouble',
    xtype: 'filterDouble',
    width: '100%',
    hidden: true,
    prefix: '(d)', //Override this if needed
    operator: null,
    value: null,
    expression: null, //Initial value of the filter 
    style: {
        'padding': '0px',
        'margin': '0px',
        'border-width': '1px',
        'border-style': 'solid',
        'border-color': '#d0d0d0',
        'height': '100%'
    },
    layout: 'hbox',
    constructor: function (config) {
        var me = this,
                expression = arguments && arguments[0] && arguments[0].expression;

        if (expression) {
            var start = expression.indexOf('[') + 1,
                    end = expression.indexOf(']');

            me.operator = expression.substring(start, end);
            me.value = expression.substring(end + 1);
        }

        me.callParent(arguments);
    },
    getValue: function () {
        var me = this,
                operator = me.operator,
                value = me.value;
        if (operator && value) {
            return '[' + operator + ']' + value;
        }
    },
    defaults: {
        width: '50%'
    },
    items: [
        {
            xtype: 'button',
            width: '100%',
            cls: 'disabled-button',
            text: 'Select',
            listeners: {
                render: function () {
                    var me = this,
                            parent = me.up();

                    if (parent.operator && parent.value) {
                        parent.refreshValues(this, parent.operator, parent.value);
                    }
                },
                click: function (cmp, e) {
                    var filterCmp = cmp.up();
                    var win = Ext.create('Ext.window.Window', {
                        id: filterCmp.id + 'dateRangeWindow',
                        header: false,
                        bodyBorder: false,
                        closable: true,
                        floating: true,
                        width: 200,
                        height: 130,
                        triggerButton: cmp,
                        defaults: {
                            style: {
                                margin: '10px'
                            }
                        },
                        items: [
                            {
                                layout: 'hbox',
                                defaults: {
                                    width: '48%'
                                },
                                items: [
                                    {
                                        xtype: 'combobox',
                                        valueField: 'id',
                                        displayField: 'name',
                                        value: filterCmp.operator,
                                        store: Ext.create('Ext.data.Store', {
                                            fields: ['id', 'name'],
                                            data: [
                                                {id: '', name: ""},
                                                {id: 'eq', name: " = "},
                                                {id: 'ne', name: " != "},
                                                {id: 'gt', name: " > "},
                                                {id: 'lt', name: " < "},
                                                {id: 'ge', name: " >= "},
                                                {id: 'le', name: " <= "},
                                            ]
                                        }),
                                    },
                                    {
                                        xtype: 'textfield',
                                        value: filterCmp.value
                                    }
                                ]
                            },
                            {
                                xtype: 'button',
                                text: 'Buscar',
                                height: 30,
                                width: 170,
                                style: {
                                    margin: '0px 10px 0px 10px'
                                },
                                listeners: {
                                    click: {
                                        fn: function (component, e, eOpts) {
                                            var me = this,
                                                    w = me.up(),
                                                    fields = w.items.items[0].items.items,
                                                    operatorField = fields[0],
                                                    valueField = fields[1],
                                                    operator = operatorField.getValue(),
                                                    value = valueField.getValue(),
                                                    button = w.config.triggerButton;

                                            button.up().refreshValues(button, operator, value, operatorField);

                                            button.up().up().up().up().getStore().loadPage(1);
                                            w.destroy();
                                        }
                                    }
                                }
                            },
                            {
                                xtype: 'button',
                                text: 'Cancelar',
                                cls: 'disabled-button',
                                height: 30,
                                width: 170,
                                style: {
                                    margin: '0px 10px 0px 10px'
                                },
                                listeners: {
                                    click: {
                                        fn: function (component, e, eOpts) {
                                            this.up().destroy();
                                        }
                                    }
                                }
                            }
                        ]
                    });

                    win.showAt(cmp.getX(), cmp.getY() + cmp.getHeight());
                }
            }
        }

    ],
    refreshValues: function (button, operator, value, operatorField) {
        if (operator && value) {
            var op = operatorField ? operatorField.getDisplayValue() : this.opValues[operator];

            button.removeCls('disabled-button');
            button.setText(op + ' ' + value);
            button.up().operator = operator;
            button.up().value = value;
        } else {
            button.up().operator = '';
            button.up().value = '';
            button.setText('');
            button.addCls('disabled-button');
        }
    },
    opValues: {
        eq: " = ",
        ne: " != ",
        gt: " > ",
        lt: " < ",
        ge: " >= ",
        le: " <= ",
    }
});