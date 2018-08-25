W.ShellView = Backbone.View.extend({ 
    el: '#wrapper',
    render: function () {
        this.$el.html( _.template($('#ShellView').html())); 
   
        return this;
    } 
});