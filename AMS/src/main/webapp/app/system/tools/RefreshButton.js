Ext.define('Admin.system.tools.RefreshButton', {
    extend: 'Ext.button.Button',
    xtype: 'refreshButton',
    iconCls: 'x-fa fa-refresh',
    isActive: false,
    requires: ['Ext.form.Text'],
    autoRefreshInterval: null,
    refreshingTime: null,
    currentTime: null,
    updateButtonText: function () {
        this.setText(this.currentTime);
        this.addCls('active-button')
    },
    setAutoRefresh: function ( ) {
        var me = this;

        if (!me.autoRefreshInterval) {
            me.autoRefreshInterval = setInterval(function () {
                if (me.currentTime > 0) {
                    me.updateButtonText()
                    me.currentTime--
                } else {
                    me.up().up().up().items.items[0].items.items[0].getStore().loadPage(1);
                    me.currentTime = me.refreshingTime
                }
            }, 1000);
        }

    },
    listeners: {
        click: function (cmp, e) {
            var win = Ext.create('Ext.window.Window', {
                id: 'dateRangeWindow',
                header: false,
                bodyBorder: false,
                closable: true,
                floating: true,
                width: 185,
                height: 170, 
                closable:true,
                        triggerButton: cmp,
                defaults: {
                    xtype: 'button',
                    style: {
                        margin: '5px'
                    }
                },
                items: [
                    {
                        xtype: 'textfield',
                        fieldLabel: 'Seconds:',
                        width: 160,
                        labelWidth: 80,
                        value: cmp.refreshingTime
                    },
                    { 
                        text: 'Start',
                        height: 30,
                        width: 170, 
                        listeners: {
                            click: {
                                fn: function (component, e, eOpts) {
                                    var me = this,
                                            w = me.up(),
                                            timerValue = w.items.items[0].getValue(),
                                            button = w.config.triggerButton;

                                    button.refreshingTime = button.currentTime = parseInt(timerValue)

                                    button.updateButtonText()
                                    button.setAutoRefresh()

                                    w.destroy();
                                }
                            }
                        }
                    },
                    { 
                        text: 'Stop',
                        cls: 'cash-active',
                        height: 30,
                        width: 170, 
                        listeners: {
                            click: {
                                fn: function (component, e, eOpts) {
                                    var me = this,
                                            w = me.up(),
                                            button = w.config.triggerButton;

                                    button.setText('');
                                    button.removeCls('active-button')

                                    if (button.autoRefreshInterval) {
                                        clearInterval(button.autoRefreshInterval);
                                        button.autoRefreshInterval = null;
                                    }


                                    w.destroy();
                                }
                            }
                        }
                    },
                    { 
                        text: 'Close',
                        cls: 'disabled-button',
                        height: 30,
                        width: 170, 
                        listeners: {
                            click: {
                                fn: function (component, e, eOpts) {
                                    var me = this,
                                            w = me.up();

                                    w.destroy();
                                }
                            }
                        }
                    }
                ]
            });

            win.showAt(cmp.getX(), cmp.getY() + cmp.getHeight());
        }
    }
});