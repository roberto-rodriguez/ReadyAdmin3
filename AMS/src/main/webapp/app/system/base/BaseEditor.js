Ext.define('Admin.system.base.BaseEditor', {
    extend: 'Ext.form.Panel',
    xtype: 'baseEditor',
    defaultType: 'baseStringField',
    bodyPadding: '10 25',
    width: "100%",
    height: 300,
    layout: 'vbox',
    hidden: true, //Editors will be always hidden when the ViewTab starts
    border: '1px solid #32404e',
    data: null, //data received from upstream
    cls: 'base-editor',
    style: {
        'padding': '10px'
    },
    defaults: {
        xtype: 'baseFieldContainer'
    },
    bbar: [
        '->',
        {
            text: 'Cancel',
            ui: 'soft-green',
            handler: function () {
                var me = this,
                        viewTab = me.up().up().up(),
                        items = viewTab.items.items;
                //viewTab.close();
                viewTab.down('editButton').show();
                items[0].show();
                items[1].hide();
                if (items[2] && items[2] instanceof Admin.system.base.BaseSubPanel) {
                    if (items[2].down('baseEditor') && items[2].down('baseDetails')) {
                        items[2].down('editButton').show();
                        items[2].down('baseEditor').hide();
                        items[2].down('baseDetails').show();
                    }
                }
            }
        },
        {
            text: 'Accept',
            ui: 'green',
            //TODO Make a Controller for the editor and put this logic and the validation in the Controller
            handler: function () {
                var me = this,
                        editor = me.up().up(),
                        viewTab = editor.up(),
                        viewData = viewTab.viewData,
                        values = editor.getValues(),
                        url = viewTab.idDv + '/' + viewTab.entity + '/save';
//                if (!editor.isValid()) {
//                    Ext.Msg.alert('Information', "You must fill out all the required fields correctly");
//
//                } else { 

                Request.load({
                    url: url,
                    method: 'POST',
                    mask: 'Saving...',
                    jsonData: values,
//                    jsonData: formData.typedValues,
                    ignoreError: true,
                    success: function (response) {
                        if (response.status === 500) {//en caso de lanzar una exepcion
                            Ext.Msg.alert('Information', response.statusMessage);
                        } else {
                            var items = viewTab.items.items,
                                    responseData = response.data;

                            var data = Ext.applyIf(values, viewData);
                            data = Ext.apply(data, responseData);

                            viewTab.entityId = id;
  
                            var idField = editor.getForm().findField('id');

                            if (idField) {
                                idField.setValue(id);
                            }

                            viewTab.setTabTitle(data);

                            items[0].setData(data); //Load details
                            viewTab.down('editButton').show();
                            items[0].show();
                            items[1].hide();

                            var subPanel = items[2];

                            if (subPanel && subPanel instanceof Admin.system.base.BaseSubPanel) {
                                subPanel.show();

                                var activeSubTab = subPanel.getActiveTab();

                                if (activeSubTab) {
                                    var activeTabElement = activeSubTab.items.items[0];

                                    if (activeTabElement instanceof Admin.system.base.BasePaginatedGrid) {
                                        activeTabElement.getStore().loadPage(1);
                                    }
                                }
                            }

                            var originCmpId = viewTab.originCmpId;

                            if (originCmpId) {
                                var originCmp = Ext.getCmp(originCmpId);
                                if (originCmp && originCmp.getStore()) {
                                    originCmp.getStore().load();
                                }
                            }
                        }
                    }
                });
//                }
            }
        },
        '           '
    ],
    init: function (data) {
        var me = this,
                data = data || {};

        this.data = data; //This is NEW (Sometimes initData needs the data)

        Ext.apply(data, me.initData());


        me.getForm().setValues(data);

        Ext.each(me.getForm().getFields().items, function (field) {
            if (field.init) {
                field.init(data);
            }
        });
    },
    getValues: function () {
        var me = this,
                values = me.getForm().getValues();

        Ext.each(me.getForm().getFields().items, function (field) {
            var name = field.name;

            if (field.getVal) {
                values[name] = field.getVal()
            }

            if (field instanceof Admin.system.field.BaseSelectField) {
                var displayValueName = name;
                if (displayValueName.indexOf('Id') > -1) {
                    displayValueName = displayValueName.substring(0, displayValueName.indexOf('Id'));
                }
                values[displayValueName] = field.rawValue;
            }

        });

        return values;
    },
    validate: function () {
        var me = this,
                valid = true;
        //TODO 
        return valid;
    },
    //This function is run before the editor loads
    //Is used to add data to the editor's data 
    //(Ej: usually the superEntityId needs to be sent along with the form data, 
    //and it is not available when creating the entify)
    initData: function () {
        return {};
    }
});