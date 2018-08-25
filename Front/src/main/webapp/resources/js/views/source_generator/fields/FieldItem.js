W.FieldItem = W.Item.extend({
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

        var {title, leftIcon, rightIcon, rightIconColor} = item;

        $el.append(this.buildIcon(leftIcon, true));

        $el.append('<p>' + title + '</p>');

        $el.append(this.buildIcon((rightIcon), false, rightIconColor));

        return this;
    },
    onClick: function () {
        W.fieldEditor.model = this.model;
        W.fieldEditor.render();

        this.select();
    },
    modelToItem: function (model) {
        var fieldConfig = model.attributes;

        var item = { 
            title: fieldConfig.name,
            rightIcon: fieldConfig.entity || fieldConfig.type,
            leftIcon: fieldConfig.includeGrid ? 'fa-table' : 'fa-minus'
        }

        if (fieldConfig.type == 'Entity') {
            item.rightIconColor = 'red';
        }

        return item;
    }
});
