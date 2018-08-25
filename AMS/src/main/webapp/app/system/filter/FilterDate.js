Ext.define('Admin.system.filter.FilterDate', {
    extend: 'Ext.panel.Panel',
    xtype: 'filterDate',
    width: '100%',
    hidden: true,
    prefix: '(D)',
    startDate: null,
    endDate: null,
    style: {
        'padding': '0px',
        'margin': '0px',
        'border-width': '1px',
        'border-style': 'solid',
        'border-color': '#d0d0d0',
        'height': '100%'
    },
    layout: 'hbox',
    constructor: function () {
        var me = this,
                expression = arguments && arguments[0] && arguments[0].expression;

        if (expression) {
            expression = expression.split('(D)')[1];

            var dates = expression && expression.split(',');

            if (dates[0]) {
                me.startDate = parseInt(dates[0]);
            }

            if (dates[1]) {
                me.endDate = parseInt(dates[1]);
            }
        }

        me.callParent(arguments);
    },
    getValue: function () {
        var me = this,
                startDate = me.startDate,
                endDate = me.endDate;
        if (startDate || endDate) {
            return startDate + ',' + endDate;
        }
    },
    items: [
        {
            xtype: 'button',
            width: '100%',
            cls: 'disabled-button',
            text: 'Select Dates',
            listeners: {
                render: function () {
                    var me = this,
                            parent = me.up();

                    if (parent.startDate || parent.endDate) {
                        parent.refreshValues(this, parent.startDate, parent.endDate);
                    }
                },
                click: function (cmp, e) {
                    var filterCmp = cmp.up();
                    var win = Ext.create('Ext.window.Window', {
                        id: 'dateRangeWindow',
                        header: false,
                        bodyBorder: false,
                        closable: true,
                        floating: true,
                        width: 200,
                        height: 172,
                        triggerButton: cmp,
                        defaults: {
                            xtype: 'datefield',
                            style: {
                                margin: '10px'
                            },
                            listeners: {
                                change: {
                                    fn: function (component, e, eOpts) {
                                        var me = this,
                                                w = me.up(),
                                                startDateValue = w.items.items[0].getValue(),
                                                endDateValue = w.items.items[1].getValue(),
                                                startDate = startDateValue && startDateValue.getTime(),
                                                endDate = endDateValue && endDateValue.getTime(),
                                                button = w.config.triggerButton;

                                        button.up().refreshValues(button, startDate, endDate);

                                    }
                                }
                            }
                        },
                        items: [
                            {
                                value: filterCmp.startDate && new Date(filterCmp.startDate)
                            },
                            {
                                value: filterCmp.endDate && new Date(filterCmp.endDate)
                            },
                            {
                                xtype: 'button',
                                text: 'Search',
                                height: 30,
                                width: 170,
                                style: {
                                    margin: '0px 10px 0px 10px'
                                },
                                listeners: {
                                    click: {
                                        fn: function (component, e, eOpts) {
                                            this.up().config.triggerButton.up().up().up().up().getStore().loadPage(1);
                                            this.up().destroy();
                                        }
                                    }
                                }
                            },
                            {
                                xtype: 'button',
                                text: 'Cancel',
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
    refreshValues: function (button, startDate, endDate) {
        var text = '';

        if (startDate || endDate) {
            button.removeCls('disabled-button');

            var formattedStartDate = startDate && Util.formatDate(startDate, 'm/d/y'),
                    formattedEndDate = endDate && Util.formatDate(endDate, 'm/d/y');

            if (formattedStartDate && formattedEndDate) {
                text = formattedStartDate + ' - ' + formattedEndDate;
            } else {
                if (formattedStartDate) {
                    text = ' > ' + formattedStartDate;
                } else {
                    text = ' < ' + formattedEndDate;
                }
            }

        } else {
            button.addCls('disabled-button');
        }

        button.setText(text);

        if (startDate) {
            button.up().startDate = startDate;
        } else {
            button.up().startDate = null;
        }

        if (endDate) {
            button.up().endDate = endDate;
        } else {
            button.up().endDate = null;
        }
    }
});