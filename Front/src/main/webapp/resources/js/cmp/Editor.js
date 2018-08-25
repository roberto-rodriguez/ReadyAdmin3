W.Editor = W.Container.extend({
    title: null,
    className: 'section',
    fields: [],
    model: null,
    alias: null,
    render: function () {
        W.Container.prototype.render.call(this);
        var me = this;

        var editor = new Backform.Form({
            model: this.model || this.createModel(),
            fields: this.fields, // Will get converted to a collection of Backbone.Field models
            events: {
                "submit": function (e) {
                    e.preventDefault();

                    me.onSave(me.model);

                    return false;
                }
            }
        });

        if (this.alias) {
            this.editor = W[this.alias] = editor;
        }

        this.$el.append(editor.render().el);

        return this;
    }
});


 