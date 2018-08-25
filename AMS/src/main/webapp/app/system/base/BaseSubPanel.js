Ext.define('Admin.system.base.BaseSubPanel', {
    extend: 'Ext.tab.Panel',
    xtype: 'baseSubPanel',
    hidden: true,
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    width: '100%',
//    margin: '5px 0px 0px 0px', 
    listeners: {
        render: function () {
            var me = this;
            if(me.up().isShowingOnBottom())return;
            
            if (me.up().subPanelsToShow) {
                me.up().subPanelsToShow.forEach(function (subPanel) {
                    me.config.subPanels.forEach(function (item) {
                        if (item.xtype === subPanel) {
                            me.add(item);
                        }
                    });
                });
            } else {
                if (me.config.subPanels) { 
                    me.config.subPanels.forEach(function (item) {
                        me.add(item);
                    });
                }

            }

            if (me.items.items.length > 0) {
                me.setActiveTab(me.items.items[0]);
            }
        }
    }
});
