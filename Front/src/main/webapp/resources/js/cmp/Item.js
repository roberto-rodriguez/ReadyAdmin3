W.Item = Backbone.View.extend({
    className: 'item',
    parent: null,
    leftIcon: 'fa-play-circle',
    rightIcon: 'fa-caret-right',
    rightIconColor: null,
    onSelect: null,
    model: null,
    initialize: function () { 
       this.model && this.listenTo(this.model, "change", this.render);
    },
    render: function () {
        var _this = this,
                $el = this.$el;

        var {title, handler, leftIcon, rightIcon, rightIconColor} = this.options;

        $el.append(this.buildIcon(leftIcon || this.leftIcon, true));

        $el.append('<p>' + title + '</p>');

        $el.append(this.buildIcon((rightIcon || this.rightIcon), false, rightIconColor));

        $el.on('click', function () {
            handler && handler();

            _this.select();
        })
        return this;
    },
    buildIcon: function (icon, left, color) {
        return new W.Icon({icon, left, color}).render().el
    },
    select: function () {  
        this.parent.deselectAllItems();
        this.parent.selectedItem = this;

        if (!this.$el.hasClass('selected')) {
            this.$el.addClass('selected');
        }
    }
});
