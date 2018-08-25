Ext.define('Admin.system.base.BaseViewTab', {
    extend: 'Admin.system.base.BaseTab',
    xtype: 'baseViewTab',
    cls: 'shadow',
    title: '',
    view: '',
    superEntityId: null, //entityId if the super Tab of the view that oppened me 
    additionalValues: null, //additional values to send when submit the form,
    //this will be an array of objs with the format:
    //[{propertyName:'property_name', propertyValue:'name_of_the_value_parameter'}]
    loadFilter: null, //Use this when loading single views (Ej: Distributor, Merchant, Partner)
    loadFilterValue: null, //This is the property in Principal that will determine the value.
    viewData: null, //Data given by BaseController in the init function (This is required in some cases like editing tx)
    tbar: [
        '->',
        {
            xtype: 'editButton',
            handler: function () {
                var me = this;
                me.hide();
                me.up().up().editMode();
            }
        }
    ],
    listeners: {
        render: function () {
            var me = this,
                    container = me.up();

            if (container.items.items.length === 1) { //If get's here is because Is a single view
                
                Request.load({
                    url: container.xtype + '/' + me.entity + '/load',
                    params: 'params=' + me.loadFilter + C.IS(I) + Session.Principal[ me.loadFilterValue ],
                    success: function (response) {
                        var data = response.data;
                        me.superData = data;
                        me.config.data = data;//This will be used in setTabTitle
                        me.setTabTitle();
                        me.entityId = data.id;
                        me.init(data);
                    }
                });
            }
        }
    },
    init: function (data) {
        this.viewData = data;

        var me = this,
                baseEditor = me.down('baseEditor'),
                baseDetails = me.down('baseDetails'),
                subPanel = me.down('baseSubPanel');

        if (baseEditor) {
            baseEditor.init(data);
        }

        if (baseDetails) {
            baseDetails.setData(data);            
        }

        if (data && data.id) {
            if (subPanel) {
                me.down('baseSubPanel').show();
            }
        } else {
            if (baseEditor && baseDetails) {
                me.editMode();
            }
        }


    },
    editMode: function () {
        var me = this,
                baseEditor = me.down('baseEditor'),
                baseDetails = me.down('baseDetails');
        me.down('editButton').hide();
        baseDetails.hide();
        baseEditor.show();
    },
    setTabTitle: function (newData) {   //this function should be overriden when required
        var me = this,
                data = newData || me.config.data,
                title = '';

        if (me.staticTabTitle) {
            title = me.staticTabTitle;
        } else {
            if (data && data.id) {
                var titleName = data[ me.propertyTitleName || 'name']; //if data has not name, then specify 'propertyTitleName'

                title = me.viewName;

                if (titleName) {
                    title += ': ' + titleName;
                }
            } else {
                title = 'New ' + me.viewName;
            }
        }
        me.setTitle(title);
    }
});
