W.FieldsCol = W.Col.extend({
    header: 'Fields',
    defaultItemType: 'FieldItem',
    initialize: function () {
        this.collection = new W.FieldCollection();
        W.fieldsCollection = this.collection;

        this.collection.bind('add', this.addItem, this);
        this.collection.bind('remove', this.render, this);
    },
    tbar: [
        {
            title: 'Mode Down',
            icon: 'fa-arrow-down',
            left: true,
            onclick: function () {
                alert('Add Test Case')
            }
        },
        {
            title: 'Move Up',
            icon: 'fa-arrow-up',
            left: true,
            onclick: function () {
                alert('Clone Test Case')
            }
        },
        {
            title: 'Remove Field',
            icon: 'fa-trash',
            left: true,
            onClick: 'remove'
        },
        {
            xtype: 'CloseIcon'
        }
    ],
    remove: function(){
        W.fieldsCollection.remove(this.selectedItem.model);
    }
});
 