W.SrcFilesCol = W.Col.extend({
    header: 'Source Files',
    defaultItemType: 'SrcItem',
    initialize: function () {
        this.collection = new W.SrcCollection();
        W.srcCollection = this.collection;

        this.collection.bind('add', this.addItem, this);
        this.collection.bind('remove', this.render, this);
    },
    tbar: [
        {
            title: 'Generate Source Code',
            icon: 'Generate',
            color: 'red',
            left: true,
            onClick: 'onSave'
        },
        {
            title: 'Revert Changes',
            icon: 'fa-reply',
            color: 'red',
            left: true,
            hidden: true,
            onClick: 'onRevert'
        },
        {
            xtype: 'CloseIcon'
        },
        {
            title: 'Remove Field',
            icon: 'fa-trash',
            onClick: 'remove'
        }
    ],
    close: function () {
        W.Coll.prototype.close.call(this);
        W.fieldsCol && W.fieldsCol.show();
    },
    onSave: function () {
        Request.get('dev/write');
        this.toogleGenerateButton(false);
    },
    onRevert: function () {
        var request = W.entitySection.model.toJSON();
        if (W.fieldsCollection) {
            request.fields = W.fieldsCollection.toJSON();
        }

        Request.post('dev/generate', request, (response) => {
            Request.get('dev/revert');
        });
    },
    toogleGenerateButton: function (showGenerate) {
        if (showGenerate) {
            this.tools[0].show();
            this.tools[1].hide();
        } else {
            this.tools[0].hide();
            this.tools[1].show();
        }
    },
    remove: function () {
        W.srcCollection.remove(this.selectedItem.model);

        //TODO remove in the server
    }
});
 