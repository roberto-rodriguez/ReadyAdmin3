
W.FieldEditor = W.Editor.extend({
    title: 'Create Field',
    alias: 'fieldEditor',
    tbar: [{//Default toolbar
            title: 'Save Field',
            icon: 'Save',
            color: 'green',
            left: true,
            onClick: 'save'
        },
        {
            title: 'Minimize',
            icon: 'fa-window-minimize'
        }],
    fields: [
        {
            name: "name",
            label: "Field Name:",
            control: "input"
        }, 
        {
            name: "type",
            label: "Type:",
            control: "select",
            options: [
                {label: "String", value: "String"},
                {label: "Boolean", value: "Boolean"}, 
                {label: "Integer", value: "Integer"},
                {label: "Long", value: "Long"},
                {label: "Double", value: "Double"},
                {label: "Date", value: "Date"}
            ]
        },
//        {
//            name: "entity",
//            label: "Entity:",
//            control: "select",
//            options: [
//                {label: "User", value: "String"},
//                {label: "Role", value: "Boolean"},
//                {label: "Page Access", value: "Character"}
//            ]
//        },
        {name: "includeGrid", label: "Include in Grid", control: "checkbox"}
    ],
    createModel: function () {
        return new W.FieldModel({
            type: 'String',
            includeGrid: true
        });
    },
    save: function () {
        this.parent.close( true )

        if (W.fieldsCol) {
            W.fieldsCol.show();
        } else {
            W.homeView.addItem({
                xtype: 'FieldsCol',
                alias: 'fieldsCol'
            });
        }

        W.fieldsCollection.add(this.editor.model);

        this.render();
    }
});



 