W.Icon = Backbone.View.extend({
    tagName: 'i',
    events: {
        'click': 'onClick'
    },
    render: function () {
        var {title, icon, onClick, left, color} = this.options;

        var cls;

        if (icon && icon.indexOf('fa-') == 0) {
            cls = 'fa tool ' + icon;

            this.$el.css("color", color);
        } else {
            cls = 'circle';
            this.$el[0].textContent = icon;

            this.$el.css("background-color", color);
        }

        if (left) {
            cls += ' left';
        } else {
            cls += ' right';
        }

        this.$el.addClass(cls);
        this.$el.attr('title', title);
//        this.$el.on('click', onclick);

        return this;
    },
    onClick: function(a,b,c,d){
        this.container[this.options.onClick]();
    },
    hide: function(){
        this.$el.hide();
    },
    show: function(){
        this.$el.show();
    },
});
 