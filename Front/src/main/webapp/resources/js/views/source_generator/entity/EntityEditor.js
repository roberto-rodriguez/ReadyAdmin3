
W.EntityEditor = W.Editor.extend({
    title: 'Create Entity',
    alias: 'entitySection',
    initialize: function () {
        var me = this;
        Request.get('dev/uifolders', folders => {
            me.fields.push(
                    {
                        name: "uipck",
                        label: "UI Package:",
                        control: "select",
                        options: folders.map(f => ({label: f, value:f.replace('.', "/")}))
                    }
            );

            me.render();
        });


    },
    tbar: [{
            title: 'View Prefiew Files',
            icon: 'Preview',
            color: 'green',
            left: true,
            onClick: 'onSave'
        },
        {
            title: 'Minimize',
            icon: 'fa-window-minimize',
            onclick: function () {
                alert('minimize')
            }
        }],
    fields: [
        {
            name: "pck",
            label: "Type:",
            control: "radio",
            options: [
                {label: "App", value: "app"},
                {label: "Sys", value: "system"},
                {label: "Nom", value: "nom"}
            ]
        },
        {
            name: "name",
            label: "Name:",
            control: "input"
        }
    ],
    createModel: function () {
        return new W.EntityModel({
            uipck: 'view',
            pck: 'app'
        });
    },
    onSave: function () {
        var request = this.editor.model.toJSON();
        if (W.fieldsCollection) {
            request.fields = W.fieldsCollection.toJSON();
        }


        Request.post('dev/generate', request, (response) => {
            this.parent.close(true);
            if (W.srcFilesCol) {
                W.srcFilesCol.show();
            } else {
                W.homeView.addItem({
                    xtype: 'SrcFilesCol',
                    alias: 'srcFilesCol'
                });
                W.srcFilesCol && W.srcFilesCol.toogleGenerateButton(true);
                W.srcCollection.fetch();
            }
        });
    }
});

 