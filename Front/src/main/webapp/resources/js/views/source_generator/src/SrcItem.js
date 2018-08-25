W.SrcItem = W.Item.extend({
    model: null,
    initialize: function () {
        this.model && this.listenTo(this.model, "change", this.render);
    },
    events: {
        click: 'onClick'
    },
    render: function () {
        var $el = this.$el;

        $el.empty();

        var item = this.modelToItem(this.model);

        var {title, leftIcon} = item;

        $el.append(this.buildIcon(leftIcon, true));

        $el.append('<p>' + title + '</p>');


        return this;
    },
    onClick: function () {
//        W.fieldEditor.model = this.model;
//        W.fieldEditor.render();

        if (W.srcCodeCol) {
            W.srcCodeCol.show();
        } else {
            W.homeView.addItem({
                xtype: 'SrcCodeCol',
                alias: 'srcCodeCol' 
            });
        }
        
        W.srcCodeCol.title = this.model.get('fullName');
        W.srcCodeCol.render();
        
        W.textArea.$el.val( this.model.get('src') );

        this.select();
    },
    modelToItem: function (model) {
        var fieldConfig = model.attributes;

        return {
            title: fieldConfig.name,
            leftIcon: fieldConfig.name.indexOf('java') >= 0 ? 'fa-file-code' : 'fa-code'
        }
    }
});
