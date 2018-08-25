W.Container = Backbone.View.extend({
    tpl: null, //Id of the tpl to be applied
    title: null,
    titleClass: null,
    toolbar: null,
    tbar: null,
    items: [],
    modelToItem: null, //translate the data in the model to the data to render in the item
    tools: [], //rendered tools
    children: [], //rendered items  
    defaultItemType: 'Item', 
    index: null,
    render: function () {
        this.children = []; //important otherwise will keep adding all children to the same list
        this.tools = []; //important otherwise will keep adding all children to the same list
        this.$el.empty();
        
        if (this.tpl) {
            this.$el.html(_.template($('#' + this.tpl).html(), this.__proto__));
        }

        if (this.tbar || this.title) {
            this.renderToolBar();
        }

        this.addItems();

        return this;
    },
    addItems: function () {
        var me = this;
        this.items.forEach(t => this.addItem(t));

        if (this.collection) {
            me.collection.models.forEach( elem => me.addItem(elem) );
        }
    },
    addItem: function (config) {
        if (config instanceof Backbone.Model) {
            config.model = config;
        }
        
        var xtype = config.xtype || this.defaultItemType
                 
        var item = new W[xtype](config);
        item.parent = this;
        item.index = this.children.length;

        this.children.push(item);
        this.$el.append(item.render().el);

        var alias = config.alias || item.options.alias;
        
        if(alias){
            W[alias] = item;
        }
    },
    deselectAllItems: function () {
        $('.item', this.$el).removeClass('selected');
    },
    renderToolBar: function () {
        this.toolbar = $('.toolbar', this.$el);

        if (!this.toolbar.length) {
            this.$el.append($('<div>').addClass('item grey tbar'));
            this.toolbar = $('.tbar', this.$el);
        }

        this.tbar.forEach(t => {
            var xtype = t.xtype || 'Icon';
            var icon = new W[xtype](t);

            icon.container = this;
            this.toolbar.append(icon.render().el)
            
            if(t.hidden){
                icon.hide();
            }
            
            this.tools.push(icon);
        });

        if (this.title) {
            var title = $('<p>');
            title.text(this.title).addClass('x-title');
            
            this.titleCls && title.addClass( this.titleCls );
            
            this.titleClass && title.addClass(this.titleClass);

            this.toolbar.append(title);
        }
    },
    show: function(){
        this.$el.show();
    },
    hide: function(){
        this.$el.hide();
    } 
});

 