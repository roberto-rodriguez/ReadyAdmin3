Ext.define('Admin.system.dv.EntityController', {
    extend: 'Admin.system.base.BaseController',
    alias: 'controller.entityController',
    init: function () {
        this.control({
            'fieldEditor': {
                saveField: this.saveField
            }
        });

        this.superclass.init.call(this);
    },
    showFieldIcons: function () {
        var refs = this.getReferences(),
                fieldIcons = refs.fieldIcons,
                fieldEditor = refs.fieldEditor;

        fieldEditor.destroy();
        fieldIcons.show();
    },
    saveField: function (cmp, b, c) {
        var fieldEditor = cmp.up().up(), 
                fieldset = fieldEditor.up(),
                entityItems = fieldset.up().items.items[0].items.items,
                displayField = entityItems[3],
                orderField = entityItems[4],
                fieldsGrid = fieldset.up().up().up().down('createEntityFieldGrid');
 
        var data = fieldEditor.getData();
        var elem = fieldsGrid.getStore().add(data);

        this.showFieldIcons();

        var item = {
            id: elem.id,
            name: data.name
        }

        displayField.getStore().insert(0, item);

        if (!displayField.getValue()) {
            displayField.setValue(item.id);
        }

        if (!orderField.getValue()) {
            orderField.setValue(item.id);
        }
        
        
    }
});
