Ext.define('Admin.system.base.BaseTab', {
    extend: 'Ext.panel.Panel',
    xtype: 'baseTab',
    showingOnTop: true,
    filters: null, // This is dynamic (change for subTabs when click in the super grid)
    staticFilters: null, // This is static, never changes, and if exists, it is taken instead of 'filters' 
    filterPrefix: null, //just use in case it be different of (L)
    entity: null, // The entity of the tab
    //@Deprecated
    // previousTabSuperEntity:null,  //The selected row data on the super grid in the prevoius Tab
    entityId: null,
    originCmpId: null, //id of the component that originated me, this is for refresh that component after edit here
    layout: 'vbox',
    data: null,
    superData: null, //If this is a GridTab, superData will be the data of the selected Row in the super Grid
    //If is a view Tab, superData will be the selectedRow on the super grid of the prevoius tab
    viewName: null, //to display in the title
    staticTabTitle: null, //use if want to show always the same title
    propertyTitleName: null, //Name of the property in the data that gives name to the Tab (by default 'name')
    propertyFilterValue: null, //Property in the selected row (in the super grid) that will be used as value in 'getFilters'
    staticPropertyFilterValue: null, //Property in the selected row (in the current grid) that will be used as value in 'getFilters'
    subPanelsToShow: null, //if you want to display just certain subPanels, specify here as an array
    style: {
        borderColor: '#808080',
        borderStyle: 'solid',
        borderWidth: '1px'
    },
//    autoScroll: true,
//    overflowY: 'scroll',
    constructor: function (config) {
        var me = this;

        if (!me.isShowingOnBottom()) {
            this.autoScroll = true;
            this.overflowY = 'scroll';
        }

        me.callParent(arguments);
    },
    listeners: {
        render: function () {
            var me = this;
            if (me.setTabTitle) {
                me.setTabTitle();
            }

            if (me.isShowingOnBottom()) { // When !isShowingOnBottom(), show a small table
                if ((me.items.items[0] instanceof Admin.system.base.BasePaginatedGrid)) {
                    me.items.items[0].setHeight(250);
                    if (me.items.items[0].getStore()) {
                        me.items.items[0].getStore().pageSize = 5;
                    }
                }

                me.addCls('no-border');
            }
        },
        activate: function (newTab, Cmp) {

            var me = this,
                    subPanel = me.down('baseSubPanel');

            if (newTab.isShowingOnBottom()
                    && (newTab.items.items[0] instanceof Admin.system.base.BasePaginatedGrid)) {

                newTab.filters = me.up().up().filters;
                newTab.entityId = me.up().up().entityId;
                newTab.items.items[0].getStore().loadPage(1);
            } else {
                if (subPanel && !subPanel.isHidden()) {
                    var activeSubTab = subPanel.getActiveTab();

                    if (activeSubTab && activeSubTab instanceof Admin.system.base.BaseGridTab) {
                        activeSubTab.items.items[0].getStore().loadPage(1);
                    }
                }
            }
        }
    },
    getFilters: function (giveWhenIsOnTop) {
        var me = this,
                tab = this,
                result = '';

        if (giveWhenIsOnTop || tab.isShowingOnBottom()) {
            if (tab.isShowingOnBottom()) {
                tab = tab.up().up();
            }

            var filterValue = tab.entityId;

            if (me.propertyFilterValue && tab.superData) {
                filterValue = tab.superData[me.propertyFilterValue];
            }

            if ((me.staticPropertyFilterValue || tab.staticPropertyFilterValue)) { //Era me.data  
                if (tab.superData) {
                    filterValue = tab.superData[me.staticPropertyFilterValue || tab.staticPropertyFilterValue];
                } else {
                    filterValue = -1;  //If get;s here, is because is adding new entity
                }

            }

            result = (me.staticFilters || tab.staticFilters || me.filters) + C.IS + (me.filterPrefix || '(I)') + filterValue;
        }
        
        var aditionalFilters = this.getAdditionalFilters();
        
        if(aditionalFilters){
            if(result){
                result += C.AND;
            }
            
            result += aditionalFilters;
        }
        
        return result;
    },
    isShowingOnBottom: function () {
        return this.up() instanceof Admin.system.base.BaseSubPanel;
    },
    getAdditionalFilters: function () {
        return "";
    } //Override if need to add additional filters
});
